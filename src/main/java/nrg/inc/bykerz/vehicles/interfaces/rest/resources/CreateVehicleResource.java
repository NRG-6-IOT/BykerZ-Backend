package nrg.inc.bykerz.vehicles.interfaces.rest.resources;

import java.util.Date;

public record CreateVehicleResource(
        Long ownerId,
        Long modelId,
        String year,
        String plate
) {}
