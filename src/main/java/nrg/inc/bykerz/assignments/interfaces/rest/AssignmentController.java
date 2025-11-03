package nrg.inc.bykerz.assignments.interfaces.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import nrg.inc.bykerz.assignments.domain.model.commands.UpdateAssignmentStatusCommand;
import nrg.inc.bykerz.assignments.domain.model.queries.GetAssignmentByIdQuery;
import nrg.inc.bykerz.assignments.domain.model.valueobjects.AssignmentStatus;
import nrg.inc.bykerz.assignments.domain.services.AssignmentCommandService;
import nrg.inc.bykerz.assignments.domain.services.AssignmentQueryService;
import nrg.inc.bykerz.assignments.interfaces.rest.resources.AssignmentResource;
import nrg.inc.bykerz.assignments.interfaces.rest.resources.UpdateAssignmentStatusResource;
import nrg.inc.bykerz.assignments.interfaces.rest.resources.UpdateAssignmentTypeResource;
import nrg.inc.bykerz.assignments.interfaces.rest.transform.AssignmentResourceFromEntityAssembler;
import nrg.inc.bykerz.assignments.interfaces.rest.transform.UpdateAssignmentStatusCommandFromResourceAssembler;
import nrg.inc.bykerz.assignments.interfaces.rest.transform.UpdateAssignmentTypeCommandFromResourceAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/assignments")
@Tag(name= "Assignments", description = "Assignment Management API")
public class AssignmentController {
    private final AssignmentQueryService assignmentQueryService;
    private final AssignmentCommandService assignmentCommandService;
    public AssignmentController(AssignmentQueryService assignmentQueryService, AssignmentCommandService assignmentCommandService) {
        this.assignmentQueryService = assignmentQueryService;
        this.assignmentCommandService = assignmentCommandService;
    }

    @PatchMapping("{assignmentId}/status")
    @Operation(summary = "Update Assignment Status", description = "Update the status of an existing assignment")
    public ResponseEntity<AssignmentResource> updateAssignmentStatus(@PathVariable Long assignmentId, @RequestBody UpdateAssignmentStatusResource resource) {
        var assignmentOpt = this.assignmentQueryService.handle(new GetAssignmentByIdQuery(assignmentId));
        if(assignmentOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var updateAssignmentStatusCommand = UpdateAssignmentStatusCommandFromResourceAssembler.updateAssignmentStatusCommandFromResourceAssembler(assignmentId, resource);
        var updatedAssignmentOpt = this.assignmentCommandService.handle(updateAssignmentStatusCommand);
        if(updatedAssignmentOpt.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var updatedAssignment = updatedAssignmentOpt.get();
        var assignmentResource = AssignmentResourceFromEntityAssembler.toResourceFromEntity(updatedAssignment);
        return ResponseEntity.ok(assignmentResource);
    }

    @PatchMapping("{assignmentId}/type")
    @Operation(summary = "Update Assignment Type", description = "Update the type of an existing assignment")
    public ResponseEntity<AssignmentResource> updateAssignmentType(@PathVariable Long assignmentId, @RequestBody UpdateAssignmentTypeResource resource) {
        var assignmentOpt = this.assignmentQueryService.handle(new GetAssignmentByIdQuery(assignmentId));
        if (assignmentOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var updateAssignmentTypeCommand = UpdateAssignmentTypeCommandFromResourceAssembler.updateAssignmentTypeCommandFromResourceAssembler(assignmentId, resource);
        var updatedAssignmentOpt = this.assignmentCommandService.handle(updateAssignmentTypeCommand);
        if (updatedAssignmentOpt.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var updatedAssignment = updatedAssignmentOpt.get();
        var assignmentResource = AssignmentResourceFromEntityAssembler.toResourceFromEntity(updatedAssignment);
        return ResponseEntity.ok(assignmentResource);
    }

    @GetMapping("{assignmentId}")
    @Operation(summary = "Get Assignment by ID", description = "Retrieve an assignment by its ID")
    public ResponseEntity<AssignmentResource> getAssignmentById(@PathVariable Long assignmentId) {
        var assignmentOpt = this.assignmentQueryService.handle(new GetAssignmentByIdQuery(assignmentId));
        if (assignmentOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var assignment = assignmentOpt.get();
        var assignmentResource = AssignmentResourceFromEntityAssembler.toResourceFromEntity(assignment);
        return ResponseEntity.ok(assignmentResource);
    }
}
