package nrg.inc.bykerz.assignments.domain.services;

import nrg.inc.bykerz.assignments.domain.model.aggregates.Assignment;
import nrg.inc.bykerz.assignments.domain.model.queries.*;

import java.util.List;
import java.util.Optional;

public interface AssignmentQueryService {
    Optional<Assignment> handle(GetAssignmentByOwnerIdQuery query);
    List<Assignment> handle(GetAssignmentsByMechanicIdAndStatusQuery query);
    Optional<Assignment> handle(GetAssignmentByIdQuery query);
    Optional<Assignment> handle(GetAssigmentByCodeQuery query);
    Optional<Assignment> handle(GetAssignmentByVehicleIdQuery getAssignmentByVehicleIdQuery);
}
