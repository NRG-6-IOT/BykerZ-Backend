package nrg.inc.bykerz.vehicles.interfaces.rest.resources;

public record CreateVehicleResource(
        Long modelId,
        String year,
        String plate
) {}
