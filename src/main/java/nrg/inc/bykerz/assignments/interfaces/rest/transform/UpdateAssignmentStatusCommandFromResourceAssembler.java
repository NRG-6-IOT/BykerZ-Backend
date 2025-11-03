package nrg.inc.bykerz.assignments.interfaces.rest.transform;

import nrg.inc.bykerz.assignments.domain.model.commands.UpdateAssignmentStatusCommand;
import nrg.inc.bykerz.assignments.interfaces.rest.resources.UpdateAssignmentStatusResource;

public class UpdateAssignmentStatusCommandFromResourceAssembler {
    public static UpdateAssignmentStatusCommand updateAssignmentStatusCommandFromResourceAssembler(Long assignmentId, UpdateAssignmentStatusResource resource) {
        return new UpdateAssignmentStatusCommand(assignmentId, resource.status());
    }
}
