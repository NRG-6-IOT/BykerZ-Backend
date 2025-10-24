package nrg.inc.bykerz.vehicles.domain.services;

import nrg.inc.bykerz.vehicles.domain.model.entities.Model;
import nrg.inc.bykerz.vehicles.domain.model.queries.GetAllModels;
import nrg.inc.bykerz.vehicles.domain.model.queries.GetModelById;

import java.util.List;
import java.util.Optional;

public interface ModelQueryService {

    List<Model> handle(GetAllModels getAllModels);

    Optional<Model> handle(GetModelById getModelById);

}
