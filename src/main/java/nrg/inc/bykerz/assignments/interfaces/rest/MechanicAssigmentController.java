package nrg.inc.bykerz.assignments.interfaces.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import nrg.inc.bykerz.assignments.domain.model.queries.GetAssignmentsByMechanicIdAndStatusQuery;
import nrg.inc.bykerz.assignments.domain.services.AssignmentCommandService;
import nrg.inc.bykerz.assignments.domain.services.AssignmentQueryService;
import nrg.inc.bykerz.assignments.interfaces.rest.resources.AssignmentResource;
import nrg.inc.bykerz.assignments.interfaces.rest.transform.AssignmentResourceFromEntityAssembler;
import nrg.inc.bykerz.assignments.domain.model.queries.GetMechanicByIdQuery;
import nrg.inc.bykerz.assignments.domain.services.MechanicQueryService;
import nrg.inc.bykerz.assignments.interfaces.rest.transform.CreateAssigmentCommandAssembler;
import nrg.inc.bykerz.shared.application.internal.outboundservices.acl.ExternalVehiclesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/mechanic/{mechanicId}/assignments")
@Tag(name= "Assignments", description = "Assignment Management API")
public class MechanicAssigmentController {
    private final AssignmentQueryService assignmentQueryService;
    private final AssignmentCommandService assignmentCommandService;
    private final MechanicQueryService mechanicQueryService;
    private final ExternalVehiclesService externalVehiclesService;
    public MechanicAssigmentController(AssignmentQueryService assignmentQueryService, AssignmentCommandService assignmentCommandService, MechanicQueryService mechanicQueryService, ExternalVehiclesService externalVehiclesService) {
        this.assignmentQueryService = assignmentQueryService;
        this.assignmentCommandService = assignmentCommandService;
        this.mechanicQueryService = mechanicQueryService;
        this.externalVehiclesService = externalVehiclesService;
    }

    @GetMapping("/{status}")
    @Operation(summary = "Get Assignments for Mechanic by Status", description = "Get all assignments associated to the specified mechanic filtered by status.")
    public ResponseEntity<List<AssignmentResource>> getAssignments(
            @PathVariable Long mechanicId,
            @PathVariable String status) {
        var mechanicOpt = this.mechanicQueryService.handle(new GetMechanicByIdQuery(mechanicId));
        if (mechanicOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var mechanic = mechanicOpt.get();

        var assignments = this.assignmentQueryService.handle(
                new GetAssignmentsByMechanicIdAndStatusQuery(mechanicId, status)
        );
        if (assignments.isEmpty()) {
            return ResponseEntity.ok(List.of());
        }
        var assignmentResources = assignments.stream()
                .map(a -> {
                    if (a.getOwnerId() == null) {
                        return AssignmentResourceFromEntityAssembler.toResourceFromEntity(a, mechanic, null);
                    }
                    return this.externalVehiclesService.getOwnerById(a.getOwnerId())
                            .map(owner -> AssignmentResourceFromEntityAssembler.toResourceFromEntity(a, mechanic, owner))
                            .orElseGet(() -> AssignmentResourceFromEntityAssembler.toResourceFromEntity(a, mechanic, null));
                })
                .toList();
        return ResponseEntity.ok(assignmentResources);
    }

    @PostMapping()
    @Operation(summary = "Create Assignment for Mechanic", description = "Create a new assignment associated to the specified mechanic.")
    public ResponseEntity<AssignmentResource> createAssignment(@PathVariable Long mechanicId) {
        var mechanicOpt = this.mechanicQueryService.handle(new GetMechanicByIdQuery(mechanicId));
        if (mechanicOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var mechanic = mechanicOpt.get();
        var command = CreateAssigmentCommandAssembler.toCommand(mechanicId);
        var assignmentOpt = this.assignmentCommandService.handle(command);
        if (assignmentOpt.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var assignment = assignmentOpt.get();
        var assignmentResource = AssignmentResourceFromEntityAssembler.toResourceFromEntity(assignment, mechanic, null);
        return ResponseEntity.ok(assignmentResource);
    }
}
