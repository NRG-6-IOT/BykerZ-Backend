package nrg.inc.bykerz.vehicles.domain.services;

import nrg.inc.bykerz.vehicles.domain.model.entities.Model;
import nrg.inc.bykerz.vehicles.domain.model.queries.GetAllBrandsQuery;
import nrg.inc.bykerz.vehicles.domain.model.queries.GetAllModelsQuery;
import nrg.inc.bykerz.vehicles.domain.model.queries.GetModelByIdQuery;
import nrg.inc.bykerz.vehicles.domain.model.queries.GetModelsByBrandQuery;

import java.util.List;
import java.util.Optional;

public interface ModelQueryService {

    List<Model> handle(GetAllModelsQuery query);

    Optional<Model> handle(GetModelByIdQuery query);

    List<Model> handle(GetModelsByBrandQuery query);

    List<String> handle(GetAllBrandsQuery query);
}
