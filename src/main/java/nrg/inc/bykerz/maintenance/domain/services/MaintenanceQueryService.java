package nrg.inc.bykerz.maintenance.domain.services;

import nrg.inc.bykerz.maintenance.domain.model.agreggates.Maintenance;
import nrg.inc.bykerz.maintenance.domain.model.queries.GetAllMaintenancesByUserIdQuery;

import java.util.List;

public interface MaintenanceQueryService {
    List<Maintenance> handle(GetAllMaintenancesByUserIdQuery query);
}
