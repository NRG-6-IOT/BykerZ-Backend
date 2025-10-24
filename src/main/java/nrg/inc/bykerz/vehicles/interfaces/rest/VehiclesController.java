package nrg.inc.bykerz.vehicles.interfaces.rest;

import nrg.inc.bykerz.vehicles.domain.model.commands.CreateVehicleCommand;
import nrg.inc.bykerz.vehicles.domain.model.queries.GetAllModelsQuery;
import nrg.inc.bykerz.vehicles.domain.model.queries.GetModelByIdQuery;
import nrg.inc.bykerz.vehicles.domain.model.queries.GetVehicleByIdQuery;
import nrg.inc.bykerz.vehicles.domain.services.VehicleCommandService;
import nrg.inc.bykerz.vehicles.domain.services.VehicleQueryService;
import nrg.inc.bykerz.vehicles.interfaces.rest.resources.CreateVehicleResource;
import nrg.inc.bykerz.vehicles.interfaces.rest.resources.VehicleResource;
import nrg.inc.bykerz.vehicles.interfaces.rest.transform.CreateVehicleCommandFromResourceAssembler;
import nrg.inc.bykerz.vehicles.interfaces.rest.transform.VehicleResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/vehicles")
@Tag(name = "Vehicles", description = "Operations related to vehicles")
public class VehiclesController {

    private final VehicleCommandService vehicleCommandService;
    private final VehicleQueryService vehicleQueryService;

    public VehiclesController(VehicleCommandService vehicleCommandService, VehicleQueryService vehicleQueryService) {
        this.vehicleCommandService = vehicleCommandService;
        this.vehicleQueryService = vehicleQueryService;
    }

    @PostMapping
    @Operation(summary = "Create a new vehicle")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Vehicle created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    public ResponseEntity<VehicleResource> createVehicle(@RequestBody CreateVehicleResource createVehicleResource) {
        CreateVehicleCommand command = CreateVehicleCommandFromResourceAssembler.toCommandFromResource(createVehicleResource);
        var vehicle = vehicleCommandService.handle(command)
                .orElseThrow(() -> new IllegalArgumentException("Error creating vehicle"));

        return new ResponseEntity<>(VehicleResourceFromEntityAssembler.toResourceFromEntity(vehicle), HttpStatus.CREATED);
    }

    @GetMapping("/{vehicleId}")
    @Operation(summary = "Get vehicle by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Vehicle retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Vehicle not found")
    })
    public ResponseEntity<VehicleResource> getVehicleById(@PathVariable Long vehicleId) {
        var vehicle = vehicleQueryService.handle(new GetVehicleByIdQuery(vehicleId));
        return vehicle.map(v -> ResponseEntity.ok(VehicleResourceFromEntityAssembler.toResourceFromEntity(v)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
