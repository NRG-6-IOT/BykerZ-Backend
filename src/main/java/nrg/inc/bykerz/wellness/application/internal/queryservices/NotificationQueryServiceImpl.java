package nrg.inc.bykerz.wellness.application.internal.queryservices;

import nrg.inc.bykerz.wellness.domain.model.entities.Notification;
import nrg.inc.bykerz.wellness.domain.model.queries.GetAllNotificationsQuery;
import nrg.inc.bykerz.wellness.domain.model.queries.GetNotificationByIdQuery;
import nrg.inc.bykerz.wellness.domain.model.queries.GetNotificationsByVehicleIdQuery;
import nrg.inc.bykerz.wellness.domain.services.NotificationQueryService;
import nrg.inc.bykerz.wellness.infrastructure.persistence.jpa.repositories.NotificationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotificationQueryServiceImpl implements NotificationQueryService {

    private final NotificationRepository notificationRepository;

    public NotificationQueryServiceImpl(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Override
    public Optional<Notification> handle(GetNotificationByIdQuery getNotificationByIdQuery) {
        return notificationRepository.findById(getNotificationByIdQuery.notificationId());
    }

    @Override
    public List<Notification> handle(GetAllNotificationsQuery getAllNotificationsQuery) {
        return notificationRepository.findAll();
    }

    @Override
    public List<Notification> handle(GetNotificationsByVehicleIdQuery getNotificationsByVehicleIdQuery) {
        return notificationRepository.findByVehicleId(getNotificationsByVehicleIdQuery.vehicleId());
    }
}
