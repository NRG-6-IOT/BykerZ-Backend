package nrg.inc.bykerz.vehicles.application.internal.acl;

import nrg.inc.bykerz.vehicles.domain.model.entities.Vehicle;
import nrg.inc.bykerz.vehicles.domain.model.queries.GetVehicleByIdQuery;
import nrg.inc.bykerz.vehicles.domain.model.queries.GetVehicleByPlateQuery;
import nrg.inc.bykerz.vehicles.domain.model.queries.GetVehiclesByOwnerIdQuery;
import nrg.inc.bykerz.vehicles.domain.model.valueobjects.Plate;
import nrg.inc.bykerz.vehicles.domain.services.VehiclesQueryService;
import nrg.inc.bykerz.vehicles.interfaces.acl.VehiclesContextFacade;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehiclesContextFacadeImpl implements VehiclesContextFacade {
    private final VehiclesQueryService vehiclesQueryService;

    public VehiclesContextFacadeImpl(VehiclesQueryService vehiclesQueryService) {
        this.vehiclesQueryService = vehiclesQueryService;
    }

    @Override
    public Optional<Vehicle> fetchVehicleById(Long vehicleId) {
        if (vehicleId == null || vehicleId <= 0) {
            return Optional.empty();
        }
        return this.vehiclesQueryService.handle(new GetVehicleByIdQuery(vehicleId));
    }

    @Override
    public Optional<Vehicle> fetchVehicleByPlate(String plate) {
        if (!Plate.isValid(plate)) {
            return Optional.empty();
        }
        return this.vehiclesQueryService.handle(new GetVehicleByPlateQuery(plate));
    }

    @Override
    public List<Vehicle> fetchVehiclesByOwnerId(Long ownerId) {
        if (ownerId == null || ownerId <= 0) {
            return List.of();
        }
        return this.vehiclesQueryService.handle(new GetVehiclesByOwnerIdQuery(ownerId));
    }


}
