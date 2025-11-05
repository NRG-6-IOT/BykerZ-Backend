package nrg.inc.bykerz.shared.application.commandservices;

import nrg.inc.bykerz.profiles.infrastructure.persistence.jpa.repositories.ProfileRepository;
import nrg.inc.bykerz.shared.domain.model.aggregates.Owner;
import nrg.inc.bykerz.shared.domain.model.commands.CreateOwnerCommand;
import nrg.inc.bykerz.shared.domain.services.OwnerCommandService;
import nrg.inc.bykerz.shared.infrastructure.persistence.jpa.repositories.OwnerRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OwnerCommandServiceImpl implements OwnerCommandService {

    private final OwnerRepository ownerRepository;
    private final ProfileRepository profileRepository;

    public OwnerCommandServiceImpl(OwnerRepository ownerRepository, ProfileRepository profileRepository) {
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
        var owner = new Owner(profile);
        var savedOwner = ownerRepository.save(owner);
        return Optional.of(savedOwner);
    }
}
