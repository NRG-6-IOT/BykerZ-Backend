package nrg.inc.bykerz.assignments.interfaces.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
import nrg.inc.bykerz.vehicles.interfaces.acl.VehiclesContextFacade;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/api/v1/assignments")
@Tag(name= "Assignments", description = "Assignment Management API")
public class AssignmentController {
    private final AssignmentQueryService assignmentQueryService;
    private final AssignmentCommandService assignmentCommandService;
    private final MechanicQueryService mechanicQueryService;
    private final VehiclesContextFacade vehiclesContextFacade;
    public AssignmentController(AssignmentQueryService assignmentQueryService, AssignmentCommandService assignmentCommandService, MechanicQueryService mechanicQueryService, VehiclesContextFacade vehiclesContextFacade) {
        this.assignmentQueryService = assignmentQueryService;
        this.assignmentCommandService = assignmentCommandService;
        this.mechanicQueryService = mechanicQueryService;
        this.vehiclesContextFacade = vehiclesContextFacade;
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
        var mechanicOpt = this.mechanicQueryService.handle(new GetMechanicByIdQuery(updatedAssignment.getMechanicId()));
        if (mechanicOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var mechanic = mechanicOpt.get();
        var vehicleOpt = this.vehiclesContextFacade.fetchVehicleById(updatedAssignment.getVehicleId());
        if (vehicleOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var vehicle = vehicleOpt.get();
        var assignmentResource = AssignmentResourceFromEntityAssembler.toResourceFromEntity(updatedAssignment, mechanic, vehicle);
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

        var mechanicOpt = this.mechanicQueryService.handle(new GetMechanicByIdQuery(updatedAssignment.getMechanicId()));
        if (mechanicOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var mechanic = mechanicOpt.get();
        var vehicleOpt = this.vehiclesContextFacade.fetchVehicleById(updatedAssignment.getVehicleId());
        if (vehicleOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var vehicle = vehicleOpt.get();
        var assignmentResource = AssignmentResourceFromEntityAssembler.toResourceFromEntity(updatedAssignment, mechanic, vehicle);
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

        var mechanicOpt = this.mechanicQueryService.handle(new GetMechanicByIdQuery(assignment.getMechanicId()));
        if (mechanicOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var mechanic = mechanicOpt.get();
        var vehicleOpt = this.vehiclesContextFacade.fetchVehicleById(assignment.getVehicleId());
        if (vehicleOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var vehicle = vehicleOpt.get();
        var assignmentResource = AssignmentResourceFromEntityAssembler.toResourceFromEntity(assignment, mechanic, vehicle);
        return ResponseEntity.ok(assignmentResource);
    }
}
