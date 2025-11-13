package nrg.inc.bykerz.wellness.application.acl;

import nrg.inc.bykerz.wellness.domain.model.aggregates.WellnessMetric;
import nrg.inc.bykerz.wellness.domain.model.queries.GetWellnessMetricByIdQuery;
import nrg.inc.bykerz.wellness.domain.services.WellnessMetricQueryService;
import nrg.inc.bykerz.wellness.interfaces.acl.WellnessMetricContextFacade;

import java.util.Optional;

public class WellnessMetricContextFacadeImpl implements WellnessMetricContextFacade {

    private final WellnessMetricQueryService wellnessMetricQueryService;

    public WellnessMetricContextFacadeImpl(WellnessMetricQueryService wellnessMetricQueryService) {
        this.wellnessMetricQueryService = wellnessMetricQueryService;
    }


    @Override
    public Optional<WellnessMetric> fetchWellnessMetricById(Long wellnessMetricId) {
        return wellnessMetricQueryService.handle(new GetWellnessMetricByIdQuery(wellnessMetricId));
    }
}
