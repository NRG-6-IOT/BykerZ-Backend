package nrg.inc.bykerz.assignments.domain.model.commands;

public record UpdateAssignmentTypeCommand(
        Long assignmentId,
        String assignmentType
) {
}
