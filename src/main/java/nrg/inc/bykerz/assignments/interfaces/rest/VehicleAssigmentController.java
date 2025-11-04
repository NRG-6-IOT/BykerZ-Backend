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
import nrg.inc.bykerz.shared.domain.model.queries.GetMechanicByCodeQuery;
import nrg.inc.bykerz.shared.domain.model.queries.GetMechanicByIdQuery;
import nrg.inc.bykerz.shared.domain.services.MechanicQueryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/vehicles/{vehicleId}/assignment")
@Tag(name= "Assignments", description = "Assignment Management API")
public class VehicleAssigmentController {
    private final AssignmentQueryService assignmentQueryService;
    private final AssignmentCommandService assignmentCommandService;
    private final MechanicQueryService mechanicQueryService;

    public VehicleAssigmentController(AssignmentQueryService assignmentQueryService, AssignmentCommandService assignmentCommandService, MechanicQueryService mechanicQueryService) {
        this.assignmentQueryService = assignmentQueryService;
        this.assignmentCommandService = assignmentCommandService;
        this.mechanicQueryService = mechanicQueryService;
    }


    @PostMapping
    @Operation(summary = "Create Assignment for Vehicle", description = "Creates a new assignment for the specified vehicle and associate it to a mechanic depending of its code.")
    public ResponseEntity<AssignmentResource> createAssignment(@PathVariable Long vehicleId, @RequestBody CreateAssigmentResource resource) {
        //TO-DO: Use mechanic code to find mechanic id
        //TO-DO: Implement validation get vehicle by vehicle id
        var mechanicOpt = this.mechanicQueryService.handle(new GetMechanicByCodeQuery(resource.mechanicCode()));
        if (mechanicOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var mechanic = mechanicOpt.get();
        var command = CreateAssigmentCommandAssembler.toCommand(
                vehicleId,
                mechanic.getId()
        );
        var assignmentOpt = this.assignmentCommandService.handle(command);
        if (assignmentOpt.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var assignment = assignmentOpt.get();
        var assignmentResource = AssignmentResourceFromEntityAssembler.toResourceFromEntity(assignment, mechanic);
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
        var mechanicOpt = this.mechanicQueryService.handle(new GetMechanicByIdQuery(assignment.getMechanicId()));
        if (mechanicOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var mechanic = mechanicOpt.get();
        var assignmentResource = AssignmentResourceFromEntityAssembler.toResourceFromEntity(assignment, mechanic);
        return ResponseEntity.ok(assignmentResource);
    }
}
