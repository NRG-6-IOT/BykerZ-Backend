package nrg.inc.bykerz.profiles.application.internal.commandservices;

import nrg.inc.bykerz.profiles.application.internal.outboundservices.acl.ExternalIamService;
import nrg.inc.bykerz.profiles.domain.model.aggregates.Profile;
import nrg.inc.bykerz.profiles.domain.model.commands.CreateProfileCommand;
import nrg.inc.bykerz.profiles.domain.model.valueobjects.EmailAddress;
import nrg.inc.bykerz.profiles.domain.services.ProfileCommandService;
import nrg.inc.bykerz.profiles.infrastructure.persistence.jpa.repositories.ProfileRepository;
import nrg.inc.bykerz.assignments.domain.model.commands.CreateMechanicCommand;
import nrg.inc.bykerz.shared.application.internal.outboundservices.acl.ExternalVehiclesService;
import nrg.inc.bykerz.vehicles.domain.model.commands.CreateOwnerCommand;
import nrg.inc.bykerz.assignments.domain.services.MechanicCommandService;
import nrg.inc.bykerz.vehicles.domain.services.OwnerCommandService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfileCommandServiceImpl implements ProfileCommandService {
    private final ProfileRepository profileRepository;
    private final ExternalIamService externalIamService;
    private final MechanicCommandService mechanicCommandService;
    private final ExternalVehiclesService externalVehicleService;
    public ProfileCommandServiceImpl(
            ProfileRepository profileRepository,
            ExternalIamService externalIamService,
            MechanicCommandService mechanicCommandService,
            ExternalVehiclesService externalVehicleService) {
        this.profileRepository = profileRepository;
        this.externalIamService = externalIamService;
        this.mechanicCommandService = mechanicCommandService;
        this.externalVehicleService = externalVehicleService;
    }

    @Override
    public Optional<Profile> handle(CreateProfileCommand command) {

        var user = this.externalIamService.getUserById(command.userId());
        if (user.isEmpty()) {
            throw new IllegalArgumentException("User with given ID does not exist.");
        }

        var emailAddress = new EmailAddress(command.email());
        if (profileRepository.existsByEmailAddress(emailAddress)) {
            throw new IllegalArgumentException("Profile with email address already exists.");
        }

        var profile = new Profile(command);
        profileRepository.save(profile);

        if(user.get().getUserRoles().stream().anyMatch(role -> role.getName().name().equals("ROLE_MECHANIC"))) {
            var mechanic = mechanicCommandService.handle(new CreateMechanicCommand(profile.getId()));
            if (mechanic.isEmpty()) {
                throw new IllegalArgumentException("Could not create mechanic for profile.");
            }
        }

        if(user.get().getUserRoles().stream().anyMatch(role -> role.getName().name().equals("ROLE_OWNER"))) {
            var owner = externalVehicleService.createOwner(profile.getId());
            if (owner == 0L) {
                // Revert profile creation
                profileRepository.deleteById(profile.getId());
                throw new IllegalArgumentException("Could not create owner for profile.");
            }
        }

        return Optional.of(profile);
    }
}
