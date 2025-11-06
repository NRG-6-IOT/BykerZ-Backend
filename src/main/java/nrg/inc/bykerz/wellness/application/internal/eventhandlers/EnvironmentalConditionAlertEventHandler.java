package nrg.inc.bykerz.wellness.application.internal.eventhandlers;

import nrg.inc.bykerz.wellness.domain.model.commands.CreateNotificationCommand;
import nrg.inc.bykerz.wellness.domain.model.events.EnvironmentalConditionAlertEvent;
import nrg.inc.bykerz.wellness.domain.services.NotificationCommandService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class EnvironmentalConditionAlertEventHandler {

    private final NotificationCommandService notificationCommandService;

    public EnvironmentalConditionAlertEventHandler(NotificationCommandService notificationCommandService) {
        this.notificationCommandService = notificationCommandService;
    }

    @EventListener(EnvironmentalConditionAlertEvent.class)
    public void on(EnvironmentalConditionAlertEvent event) {
        CreateNotificationCommand command = new CreateNotificationCommand(
                event.getVehicleId(),
                "Environmental Alert - " + event.getAlertType(),
                event.getDescription(),
                "ENVIRONMENTAL_ALERT",
                event.getSeverity(),
                event.getRegisteredAt()
        );

        notificationCommandService.handle(command);
        System.out.println("✅ Environmental notification created: " + event.getDescription());
    }
}
