package nrg.inc.bykerz.vehicles.interfaces.rest;

import nrg.inc.bykerz.shared.domain.model.queries.GetOwnerByIdQuery;
import nrg.inc.bykerz.shared.domain.services.OwnerQueryService;
import nrg.inc.bykerz.vehicles.domain.model.commands.CreateVehicleCommand;
import nrg.inc.bykerz.vehicles.domain.model.commands.DeleteVehicleCommand;
import nrg.inc.bykerz.vehicles.domain.model.queries.GetModelByIdQuery;
import nrg.inc.bykerz.vehicles.domain.model.queries.GetVehicleByIdQuery;
import nrg.inc.bykerz.vehicles.domain.model.queries.GetVehiclesByOwnerIdQuery;
import nrg.inc.bykerz.vehicles.domain.services.ModelQueryService;
import nrg.inc.bykerz.vehicles.domain.services.VehicleCommandService;
import nrg.inc.bykerz.vehicles.domain.services.VehiclesQueryService;
import nrg.inc.bykerz.vehicles.interfaces.rest.resources.CreateVehicleResource;
import nrg.inc.bykerz.vehicles.interfaces.rest.resources.UpdateVehicleResource;
import nrg.inc.bykerz.vehicles.interfaces.rest.resources.VehicleResource;
import nrg.inc.bykerz.vehicles.interfaces.rest.transform.CreateVehicleCommandFromResourceAssembler;
import nrg.inc.bykerz.vehicles.interfaces.rest.transform.UpdateVehicleCommandFromResourceAssembler;
import nrg.inc.bykerz.vehicles.interfaces.rest.transform.VehicleResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

import java.util.List;

@RestController
@RequestMapping("/api/v1/vehicles")
@Tag(name = "Vehicles", description = "Operations related to vehicles")
public class VehiclesController {

    private final VehicleCommandService vehicleCommandService;
    private final VehiclesQueryService vehiclesQueryService;
    private final ModelQueryService modelQueryService;
    private final OwnerQueryService ownerQueryService;

    public VehiclesController(
            VehicleCommandService vehicleCommandService,
            VehiclesQueryService vehiclesQueryService,
            ModelQueryService modelQueryService,
            OwnerQueryService ownerQueryService
    ) {
        this.vehicleCommandService = vehicleCommandService;
        this.vehiclesQueryService = vehiclesQueryService;
        this.modelQueryService = modelQueryService;
        this.ownerQueryService = ownerQueryService;
    }

    @PostMapping("/{ownerId}")
    @Operation(summary = "Create a new vehicle for a owner")
    public ResponseEntity<VehicleResource> createVehicle(@RequestBody CreateVehicleResource createVehicleResource, @PathVariable Long ownerId) {

        var ownerOpt = ownerQueryService.handle(new GetOwnerByIdQuery(ownerId));

        if (ownerOpt.isEmpty()) {
            ResponseEntity.notFound().build();
        }

        var model = modelQueryService.handle(new GetModelByIdQuery(createVehicleResource.modelId()));

        if(model.isEmpty()) {
            ResponseEntity.notFound().build();
        }

        CreateVehicleCommand command = CreateVehicleCommandFromResourceAssembler.toCommandFromResource(createVehicleResource, ownerId);
        var vehicle = vehicleCommandService.handle(command)
                .orElseThrow(() -> new IllegalArgumentException("Error creating vehicle"));
        return new ResponseEntity<>(VehicleResourceFromEntityAssembler.toResourceFromEntity(vehicle), HttpStatus.CREATED);
    }

    @GetMapping("/{vehicleId}")
    @Operation(summary = "Get vehicle by ID")
    public ResponseEntity<VehicleResource> getVehicleById(@PathVariable Long vehicleId) {
        var vehicle = vehiclesQueryService.handle(new GetVehicleByIdQuery(vehicleId));
        return vehicle.map(v -> ResponseEntity.ok(VehicleResourceFromEntityAssembler.toResourceFromEntity(v)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/owner/{ownerId}")
    @Operation(summary = "Get vehicles by owner ID")
    public ResponseEntity<List<VehicleResource>> getVehiclesByOwnerId(@PathVariable Long ownerId) {
        var ownerOpt = ownerQueryService.handle(new GetOwnerByIdQuery(ownerId));
        if (ownerOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var vehicles = vehiclesQueryService.handle(new GetVehiclesByOwnerIdQuery(ownerId));
        if (vehicles.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok().body(
                vehicles.stream()
                        .map(VehicleResourceFromEntityAssembler::toResourceFromEntity)
                        .toList()
        );
    }

    @DeleteMapping("/{vehicleId}")
    @Operation(summary = "Deletes a vehicle by id")
    public void deleteVehicleById(@PathVariable Long vehicleId) {
        var vehicle = vehiclesQueryService.handle(new GetVehicleByIdQuery(vehicleId));
        if (vehicle.isEmpty()) {
            ResponseEntity.notFound().build();
        }
        vehicleCommandService.handle(new DeleteVehicleCommand(vehicleId));
        ResponseEntity.noContent().build();
    }

    @PutMapping("/{vehicleId}")
    @Operation(summary = "Updates vehicle data by id")
    public ResponseEntity<VehicleResource> updateVehicle(@PathVariable Long vehicleId, @RequestBody UpdateVehicleResource resource) {
        var vehicle = vehiclesQueryService.handle(new GetVehicleByIdQuery(vehicleId));
        if (vehicle.isEmpty()) {
            ResponseEntity.notFound().build();
        }
        var newVehicle = vehicleCommandService.handle(UpdateVehicleCommandFromResourceAssembler.toCommandFromResource(resource, vehicleId));

        if (newVehicle.isEmpty()) {
            ResponseEntity.notFound().build();
        }

        return new ResponseEntity<>(VehicleResourceFromEntityAssembler.toResourceFromEntity(newVehicle.get()), HttpStatus.OK);
    }

}
