package nrg.inc.bykerz.vehicle.application.internal.commandservices;

import nrg.inc.bykerz.vehicle.domain.model.commands.CreateModelCommand;
import nrg.inc.bykerz.vehicle.domain.model.entities.Brand;
import nrg.inc.bykerz.vehicle.domain.model.entities.Model;
import nrg.inc.bykerz.vehicle.domain.services.ModelCommandService;
import nrg.inc.bykerz.vehicle.infrastructure.persistence.jpa.repositories.BrandRepository;
import nrg.inc.bykerz.vehicle.infrastructure.persistence.jpa.repositories.ModelRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ModelCommandServiceImpl implements ModelCommandService {

    final private ModelRepository modelRepository;
    final private BrandRepository brandRepository;

    public ModelCommandServiceImpl(ModelRepository modelRepository, BrandRepository brandRepository) {
        this.modelRepository = modelRepository;
        this.brandRepository = brandRepository;
    }

    @Override
    public Optional<Model> handle(CreateModelCommand createModelCommand) {

        Brand brand = brandRepository.findById(createModelCommand.brandId())
                .orElseThrow(() -> new IllegalArgumentException("Brand not found"));

        Model model = new Model(createModelCommand, brand);

        try{
            modelRepository.save(model);
            return Optional.of(model);
        } catch(Exception e){
            throw new IllegalArgumentException("Error creating model: " + e.getMessage());
        }
    }
}
