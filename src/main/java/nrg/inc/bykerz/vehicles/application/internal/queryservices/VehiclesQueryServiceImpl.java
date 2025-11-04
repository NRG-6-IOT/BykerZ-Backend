package nrg.inc.bykerz.vehicles.application.internal.queryservices;

import nrg.inc.bykerz.vehicles.domain.model.aggregates.Vehicle;
import nrg.inc.bykerz.vehicles.domain.model.queries.GetVehicleByIdQuery;
import nrg.inc.bykerz.vehicles.domain.model.queries.GetVehicleByPlateQuery;
import nrg.inc.bykerz.vehicles.domain.model.queries.GetVehiclesByOwnerIdQuery;
import nrg.inc.bykerz.vehicles.domain.model.valueobjects.Plate;
import nrg.inc.bykerz.vehicles.domain.services.VehiclesQueryService;
import nrg.inc.bykerz.vehicles.infrastructure.persistence.jpa.repositories.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehiclesQueryServiceImpl implements VehiclesQueryService {

    final private VehicleRepository vehicleRepository;

    public VehiclesQueryServiceImpl(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }


    @Override
    public Optional<Vehicle> handle(GetVehicleByIdQuery query) {
        return vehicleRepository.findById(query.vehicleId());
    }

    @Override
    public Optional<Vehicle> handle(GetVehicleByPlateQuery query) {
        return vehicleRepository.findByPlate(new Plate(query.plate()));
    }

    @Override
    public List<Vehicle> handle(GetVehiclesByOwnerIdQuery query) {
        return vehicleRepository.findByOwnerId(query.ownerId());
    }
}
