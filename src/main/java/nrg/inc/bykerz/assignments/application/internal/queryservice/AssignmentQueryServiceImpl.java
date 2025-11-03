package nrg.inc.bykerz.assignments.application.internal.queryservice;

import nrg.inc.bykerz.assignments.domain.model.aggregates.Assignment;
import nrg.inc.bykerz.assignments.domain.model.queries.GetAssignmentByIdQuery;
import nrg.inc.bykerz.assignments.domain.model.queries.GetAssignmentByVehicleIdQuery;
import nrg.inc.bykerz.assignments.domain.model.queries.GetAssignmentsByMechanicIdQuery;
import nrg.inc.bykerz.assignments.domain.services.AssignmentQueryService;
import nrg.inc.bykerz.assignments.infrastructure.persistence.jpa.repositories.AssignmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AssignmentQueryServiceImpl implements AssignmentQueryService {
    private final AssignmentRepository assignmentRepository;
    public AssignmentQueryServiceImpl(AssignmentRepository assignmentRepository) {
        this.assignmentRepository = assignmentRepository;
    }

    @Override
    public Optional<Assignment> handle(GetAssignmentByVehicleIdQuery getAssignmentByVehicleIdQuery) {
        return this.assignmentRepository.findByVehicleId(getAssignmentByVehicleIdQuery.vehicleId());
    }

    @Override
    public List<Assignment> handle(GetAssignmentsByMechanicIdQuery getAssignmentsByMechanicIdQuery) {
        return this.assignmentRepository.findByMechanicId(getAssignmentsByMechanicIdQuery.mechanicId());
    }

    @Override
    public Optional<Assignment> handle(GetAssignmentByIdQuery getAssignmentByIdQuery) {
        return this.assignmentRepository.findById(getAssignmentByIdQuery.assignmentId());
    }
}
