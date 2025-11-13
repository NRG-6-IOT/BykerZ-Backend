package nrg.inc.bykerz.report.domain.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import nrg.inc.bykerz.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

/*
@Getter
@Entity
public class MetricType extends AuditableAbstractAggregateRoot<MetricType> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long metricTypeId;

    private String metricName;

    private String metricDescription;

    protected MetricType() {
        super();
    }

    public MetricType(String metricName, String metricDescription) {
        this.metricName = metricName;
        this.metricDescription = metricDescription;
    }

    public MetricType(Long metricTypeId, String metricName, String metricDescription) {
        this.metricTypeId = metricTypeId;
        this.metricName = metricName;
        this.metricDescription = metricDescription;
    }

    // compatibility accessor used in some parts of the codebase
    public Long metricTypeId() {
        return this.metricTypeId;
    }
}
*/