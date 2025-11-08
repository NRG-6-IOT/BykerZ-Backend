package nrg.inc.bykerz.vehicles.application.internal.acl;

import nrg.inc.bykerz.vehicles.domain.model.aggregates.Owner;
import nrg.inc.bykerz.vehicles.domain.model.entities.Vehicle;
import nrg.inc.bykerz.vehicles.domain.model.queries.GetOwnerByIdQuery;
import nrg.inc.bykerz.vehicles.domain.model.queries.GetVehicleByIdQuery;
import nrg.inc.bykerz.vehicles.domain.model.queries.GetVehicleByPlateQuery;
import nrg.inc.bykerz.vehicles.domain.model.queries.GetVehiclesByOwnerIdQuery;
import nrg.inc.bykerz.vehicles.domain.model.valueobjects.Plate;
import nrg.inc.bykerz.vehicles.domain.services.OwnerQueryService;
import nrg.inc.bykerz.vehicles.domain.services.VehiclesQueryService;
import nrg.inc.bykerz.vehicles.interfaces.acl.VehiclesContextFacade;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehiclesContextFacadeImpl implements VehiclesContextFacade {
    private final OwnerQueryService ownerQueryService;
    private final VehiclesQueryService vehiclesQueryService;

    public VehiclesContextFacadeImpl(OwnerQueryService ownerQueryService, VehiclesQueryService vehiclesQueryService) {
        this.ownerQueryService = ownerQueryService;
        this.vehiclesQueryService = vehiclesQueryService;
    }


    @Override
    public Optional<Owner> fetchOwnerById(Long ownerId) {
        return ownerQueryService.handle(new GetOwnerByIdQuery(ownerId));
    }

    @Override
    public List<Vehicle> fetchVehiclesByOwnerId(Long ownerId) {
        return vehiclesQueryService.handle(new GetVehiclesByOwnerIdQuery(ownerId));
    }

    @Override
    public Optional<Vehicle> fetchVehicleById(Long vehicleId) {
        return vehiclesQueryService.handle(new GetVehicleByIdQuery(vehicleId));
    }
}
