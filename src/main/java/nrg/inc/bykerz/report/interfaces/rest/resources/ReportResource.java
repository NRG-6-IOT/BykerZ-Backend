package nrg.inc.bykerz.report.interfaces.rest.resources;

import lombok.Getter;
import lombok.Setter;
import nrg.inc.bykerz.report.domain.model.entities.Metric;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReportResource {
    @Getter
    @Setter
    private Long reportId;
    @Getter
    @Setter
    private Long vehicleId;
    private Date reportDate;
    private List<Metric> metrics = new ArrayList<>();

    public ReportResource() {
    }

    public ReportResource(Long reportId, Long vehicleId, Date reportDate, List<Metric> metrics) {
        this.reportId = reportId;
        this.vehicleId = vehicleId;
        this.reportDate = reportDate == null ? null : new Date(reportDate.getTime());
        this.metrics = metrics == null ? new ArrayList<>() : new ArrayList<>(metrics);
    }

    public Date getReportDate() {
        return reportDate == null ? null : new Date(reportDate.getTime());
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate == null ? null : new Date(reportDate.getTime());
    }

    public List<Metric> getMetrics() {
        return new ArrayList<>(metrics);
    }

    public void setMetrics(List<Metric> metrics) {
    }
}

