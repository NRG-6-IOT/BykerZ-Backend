package nrg.inc.bykerz.assignments.domain.model.commands;

public record AssignOwnerToAssignmentCommand(
    String assignmentCode,
    Long ownerId
) {
}
