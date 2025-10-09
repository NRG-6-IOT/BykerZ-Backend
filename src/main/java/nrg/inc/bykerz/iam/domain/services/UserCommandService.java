package nrg.inc.bykerz.iam.domain.services;

import nrg.inc.bykerz.iam.domain.model.aggregates.User;
import nrg.inc.bykerz.iam.domain.model.commands.*;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.Optional;

public interface UserCommandService {
    //Optional<User> handle(CreateUserCommand createUserCommand);

    void handle(DeleteUserCommand deleteUserCommand);

    Optional<User> handle(UpdateUserCommand updateUserCommand , Long userId);

    Optional<ImmutablePair<User,String>> handle(SignInCommand signInCommand);

    Optional<User> handle(SignUpCommand signUpCommand);

}
