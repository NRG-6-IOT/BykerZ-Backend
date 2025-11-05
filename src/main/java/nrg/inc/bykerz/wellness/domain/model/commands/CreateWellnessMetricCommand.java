package nrg.inc.bykerz.wellness.domain.model.commands;

import nrg.inc.bykerz.wellness.domain.model.valueobjects.*;

public record CreateWellnessMetricCommand(
        Long vehicleId,
        Coordinates coordinates,
        AirQuality airQuality,
        EnvironmentalConditions environmentalConditions,
        AtmosphericPressure atmosphericPressure,
        StatusImpact statusImpact
) {
    public CreateWellnessMetricCommand{
        if (vehicleId == null || vehicleId<=0) {
            throw new IllegalArgumentException("vehicleId cannot be null, less than or equal to zero");
        }
        // The others validations were added in their respective value object classes
    }
}
