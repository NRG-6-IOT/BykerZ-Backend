package nrg.inc.bykerz.vehicles.interfaces.rest.resources;

import java.util.Date;

public record VehicleResource(
        Long id,
        Long ownerId,
        ModelResource model,
        String year,
        String plate
) {
}
