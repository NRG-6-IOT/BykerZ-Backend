package nrg.inc.bykerz.assignments.interfaces.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import nrg.inc.bykerz.assignments.domain.model.queries.GetAssignmentByVehicleIdQuery;
import nrg.inc.bykerz.assignments.domain.services.AssignmentCommandService;
import nrg.inc.bykerz.assignments.domain.services.AssignmentQueryService;
import nrg.inc.bykerz.assignments.interfaces.rest.resources.AssignmentResource;
import nrg.inc.bykerz.assignments.interfaces.rest.resources.CreateAssigmentResource;
import nrg.inc.bykerz.assignments.interfaces.rest.transform.AssignmentResourceFromEntityAssembler;
import nrg.inc.bykerz.assignments.interfaces.rest.transform.CreateAssigmentCommandAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/vehicles/{vehicleId}/assignment")
@Tag(name= "Assignments", description = "Assignment Management API")
public class VehicleAssigmentController {
    private final AssignmentQueryService assignmentQueryService;
    private final AssignmentCommandService assignmentCommandService;

    public VehicleAssigmentController(AssignmentQueryService assignmentQueryService, AssignmentCommandService assignmentCommandService) {
        this.assignmentQueryService = assignmentQueryService;
        this.assignmentCommandService = assignmentCommandService;
    }

    @PostMapping
    @Operation(summary = "Create Assignment for Vehicle", description = "Creates a new assignment for the specified vehicle and associate it to a mechanic depending of its code.")
    public ResponseEntity<AssignmentResource> createAssignment(@PathVariable Long vehicleId, @RequestBody CreateAssigmentResource resource) {
        //TO-DO: Use mechanic code to find mechanic id
        //TO-DO: Implement validation get vehicle by vehicle id
        var command = CreateAssigmentCommandAssembler.toCommand(
                vehicleId,
                1L
        );
        var assignmentOpt = this.assignmentCommandService.handle(command);
        if (assignmentOpt.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var assignment = assignmentOpt.get();
        var assignmentResource = AssignmentResourceFromEntityAssembler.toResourceFromEntity(assignment);
        return ResponseEntity.ok(assignmentResource);
    }

    @GetMapping()
    @Operation(summary = "Get Assignment for Vehicle", description = "Get the assignment associated to the specified vehicle.")
    public ResponseEntity<AssignmentResource> getAssignment(@PathVariable Long vehicleId) {
        //TO-DO: Implement validation get vehicle by vehicle id
        var assignmentOpt = this.assignmentQueryService.handle(new GetAssignmentByVehicleIdQuery(vehicleId));
        if(assignmentOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var assignment = assignmentOpt.get();
        var assignmentResource = AssignmentResourceFromEntityAssembler.toResourceFromEntity(assignment);
        return ResponseEntity.ok(assignmentResource);
    }
}
