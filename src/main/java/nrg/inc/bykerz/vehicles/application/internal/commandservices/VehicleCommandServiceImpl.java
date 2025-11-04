package nrg.inc.bykerz.vehicles.application.internal.commandservices;

import nrg.inc.bykerz.vehicles.domain.model.aggregates.Vehicle;
import nrg.inc.bykerz.vehicles.domain.model.commands.CreateVehicleCommand;
import nrg.inc.bykerz.vehicles.domain.model.commands.DeleteVehicleCommand;
import nrg.inc.bykerz.vehicles.domain.model.commands.UpdateVehicleCommand;
import nrg.inc.bykerz.vehicles.domain.model.valueobjects.Plate;
import nrg.inc.bykerz.vehicles.domain.services.VehicleCommandService;
import nrg.inc.bykerz.vehicles.infrastructure.persistence.jpa.repositories.VehicleRepository;
import nrg.inc.bykerz.vehicles.infrastructure.persistence.jpa.repositories.ModelRepository;
import org.springframework.stereotype.Service;

import java.util.Objects;
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
        if (vehicleRepository.existsByPlate(new Plate(command.plate()))) throw new IllegalArgumentException("Another vehicle already exists with plate: " + command.plate());

        try {
            var vehicle = new Vehicle(command.ownerId(), model.get(), command.year(), command.plate());
            vehicleRepository.save(vehicle);
            return Optional.of(vehicle);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error creating vehicle: " + e.getMessage());
        }
    }

    @Override
    public Optional<Vehicle> handle(UpdateVehicleCommand command) {
        var vehicle = vehicleRepository.findById(command.id());
        if (vehicle.isEmpty()) throw new IllegalArgumentException("Cannot find vehicle with id: " + command.id());
        if (Objects.equals(vehicle.get().getPlate(), command.plate()) && vehicleRepository.existsByPlate(new Plate(command.plate()))) throw new IllegalArgumentException("Another vehicle already exists with plate: " + command.plate());
        var updatedVehicle = vehicle.get().UpdateVehicle(command);

        try {
            vehicleRepository.save(updatedVehicle);
            return Optional.of(updatedVehicle);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error updating vehicle: " + e.getMessage());
        }
    }

    @Override
    public void handle(DeleteVehicleCommand command) {
        if (vehicleRepository.existsById(command.id())) {
            vehicleRepository.deleteById(command.id());
        } else  {
            throw new IllegalArgumentException("Cannot find vehicle with id: " + command.id());
        }
    }
}