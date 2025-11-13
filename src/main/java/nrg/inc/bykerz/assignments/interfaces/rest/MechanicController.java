package nrg.inc.bykerz.assignments.interfaces.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import nrg.inc.bykerz.assignments.domain.model.queries.GetAssignmentsByMechanicIdAndStatusQuery;
import nrg.inc.bykerz.assignments.domain.model.queries.GetMechanicByIdQuery;
import nrg.inc.bykerz.assignments.domain.services.AssignmentQueryService;
import nrg.inc.bykerz.assignments.domain.services.MechanicQueryService;
import nrg.inc.bykerz.shared.application.internal.outboundservices.acl.ExternalVehiclesService;
import nrg.inc.bykerz.vehicles.interfaces.rest.resources.OwnerResource;
import nrg.inc.bykerz.vehicles.interfaces.rest.transform.OwnerResourceFromEntityAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(value = "/api/v1/mechanic")
@Tag(name= "Mechanic", description = "Mechanic Management API")
public class MechanicController {
    private final AssignmentQueryService assignmentQueryService;
    private final MechanicQueryService mechanicQueryService;
    private final ExternalVehiclesService externalVehiclesService;
    public MechanicController(AssignmentQueryService assignmentQueryService, MechanicQueryService mechanicQueryService, ExternalVehiclesService externalVehiclesService) {
        this.assignmentQueryService = assignmentQueryService;
        this.mechanicQueryService = mechanicQueryService;
        this.externalVehiclesService = externalVehiclesService;
    }

    @GetMapping("{mechanicId}/owners")
    @Operation(summary = "Get Owners for Mechanic", description = "Get all vehicle owners associated to the specified mechanic's assignments.")
    public ResponseEntity<List<OwnerResource>> getOwnersForMechanic(@PathVariable Long mechanicId) {
        var mechanicOpt = this.mechanicQueryService.handle(new GetMechanicByIdQuery(mechanicId));
        if (mechanicOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var assignments = this.assignmentQueryService.handle(
                new GetAssignmentsByMechanicIdAndStatusQuery(mechanicId, "ACTIVE")
        );
        if (assignments.isEmpty()) {
            return ResponseEntity.ok(List.of());
        }
        var ownerResources = assignments.stream()
                .map(a -> {
                    var ownerId = a.getOwnerId();
                    if (ownerId == null) return null;
                    return this.externalVehiclesService.getOwnerById(ownerId) // Optional<OwnerEntity> or Optional<Owner>
                            .map(owner -> OwnerResourceFromEntityAssembler.toResourceFromEntity(owner)) // convert entity -> OwnerResource
                            .orElse(null);
                })
                .filter(Objects::nonNull)
                .distinct()
                .toList();

        return ResponseEntity.ok(ownerResources);
    }
}
