package nrg.inc.bykerz.vehicles.domain.services;


import nrg.inc.bykerz.vehicles.domain.model.aggregates.Vehicle;
import nrg.inc.bykerz.vehicles.domain.model.queries.GetAllModels;
import nrg.inc.bykerz.vehicles.domain.model.queries.GetModelById;

import java.util.List;
import java.util.Optional;

public interface VehicleQueryService {

    List<Vehicle> handle(GetAllModels getAllModels);

    Optional<Vehicle> handle(GetModelById getModelById);

}
