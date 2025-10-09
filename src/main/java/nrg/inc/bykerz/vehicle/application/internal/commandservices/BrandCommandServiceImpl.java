package nrg.inc.bykerz.vehicle.application.internal.commandservices;

import nrg.inc.bykerz.vehicle.domain.model.commands.CreateBrandCommand;
import nrg.inc.bykerz.vehicle.domain.model.entities.Brand;
import nrg.inc.bykerz.vehicle.domain.services.BrandCommandService;
import nrg.inc.bykerz.vehicle.infrastructure.persistence.jpa.repositories.BrandRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BrandCommandServiceImpl implements BrandCommandService {

    final private BrandRepository brandRepository;

    public BrandCommandServiceImpl(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public Optional<Brand> handle(CreateBrandCommand createBrandCommand) {
        var brand = new Brand(createBrandCommand);

        try{
            brandRepository.save(brand);
            return Optional.of(brand);
        } catch (Exception e){
            throw new IllegalArgumentException("Error creating brand: " + e.getMessage());
        }
    }
}
