package nrg.inc.bykerz.profiles.application.internal.acl;

import nrg.inc.bykerz.profiles.domain.model.commands.CreateProfileCommand;
import nrg.inc.bykerz.profiles.domain.services.ProfileCommandService;
import nrg.inc.bykerz.profiles.interfaces.acl.ProfileContextFacade;
import org.springframework.stereotype.Service;

@Service
public class ProfileContextFacadeImpl implements ProfileContextFacade {
    private final ProfileCommandService profileCommandService;

    public ProfileContextFacadeImpl(ProfileCommandService profileCommandService) {
        this.profileCommandService = profileCommandService;
    }

    @Override
    public Long createProfile(String firstName, String lastName, String email, Long userId) {
        var createProfileCommand = new CreateProfileCommand(email, firstName, lastName, userId);

        var result = profileCommandService.handle(createProfileCommand);
        if (result.isEmpty()) return 0L;
        return result.get().getId();
    }
}
