package nrg.inc.bykerz.assignments.interfaces.rest.resources;

import nrg.inc.bykerz.vehicles.interfaces.rest.resources.OwnerResource;
import nrg.inc.bykerz.vehicles.interfaces.rest.resources.VehicleResource;

public record AssignmentResource(
        Long id,
        OwnerResource owner,
        MechanicResource mechanic,
        String type,
        String status,
        String assignmentCode,
        String createdAt
) {
}
