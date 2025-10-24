package nrg.inc.bykerz.vehicles.interfaces.rest.resources;

import java.util.Date;

public record CreateVehicleResource(
        Long mechanicId,
        Long modelId,
        Date manufacturingDate,
        String plate
) {}
