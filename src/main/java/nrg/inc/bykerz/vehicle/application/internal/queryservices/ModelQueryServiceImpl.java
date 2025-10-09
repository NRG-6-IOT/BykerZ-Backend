package nrg.inc.bykerz.vehicle.application.internal.queryservices;


import nrg.inc.bykerz.vehicle.domain.model.entities.Model;
import nrg.inc.bykerz.vehicle.domain.model.queries.GetAllModels;
import nrg.inc.bykerz.vehicle.domain.model.queries.GetModelById;
import nrg.inc.bykerz.vehicle.domain.services.ModelQueryService;
import nrg.inc.bykerz.vehicle.infrastructure.persistence.jpa.repositories.ModelRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ModelQueryServiceImpl implements ModelQueryService {

    final private ModelRepository modelRepository;

    public ModelQueryServiceImpl(ModelRepository modelRepository) {
        this.modelRepository = modelRepository;
    }

    @Override
    public List<Model> handle(GetAllModels getAllModels) {
        return modelRepository.findAll();
    }

    @Override
    public Optional<Model> handle(GetModelById getModelById) {
        return modelRepository.findById(getModelById.modelId());
    }
}
