package nrg.inc.bykerz.iam.application.acl;

import nrg.inc.bykerz.iam.domain.model.aggregates.User;
import nrg.inc.bykerz.iam.domain.model.commands.CreateUserCommand;
import nrg.inc.bykerz.iam.domain.model.entities.Role;
import nrg.inc.bykerz.iam.domain.model.queries.GetUserByIdQuery;
import nrg.inc.bykerz.iam.domain.model.valueobjects.Roles;
import nrg.inc.bykerz.iam.domain.services.UserCommandService;
import nrg.inc.bykerz.iam.domain.services.UserQueryService;
import nrg.inc.bykerz.iam.interfaces.acl.IamContextFacade;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class IamContextFacadeImpl implements IamContextFacade {

    private final UserQueryService userQueryService;
    private final UserCommandService userCommandService;

    public IamContextFacadeImpl(UserQueryService userQueryService, UserCommandService userCommandService) {
        this.userQueryService = userQueryService;
        this.userCommandService = userCommandService;
    }

    @Override
    public Optional<User> fetchUserById(Long userId) {
        if( userId == null || userId <= 0) {
            return Optional.empty();
        }
        var getUserByIdQuery = new GetUserByIdQuery(userId);
        var result = userQueryService.handle(getUserByIdQuery);
        if (result.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(result.get());
    }
}
