package nrg.inc.bykerz.vehicles.application.internal.queryservices;


import nrg.inc.bykerz.vehicles.domain.model.aggregates.Model;
import nrg.inc.bykerz.vehicles.domain.model.queries.GetAllBrandsQuery;
import nrg.inc.bykerz.vehicles.domain.model.queries.GetAllModelsQuery;
import nrg.inc.bykerz.vehicles.domain.model.queries.GetModelByIdQuery;
import nrg.inc.bykerz.vehicles.domain.model.queries.GetModelsByBrandQuery;
import nrg.inc.bykerz.vehicles.domain.services.ModelQueryService;
import nrg.inc.bykerz.vehicles.infrastructure.persistence.jpa.repositories.ModelRepository;
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
    public List<Model> handle(GetAllModelsQuery getAllModelsQuery) {
        return modelRepository.findAll();
    }

    @Override
    public Optional<Model> handle(GetModelByIdQuery getModelByIdQuery) {
        return modelRepository.findById(getModelByIdQuery.modelId());
    }

    @Override
    public List<Model> handle(GetModelsByBrandQuery query) {
        return modelRepository.findByBrand(query.brand());
    }

    @Override
    public List<String> handle(GetAllBrandsQuery query) {
        return modelRepository.findAllBrands();
    }
}
