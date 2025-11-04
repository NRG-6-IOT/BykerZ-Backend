package nrg.inc.bykerz.vehicles.application.internal.acl;

import nrg.inc.bykerz.vehicles.domain.model.aggregates.Vehicle;
import nrg.inc.bykerz.vehicles.domain.model.queries.GetVehicleByIdQuery;
import nrg.inc.bykerz.vehicles.domain.services.VehiclesQueryService;
import nrg.inc.bykerz.vehicles.interfaces.acl.VehiclesContextFacade;
import org.springframework.stereotype.Service;

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
}
