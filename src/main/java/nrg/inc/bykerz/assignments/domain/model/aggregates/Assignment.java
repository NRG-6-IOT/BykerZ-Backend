package nrg.inc.bykerz.assignments.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import nrg.inc.bykerz.assignments.domain.model.commands.CreateAssignmentCommand;
import nrg.inc.bykerz.assignments.domain.model.commands.UpdateAssignmentStatusCommand;
import nrg.inc.bykerz.assignments.domain.model.commands.UpdateAssignmentTypeCommand;
import nrg.inc.bykerz.assignments.domain.model.valueobjects.AssignmentCode;
import nrg.inc.bykerz.assignments.domain.model.valueobjects.AssignmentStatus;
import nrg.inc.bykerz.assignments.domain.model.valueobjects.AssignmentType;
import nrg.inc.bykerz.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;


@Entity
@Getter
@Setter
public class Assignment extends AuditableAbstractAggregateRoot<Assignment> {

    @Column(name = "owner_id", nullable = true)
    private Long ownerId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mechanic_id")
    private Mechanic mechanic;

    @Enumerated(EnumType.STRING)
    private AssignmentStatus status;

    @Enumerated(EnumType.STRING)
    private AssignmentType type;

    @Embedded
    @AttributeOverride(name = "assignmentCode", column = @Column(name = "assignmentCode", unique = true))
    private AssignmentCode assignmentCode;

    protected Assignment() {
    }

    /**
     * Constructor que recibe el comando y la entidad Mechanic (resolver el Mechanic fuera de la entidad).
     */
    public Assignment(CreateAssignmentCommand command, AssignmentCode assignmentCode) {
        this.status = AssignmentStatus.valueOf(command.assignmentStatus().toUpperCase());
        this.type = AssignmentType.valueOf(command.assignmentType().toUpperCase());
        this.assignmentCode = assignmentCode;
    }

    public void updateStatus(UpdateAssignmentStatusCommand command) {
        this.status = AssignmentStatus.valueOf(command.assignmentStatus().toUpperCase());
    }

    public void updateType(UpdateAssignmentTypeCommand command) {
        this.type = AssignmentType.valueOf(command.assignmentType().toUpperCase());
    }
}
