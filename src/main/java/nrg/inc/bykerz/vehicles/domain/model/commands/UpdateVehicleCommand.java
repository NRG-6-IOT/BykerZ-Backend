package nrg.inc.bykerz.vehicles.domain.model.commands;

public record UpdateVehicleCommand(
        Long id,
        String plate
) {}