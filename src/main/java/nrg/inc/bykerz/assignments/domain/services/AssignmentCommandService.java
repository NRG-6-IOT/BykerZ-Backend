package nrg.inc.bykerz.assignments.domain.services;

import nrg.inc.bykerz.assignments.domain.model.aggregates.Assignment;
import nrg.inc.bykerz.assignments.domain.model.commands.CreateAssignmentCommand;
import nrg.inc.bykerz.assignments.domain.model.commands.UpdateAssignmentStatusCommand;
import nrg.inc.bykerz.assignments.domain.model.commands.UpdateAssignmentTypeCommand;

import java.util.Optional;

public interface AssignmentCommandService {
    Optional<Assignment> handle(CreateAssignmentCommand createAssignmentCommand);
    Optional<Assignment> handle(UpdateAssignmentStatusCommand updateAssignmentStatusCommand);
    Optional<Assignment> handle(UpdateAssignmentTypeCommand updateAssignmentTypeCommand);
}
