package nrg.inc.bykerz.vehicles.domain.model.commands;

public record UpdateVehicleCommand(
        Long mechanicId,
        String plate
) {}
