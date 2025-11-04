package nrg.inc.bykerz.vehicles.interfaces.rest.resources;

public record VehicleResource(
        Long id,
        Long ownerId,
        ModelResource model,
        String year,
        String plate
) {
}
