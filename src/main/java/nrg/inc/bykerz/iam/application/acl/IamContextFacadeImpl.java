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
    public Long createUser(String username, String password, List<String> roles) {
        List<String> roleNames = roles == null ? Collections.emptyList() : roles;

        List<Role> roleEntities = roleNames.stream()
                .filter(n -> n != null && !n.isBlank())
                .map(n -> {
                    try {
                        return new Role(Roles.valueOf(n.trim().toUpperCase()));
                    } catch (IllegalArgumentException ex) {
                        return Role.getDefaultRole();
                    }
                })
                .collect(Collectors.toList());

        roleEntities = Role.validateRoleSet(roleEntities);

        var createUserCommand = new CreateUserCommand(username, password, roleEntities);
        var user = userCommandService.handle(createUserCommand);
        return user.isEmpty() ? Long.valueOf(0L) : user.get().getId();
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
