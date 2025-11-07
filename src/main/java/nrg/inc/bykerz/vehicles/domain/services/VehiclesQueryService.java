package nrg.inc.bykerz.vehicles.domain.services;


import nrg.inc.bykerz.vehicles.domain.model.entities.Vehicle;
import nrg.inc.bykerz.vehicles.domain.model.queries.GetVehicleByIdQuery;
import nrg.inc.bykerz.vehicles.domain.model.queries.GetVehicleByPlateQuery;
import nrg.inc.bykerz.vehicles.domain.model.queries.GetVehiclesByOwnerIdQuery;

import java.util.List;
import java.util.Optional;

public interface VehiclesQueryService {

    Optional<Vehicle> handle(GetVehicleByIdQuery query);

    Optional<Vehicle> handle(GetVehicleByPlateQuery query);
    List<Vehicle> handle(GetVehiclesByOwnerIdQuery query);
}
