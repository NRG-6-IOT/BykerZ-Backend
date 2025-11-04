package nrg.inc.bykerz.assignments.interfaces.rest.resources;

import nrg.inc.bykerz.shared.interfaces.rest.resources.MechanicResource;

public record AssignmentResource(
        Long id,
        Long vehicleId,
        MechanicResource mechanic,
        String type,
        String status,
        String createdAt
) {
}
