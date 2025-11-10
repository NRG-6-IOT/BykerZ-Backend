package nrg.inc.bykerz.vehicles.domain.services;


import nrg.inc.bykerz.vehicles.domain.model.entities.Vehicle;
import nrg.inc.bykerz.vehicles.domain.model.queries.GetVehicleByIdQuery;
import nrg.inc.bykerz.vehicles.domain.model.queries.GetVehicleByPlateQuery;

import java.util.Optional;

public interface VehiclesQueryService {

    Optional<Vehicle> handle(GetVehicleByIdQuery query);

    Optional<Vehicle> handle(GetVehicleByPlateQuery query);

}
