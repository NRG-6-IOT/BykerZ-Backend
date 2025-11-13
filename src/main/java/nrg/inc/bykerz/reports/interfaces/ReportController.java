package nrg.inc.bykerz.reports.interfaces;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import nrg.inc.bykerz.reports.domain.model.queries.GetReportByVehicleIdQuery;
import nrg.inc.bykerz.reports.domain.services.ReportsQueryService;
import nrg.inc.bykerz.reports.interfaces.rest.resources.ReportResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/*
@RestController
@RequestMapping("/api/v1/reports")
@Tag(name = "Reports", description = "Reports API")
public class ReportController {

    private final ReportsQueryService reportsQueryService;

    private static final Logger LOGGER = LoggerFactory.getLogger(ReportController.class);

    public ReportController(ReportsQueryService reportsQueryService) {
        this.reportsQueryService = reportsQueryService;
    }

    @GetMapping(value = "/vehicle/{vehicleId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get aggregated report by vehicle ID", description = "Aggregates vehicle, maintenances and assignment data for the given vehicleId")
    public ResponseEntity<ReportResource> getReportByVehicleId(@PathVariable Long vehicleId) {
        var reportOpt = reportsQueryService.handle(new GetReportByVehicleIdQuery(vehicleId));
        if (reportOpt.isEmpty()) {
            LOGGER.debug("ReportController: report not found for vehicle id={}", vehicleId);
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(reportOpt.get());
    }

    @GetMapping(value = "/vehicle/{vehicleId}/export", produces = "text/csv")
    @Operation(summary = "Export aggregated report by vehicle ID as CSV")
    public ResponseEntity<String> exportReportByVehicleId(@PathVariable Long vehicleId) {
        var reportOpt = reportsQueryService.handle(new GetReportByVehicleIdQuery(vehicleId));
        if (reportOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var report = reportOpt.get();

        StringBuilder csv = new StringBuilder();
        csv.append("Section,Field,Value\n");

        // Vehicle section
        var vehicle = report.vehicle();
        csv.append("Vehicle,Model,").append(vehicle.model().name()).append("\n");
        csv.append("Vehicle,Brand,").append(vehicle.model().brand()).append("\n");
        csv.append("Vehicle,Model Year,").append(vehicle.model().modelYear()).append("\n");
        csv.append("Vehicle,Type,").append(vehicle.model().type()).append("\n");
        csv.append("Vehicle,Displacement,").append(vehicle.model().displacement()).append("\n");
        csv.append("Vehicle,Potency,").append(vehicle.model().potency()).append("\n");
        csv.append("Vehicle,Engine Type,").append(vehicle.model().engineType()).append("\n");
        csv.append("Vehicle,Weight,").append(vehicle.model().weight()).append("\n");

        // Assignment section
        if (report.assignment() != null) {
            csv.append("Assignment,Mechanic Name,").append(report.assignment().mechanic().completeName()).append("\n");
            csv.append("Assignment,Status,").append(report.assignment().status()).append("\n");
            csv.append("Assignment,Type,").append(report.assignment().type()).append("\n");
        }

        // Maintenances section
        if (report.maintenances() != null && !report.maintenances().isEmpty()) {
            for (var m : report.maintenances()) {
                csv.append("Maintenance,Date,").append(m.dateOfService()).append("\n");
                csv.append("Maintenance,Location,").append(m.location()).append("\n");
                csv.append("Maintenance,Description,").append(m.description()).append("\n");
                csv.append("Maintenance,State,").append(m.state()).append("\n");
            }
        }

        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=vehicle-report-" + vehicleId + ".csv")
                .body(csv.toString());
    }

}
*/