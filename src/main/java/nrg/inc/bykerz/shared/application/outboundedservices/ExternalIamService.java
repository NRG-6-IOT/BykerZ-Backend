package nrg.inc.bykerz.shared.application.outboundedservices;

import nrg.inc.bykerz.iam.domain.model.aggregates.User;
import nrg.inc.bykerz.iam.interfaces.acl.IamContextFacade;
import org.springframework.stereotype.Service;

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
