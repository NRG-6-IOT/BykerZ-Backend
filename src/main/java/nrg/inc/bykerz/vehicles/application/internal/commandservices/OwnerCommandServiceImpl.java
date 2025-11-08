package nrg.inc.bykerz.vehicles.application.internal.commandservices;

import nrg.inc.bykerz.profiles.infrastructure.persistence.jpa.repositories.ProfileRepository;
import nrg.inc.bykerz.vehicles.domain.model.aggregates.Owner;
import nrg.inc.bykerz.vehicles.domain.model.commands.AddVehicleToOwnerCommand;
import nrg.inc.bykerz.vehicles.domain.model.commands.CreateOwnerCommand;
import nrg.inc.bykerz.vehicles.domain.model.commands.DeleteVehicleFromOwnerCommand;
import nrg.inc.bykerz.vehicles.domain.model.commands.UpdateVehicleFromOwnerCommand;
import nrg.inc.bykerz.vehicles.domain.model.entities.Vehicle;
import nrg.inc.bykerz.vehicles.domain.model.valueobjects.Plate;
import nrg.inc.bykerz.vehicles.domain.services.OwnerCommandService;
import nrg.inc.bykerz.vehicles.infrastructure.persistence.jpa.repositories.ModelRepository;
import nrg.inc.bykerz.vehicles.infrastructure.persistence.jpa.repositories.OwnerRepository;
import nrg.inc.bykerz.vehicles.infrastructure.persistence.jpa.repositories.VehicleReadRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OwnerCommandServiceImpl implements OwnerCommandService {

    private final OwnerRepository ownerRepository;
    private final ProfileRepository profileRepository;
    private final VehicleReadRepository vehicleReadRepository;
    private final ModelRepository modelRepository;

    public OwnerCommandServiceImpl(
            OwnerRepository ownerRepository,
            ProfileRepository profileRepository,
            VehicleReadRepository vehicleReadRepository,
            ModelRepository modelRepository
    ) {
        this.modelRepository = modelRepository;
        this.vehicleReadRepository = vehicleReadRepository;
        this.ownerRepository = ownerRepository;
        this.profileRepository = profileRepository;
    }

    @Override
    public Optional<Owner> handle(CreateOwnerCommand command) {
        var profileOpt = profileRepository.findById(command.profileId());
        if (profileOpt.isEmpty()) {
            return Optional.empty();
        }
        var profile = profileOpt.get();
        var owner = new Owner(profile.getId());
        var savedOwner = ownerRepository.save(owner);
        return Optional.of(savedOwner);
    }

    @Override
    public Optional<Vehicle> handle(AddVehicleToOwnerCommand command) {
        var plateExists = vehicleReadRepository.existsByPlate(new Plate(command.plate()));
        if (plateExists) {
            throw new IllegalArgumentException("Vehicle with given plate already exists.");
        }

        var model = modelRepository.findById(command.modelId());
        if (model.isEmpty()) {
            throw new IllegalArgumentException("Model with given ID does not exist.");
        }

        var owner = ownerRepository.findById(command.ownerId());
        if (owner.isEmpty()) {
            throw new IllegalArgumentException("Owner with given ID does not exist.");
        }

        var ownerEntity = owner.get();
        var vehicle = ownerEntity.AddVehicle(command.modelId(), command.year(), command.plate());

        try {
            ownerRepository.save(ownerEntity);
            return Optional.of(vehicle);
        } catch (Exception e) {
            throw new RuntimeException("Could not save vehicle to owner: " + e.getMessage(), e);
        }
    }

    @Override
    public Optional<Vehicle> handle(UpdateVehicleFromOwnerCommand command) {
        var plateExists = vehicleReadRepository.existsByPlate(new Plate(command.plate()));
        if (plateExists) {
            throw new IllegalArgumentException("Vehicle with given plate already exists.");
        }

        var owner = ownerRepository.findById(command.ownerId());
        if (owner.isEmpty()) {
            throw new IllegalArgumentException("Owner with given ID does not exist.");
        }

        var ownerEntity = owner.get();
        var vehicle = ownerEntity.UpdateVehicle(command);

        try {
            ownerRepository.save(ownerEntity);
            return Optional.of(vehicle);
        } catch (Exception e) {
            throw new RuntimeException("Could not update vehicle to owner: " + e.getMessage(), e);
        }
    }

    @Override
    public void handle(DeleteVehicleFromOwnerCommand command) {
        var owner = ownerRepository.findById(command.ownerId());
        if (owner.isEmpty()) {
            throw new IllegalArgumentException("Owner with given ID does not exist.");
        }

        var ownerEntity = owner.get();
        ownerEntity.DeleteVehicle(command.vehicleId());

        try {
            ownerRepository.save(ownerEntity);
        } catch (Exception e) {
            throw new RuntimeException("Could not delete vehicle from owner: " + e.getMessage(), e);
        }
    }
}
