package nrg.inc.bykerz.assignments.domain.services;

import nrg.inc.bykerz.assignments.domain.model.aggregates.Assignment;
import nrg.inc.bykerz.assignments.domain.model.commands.AssignOwnerToAssignmentCommand;
import nrg.inc.bykerz.assignments.domain.model.commands.CreateAssignmentCommand;
import nrg.inc.bykerz.assignments.domain.model.commands.UpdateAssignmentStatusCommand;
import nrg.inc.bykerz.assignments.domain.model.commands.UpdateAssignmentTypeCommand;

import java.util.Optional;

public interface AssignmentCommandService {
    Optional<Assignment> handle(CreateAssignmentCommand command);
    Optional<Assignment> handle(UpdateAssignmentStatusCommand command);
    Optional<Assignment> handle(UpdateAssignmentTypeCommand command);
    Optional<Assignment> handle(AssignOwnerToAssignmentCommand command);
}
