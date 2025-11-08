package nrg.inc.bykerz.vehicles.interfaces.rest.transform;

import nrg.inc.bykerz.vehicles.domain.model.entities.Vehicle;
import nrg.inc.bykerz.vehicles.interfaces.rest.resources.ModelResource;
import nrg.inc.bykerz.vehicles.interfaces.rest.resources.VehicleResource;

public class VehicleResourceFromEntityAssembler {
    public static VehicleResource toResourceFromEntity(Vehicle vehicle) {
        ModelResource model = ModelResourceFromEntityAssembler.toResourceFromEntity(vehicle.getModel());

        return new VehicleResource(
                vehicle.getId(),
                vehicle.getOwner().getId(),
                model,
                vehicle.getPlate().plate(),
                vehicle.getYear().year()
        );
    }
}
