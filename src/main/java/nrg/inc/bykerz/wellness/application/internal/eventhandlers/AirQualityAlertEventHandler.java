package nrg.inc.bykerz.wellness.application.internal.eventhandlers;

import nrg.inc.bykerz.wellness.domain.model.commands.CreateNotificationCommand;
import nrg.inc.bykerz.wellness.domain.model.events.AirQualityAlertEvent;
import nrg.inc.bykerz.wellness.domain.services.NotificationCommandService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class AirQualityAlertEventHandler {

    private final NotificationCommandService notificationCommandService;

    public AirQualityAlertEventHandler(NotificationCommandService notificationCommandService) {
        this.notificationCommandService = notificationCommandService;
    }

    @EventListener(AirQualityAlertEvent.class)
    public void on(AirQualityAlertEvent event) {
        CreateNotificationCommand command = new CreateNotificationCommand(
                event.getVehicleId(),
                "Air Quality Alert - " + event.getAlertType(),
                event.getDescription(),
                "AIR_QUALITY_ALERT",
                event.getSeverity(),
                event.getRegisteredAt()
        );

        notificationCommandService.handle(command);
        System.out.println("✅ Air quality notification created: " + event.getDescription());
    }
}
