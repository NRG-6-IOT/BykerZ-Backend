package nrg.inc.bykerz.iam.application.internal.queryservices;

import nrg.inc.bykerz.iam.domain.model.entities.Role;
import nrg.inc.bykerz.iam.domain.model.queries.GetAllRolesQuery;
import nrg.inc.bykerz.iam.domain.services.RoleQueryService;
import nrg.inc.bykerz.iam.infrastructure.persistence.jpa.repositories.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleQueryServiceImpl implements RoleQueryService {

    private final RoleRepository roleRepository;

    public RoleQueryServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> handle(GetAllRolesQuery getAllRolesQuery) {
        return roleRepository.findAll();
    }

}
