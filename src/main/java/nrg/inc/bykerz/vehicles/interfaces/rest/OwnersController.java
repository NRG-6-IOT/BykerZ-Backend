package nrg.inc.bykerz.vehicles.interfaces.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import nrg.inc.bykerz.vehicles.domain.model.commands.CreateOwnerCommand;
import nrg.inc.bykerz.vehicles.domain.model.queries.GetAllOwnersQuery;
import nrg.inc.bykerz.vehicles.domain.model.queries.GetOwnerByVehicleIdQuery;
import nrg.inc.bykerz.vehicles.domain.services.OwnerCommandService;
import nrg.inc.bykerz.vehicles.domain.services.OwnerQueryService;
import nrg.inc.bykerz.vehicles.interfaces.rest.resources.CreateOwnerResource;
import nrg.inc.bykerz.vehicles.interfaces.rest.resources.OwnerResource;
import nrg.inc.bykerz.vehicles.interfaces.rest.transform.CreateOwnerCommandFromResourceAssembler;
import nrg.inc.bykerz.vehicles.interfaces.rest.transform.OwnerResourceFromEntityAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/owners")
@Tag(name = "Owners", description = "Operations related to vehicle owners")
public class OwnersController {

    private final OwnerCommandService ownerCommandService;
    private final OwnerQueryService ownerQueryService;

    public OwnersController(OwnerCommandService ownerCommandService, OwnerQueryService ownerQueryService) {
        this.ownerCommandService = ownerCommandService;
        this.ownerQueryService = ownerQueryService;
    }

    @GetMapping
    @Operation(summary = "Get all vehicle owners", description = "Retrieve a list of all vehicle owners")
    public ResponseEntity<List<OwnerResource>> getAllOwners() {
        var owners = this.ownerQueryService.handle(new GetAllOwnersQuery());
        var ownerList = owners.stream().map(OwnerResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(ownerList);
    }

    @GetMapping("/vehicle/{vehicleId}")
    @Operation(summary = "Get owner by vehicle ID", description = "Retrieve the owner of a specific vehicle by its ID")
    public ResponseEntity<OwnerResource> getOwnerByVehicleId(@PathVariable Long vehicleId) {
        var ownerOpt = ownerQueryService.handle(new GetOwnerByVehicleIdQuery(vehicleId));
        if (ownerOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var ownerResource = OwnerResourceFromEntityAssembler.toResourceFromEntity(ownerOpt.get());
        return ResponseEntity.ok(ownerResource);
    }

    @PostMapping
    @Operation(summary = "Create a new vehicle owner", description = "Create a new vehicle owner with the provided profile ID")
    public ResponseEntity<OwnerResource> createOwner(@RequestBody CreateOwnerResource resource) {
        var command = CreateOwnerCommandFromResourceAssembler.toCommandFromResource(resource);
        var owner = this.ownerCommandService.handle(command);
        if (owner.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        var ownerResource = OwnerResourceFromEntityAssembler.toResourceFromEntity(owner.get());
        return ResponseEntity.ok(ownerResource);
    }

}
