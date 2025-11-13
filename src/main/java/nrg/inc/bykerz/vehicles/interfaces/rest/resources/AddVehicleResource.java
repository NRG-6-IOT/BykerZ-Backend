package nrg.inc.bykerz.vehicles.interfaces.rest.resources;

public record AddVehicleResource(
        String plate,
        String year,
        Long modelId
) {
}
