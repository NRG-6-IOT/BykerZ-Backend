package nrg.inc.bykerz.wellness.interfaces.rest.transform;

import nrg.inc.bykerz.wellness.domain.model.commands.UpdateWellnessMetricCommand;
import nrg.inc.bykerz.wellness.interfaces.rest.resources.UpdateWellnessMetricResource;

public class UpdateWellnessMetricCommandFromResourceAssembler {
    public static UpdateWellnessMetricCommand toCommandFromResource(UpdateWellnessMetricResource resource){
        return new UpdateWellnessMetricCommand(
                resource.wellnessMetricId(),

                resource.latitude(),
                resource.longitude(),

                resource.CO2Ppm(),
                resource.NH3Ppm(),
                resource.BenzenePpm(),

                resource.temperatureCelsius(),
                resource.humidityPercentage(),

                resource.pressureHpa(),

                resource.impactDetected()
        );

    }
}
