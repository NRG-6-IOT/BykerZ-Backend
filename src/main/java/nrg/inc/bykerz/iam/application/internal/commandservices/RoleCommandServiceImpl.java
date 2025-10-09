package nrg.inc.bykerz.iam.application.internal.commandservices;

import nrg.inc.bykerz.iam.domain.model.commands.SeedRolesCommand;
import nrg.inc.bykerz.iam.domain.model.entities.Role;
import nrg.inc.bykerz.iam.domain.model.valueobjects.Roles;
import nrg.inc.bykerz.iam.domain.services.RoleCommandService;
import nrg.inc.bykerz.iam.infrastructure.persistence.jpa.repositories.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class RoleCommandServiceImpl implements RoleCommandService {
    private final RoleRepository roleRepository;

    public RoleCommandServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }


    @Override
    public void handle(SeedRolesCommand seedRolesCommand) {
        Arrays.stream(Roles.values()).forEach(role -> {
            if (!roleRepository.existsByName(role)) {
                roleRepository.save(new Role(Roles.valueOf(role.name())));
            }
        });
    }
}
