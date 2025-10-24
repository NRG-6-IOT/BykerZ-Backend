package nrg.inc.bykerz.report.domain.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import nrg.inc.bykerz.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

@Getter
@Entity
public class Metric extends AuditableAbstractAggregateRoot<Metric> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long metricId;

    @ManyToOne
    private MetricType type;

    private String metricValue;

    protected Metric() {
        super();
    }

    public Metric(MetricType type, String metricValue) {
        this.type = type;
        this.metricValue = metricValue;
    }

    public Metric(Long metricId, MetricType type, String metricValue) {
        this.metricId = metricId;
        this.type = type;
        this.metricValue = metricValue;
    }

    // keep compatibility with existing code that calls metric.metricId()
    public Long metricId() {
        return this.metricId;
    }
}
