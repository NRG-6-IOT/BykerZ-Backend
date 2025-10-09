package nrg.inc.bykerz.vehicle.interfaces.rest.resources;

import nrg.inc.bykerz.vehicle.domain.model.commands.CreateModelCommand;

import java.util.Date;

public record CreateVehicleResource(
        Long mechanicId,
        Long modelId,
        Date manufacturingDate,
        String plate
) {}
