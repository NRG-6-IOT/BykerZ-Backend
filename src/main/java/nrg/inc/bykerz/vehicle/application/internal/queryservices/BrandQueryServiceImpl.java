package nrg.inc.bykerz.vehicle.application.internal.queryservices;

import nrg.inc.bykerz.vehicle.domain.model.entities.Brand;
import nrg.inc.bykerz.vehicle.domain.model.queries.GetAllBrands;
import nrg.inc.bykerz.vehicle.domain.model.queries.GetBrandById;
import nrg.inc.bykerz.vehicle.domain.services.BrandQueryService;
import nrg.inc.bykerz.vehicle.infrastructure.persistence.jpa.repositories.BrandRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BrandQueryServiceImpl implements BrandQueryService {

    final private BrandRepository brandRepository;

    public BrandQueryServiceImpl(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public List<Brand> handle(GetAllBrands getAllBrands) {
        return brandRepository.findAll();
    }

    @Override
    public Optional<Brand> handle(GetBrandById getBrandById) {
        return brandRepository.findById(getBrandById.brandId());
    }
}
