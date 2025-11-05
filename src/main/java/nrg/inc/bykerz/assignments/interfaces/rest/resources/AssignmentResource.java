package nrg.inc.bykerz.assignments.interfaces.rest.resources;

import nrg.inc.bykerz.shared.interfaces.rest.resources.MechanicResource;
import nrg.inc.bykerz.vehicles.interfaces.rest.resources.VehicleResource;

public record AssignmentResource(
        Long id,
        VehicleResource vehicle,
        MechanicResource mechanic,
        String type,
        String status,
        String createdAt
) {
}
