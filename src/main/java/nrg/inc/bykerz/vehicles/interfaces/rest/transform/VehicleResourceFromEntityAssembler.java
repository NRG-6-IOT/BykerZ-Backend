package nrg.inc.bykerz.vehicles.interfaces.rest.transform;

import nrg.inc.bykerz.vehicles.domain.model.aggregates.Owner;
import nrg.inc.bykerz.vehicles.domain.model.entities.Vehicle;
import nrg.inc.bykerz.vehicles.interfaces.rest.resources.ModelResource;
import nrg.inc.bykerz.vehicles.interfaces.rest.resources.OwnerResource;
import nrg.inc.bykerz.vehicles.interfaces.rest.resources.VehicleResource;

public class VehicleResourceFromEntityAssembler {
    public static VehicleResource toResourceFromEntity(Vehicle vehicle) {
        ModelResource model = ModelResourceFromEntityAssembler.toResourceFromEntity(vehicle.getModel());
        OwnerResource owner = OwnerResourceFromEntityAssembler.toResourceFromEntity(vehicle.getOwner());

        return new VehicleResource(
                vehicle.getId(),
                owner,
                model,
                vehicle.getPlate().plate(),
                vehicle.getYear().year()
        );
    }
}
