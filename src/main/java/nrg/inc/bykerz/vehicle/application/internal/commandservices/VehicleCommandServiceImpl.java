package nrg.inc.bykerz.vehicle.application.internal.commandservices;

import nrg.inc.bykerz.vehicle.domain.model.aggregates.Vehicle;
import nrg.inc.bykerz.vehicle.domain.model.commands.CreateVehicleCommand;
import nrg.inc.bykerz.vehicle.domain.model.entities.Model;
import nrg.inc.bykerz.vehicle.domain.services.VehicleCommandService;
import nrg.inc.bykerz.vehicle.infrastructure.persistence.jpa.repositories.VehicleRepository;
import nrg.inc.bykerz.vehicle.infrastructure.persistence.jpa.repositories.ModelRepository;
import org.springframework.stereotype.Service;

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
    public Optional<Vehicle> handle(CreateVehicleCommand createVehicleCommand) {
        // Buscar el Model existente
        Model model = modelRepository.findById(createVehicleCommand.modelId())
                .orElseThrow(() -> new IllegalArgumentException("Model not found with id: " + createVehicleCommand.modelId()));

        // Crear Vehicle usando el model existente
        Vehicle vehicle = new Vehicle(createVehicleCommand, model);

        // Guardar Vehicle
        try {
            vehicleRepository.save(vehicle);
            return Optional.of(vehicle);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error creating vehicle: " + e.getMessage(), e);
        }
    }
}