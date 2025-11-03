package nrg.inc.bykerz.assignments.interfaces.rest.transform;

import nrg.inc.bykerz.assignments.domain.model.commands.UpdateAssignmentTypeCommand;
import nrg.inc.bykerz.assignments.interfaces.rest.resources.UpdateAssignmentTypeResource;

public class UpdateAssignmentTypeCommandFromResourceAssembler {
    public static UpdateAssignmentTypeCommand updateAssignmentTypeCommandFromResourceAssembler(Long assignmentId, UpdateAssignmentTypeResource resource) {
        return new UpdateAssignmentTypeCommand(assignmentId, resource.type());
    }
}
