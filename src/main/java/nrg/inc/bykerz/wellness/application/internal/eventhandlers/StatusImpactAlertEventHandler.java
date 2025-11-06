package nrg.inc.bykerz.wellness.application.internal.eventhandlers;

import nrg.inc.bykerz.wellness.domain.model.commands.CreateNotificationCommand;
import nrg.inc.bykerz.wellness.domain.model.events.StatusImpactAlertEvent;
import nrg.inc.bykerz.wellness.domain.services.NotificationCommandService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class StatusImpactAlertEventHandler {

    private final NotificationCommandService notificationCommandService;

    public StatusImpactAlertEventHandler(NotificationCommandService notificationCommandService) {
        this.notificationCommandService = notificationCommandService;
    }

    @EventListener(StatusImpactAlertEvent.class)
    public void on(StatusImpactAlertEvent event) {
        CreateNotificationCommand command = new CreateNotificationCommand(
                event.getVehicleId(),
                "Impact Alert",
                event.getDescription(),
                "IMPACT_ALERT",
                event.getSeverity(),
                event.getRegisteredAt()
        );

        notificationCommandService.handle(command);
        System.out.println("🚨 Impact notification created: " + event.getDescription());
    }
}
