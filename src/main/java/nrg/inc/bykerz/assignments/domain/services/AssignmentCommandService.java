package nrg.inc.bykerz.assignments.domain.services;

import nrg.inc.bykerz.assignments.domain.model.aggregates.Assignment;
import nrg.inc.bykerz.assignments.domain.model.commands.*;

import java.util.Optional;

public interface AssignmentCommandService {
    Optional<Assignment> handle(CreateAssignmentCommand command);
    Optional<Assignment> handle(UpdateAssignmentStatusCommand command);
    Optional<Assignment> handle(UpdateAssignmentTypeCommand command);
    Optional<Assignment> handle(AssignOwnerToAssignmentCommand command);
    void handle(DeleteAssignmentCommand command);
}
