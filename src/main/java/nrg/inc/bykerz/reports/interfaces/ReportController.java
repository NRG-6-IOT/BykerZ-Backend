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

@RestController
@RequestMapping("/api/v1/reports")
@Tag(name = "Reports", description = "Reports API")
public class ReportController {

    private final ReportsQueryService reportsQueryService;

    private static final Logger LOGGER = LoggerFactory.getLogger(ReportController.class);

    public ReportController(ReportsQueryService reportsQueryService) {
        this.reportsQueryService = reportsQueryService;
    }

    @GetMapping(value = "/owner/{vehicleId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get aggregated report by vehicle ID", description = "Aggregates vehicle, maintenances and assignment data for the given vehicleId")
    public ResponseEntity<ReportResource> getReportByVehicleId(@PathVariable Long vehicleId) {
        var reportOpt = reportsQueryService.handle(new GetReportByVehicleIdQuery(vehicleId));
        if (reportOpt.isEmpty()) {
            LOGGER.debug("ReportController: report not found for vehicle id={}", vehicleId);
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(reportOpt.get());
    }

    @GetMapping(value = "/owner/{vehicleId}/export", produces = "text/csv")
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
        if (vehicle != null && vehicle.model() != null) {
            csv.append("Vehicle,Model,").append(escapeCSV(vehicle.model().name())).append("\n");
            csv.append("Vehicle,Brand,").append(escapeCSV(vehicle.model().brand())).append("\n");
            csv.append("Vehicle,Model Year,").append(escapeCSV(vehicle.model().modelYear())).append("\n");
            csv.append("Vehicle,Type,").append(escapeCSV(vehicle.model().type())).append("\n");
            csv.append("Vehicle,Displacement,").append(escapeCSV(vehicle.model().displacement())).append("\n");
            csv.append("Vehicle,Potency,").append(escapeCSV(vehicle.model().potency())).append("\n");
            csv.append("Vehicle,Engine Type,").append(escapeCSV(vehicle.model().engineType())).append("\n");
            csv.append("Vehicle,Weight,").append(escapeCSV(vehicle.model().weight())).append("\n");
        }

        // Assignment section
        if (report.assignment() != null) {
            var assignment = report.assignment();
            if (assignment.mechanic() != null) {
                csv.append("Assignment,Mechanic Name,").append(escapeCSV(assignment.mechanic().completeName())).append("\n");
            }
            csv.append("Assignment,Status,").append(escapeCSV(assignment.status())).append("\n");
            csv.append("Assignment,Type,").append(escapeCSV(assignment.type())).append("\n");
        }

        // Maintenances section
        if (report.maintenances() != null && !report.maintenances().isEmpty()) {
            for (var m : report.maintenances()) {
                csv.append("Maintenance,Date,").append(escapeCSV(m.dateOfService())).append("\n");
                csv.append("Maintenance,Location,").append(escapeCSV(m.location())).append("\n");
                csv.append("Maintenance,Description,").append(escapeCSV(m.description())).append("\n");
                csv.append("Maintenance,State,").append(escapeCSV(m.state())).append("\n");
            }
        }

        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=owner-report-" + vehicleId + ".csv")
                .body(csv.toString());
    }

    private String escapeCSV(String value) {
        if (value == null) {
            return "";
        }
        if (value.contains(",") || value.contains("\"") || value.contains("\n")) {
            return "\"" + value.replace("\"", "\"\"") + "\"";
        }
        return value;
    }
}
