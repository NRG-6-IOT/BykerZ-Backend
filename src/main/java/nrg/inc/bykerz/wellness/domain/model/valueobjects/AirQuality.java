package nrg.inc.bykerz.wellness.domain.model.valueobjects;

public record AirQuality(Double CO2Ppm, Double NH3Ppm, Double BenzenePpm) {
    public AirQuality{
        if (CO2Ppm < 0 || NH3Ppm < 0 || BenzenePpm < 0) {
            throw new IllegalArgumentException("Air quality levels cannot be negative");
        }
    }
}
