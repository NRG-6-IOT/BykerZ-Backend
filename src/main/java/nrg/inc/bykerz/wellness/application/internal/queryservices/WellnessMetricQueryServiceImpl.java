package nrg.inc.bykerz.wellness.application.internal.queryservices;

import nrg.inc.bykerz.wellness.domain.model.aggregates.WellnessMetric;
import nrg.inc.bykerz.wellness.domain.model.queries.GetAllWellnessMetricsQuery;
import nrg.inc.bykerz.wellness.domain.model.queries.GetWellnessMetricByIdQuery;
import nrg.inc.bykerz.wellness.domain.model.queries.GetWellnessMetricsByVehicleIdQuery;
import nrg.inc.bykerz.wellness.domain.services.WellnessMetricQueryService;
import nrg.inc.bykerz.wellness.infrastructure.persistence.jpa.repositories.WellnessMetricRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WellnessMetricQueryServiceImpl implements WellnessMetricQueryService
{
    private final WellnessMetricRepository wellnessMetricRepository;

    public WellnessMetricQueryServiceImpl(WellnessMetricRepository wellnessMetricRepository) {
        this.wellnessMetricRepository = wellnessMetricRepository;
    }

    @Override
    public Optional<WellnessMetric> handle(GetWellnessMetricByIdQuery getWellnessMetricByIdQuery) {
        return wellnessMetricRepository.findById(getWellnessMetricByIdQuery.wellnessMetricId());
    }

    @Override
    public List<WellnessMetric> handle(GetAllWellnessMetricsQuery getAllWellnessMetricsQuery) {
        return wellnessMetricRepository.findAll();
    }

    @Override
    public List<WellnessMetric> handle(GetWellnessMetricsByVehicleIdQuery getWellnessMetricsByVehicleIdQuery) {
        return wellnessMetricRepository.findByVehicleId(getWellnessMetricsByVehicleIdQuery.vehicleId());
    }
}
