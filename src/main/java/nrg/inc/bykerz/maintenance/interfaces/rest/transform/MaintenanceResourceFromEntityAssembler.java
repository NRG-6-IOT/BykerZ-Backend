package nrg.inc.bykerz.maintenance.interfaces.rest.transform;

import nrg.inc.bykerz.maintenance.domain.model.agreggates.Maintenance;
import nrg.inc.bykerz.maintenance.interfaces.rest.resources.MaintenanceResource;

public class MaintenanceResourceFromEntityAssembler {
    public static MaintenanceResource toResourceFromEntity(Maintenance entity){
        return new MaintenanceResource(
                entity.getId(),
                entity.getDetails(),
                entity.getVehicle().getId(),
                entity.getDateOfService().toString(),
                entity.getLocation(),
                entity.getDescription(),
                entity.getState().toString(),
                entity.getMaintenanceExpense() != null ?
                        ExpenseResourceFromEntityAssembler.toResourceFromEntity(entity.getMaintenanceExpense())
                        : null
        );
    }
}
