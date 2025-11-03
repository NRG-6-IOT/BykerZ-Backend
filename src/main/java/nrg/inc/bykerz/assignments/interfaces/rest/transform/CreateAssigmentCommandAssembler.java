package nrg.inc.bykerz.assignments.interfaces.rest.transform;

import nrg.inc.bykerz.assignments.domain.model.commands.CreateAssignmentCommand;

public class CreateAssigmentCommandAssembler {
    public static CreateAssignmentCommand toCommand(Long vehicleId, Long mechanicId) {
        return new CreateAssignmentCommand(
                vehicleId,
                mechanicId,
                "PENDING",
                "UNCATEGORIZED"
        );
    }
}
