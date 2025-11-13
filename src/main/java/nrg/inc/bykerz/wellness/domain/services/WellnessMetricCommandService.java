package nrg.inc.bykerz.wellness.domain.services;

import nrg.inc.bykerz.wellness.domain.model.aggregates.WellnessMetric;
import nrg.inc.bykerz.wellness.domain.model.commands.CreateWellnessMetricCommand;
import nrg.inc.bykerz.wellness.domain.model.commands.DeleteWellnessMetricCommand;
import nrg.inc.bykerz.wellness.domain.model.commands.UpdateWellnessMetricCommand;

import java.util.Optional;

public interface WellnessMetricCommandService {

    Long handle(CreateWellnessMetricCommand createWellnessMetricCommand);

    Optional<WellnessMetric> handle(UpdateWellnessMetricCommand updateWellnessMetricCommand);

    void handle (DeleteWellnessMetricCommand deleteWellnessMetricCommand);

}
