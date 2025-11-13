package nrg.inc.bykerz.profiles.domain.services;

import nrg.inc.bykerz.profiles.domain.model.aggregates.Profile;
import nrg.inc.bykerz.profiles.domain.model.queries.GetProfileByEmailQuery;
import nrg.inc.bykerz.profiles.domain.model.queries.GetProfileByIdQuery;
import nrg.inc.bykerz.profiles.domain.model.queries.GetProfileByUserId;
import nrg.inc.bykerz.profiles.domain.model.queries.GetProfileByUsernameQuery;

import java.util.Optional;

public interface ProfileQueryService {
    Optional<Profile> handle(GetProfileByIdQuery query);
    Optional<Profile> handle(GetProfileByEmailQuery query);
    Optional<Profile> handle(GetProfileByUserId query);
    Optional<Profile> handle(GetProfileByUsernameQuery query);
}
