package nrg.inc.bykerz.assignments.application.internal.queryservice;

import nrg.inc.bykerz.assignments.domain.model.aggregates.Assignment;
import nrg.inc.bykerz.assignments.domain.model.queries.GetAssigmentByCodeQuery;
import nrg.inc.bykerz.assignments.domain.model.queries.GetAssignmentByIdQuery;
import nrg.inc.bykerz.assignments.domain.model.queries.GetAssignmentByOwnerIdQuery;
import nrg.inc.bykerz.assignments.domain.model.queries.GetAssignmentsByMechanicIdAndStatusQuery;
import nrg.inc.bykerz.assignments.domain.model.valueobjects.AssignmentCode;
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
    public Optional<Assignment> handle(GetAssignmentByOwnerIdQuery query) {
        return this.assignmentRepository.findByOwnerId(query.ownerId());
    }

    @Override
    public List<Assignment> handle(GetAssignmentsByMechanicIdAndStatusQuery query) {
        AssignmentStatus status;
        try {
            status = AssignmentStatus.valueOf(query.status().toUpperCase());
        } catch (IllegalArgumentException e) {
            // Manejo de error si el estado no es válido
            throw new RuntimeException("Invalid status: " + query.status(), e);
        }

        return this.assignmentRepository.findByMechanic_Id(query.mechanicId()).stream()
                .filter(assignment -> assignment.getStatus().equals(status))
                .toList();
    }

    @Override
    public Optional<Assignment> handle(GetAssignmentByIdQuery query) {
        return this.assignmentRepository.findById(query.assignmentId());
    }

    @Override
    public Optional<Assignment> handle(GetAssigmentByCodeQuery query) {
        var code = new AssignmentCode(query.assignmentCode());
        return this.assignmentRepository.findByAssignmentCode(code);
    }
}
