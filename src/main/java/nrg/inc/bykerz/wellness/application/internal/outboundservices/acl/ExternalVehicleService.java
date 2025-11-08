package nrg.inc.bykerz.wellness.application.internal.outboundservices.acl;

import nrg.inc.bykerz.vehicles.domain.model.entities.Vehicle;
import nrg.inc.bykerz.vehicles.interfaces.acl.VehiclesContextFacade;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExternalVehicleService {
    private final VehiclesContextFacade vehiclesContextFacade;

    public ExternalVehicleService(VehiclesContextFacade vehiclesContextFacade) {
        this.vehiclesContextFacade = vehiclesContextFacade;
    }

    public Optional<Vehicle> fetchVehicleById(Long vehicleId){
        try{
            return vehiclesContextFacade.fetchVehicleById(vehicleId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
