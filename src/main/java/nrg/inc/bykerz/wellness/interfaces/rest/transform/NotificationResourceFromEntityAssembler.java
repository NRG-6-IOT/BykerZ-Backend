package nrg.inc.bykerz.wellness.interfaces.rest.transform;

import nrg.inc.bykerz.wellness.domain.model.entities.Notification;
import nrg.inc.bykerz.wellness.interfaces.rest.resources.NotificationResource;

public class NotificationResourceFromEntityAssembler {
    public static NotificationResource toResourceFromEntity(Notification entity) {
        return new NotificationResource(
                entity.getId(),
                entity.getVehicleId(),
                entity.getTitle(),
                entity.getMessage(),
                entity.getType(),
                entity.getSeverity(),
                entity.getRead(),
                entity.getOccurredAt()
        );
    }
}
