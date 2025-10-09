package nrg.inc.bykerz.iam.application.internal.queryservices;

import nrg.inc.bykerz.iam.domain.model.aggregates.User;
import nrg.inc.bykerz.iam.domain.model.queries.*;
import nrg.inc.bykerz.iam.domain.services.UserQueryService;
import nrg.inc.bykerz.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserQueryServiceImpl implements UserQueryService {

    private final UserRepository userRepository;

    public UserQueryServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> handle(GetUserByIdQuery getUserByIdQuery) {
        return userRepository.findById(getUserByIdQuery.userId());
    }

    @Override
    public Optional<User> handle(GetUserByUsernameQuery getUserByUsernameQuery) {
        return userRepository.findByUsername(getUserByUsernameQuery.username());
    }

    @Override
    public List<User> handle(GetAllUsersQuery getAllUsersQuery) {
        return userRepository.findAll();
    }



}
