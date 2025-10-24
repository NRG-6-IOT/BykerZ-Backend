package nrg.inc.bykerz.vehicles.interfaces.rest.transform;

import nrg.inc.bykerz.vehicles.domain.model.entities.Brand;
import nrg.inc.bykerz.vehicles.interfaces.rest.resources.BrandResource;

public class BrandResourceFromEntityAssembler {

    public static BrandResource toResourceFromEntity(Brand brand) {
        return new BrandResource(
                brand.getId(),
                brand.getBrandName()
        );
    }
}
