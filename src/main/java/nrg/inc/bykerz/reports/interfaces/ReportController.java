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

}
