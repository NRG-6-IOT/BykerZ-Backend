package nrg.inc.bykerz.assignments.interfaces.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import nrg.inc.bykerz.assignments.domain.model.queries.GetAssignmentsByMechanicIdAndStatusQuery;
import nrg.inc.bykerz.assignments.domain.services.AssignmentQueryService;
import nrg.inc.bykerz.assignments.interfaces.rest.resources.AssignmentResource;
import nrg.inc.bykerz.assignments.interfaces.rest.transform.AssignmentResourceFromEntityAssembler;
import nrg.inc.bykerz.assignments.domain.model.queries.GetMechanicByIdQuery;
import nrg.inc.bykerz.assignments.domain.services.MechanicQueryService;
import nrg.inc.bykerz.vehicles.interfaces.acl.VehiclesContextFacade;
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
    private final VehiclesContextFacade vehiclesContextFacade;
    public MechanicAssigmentController(AssignmentQueryService assignmentQueryService, MechanicQueryService mechanicQueryService, VehiclesContextFacade vehiclesContextFacade) {
        this.assignmentQueryService = assignmentQueryService;
        this.mechanicQueryService = mechanicQueryService;
        this.vehiclesContextFacade = vehiclesContextFacade;
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
                .flatMap(a -> this.vehiclesContextFacade.fetchVehicleById(a.getVehicleId())
                        .map(vehicle -> AssignmentResourceFromEntityAssembler.toResourceFromEntity(a, mechanic, vehicle))
                        .stream()
                )
                .toList();

        return ResponseEntity.ok(assignmentResources);
    }
}
