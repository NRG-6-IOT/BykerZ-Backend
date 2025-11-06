package nrg.inc.bykerz.wellness.interfaces.acl;

import nrg.inc.bykerz.wellness.domain.model.aggregates.WellnessMetric;

import java.util.Optional;

public interface WellnessMetricContextFacade {
    Optional<WellnessMetric> fetchWellnessMetricById(Long wellnessMetricId);
}
