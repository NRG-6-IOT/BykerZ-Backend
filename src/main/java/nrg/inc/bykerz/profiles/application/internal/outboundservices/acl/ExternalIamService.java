package nrg.inc.bykerz.profiles.application.internal.outboundservices.acl;

import nrg.inc.bykerz.iam.domain.model.aggregates.User;
import nrg.inc.bykerz.iam.interfaces.acl.IamContextFacade;
import nrg.inc.bykerz.profiles.domain.model.valueobjects.UserId;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExternalIamService {
    private final IamContextFacade iamContextFacade;

    public ExternalIamService(IamContextFacade iamContextFacade) {
        this.iamContextFacade = iamContextFacade;
    }

    public Optional<User> getUserById(Long id) {
        var userOpt = iamContextFacade.fetchUserById(id);
        if (userOpt.isEmpty()) {
            return Optional.empty();
        }
        return  Optional.of(userOpt.get());
    }
}
