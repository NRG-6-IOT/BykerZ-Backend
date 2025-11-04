package nrg.inc.bykerz.assignments.application.internal.queryservice;

import nrg.inc.bykerz.assignments.domain.model.aggregates.Assignment;
import nrg.inc.bykerz.assignments.domain.model.queries.GetAssignmentByIdQuery;
import nrg.inc.bykerz.assignments.domain.model.queries.GetAssignmentByVehicleIdQuery;
import nrg.inc.bykerz.assignments.domain.model.queries.GetAssignmentsByMechanicIdAndStatusQuery;
import nrg.inc.bykerz.assignments.domain.model.valueobjects.AssignmentStatus;
import nrg.inc.bykerz.assignments.domain.services.AssignmentQueryService;
import nrg.inc.bykerz.assignments.infrastructure.persistence.jpa.repositories.AssignmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.Locale.filter;

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
    public List<Assignment> handle(GetAssignmentsByMechanicIdAndStatusQuery getAssignmentsByMechanicIdAndStatusQuery) {
        AssignmentStatus status;
        try {
            status = AssignmentStatus.valueOf(getAssignmentsByMechanicIdAndStatusQuery.status().toUpperCase());
        } catch (IllegalArgumentException e) {
            // Manejo de error si el estado no es válido
            throw new RuntimeException("Invalid status: " + getAssignmentsByMechanicIdAndStatusQuery.status(), e);
        }

        return this.assignmentRepository.findByMechanicId(getAssignmentsByMechanicIdAndStatusQuery.mechanicId()).stream()
                .filter(assignment -> assignment.getStatus().equals(status))
                .toList();
    }

    @Override
    public Optional<Assignment> handle(GetAssignmentByIdQuery getAssignmentByIdQuery) {
        return this.assignmentRepository.findById(getAssignmentByIdQuery.assignmentId());
    }
}
