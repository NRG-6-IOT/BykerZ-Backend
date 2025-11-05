package nrg.inc.bykerz.wellness.domain.model.valueobjects;

public record EnvironmentalConditions(Float temperatureCelsius, Float humidityPercentage) {
    public EnvironmentalConditions{
        if (temperatureCelsius < -50 || temperatureCelsius > 60) {
            throw new IllegalArgumentException("Temperature must be between -50 and 60 Celsius");
        }
        if (humidityPercentage < 0 || humidityPercentage > 100) {
            throw new IllegalArgumentException("Humidity percentage must be between 0 and 100");
        }
    }
}
