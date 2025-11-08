package nrg.inc.bykerz.assignments.application.external;

import nrg.inc.bykerz.vehicles.domain.model.aggregates.Owner;
import nrg.inc.bykerz.vehicles.interfaces.acl.VehiclesContextFacade;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExternalVehiclesService {
    private final VehiclesContextFacade  vehiclesContextFacade;
    public ExternalVehiclesService(VehiclesContextFacade vehiclesContextFacade) {
        this.vehiclesContextFacade = vehiclesContextFacade;
    }

    public Optional<Owner> getOwnerById(Long ownerId) {
        var owner = this.vehiclesContextFacade.fetchOwnerById(ownerId);
        if (owner.isEmpty()) {
            throw new IllegalArgumentException("Owner not found for ownerId: " + ownerId);
        }
        return owner;
    }
}
