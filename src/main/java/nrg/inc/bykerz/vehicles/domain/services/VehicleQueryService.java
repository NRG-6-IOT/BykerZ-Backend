package nrg.inc.bykerz.vehicles.domain.services;


import nrg.inc.bykerz.vehicles.domain.model.aggregates.Vehicle;
import nrg.inc.bykerz.vehicles.domain.model.queries.GetVehicleByIdQuery;

import java.util.Optional;

public interface VehicleQueryService {

    Optional<Vehicle> handle(GetVehicleByIdQuery query);

}
