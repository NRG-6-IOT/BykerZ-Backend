package nrg.inc.bykerz.vehicles.application.internal.queryservices;

import nrg.inc.bykerz.vehicles.domain.model.entities.Vehicle;
import nrg.inc.bykerz.vehicles.domain.model.queries.GetVehicleByIdQuery;
import nrg.inc.bykerz.vehicles.domain.model.queries.GetVehicleByPlateQuery;
import nrg.inc.bykerz.vehicles.domain.model.queries.GetVehiclesByOwnerIdQuery;
import nrg.inc.bykerz.vehicles.domain.model.valueobjects.Plate;
import nrg.inc.bykerz.vehicles.domain.services.VehiclesQueryService;
import nrg.inc.bykerz.vehicles.infrastructure.persistence.jpa.repositories.VehicleReadRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehiclesQueryServiceImpl implements VehiclesQueryService {

    final private VehicleReadRepository vehicleReadRepository;

    public VehiclesQueryServiceImpl(VehicleReadRepository vehicleReadRepository) {
        this.vehicleReadRepository = vehicleReadRepository;
    }


    @Override
    public Optional<Vehicle> handle(GetVehicleByIdQuery query) {
        return vehicleReadRepository.findById(query.vehicleId());
    }

    @Override
    public Optional<Vehicle> handle(GetVehicleByPlateQuery query) {
        return vehicleReadRepository.findByPlate(new Plate(query.plate()));
    }

    @Override
    public List<Vehicle> handle(GetVehiclesByOwnerIdQuery query) {
        return vehicleReadRepository.findByOwnerId(query.ownerId());
    }
}
