package nrg.inc.bykerz.vehicles.domain.model.commands;

public record CreateVehicleCommand(
        Long ownerId,
        Long modelId,
        String year,
        String plate
) {}
