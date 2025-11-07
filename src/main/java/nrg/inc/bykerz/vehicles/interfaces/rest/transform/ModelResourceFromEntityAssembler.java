package nrg.inc.bykerz.vehicles.interfaces.rest.transform;

import nrg.inc.bykerz.vehicles.domain.model.aggregates.Model;
import nrg.inc.bykerz.vehicles.interfaces.rest.resources.ModelResource;

public class ModelResourceFromEntityAssembler {

    public static ModelResource toResourceFromEntity(Model model) {

        return new ModelResource(
                model.getId(),
                model.getName(),
                model.getBrand(),
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
                model.getOctane()
        );
    }
}
