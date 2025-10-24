package nrg.inc.bykerz.vehicles.interfaces.rest.transform;

import nrg.inc.bykerz.vehicles.domain.model.commands.CreateModelCommand;
import nrg.inc.bykerz.vehicles.interfaces.rest.resources.CreateModelResource;

public class CreateModelCommandFromResourceAssembler {

    public static CreateModelCommand toCommandFromResource(CreateModelResource resource) {
        return new CreateModelCommand(
                resource.name(),
                resource.brandId(), // Brand ya existente
                resource.modelYear(),
                resource.originCountry(),
                resource.producedAt(),
                resource.type(),
                resource.displacement(),
                resource.potency(),
                resource.engineType(),
                resource.engineTorque(),
                resource.weight(),
                resource.transmission(),
                resource.brakes(),
                resource.tank(),
                resource.seatHeight(),
                resource.consumption(),
                resource.price(),
                resource.oilCapacity(),
                resource.connectivity(),
                resource.durability(),
                resource.octane()
        );
    }
}
