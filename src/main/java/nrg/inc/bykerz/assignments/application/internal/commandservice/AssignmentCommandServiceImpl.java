package nrg.inc.bykerz.assignments.application.internal.commandservice;

import nrg.inc.bykerz.assignments.application.external.ExternalVehiclesService;
import nrg.inc.bykerz.assignments.domain.model.aggregates.Assignment;
import nrg.inc.bykerz.assignments.domain.model.commands.AssignOwnerToAssignmentCommand;
import nrg.inc.bykerz.assignments.domain.model.commands.CreateAssignmentCommand;
import nrg.inc.bykerz.assignments.domain.model.commands.UpdateAssignmentStatusCommand;
import nrg.inc.bykerz.assignments.domain.model.commands.UpdateAssignmentTypeCommand;
import nrg.inc.bykerz.assignments.domain.model.valueobjects.AssignmentCode;
import nrg.inc.bykerz.assignments.domain.model.valueobjects.AssignmentStatus;
import nrg.inc.bykerz.assignments.domain.services.AssignmentCommandService;
import nrg.inc.bykerz.assignments.infrastructure.persistence.jpa.repositories.AssignmentRepository;
import nrg.inc.bykerz.assignments.infrastructure.persistence.jpa.repositories.MechanicRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AssignmentCommandServiceImpl implements AssignmentCommandService {

    private final AssignmentRepository assignmentRepository;
    private final MechanicRepository mechanicRepository;
    private final ExternalVehiclesService externalVehiclesService;

    public AssignmentCommandServiceImpl(AssignmentRepository assignmentRepository, MechanicRepository mechanicRepository, ExternalVehiclesService externalVehiclesService) {
        this.assignmentRepository = assignmentRepository;
        this.mechanicRepository = mechanicRepository;
        this.externalVehiclesService = externalVehiclesService;
    }

    @Override
    public Optional<Assignment> handle(CreateAssignmentCommand command) {
        var assigment = new Assignment(command, AssignmentCode.random());
        while (assignmentRepository.existsByAssignmentCode(assigment.getAssignmentCode())){
            assigment.setAssignmentCode(AssignmentCode.random());
        }
        var mechanicOpt = this.mechanicRepository.findById(command.mechanicId());
        if (mechanicOpt.isEmpty()) {
            throw new IllegalArgumentException("Mechanic with id " + command.mechanicId() + " does not exist");
        }
        var mechanic = mechanicOpt.get();
        assigment.setMechanic(mechanicOpt.get());
        mechanic.addAssignment(assigment);
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

    @Override
    public Optional<Assignment> handle(AssignOwnerToAssignmentCommand command) {
        var ownerOpt = externalVehiclesService.getOwnerById(command.ownerId());
        if (ownerOpt.isEmpty()) {
            throw new IllegalArgumentException("Owner with id " + command.ownerId() + " does not exist");
        }
        var code = new AssignmentCode(command.assignmentCode());
        var assignmentOpt = this.assignmentRepository.findByAssignmentCode(code);
        if(assignmentOpt.isEmpty()) {
            throw  new IllegalArgumentException("Assignment not found");
        }
        var assignment = assignmentOpt.get();

        if (assignment.getStatus().equals(AssignmentStatus.ACTIVE)) {
            throw new IllegalArgumentException("Assignment with code " + command.assignmentCode() + " is already active");
        }

        var conflict = this.assignmentRepository
                .existsByOwnerIdAndIdNotAndStatusNot(command.ownerId(), assignment.getId(), AssignmentStatus.CANCELLED);
        if (conflict) {
            throw new IllegalArgumentException("Owner with id " + command.ownerId() + " is already assigned to another non-cancelled assignment");
        }

        assignment.setOwnerId(ownerOpt.get().getId());
        var updatedAssignment = this.assignmentRepository.save(assignment);
        return Optional.of(updatedAssignment);
    }
}
