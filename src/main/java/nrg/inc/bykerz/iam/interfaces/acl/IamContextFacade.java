package nrg.inc.bykerz.iam.interfaces.acl;

import nrg.inc.bykerz.iam.domain.model.aggregates.User;

import java.util.Optional;

public interface IamContextFacade {
    Optional<User> fetchUserById(Long userId);

    Optional<User> fetchUserByUsername(String username);
}
