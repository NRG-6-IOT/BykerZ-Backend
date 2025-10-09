package nrg.inc.bykerz.vehicle.interfaces.rest.transform;

import nrg.inc.bykerz.vehicle.domain.model.aggregates.Vehicle;
import nrg.inc.bykerz.vehicle.domain.model.entities.Brand;
import nrg.inc.bykerz.vehicle.domain.model.entities.Model;
import nrg.inc.bykerz.vehicle.interfaces.rest.resources.VehicleResource;

public class VehicleResourceFromEntityAssembler {
    public static VehicleResource toResourceFromEntity(Vehicle vehicle) {
        Model model = vehicle.getModel();
        Brand brand = model.getBrand();

        return new VehicleResource(
                vehicle.getId(),
                vehicle.getMechanicId(),
                model.getName(),
                brand.getBrandName(),
                model.getModelYear(),
                model.getOriginCountry(),
                model.getProducedAt(),
                model.getType(),
                model.getDisplacement(),
                model.getPotency(),
                model.getEngineType(),
                model.getEngineTorque(),
                model.getWeight(),
                model.getTransmission(),
                model.getBrakes(),
                model.getTank(),
                model.getSeatHeight(),
                model.getConsumption(),
                model.getPrice(),
                model.getOilCapacity(),
                model.getConnectivity(),
                model.getDurability(),
                model.getOctane(),
                vehicle.getManufacturingDate(),
                vehicle.getPlate()
        );
    }
}
