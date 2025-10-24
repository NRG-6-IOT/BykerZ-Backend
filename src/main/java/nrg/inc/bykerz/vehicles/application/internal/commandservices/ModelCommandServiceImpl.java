package nrg.inc.bykerz.vehicles.application.internal.commandservices;

import nrg.inc.bykerz.vehicles.domain.model.commands.CreateModelCommand;
import nrg.inc.bykerz.vehicles.domain.model.entities.Model;
import nrg.inc.bykerz.vehicles.domain.services.ModelCommandService;
import nrg.inc.bykerz.vehicles.infrastructure.persistence.jpa.repositories.ModelRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ModelCommandServiceImpl implements ModelCommandService {

    final private ModelRepository modelRepository;

    public ModelCommandServiceImpl(ModelRepository modelRepository) {
        this.modelRepository = modelRepository;
    }

    @Override
    public Optional<Model> handle(CreateModelCommand createModelCommand) {

        Model model = new Model(createModelCommand);

        try{
            modelRepository.save(model);
            return Optional.of(model);
        } catch(Exception e){
            throw new IllegalArgumentException("Error creating model: " + e.getMessage());
        }
    }
}
