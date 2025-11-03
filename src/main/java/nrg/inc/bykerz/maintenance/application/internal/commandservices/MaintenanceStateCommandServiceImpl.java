package nrg.inc.bykerz.maintenance.application.internal.commandservices;

import nrg.inc.bykerz.maintenance.domain.model.commands.SeedMaintenanceStatesCommand;
import nrg.inc.bykerz.maintenance.domain.model.entities.MaintenanceState;
import nrg.inc.bykerz.maintenance.domain.model.valueobjects.MaintenanceStates;
import nrg.inc.bykerz.maintenance.domain.services.MaintenanceStateCommandService;
import nrg.inc.bykerz.maintenance.infrastructure.persistence.jpa.repositories.MaintenanceStateRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class MaintenanceStateCommandServiceImpl implements MaintenanceStateCommandService {

    private final MaintenanceStateRepository maintenanceStateRepository;

    public MaintenanceStateCommandServiceImpl(MaintenanceStateRepository maintenanceStateRepository) {
        this.maintenanceStateRepository = maintenanceStateRepository;
    }

    @Override
    public void handle(SeedMaintenanceStatesCommand command) {
        Arrays.stream(MaintenanceStates.values()).forEach(maintenanceState -> {
            if (!maintenanceStateRepository.existsByName(maintenanceState)) {
                maintenanceStateRepository.save(new MaintenanceState(MaintenanceStates.valueOf(maintenanceState.name())));
            }
        });
    }
}
