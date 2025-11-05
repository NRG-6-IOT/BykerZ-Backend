package nrg.inc.bykerz.wellness.application.internal.commandservices;

import aj.org.objectweb.asm.commons.TryCatchBlockSorter;
import nrg.inc.bykerz.wellness.domain.model.aggregates.WellnessMetric;
import nrg.inc.bykerz.wellness.domain.model.commands.CreateWellnessMetricCommand;
import nrg.inc.bykerz.wellness.domain.model.commands.DeleteWellnessMetricCommand;
import nrg.inc.bykerz.wellness.domain.model.commands.UpdateWellnessMetricCommand;
import nrg.inc.bykerz.wellness.domain.services.WellnessMetricCommandService;
import nrg.inc.bykerz.wellness.infrastructure.persistence.jpa.repositories.WellnessMetricRepository;

import java.util.Optional;

public class WellnessMetricCommandServiceImpl implements WellnessMetricCommandService {

    private final WellnessMetricRepository wellnessMetricRepository;

    public WellnessMetricCommandServiceImpl(WellnessMetricRepository wellnessMetricRepository) {
        this.wellnessMetricRepository = wellnessMetricRepository;
    }

    @Override
    public Long handle(CreateWellnessMetricCommand createWellnessMetricCommand) {

        //Validate the vehicle exists

        //Create the wellness metric
        var wellnessMetric = new WellnessMetric(createWellnessMetricCommand);

        try{
            wellnessMetricRepository.save(wellnessMetric);
            return wellnessMetric.getId();
        } catch (Exception e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    @Override
    public Optional<WellnessMetric> handle(UpdateWellnessMetricCommand command) {
        var metric = wellnessMetricRepository.findById(command.wellnessMetricId())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Wellness Metric with id " + command.wellnessMetricId() + " does not exist"
                ));

        var updatedMetric = metric.updateWellnessMetric(command);
        return Optional.of(wellnessMetricRepository.save(updatedMetric));
    }

    @Override
    public void handle(DeleteWellnessMetricCommand deleteWellnessMetricCommand) {
        var metric = wellnessMetricRepository.findById(deleteWellnessMetricCommand.wellnessMetricId())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Wellness Metric with id " + deleteWellnessMetricCommand.wellnessMetricId() + " does not exist"
                ));

        wellnessMetricRepository.delete(metric);
    }
}
