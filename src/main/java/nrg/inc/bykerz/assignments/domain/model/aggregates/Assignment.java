package nrg.inc.bykerz.assignments.domain.model.aggregates;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import nrg.inc.bykerz.assignments.domain.model.commands.CreateAssignmentCommand;
import nrg.inc.bykerz.assignments.domain.model.commands.UpdateAssignmentStatusCommand;
import nrg.inc.bykerz.assignments.domain.model.commands.UpdateAssignmentTypeCommand;
import nrg.inc.bykerz.assignments.domain.model.valueobjects.AssignmentStatus;
import nrg.inc.bykerz.assignments.domain.model.valueobjects.AssignmentType;
import nrg.inc.bykerz.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;


@Entity
@Getter
public class Assignment extends AuditableAbstractAggregateRoot<Assignment> {

    private Long vehicleId;
    private Long mechanicId;
    private AssignmentStatus status;
    private AssignmentType type;

    public Assignment(Long vehicleId, Long mechanicId, AssignmentStatus status, AssignmentType type) {
        this.vehicleId = vehicleId;
        this.mechanicId = mechanicId;
        this.status = status;
        this.type = type;
    }

    public Assignment(CreateAssignmentCommand command) {
        this.vehicleId = command.vehicleId();
        this.mechanicId = command.mechanicId();
        this.status = AssignmentStatus.valueOf(command.assignmentStatus().toUpperCase());
        this.type = AssignmentType.valueOf(command.assignmentType().toUpperCase());
    }

    public Assignment() {
    }

    public void updateStatus(UpdateAssignmentStatusCommand command) {
        this.status = AssignmentStatus.valueOf(command.assignmentStatus().toUpperCase());;
    }

    public void updateType(UpdateAssignmentTypeCommand command) {
        this.type = AssignmentType.valueOf(command.assignmentType().toUpperCase());
    }
}
