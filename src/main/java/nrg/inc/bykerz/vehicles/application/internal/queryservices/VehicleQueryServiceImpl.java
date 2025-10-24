package nrg.inc.bykerz.vehicles.application.internal.queryservices;

import nrg.inc.bykerz.vehicles.domain.model.aggregates.Vehicle;
import nrg.inc.bykerz.vehicles.domain.model.queries.GetAllModels;
import nrg.inc.bykerz.vehicles.domain.model.queries.GetModelById;
import nrg.inc.bykerz.vehicles.domain.services.VehicleQueryService;
import nrg.inc.bykerz.vehicles.infrastructure.persistence.jpa.repositories.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleQueryServiceImpl implements VehicleQueryService {

    final private VehicleRepository vehicleRepository;

    public VehicleQueryServiceImpl(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public List<Vehicle> handle(GetAllModels getAllModels) {
        return vehicleRepository.findAll();
    }

    @Override
    public Optional<Vehicle> handle(GetModelById getModelById) {
        return vehicleRepository.findById(getModelById.modelId());
    }
}
