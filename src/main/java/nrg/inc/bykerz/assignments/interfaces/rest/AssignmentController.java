package nrg.inc.bykerz.assignments.interfaces.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import nrg.inc.bykerz.assignments.domain.model.commands.AssignOwnerToAssignmentCommand;
import nrg.inc.bykerz.assignments.domain.model.queries.GetAssigmentByCodeQuery;
import nrg.inc.bykerz.assignments.domain.model.queries.GetAssignmentByIdQuery;
import nrg.inc.bykerz.assignments.domain.services.AssignmentCommandService;
import nrg.inc.bykerz.assignments.domain.services.AssignmentQueryService;
import nrg.inc.bykerz.assignments.interfaces.rest.resources.AssignmentResource;
import nrg.inc.bykerz.assignments.interfaces.rest.resources.UpdateAssignmentStatusResource;
import nrg.inc.bykerz.assignments.interfaces.rest.resources.UpdateAssignmentTypeResource;
import nrg.inc.bykerz.assignments.interfaces.rest.transform.AssignmentResourceFromEntityAssembler;
import nrg.inc.bykerz.assignments.interfaces.rest.transform.UpdateAssignmentStatusCommandFromResourceAssembler;
import nrg.inc.bykerz.assignments.interfaces.rest.transform.UpdateAssignmentTypeCommandFromResourceAssembler;
import nrg.inc.bykerz.assignments.domain.model.queries.GetMechanicByIdQuery;
import nrg.inc.bykerz.assignments.domain.services.MechanicQueryService;
import nrg.inc.bykerz.shared.application.internal.outboundservices.acl.ExternalVehiclesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/api/v1/assignments")
@Tag(name= "Assignments", description = "Assignment Management API")
public class AssignmentController {
    private final AssignmentQueryService assignmentQueryService;
    private final AssignmentCommandService assignmentCommandService;
    private final MechanicQueryService mechanicQueryService;
    private final ExternalVehiclesService externalVehiclesService;
    public AssignmentController(AssignmentQueryService assignmentQueryService, AssignmentCommandService assignmentCommandService, MechanicQueryService mechanicQueryService, ExternalVehiclesService externalVehiclesService) {
        this.assignmentQueryService = assignmentQueryService;
        this.assignmentCommandService = assignmentCommandService;
        this.externalVehiclesService = externalVehiclesService;
        this.mechanicQueryService = mechanicQueryService;
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
        var mechanicOpt = this.mechanicQueryService.handle(new GetMechanicByIdQuery(updatedAssignment.getMechanic().getId()));
        if (mechanicOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var mechanic = mechanicOpt.get();
        var ownerOpt = updatedAssignment.getOwnerId() == null ? null : this.externalVehiclesService.getOwnerById(updatedAssignment.getOwnerId());
        if(ownerOpt != null) {
            if (ownerOpt.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
        }
        var owner = ownerOpt == null ? null : ownerOpt.get();
        var assignmentResource = AssignmentResourceFromEntityAssembler.toResourceFromEntity(updatedAssignment, mechanic, owner);

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

        var mechanicOpt = this.mechanicQueryService.handle(new GetMechanicByIdQuery(updatedAssignment.getMechanic().getId()));
        if (mechanicOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var mechanic = mechanicOpt.get();
        var ownerOpt = updatedAssignment.getOwnerId() == null ? null : this.externalVehiclesService.getOwnerById(updatedAssignment.getOwnerId());
        if(ownerOpt != null) {
            if (ownerOpt.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
        }
        var owner = ownerOpt == null ? null : ownerOpt.get();
        var assignmentResource = AssignmentResourceFromEntityAssembler.toResourceFromEntity(updatedAssignment, mechanic, owner);
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

        var mechanicOpt = this.mechanicQueryService.handle(new GetMechanicByIdQuery(assignment.getMechanic().getId()));
        if (mechanicOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var mechanic = mechanicOpt.get();

        var ownerOpt = assignment.getOwnerId() == null ? null : this.externalVehiclesService.getOwnerById(assignment.getOwnerId());
        if(ownerOpt != null) {
            if (ownerOpt.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
        }
        var owner = ownerOpt == null ? null : ownerOpt.get();

        var assignmentResource = AssignmentResourceFromEntityAssembler.toResourceFromEntity(assignment, mechanic, owner);
        return ResponseEntity.ok(assignmentResource);
    }

    @PatchMapping("code/{assignmentCode}/assign-owner/{ownerId}")
    @Operation(summary = "Assign Owner to Assignment", description = "Assign an owner to an existing assignment")
    public ResponseEntity<AssignmentResource> assignOwnerToAssignment(@PathVariable String assignmentCode, @PathVariable Long ownerId) {
        var assignmentOpt = this.assignmentQueryService.handle(new GetAssigmentByCodeQuery(assignmentCode));
        if (assignmentOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }


        var command = new AssignOwnerToAssignmentCommand(assignmentCode, ownerId);
        var updatedAssignmentOpt = this.assignmentCommandService.handle(command);
        if (updatedAssignmentOpt.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var updatedAssignment = updatedAssignmentOpt.get();
        var mechanicOpt = this.mechanicQueryService.handle(new GetMechanicByIdQuery(updatedAssignment.getMechanic().getId()));
        if (mechanicOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var mechanic = mechanicOpt.get();
        var ownerOpt = updatedAssignment.getOwnerId() == null ? null : this.externalVehiclesService.getOwnerById(updatedAssignment.getOwnerId());
        if(ownerOpt != null) {
            if (ownerOpt.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
        }
        var owner = ownerOpt == null ? null : ownerOpt.get();
        var assignmentResource = AssignmentResourceFromEntityAssembler.toResourceFromEntity(updatedAssignment, mechanic, owner);
        return ResponseEntity.ok(assignmentResource);
    }
}
