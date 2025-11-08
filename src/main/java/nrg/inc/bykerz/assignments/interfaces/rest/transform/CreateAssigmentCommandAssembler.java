package nrg.inc.bykerz.assignments.interfaces.rest.transform;

import nrg.inc.bykerz.assignments.domain.model.commands.CreateAssignmentCommand;

public class CreateAssigmentCommandAssembler {
    public static CreateAssignmentCommand toCommand(Long mechanicId) {
        return new CreateAssignmentCommand(
                mechanicId,
                "PENDING",
                "UNCATEGORIZED"
        );
    }
}
