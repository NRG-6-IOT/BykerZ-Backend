package nrg.inc.bykerz.profiles.application.internal.acl;

import nrg.inc.bykerz.profiles.domain.model.commands.CreateProfileCommand;
import nrg.inc.bykerz.profiles.domain.model.queries.GetProfileByUserId;
import nrg.inc.bykerz.profiles.domain.model.valueobjects.UserId;
import nrg.inc.bykerz.profiles.domain.services.ProfileCommandService;
import nrg.inc.bykerz.profiles.domain.services.ProfileQueryService;
import nrg.inc.bykerz.profiles.interfaces.acl.ProfileContextFacade;
import org.springframework.stereotype.Service;

@Service
public class ProfileContextFacadeImpl implements ProfileContextFacade {
    private final ProfileCommandService profileCommandService;
    private final ProfileQueryService profileQueryService;

    public ProfileContextFacadeImpl(ProfileCommandService profileCommandService, ProfileQueryService profileQueryService) {
        this.profileCommandService = profileCommandService;
        this.profileQueryService = profileQueryService;
    }

    @Override
    public Long createProfile(String firstName, String lastName, String email, String photoUrl, Long userId) {
        var createProfileCommand = new CreateProfileCommand(firstName, lastName, email, photoUrl, userId);
        var profile = profileCommandService.handle(createProfileCommand);
        return profile.isEmpty() ? Long.valueOf(0L) : profile.get().getId();
    }

    @Override
    public Long getProfileIdByUserId(Long userId) {
        UserId user = new UserId(userId);
        var getProfileByUserId = new GetProfileByUserId(user);

        var profile = profileQueryService.handle(getProfileByUserId);
        return profile.isEmpty() ? Long.valueOf(0L) : profile.get().getId();
    }
}
