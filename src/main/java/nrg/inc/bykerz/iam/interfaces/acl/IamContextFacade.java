package nrg.inc.bykerz.iam.interfaces.acl;

import nrg.inc.bykerz.iam.domain.model.aggregates.User;

import java.util.List;
import java.util.Optional;

public interface IamContextFacade {

    Long createUser(String username, String password, List<String> roles);

    Optional<User> fetchUserById(Long userId);
}
