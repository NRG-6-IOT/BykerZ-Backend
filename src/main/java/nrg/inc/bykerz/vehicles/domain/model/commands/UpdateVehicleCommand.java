package nrg.inc.bykerz.vehicles.domain.model.commands;

public record UpdateVehicleCommand(
        Long id,
        Long mechanicId,
        String plate
) {}
