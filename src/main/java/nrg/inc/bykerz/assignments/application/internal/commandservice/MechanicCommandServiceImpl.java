package nrg.inc.bykerz.assignments.application.internal.commandservice;

import nrg.inc.bykerz.profiles.infrastructure.persistence.jpa.repositories.ProfileRepository;
import nrg.inc.bykerz.assignments.domain.model.aggregates.Mechanic;
import nrg.inc.bykerz.assignments.domain.model.commands.CreateMechanicCommand;
import nrg.inc.bykerz.assignments.domain.model.valueobjects.MechanicCode;
import nrg.inc.bykerz.assignments.domain.services.MechanicCommandService;
import nrg.inc.bykerz.assignments.infrastructure.persistence.jpa.repositories.MechanicRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MechanicCommandServiceImpl implements MechanicCommandService {

    private final MechanicRepository mechanicRepository;
    private final ProfileRepository profileRepository;

    public MechanicCommandServiceImpl(MechanicRepository mechanicRepository, ProfileRepository profileRepository) {
        this.mechanicRepository = mechanicRepository;
        this.profileRepository = profileRepository;
    }

    @Override
    public Optional<Mechanic> handle(CreateMechanicCommand command) {
        var profileOpt = profileRepository.findById(command.profileId());
        if (profileOpt.isEmpty()) {
            return Optional.empty();
        }
        var profile = profileOpt.get();
        var mechanic = new Mechanic(profile, MechanicCode.random());
        while (mechanicRepository.existsByCode(mechanic.getCode())){
            mechanic.setCode(MechanicCode.random());
        }
        var savedMechanic = mechanicRepository.save(mechanic);
        return Optional.of(savedMechanic);
    }
}
