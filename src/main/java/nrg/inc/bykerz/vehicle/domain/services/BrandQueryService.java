package nrg.inc.bykerz.vehicle.domain.services;

import nrg.inc.bykerz.vehicle.domain.model.entities.Brand;
import nrg.inc.bykerz.vehicle.domain.model.queries.GetAllBrands;
import nrg.inc.bykerz.vehicle.domain.model.queries.GetBrandById;

import java.util.List;
import java.util.Optional;

public interface BrandQueryService {

    List<Brand> handle(GetAllBrands getAllBrands);

    Optional<Brand> handle(GetBrandById getBrandById);

}
