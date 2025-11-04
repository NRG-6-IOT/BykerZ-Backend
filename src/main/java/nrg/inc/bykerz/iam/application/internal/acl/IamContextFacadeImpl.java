package nrg.inc.bykerz.iam.application.internal.acl;

import nrg.inc.bykerz.iam.domain.model.aggregates.User;
import nrg.inc.bykerz.iam.domain.model.queries.GetUserByIdQuery;
import nrg.inc.bykerz.iam.domain.services.UserQueryService;
import nrg.inc.bykerz.iam.interfaces.acl.IamContextFacade;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class IamContextFacadeImpl implements IamContextFacade {

    private final UserQueryService userQueryService;

    public IamContextFacadeImpl(UserQueryService userQueryService) {
        this.userQueryService = userQueryService;
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
