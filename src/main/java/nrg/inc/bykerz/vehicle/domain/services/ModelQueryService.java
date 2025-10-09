package nrg.inc.bykerz.vehicle.domain.services;

import nrg.inc.bykerz.vehicle.domain.model.entities.Model;
import nrg.inc.bykerz.vehicle.domain.model.queries.GetAllModels;
import nrg.inc.bykerz.vehicle.domain.model.queries.GetModelById;

import java.util.List;
import java.util.Optional;

public interface ModelQueryService {

    List<Model> handle(GetAllModels getAllModels);

    Optional<Model> handle(GetModelById getModelById);

}
