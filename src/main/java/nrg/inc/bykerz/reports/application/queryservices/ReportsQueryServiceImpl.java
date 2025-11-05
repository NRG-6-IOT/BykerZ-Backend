package nrg.inc.bykerz.reports.application.queryservices;

import nrg.inc.bykerz.assignments.domain.model.queries.GetAssignmentByVehicleIdQuery;
import nrg.inc.bykerz.assignments.domain.services.AssignmentQueryService;
import nrg.inc.bykerz.assignments.interfaces.rest.resources.AssignmentResource;
import nrg.inc.bykerz.assignments.interfaces.rest.transform.AssignmentResourceFromEntityAssembler;
import nrg.inc.bykerz.maintenance.domain.model.queries.GetAllMaintenancesByVehicleIdQuery;
import nrg.inc.bykerz.maintenance.domain.services.MaintenanceQueryService;
import nrg.inc.bykerz.maintenance.interfaces.rest.resources.MaintenanceResource;
import nrg.inc.bykerz.maintenance.interfaces.rest.transform.MaintenanceResourceFromEntityAssembler;
import nrg.inc.bykerz.shared.domain.model.queries.GetMechanicByIdQuery;
import nrg.inc.bykerz.shared.domain.services.MechanicQueryService;
import nrg.inc.bykerz.vehicles.domain.model.queries.GetVehicleByIdQuery;
import nrg.inc.bykerz.vehicles.domain.services.VehiclesQueryService;
import nrg.inc.bykerz.vehicles.interfaces.rest.resources.VehicleResource;
import nrg.inc.bykerz.vehicles.interfaces.rest.transform.VehicleResourceFromEntityAssembler;
import nrg.inc.bykerz.reports.domain.model.queries.GetReportByVehicleIdQuery;
import nrg.inc.bykerz.reports.domain.services.ReportsQueryService;
import nrg.inc.bykerz.reports.interfaces.rest.resources.ReportResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReportsQueryServiceImpl implements ReportsQueryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReportsQueryServiceImpl.class);

    private final VehiclesQueryService vehiclesQueryService;
    private final MaintenanceQueryService maintenanceQueryService;
    private final AssignmentQueryService assignmentQueryService;
    private final MechanicQueryService mechanicQueryService;

    public ReportsQueryServiceImpl(
            VehiclesQueryService vehiclesQueryService,
            MaintenanceQueryService maintenanceQueryService,
            AssignmentQueryService assignmentQueryService,
            MechanicQueryService mechanicQueryService
    ) {
        this.vehiclesQueryService = vehiclesQueryService;
        this.maintenanceQueryService = maintenanceQueryService;
        this.assignmentQueryService = assignmentQueryService;
        this.mechanicQueryService = mechanicQueryService;
    }

    @Override
    public Optional<ReportResource> handle(GetReportByVehicleIdQuery query) {
        Long vehicleId = query.vehicleId();

        var vehicleOpt = vehiclesQueryService.handle(new GetVehicleByIdQuery(vehicleId));
        if (vehicleOpt.isEmpty()) {
            LOGGER.debug("ReportsQueryService: vehicle not found for id={}", vehicleId);
            return Optional.empty();
        }
        var vehicle = vehicleOpt.get();
        VehicleResource vehicleResource = VehicleResourceFromEntityAssembler.toResourceFromEntity(vehicle);

        List<MaintenanceResource> maintenancesResources = maintenanceQueryService
                .handle(new GetAllMaintenancesByVehicleIdQuery(vehicleId))
                .stream()
                .map(MaintenanceResourceFromEntityAssembler::toResourceFromEntity)
                .toList();

        AssignmentResource assignmentResource = null;
        var assignmentOpt = assignmentQueryService.handle(new GetAssignmentByVehicleIdQuery(vehicleId));
        if (assignmentOpt.isPresent()) {
            var assignment = assignmentOpt.get();
            var mechanicOpt = mechanicQueryService.handle(new GetMechanicByIdQuery(assignment.getMechanicId()));
            if (mechanicOpt.isPresent()) {
                assignmentResource = AssignmentResourceFromEntityAssembler.toResourceFromEntity(assignment, mechanicOpt.get());
            } else {
                LOGGER.debug("ReportsQueryService: assignment found for vehicleId={} but mechanic id={} not found", vehicleId, assignment.getMechanicId());
            }
        } else {
            LOGGER.debug("ReportsQueryService: no assignment for vehicleId={}", vehicleId);
        }

        var report = new ReportResource(vehicleResource, maintenancesResources, assignmentResource);
        return Optional.of(report);
    }
}

