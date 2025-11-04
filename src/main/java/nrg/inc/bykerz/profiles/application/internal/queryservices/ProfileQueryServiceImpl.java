package nrg.inc.bykerz.profiles.application.internal.queryservices;

import nrg.inc.bykerz.profiles.domain.model.aggregates.Profile;
import nrg.inc.bykerz.profiles.domain.model.queries.GetProfileByEmailQuery;
import nrg.inc.bykerz.profiles.domain.model.queries.GetProfileByIdQuery;
import nrg.inc.bykerz.profiles.domain.model.queries.GetProfileByUserId;
import nrg.inc.bykerz.profiles.domain.services.ProfileQueryService;
import nrg.inc.bykerz.profiles.infrastructure.persistence.jpa.repositories.ProfileRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfileQueryServiceImpl implements ProfileQueryService {
    private final ProfileRepository profileRepository;

    public ProfileQueryServiceImpl(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Override
    public Optional<Profile> handle(GetProfileByIdQuery query) {
        return profileRepository.findById(query.profileId());
    }

    @Override
    public Optional<Profile> handle(GetProfileByEmailQuery query) {
        return profileRepository.findByEmailAddress(query.emailAddress());
    }

    @Override
    public Optional<Profile> handle(GetProfileByUserId query) {
        return profileRepository.findByUserId(query.userId());
    }
}
