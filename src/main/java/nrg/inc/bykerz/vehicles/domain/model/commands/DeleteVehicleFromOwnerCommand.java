package nrg.inc.bykerz.vehicles.domain.model.commands;

public record DeleteVehicleFromOwnerCommand(Long ownerId, Long vehicleId) {
}
