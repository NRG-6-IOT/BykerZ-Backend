package nrg.inc.bykerz.wellness.domain.services;

import nrg.inc.bykerz.wellness.domain.model.entities.Notification;
import nrg.inc.bykerz.wellness.domain.model.queries.GetAllNotificationsQuery;
import nrg.inc.bykerz.wellness.domain.model.queries.GetNotificationByIdQuery;
import nrg.inc.bykerz.wellness.domain.model.queries.GetNotificationsByVehicleIdQuery;

import java.util.List;
import java.util.Optional;

public interface NotificationQueryService {

    Optional<Notification> handle(GetNotificationByIdQuery getNotificationByIdQuery);

    List<Notification> handle(GetAllNotificationsQuery getAllNotificationsQuery);

    List<Notification> handle(GetNotificationsByVehicleIdQuery getNotificationsByVehicleIdQuery);
}
