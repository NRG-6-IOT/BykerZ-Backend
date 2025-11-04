package nrg.inc.bykerz.assignments.interfaces.rest.resources;

public record AssignmentResource(
        Long id,
        Long vehicleId,
        Long mechanicId,
        String type,
        String status,
        String createdAt
) {
}
