package nrg.inc.bykerz.vehicles.domain.services;

import nrg.inc.bykerz.vehicles.domain.model.entities.Model;
import nrg.inc.bykerz.vehicles.domain.model.queries.GetAllModelsQuery;
import nrg.inc.bykerz.vehicles.domain.model.queries.GetModelByIdQuery;

import java.util.List;
import java.util.Optional;

public interface ModelQueryService {

    List<Model> handle(GetAllModelsQuery getAllModelsQuery);

    Optional<Model> handle(GetModelByIdQuery getModelByIdQuery);

}
