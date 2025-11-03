package nrg.inc.bykerz.assignments.application.internal.commandservice;

import nrg.inc.bykerz.assignments.domain.model.aggregates.Assignment;
import nrg.inc.bykerz.assignments.domain.model.commands.CreateAssignmentCommand;
import nrg.inc.bykerz.assignments.domain.model.commands.UpdateAssignmentStatusCommand;
import nrg.inc.bykerz.assignments.domain.model.commands.UpdateAssignmentTypeCommand;
import nrg.inc.bykerz.assignments.domain.model.valueobjects.AssignmentStatus;
import nrg.inc.bykerz.assignments.domain.services.AssignmentCommandService;
import nrg.inc.bykerz.assignments.infrastructure.persistence.jpa.repositories.AssignmentRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AssignmentCommandServiceImpl implements AssignmentCommandService {

    private final AssignmentRepository assignmentRepository;

    public AssignmentCommandServiceImpl(AssignmentRepository assignmentRepository) {
        this.assignmentRepository = assignmentRepository;
    }

    @Override
    public Optional<Assignment> handle(CreateAssignmentCommand command) {
        var assigmentOpt = this.assignmentRepository.findByVehicleId(command.vehicleId());
        if(assigmentOpt.isPresent() && assigmentOpt.get().getStatus() != AssignmentStatus.CANCELLED) {
            throw new IllegalArgumentException("Assignment for vehicle with id " + command.vehicleId() + " already exists");
        }
        var assigment = new Assignment(command);
        var createdAssignment = this.assignmentRepository.save(assigment);
        return Optional.of(createdAssignment);
    }

    @Override
    public Optional<Assignment> handle(UpdateAssignmentStatusCommand command) {
        var assignmentOpt = this.assignmentRepository.findById(command.assignmentId());
        if(assignmentOpt.isEmpty()) {
            throw  new IllegalArgumentException("Assignment not found");
        }
        var assignment = assignmentOpt.get();
        assignment.updateStatus(command);
        var updatedAssignment = this.assignmentRepository.save(assignment);
        return Optional.of(updatedAssignment);
    }

    @Override
    public Optional<Assignment> handle(UpdateAssignmentTypeCommand command) {
        var assignmentOpt = this.assignmentRepository.findById(command.assignmentId());
        if (assignmentOpt.isEmpty()) {
            throw  new IllegalArgumentException("Assignment not found");
        }
        var assignment = assignmentOpt.get();
        assignment.updateType(command);
        var updatedAssignment = this.assignmentRepository.save(assignment);
        return Optional.of(updatedAssignment);
    }
}
