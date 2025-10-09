package nrg.inc.bykerz.iam.domain.services;

import nrg.inc.bykerz.iam.domain.model.aggregates.User;
import nrg.inc.bykerz.iam.domain.model.queries.GetAllUsersQuery;
import nrg.inc.bykerz.iam.domain.model.queries.GetUserByIdQuery;
import nrg.inc.bykerz.iam.domain.model.queries.GetUserByUsernameQuery;

import java.util.List;
import java.util.Optional;

public interface UserQueryService {
    List<User> handle(GetAllUsersQuery getAllUsersQuery);

    Optional<User> handle(GetUserByIdQuery getUserByIdQuery);

    Optional<User> handle(GetUserByUsernameQuery getUserByUserNameQuery);

}
