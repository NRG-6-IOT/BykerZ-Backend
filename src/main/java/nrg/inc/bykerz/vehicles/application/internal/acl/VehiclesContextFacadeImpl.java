package nrg.inc.bykerz.vehicles.application.internal.acl;

import nrg.inc.bykerz.vehicles.domain.model.aggregates.Owner;
import nrg.inc.bykerz.vehicles.domain.model.commands.CreateOwnerCommand;
import nrg.inc.bykerz.vehicles.domain.model.entities.Vehicle;
import nrg.inc.bykerz.vehicles.domain.model.queries.GetOwnerByIdQuery;
import nrg.inc.bykerz.vehicles.domain.model.queries.GetVehicleByIdQuery;
import nrg.inc.bykerz.vehicles.domain.services.OwnerCommandService;
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
    private final OwnerCommandService ownerCommandService;

    public VehiclesContextFacadeImpl(OwnerQueryService ownerQueryService, VehiclesQueryService vehiclesQueryService, OwnerCommandService ownerCommandService) {
        this.ownerQueryService = ownerQueryService;
        this.vehiclesQueryService = vehiclesQueryService;
        this.ownerCommandService = ownerCommandService;
    }


    @Override
    public Optional<Owner> fetchOwnerById(Long ownerId) {
        return ownerQueryService.handle(new GetOwnerByIdQuery(ownerId));
    }

    @Override
    public List<Vehicle> fetchVehiclesByOwnerId(Long ownerId) {
        var ownerOpt = ownerQueryService.handle(new GetOwnerByIdQuery(ownerId));
        if (ownerOpt.isEmpty()) {
            return List.of();
        }
        return ownerOpt.get().GetVehicles();
    }

    @Override
    public Optional<Vehicle> fetchVehicleById(Long vehicleId) {
        return vehiclesQueryService.handle(new GetVehicleByIdQuery(vehicleId));
    }

    @Override
    public Long createOwner(Long profileId) {
        var createOwnerCommand = new CreateOwnerCommand(profileId);
        var owner = ownerCommandService.handle(createOwnerCommand);
        return owner.isEmpty() ? Long.valueOf(0L) : owner.get().getId();
    }
}
