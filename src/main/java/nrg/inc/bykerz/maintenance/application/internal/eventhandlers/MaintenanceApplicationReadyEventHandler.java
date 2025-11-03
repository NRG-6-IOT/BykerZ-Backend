package nrg.inc.bykerz.maintenance.application.internal.eventhandlers;

import nrg.inc.bykerz.maintenance.domain.model.commands.SeedExpenseTypesCommand;
import nrg.inc.bykerz.maintenance.domain.model.commands.SeedItemTypesCommand;
import nrg.inc.bykerz.maintenance.domain.model.commands.SeedMaintenanceStatesCommand;
import nrg.inc.bykerz.maintenance.domain.services.ExpenseTypeCommandService;
import nrg.inc.bykerz.maintenance.domain.services.ItemTypeCommandService;
import nrg.inc.bykerz.maintenance.domain.services.MaintenanceStateCommandService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class MaintenanceApplicationReadyEventHandler {

    private final ItemTypeCommandService itemTypeCommandService;
    private final ExpenseTypeCommandService expenseTypeCommandService;
    private final MaintenanceStateCommandService maintenanceStateCommandService;
    private static final Logger LOGGER= LoggerFactory.getLogger(MaintenanceApplicationReadyEventHandler.class);

    public MaintenanceApplicationReadyEventHandler(
            ItemTypeCommandService itemTypeCommandService,
            ExpenseTypeCommandService expenseTypeCommandService,
            MaintenanceStateCommandService maintenanceStateCommandService) {
        this.itemTypeCommandService = itemTypeCommandService;
        this.expenseTypeCommandService = expenseTypeCommandService;
        this.maintenanceStateCommandService = maintenanceStateCommandService;
    }

    @EventListener
    public void onApplicationReady(ApplicationReadyEvent event) {
        var applicationName= event.getApplicationContext().getId();

        LOGGER.info("Starting to verify if item types seeding is needed for {} at {}", applicationName,currentTimestamp());

        var seedItemTypesCommand = new SeedItemTypesCommand();
        itemTypeCommandService.handle(seedItemTypesCommand);

        LOGGER.info("Item types seeded successfully for application: {} at {}", applicationName, currentTimestamp());

        LOGGER.info("Starting to verify if expense types seeding is needed for {} at {}", applicationName,currentTimestamp());

        var seedExpenseTypesCommand = new SeedExpenseTypesCommand();
        expenseTypeCommandService.handle(seedExpenseTypesCommand);

        LOGGER.info("Expense types seeded successfully for application: {} at {}", applicationName, currentTimestamp());

        var seedMaintenanceStatesCommand = new SeedMaintenanceStatesCommand();
        maintenanceStateCommandService.handle(seedMaintenanceStatesCommand);

        LOGGER.info("Maintenance states seeded successfully for application: {} at {}", applicationName, currentTimestamp());
    }

    private Timestamp currentTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }

}
