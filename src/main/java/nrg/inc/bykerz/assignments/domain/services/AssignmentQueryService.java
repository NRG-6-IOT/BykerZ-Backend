package nrg.inc.bykerz.assignments.domain.services;

import nrg.inc.bykerz.assignments.domain.model.aggregates.Assignment;
import nrg.inc.bykerz.assignments.domain.model.queries.GetAssignmentByIdQuery;
import nrg.inc.bykerz.assignments.domain.model.queries.GetAssignmentByVehicleIdQuery;
import nrg.inc.bykerz.assignments.domain.model.queries.GetAssignmentsByMechanicIdQuery;

import java.util.List;
import java.util.Optional;

public interface AssignmentQueryService {
    Optional<Assignment> handle(GetAssignmentByVehicleIdQuery getAssignmentByVehicleIdQuery);
    List<Assignment> handle(GetAssignmentsByMechanicIdQuery getAssignmentsByMechanicIdQuery);
    Optional<Assignment> handle(GetAssignmentByIdQuery getAssignmentByIdQuery);
}
