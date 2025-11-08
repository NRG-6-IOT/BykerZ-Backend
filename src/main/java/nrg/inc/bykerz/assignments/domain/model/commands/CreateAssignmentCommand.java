package nrg.inc.bykerz.assignments.domain.model.commands;

public record CreateAssignmentCommand(
        Long mechanicId,
        String assignmentStatus,
        String assignmentType
) {
}
