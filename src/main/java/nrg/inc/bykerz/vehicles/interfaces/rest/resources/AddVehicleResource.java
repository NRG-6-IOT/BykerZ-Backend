package nrg.inc.bykerz.vehicles.interfaces.rest.resources;

public record AddVehicleResource(
        Long ownerId,
        String plate,
        String year,
        Long modelId
) {
}
