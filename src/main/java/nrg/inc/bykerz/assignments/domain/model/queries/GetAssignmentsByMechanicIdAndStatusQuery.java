package nrg.inc.bykerz.assignments.domain.model.queries;

public record GetAssignmentsByMechanicIdAndStatusQuery(
        Long mechanicId,
        String status
) {
}
