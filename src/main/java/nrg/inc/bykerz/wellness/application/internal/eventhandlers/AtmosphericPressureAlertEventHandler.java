package nrg.inc.bykerz.wellness.application.internal.eventhandlers;

import nrg.inc.bykerz.wellness.domain.model.commands.CreateNotificationCommand;
import nrg.inc.bykerz.wellness.domain.model.events.AtmosphericPressureAlertEvent;
import nrg.inc.bykerz.wellness.domain.services.NotificationCommandService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class AtmosphericPressureAlertEventHandler {

    private final NotificationCommandService notificationCommandService;

    public AtmosphericPressureAlertEventHandler(NotificationCommandService notificationCommandService) {
        this.notificationCommandService = notificationCommandService;
    }

    @EventListener(AtmosphericPressureAlertEvent.class)
    public void on(AtmosphericPressureAlertEvent event) {
        CreateNotificationCommand command = new CreateNotificationCommand(
                event.getVehicleId(),
                "Pressure Alert - " + event.getAlertType(),
                event.getDescription(),
                "PRESSURE_ALERT",
                event.getSeverity(),
                event.getRegisteredAt()
        );

        notificationCommandService.handle(command);
        System.out.println("✅ Pressure notification created: " + event.getDescription());
    }
}
