package nrg.inc.bykerz.report.domain.model.aggregate;
/*
import jakarta.persistence.*;
import lombok.Getter;
import nrg.inc.bykerz.report.domain.model.entities.Metric;
import nrg.inc.bykerz.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;


@Entity
public class Report extends AuditableAbstractAggregateRoot<Report> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private Long reportId;

    @Getter
    private Long vehicleId;

    // unidirectional OneToMany -> metrics table will have report_id FK
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "report_id")
    private List<Metric> metrics = new ArrayList<>();

    private Date reportDate;

    protected Report() {
        super();
    }

    // Factory constructor
    public Report(Long reportId, Long vehicleId, Date reportDate, List<Metric> metrics) {
        this.reportId = reportId;
        this.vehicleId = vehicleId;
        this.reportDate = reportDate == null ? null : new Date(reportDate.getTime());
        this.metrics = metrics == null ? new ArrayList<>() : new ArrayList<>(metrics);
    }

    public static Report generate(Long reportId, Long vehicleId, Date reportDate) {
        return new Report(reportId, vehicleId, reportDate, null);
    }

    public Date getReportDate() {
        return reportDate == null ? null : new Date(reportDate.getTime());
    }

    public List<Metric> getMetrics() {
        return new ArrayList<>(metrics);
    }

    public void addMetric(Metric metric) {
        if (metric == null) return;
        this.metrics.add(metric);
    }

    public boolean removeMetricById(Long metricId) {
        if (metricId == null) return false;
        return this.metrics.removeIf(m -> Objects.equals(m.metricId(), metricId));
    }
}
*/