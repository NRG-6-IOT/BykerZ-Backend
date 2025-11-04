package nrg.inc.bykerz.assignments.interfaces.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import nrg.inc.bykerz.assignments.domain.model.queries.GetAssignmentsByMechanicIdQuery;
import nrg.inc.bykerz.assignments.domain.services.AssignmentQueryService;
import nrg.inc.bykerz.assignments.interfaces.rest.resources.AssignmentResource;
import nrg.inc.bykerz.assignments.interfaces.rest.transform.AssignmentResourceFromEntityAssembler;
import nrg.inc.bykerz.shared.domain.model.queries.GetMechanicByIdQuery;
import nrg.inc.bykerz.shared.domain.services.MechanicQueryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/mechanic/{mechanicId}/assignments")
@Tag(name= "Assignments", description = "Assignment Management API")
public class MechanicAssigmentController {
    private final AssignmentQueryService assignmentQueryService;
    private final MechanicQueryService mechanicQueryService;

    public MechanicAssigmentController(AssignmentQueryService assignmentQueryService, MechanicQueryService mechanicQueryService) {
        this.assignmentQueryService = assignmentQueryService;
        this.mechanicQueryService = mechanicQueryService;
    }

    @GetMapping
    @Operation(summary = "Get Assignments for Mechanic", description = "Get all assignments associated to the specified mechanic.")
    public ResponseEntity<List<AssignmentResource>> getAssignments(@PathVariable Long mechanicId) {
        var mechanicOpt = this.mechanicQueryService.handle(new GetMechanicByIdQuery(mechanicId));
        if (mechanicOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var mechanic = mechanicOpt.get();

        var assignments = this.assignmentQueryService.handle(
                new GetAssignmentsByMechanicIdQuery(mechanicId)
        );
        if(assignments.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        var assignmentResources = assignments.stream()
                .map(a -> AssignmentResourceFromEntityAssembler.toResourceFromEntity(a, mechanic))
                .toList();

        return ResponseEntity.ok(assignmentResources);
    }
}
