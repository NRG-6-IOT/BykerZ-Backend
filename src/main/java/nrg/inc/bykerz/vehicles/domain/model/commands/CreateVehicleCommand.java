package nrg.inc.bykerz.vehicles.domain.model.commands;

import java.util.Date;

public record CreateVehicleCommand(
        Long mechanicId,
        Long modelId,
        Date manufacturingDate,
        String plate
) {}
