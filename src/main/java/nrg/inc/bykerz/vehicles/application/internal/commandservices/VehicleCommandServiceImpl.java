package nrg.inc.bykerz.vehicles.application.internal.commandservices;

import nrg.inc.bykerz.vehicles.domain.model.aggregates.Vehicle;
import nrg.inc.bykerz.vehicles.domain.model.commands.CreateVehicleCommand;
import nrg.inc.bykerz.vehicles.domain.model.commands.UpdateVehicleCommand;
import nrg.inc.bykerz.vehicles.domain.model.entities.Model;
import nrg.inc.bykerz.vehicles.domain.services.VehicleCommandService;
import nrg.inc.bykerz.vehicles.infrastructure.persistence.jpa.repositories.VehicleRepository;
import nrg.inc.bykerz.vehicles.infrastructure.persistence.jpa.repositories.ModelRepository;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import java.util.Optional;

@Service
public class VehicleCommandServiceImpl implements VehicleCommandService {

    private final VehicleRepository vehicleRepository;
    private final ModelRepository modelRepository;

    public VehicleCommandServiceImpl(VehicleRepository vehicleRepository, ModelRepository modelRepository) {
        this.vehicleRepository = vehicleRepository;
        this.modelRepository = modelRepository;
    }

    @Override
    public Optional<Vehicle> handle(CreateVehicleCommand command) {
        var model = modelRepository.findById(command.modelId());
        if (model.isEmpty()) throw new IllegalArgumentException("Cannot find model with id: " + command.modelId());

        try {
            var vehicle = new Vehicle(command.ownerId(), command.mechanicId(), model.get(), command.year(), command.plate());
            vehicleRepository.save(vehicle);
            return Optional.of(vehicle);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error creating vehicle: " + e.getMessage());
        }
    }

    @Override
    public Optional<Vehicle> handle(UpdateVehicleCommand command) {
        return Optional.empty();
    }
}