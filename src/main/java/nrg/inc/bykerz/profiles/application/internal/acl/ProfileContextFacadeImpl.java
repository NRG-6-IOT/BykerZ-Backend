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
    public Long createProfile(String firstName, String lastName, String email, String photoUrl, Long userId) {
        var createProfileCommand = new CreateProfileCommand(email, firstName, lastName, photoUrl, userId);
        var profile = profileCommandService.handle(createProfileCommand);
        return profile.isEmpty() ? Long.valueOf(0L) : profile.get().getId();
    }
}
