package nrg.inc.bykerz.report.interfaces;

import nrg.inc.bykerz.report.domain.model.aggregate.Report;
import nrg.inc.bykerz.report.domain.model.entities.Metric;
import nrg.inc.bykerz.report.domain.model.queries.GetMetricsByReportIdQuery;
import nrg.inc.bykerz.report.domain.model.queries.GetReportByIdQuery;
import nrg.inc.bykerz.report.domain.model.queries.GetReportByVehicleIdQuery;
import nrg.inc.bykerz.report.domain.service.ReportQueryService;
import nrg.inc.bykerz.report.interfaces.rest.resources.ReportResource;
import nrg.inc.bykerz.report.interfaces.rest.transform.ReportResourceFromEntityAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/*

@RestController
@RequestMapping("/api/v1/reports")
public class ReportController {
    private final ReportQueryService reportQueryService;
    private final ReportResourceFromEntityAssembler toResourceAssembler;

    public ReportController(
            ReportQueryService reportQueryService,
            ReportResourceFromEntityAssembler toResourceAssembler) {
        this.reportQueryService = reportQueryService;
        this.toResourceAssembler = toResourceAssembler;
    }

    @GetMapping("/{reportId}")
    public ResponseEntity<ReportResource> getReportById(@PathVariable Long reportId) {
        GetReportByIdQuery q = new GetReportByIdQuery(reportId);
        Report report = reportQueryService.handle(q);
        if (report == null) return ResponseEntity.notFound().build();
        ReportResource res = toResourceAssembler.fromEntity(report);
        return ResponseEntity.ok(res);
    }

    @GetMapping("/vehicle/{vehicleId}")
    public ResponseEntity<List<ReportResource>> getAllReportsForVehicle(@PathVariable Long vehicleId) {
        GetReportByVehicleIdQuery q = new GetReportByVehicleIdQuery(vehicleId);
        List<Report> reports = reportQueryService.handle(q);
        List<ReportResource> resources = reports.stream()
                .map(toResourceAssembler::fromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resources);
    }

    @GetMapping("/{reportId}/metrics")
    public ResponseEntity<List<Metric>> getAllMetricsFromReport(@PathVariable Long reportId) {
        GetMetricsByReportIdQuery q = new GetMetricsByReportIdQuery(reportId);
        List<Metric> metrics = reportQueryService.handle(q);
        return ResponseEntity.ok(metrics);
    }
}

 */