package nrg.inc.bykerz.vehicle.interfaces.rest.transform;

import nrg.inc.bykerz.vehicle.domain.model.entities.Brand;
import nrg.inc.bykerz.vehicle.interfaces.rest.resources.BrandResource;

public class BrandResourceFromEntityAssembler {

    public static BrandResource toResourceFromEntity(Brand brand) {
        return new BrandResource(
                brand.getId(),
                brand.getBrandName()
        );
    }
}
