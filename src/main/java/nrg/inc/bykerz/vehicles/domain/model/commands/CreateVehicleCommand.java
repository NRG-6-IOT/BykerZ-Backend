package nrg.inc.bykerz.vehicles.domain.model.commands;

import java.util.Date;

public record CreateVehicleCommand(
        Long ownerId,
        Long mechanicId,
        Long modelId,
        String year,
        String plate
) {}
