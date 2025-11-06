package nrg.inc.bykerz.maintenance.domain.model.commands;

import java.util.Date;

public record CreateMaintenanceCommand(
        String details,
        Long vehicleId,
        Date dateOfService,
        String location,
        String description,
        Long mechanicId
) {
}
