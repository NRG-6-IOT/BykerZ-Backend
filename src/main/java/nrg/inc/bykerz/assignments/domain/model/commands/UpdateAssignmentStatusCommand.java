package nrg.inc.bykerz.assignments.domain.model.commands;

public record UpdateAssignmentStatusCommand(
        Long assignmentId,
        String assignmentStatus
) {
}
