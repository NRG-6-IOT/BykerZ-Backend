package nrg.inc.bykerz.wellness.application.internal.commandservices;

import nrg.inc.bykerz.wellness.application.internal.outboundservices.acl.ExternalVehicleService;
import nrg.inc.bykerz.wellness.domain.model.commands.CreateNotificationCommand;
import nrg.inc.bykerz.wellness.domain.model.entities.Notification;
import nrg.inc.bykerz.wellness.domain.services.NotificationCommandService;
import nrg.inc.bykerz.wellness.infrastructure.persistence.jpa.repositories.NotificationRepository;
import org.springframework.stereotype.Service;

@Service
public class NotificationCommandServiceImpl implements NotificationCommandService {

    private final NotificationRepository notificationRepository;
    private final ExternalVehicleService externalVehicleService;

    public NotificationCommandServiceImpl(NotificationRepository notificationRepository, ExternalVehicleService externalVehicleService) {
        this.externalVehicleService = externalVehicleService;
        this.notificationRepository = notificationRepository;
    }

    @Override
    public Long handle(CreateNotificationCommand createNotificationCommand) {

        //Validate the vehicle exists
        var vehicleOpt =externalVehicleService.fetchVehicleById(createNotificationCommand.vehicleId());

        if(vehicleOpt.isEmpty()){
            throw new IllegalArgumentException( "Vehicle with id " + createNotificationCommand.vehicleId() + " does not exist");
        }

        //Create the notification
        var notification = new Notification(createNotificationCommand);

        try{
            notificationRepository.save(notification);
            return notification.getId();
        } catch (Exception e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }
}
