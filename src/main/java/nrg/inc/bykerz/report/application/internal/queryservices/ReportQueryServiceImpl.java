package nrg.inc.bykerz.report.application.internal.queryservices;

import nrg.inc.bykerz.report.domain.model.aggregate.Report;
import nrg.inc.bykerz.report.domain.model.entities.Metric;
import nrg.inc.bykerz.report.domain.model.queries.GetMetricsByReportIdQuery;
import nrg.inc.bykerz.report.domain.model.queries.GetReportByIdQuery;
import nrg.inc.bykerz.report.domain.model.queries.GetReportByVehicleIdQuery;
import nrg.inc.bykerz.report.domain.service.ReportQueryService;
import nrg.inc.bykerz.report.infrastructure.persistence.jpa.repositories.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;

@Service
public class ReportQueryServiceImpl implements ReportQueryService {

    private final ReportRepository reportRepository;

    @Autowired
    public ReportQueryServiceImpl(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    @Override
    public Report handle(GetReportByIdQuery query) {
        if (query == null) return null;
        Long id = extractLong(query, "getReportId", "reportId");
        if (id == null) return null;
        return reportRepository.findById(id).orElse(null);
    }

    @Override
    public List<Report> handle(GetReportByVehicleIdQuery query) {
        if (query == null) return Collections.emptyList();
        Long vehicleId = extractLong(query, "getVehicleId", "vehicleId");
        if (vehicleId == null) return Collections.emptyList();
        return reportRepository.findByVehicleId(vehicleId);
    }

    @Override
    public List<Metric> handle(GetMetricsByReportIdQuery query) {
        if (query == null) return Collections.emptyList();
        Long reportId = extractLong(query, "getReportId", "reportId");
        if (reportId == null) return Collections.emptyList();
        Report report = reportRepository.findById(reportId).orElse(null);
        return report == null ? Collections.emptyList() : report.getMetrics();
    }

    // Try common accessor names (getXxx or xxx) and return Long if found, otherwise null
    private static Long extractLong(Object target, String... candidateMethodNames) {
        if (target == null) return null;
        for (String name : candidateMethodNames) {
            try {
                Method m = target.getClass().getMethod(name);
                Object res = m.invoke(target);
                if (res instanceof Long) return (Long) res;
                if (res instanceof Number) return ((Number) res).longValue();
            } catch (NoSuchMethodException ignored) {
                // try next candidate
            } catch (Exception ignored) {
                // ignore invocation exceptions and try next
            }
        }
        return null;
    }
}
