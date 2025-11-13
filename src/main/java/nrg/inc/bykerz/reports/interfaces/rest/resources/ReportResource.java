package nrg.inc.bykerz.reports.interfaces.rest.resources;

import nrg.inc.bykerz.maintenance.interfaces.rest.resources.MaintenanceResource;
import nrg.inc.bykerz.assignments.interfaces.rest.resources.AssignmentResource;
import nrg.inc.bykerz.vehicles.interfaces.rest.resources.VehicleResource;

import java.util.List;

public record ReportResource(
        VehicleResource vehicle,
        List<MaintenanceResource> maintenances,
        AssignmentResource assignment
) {
}

