package nrg.inc.bykerz.report.application;

import nrg.inc.bykerz.report.domain.model.entities.MetricType;
import nrg.inc.bykerz.report.infrastructure.persistence.jpa.repositories.MetricTypeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/*
@Component
public class ApplicationReadyEventHandler implements ApplicationListener<ApplicationReadyEvent> {

    private final MetricTypeRepository metricTypeRepository;
    private final Logger LOGGER = LoggerFactory.getLogger(ApplicationReadyEventHandler.class);

    public ApplicationReadyEventHandler(@NonNull MetricTypeRepository metricTypeRepository) {
        this.metricTypeRepository = metricTypeRepository;
    }

    @Override
    public void onApplicationEvent(@NonNull ApplicationReadyEvent event) {
        LOGGER.info("Application started at timestamp: {}", currentTimestamp());
        try {
            List<MetricType> existing = metricTypeRepository.findAll();
            if (existing.isEmpty()) {
                List<MetricType> defaults = Arrays.asList(
                        new MetricType(1L, "mileage", "Total distance in period"),
                        new MetricType(2L, "fuel_consumption", "Fuel consumption in period")
                );
                metricTypeRepository.saveAll(defaults);
                LOGGER.info("Seeded {} default metric types", defaults.size());
            } else {
                LOGGER.info("Metric types already present: {}", existing.size());
            }
        } catch (Exception ex) {
            LOGGER.warn("Error seeding metric types: {}", ex.getMessage());
        }
    }

    public long currentTimestamp() {
        return System.currentTimeMillis();
    }
}
*/