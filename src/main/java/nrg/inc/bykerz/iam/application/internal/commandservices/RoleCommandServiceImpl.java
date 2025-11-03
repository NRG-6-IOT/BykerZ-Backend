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

    /**
     * Role Command Service Implementation
     * <p>
     *     This class implements the {@link RoleCommandService} interface to handle role-related commands such as {@link SeedRolesCommand}.
     * </p>
     * @param roleRepository the role repository
     */
    public RoleCommandServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    /**
     * Handle seed roles command
     * <p>
     *     This method handles the {@link SeedRolesCommand} command to seed the predefined roles into the system.
     * </p>
     * @param seedRolesCommand the command to seed roles
     */
    @Override
    public void handle(SeedRolesCommand seedRolesCommand) {
        Arrays.stream(Roles.values()).forEach(role -> {
            if (!roleRepository.existsByName(role)) {
                roleRepository.save(new Role(Roles.valueOf(role.name())));
            }
        });
    }
}
