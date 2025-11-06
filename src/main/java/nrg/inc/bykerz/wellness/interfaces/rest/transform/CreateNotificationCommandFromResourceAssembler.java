package nrg.inc.bykerz.wellness.interfaces.rest.transform;

import nrg.inc.bykerz.wellness.domain.model.commands.CreateNotificationCommand;
import nrg.inc.bykerz.wellness.interfaces.rest.resources.CreateNotificationResource;

import java.time.LocalDateTime;

public class CreateNotificationCommandFromResourceAssembler {
    public static CreateNotificationCommand toCommandFromResource(CreateNotificationResource createNotificationResource) {
        return new CreateNotificationCommand(
                createNotificationResource.vehicleId(),
                createNotificationResource.title(),
                createNotificationResource.message(),
                createNotificationResource.type(),
                createNotificationResource.severity(),
                LocalDateTime.now() // Set occurredAt to current time
        );
    }
}
