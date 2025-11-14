package nrg.inc.bykerz.wellness.application.internal.commandservices;

import nrg.inc.bykerz.shared.application.internal.outboundservices.acl.ExternalVehiclesService;
import nrg.inc.bykerz.wellness.domain.model.commands.CreateNotificationCommand;
import nrg.inc.bykerz.wellness.domain.model.commands.MarkNotificationAsReadCommand;
import nrg.inc.bykerz.wellness.domain.model.entities.Notification;
import nrg.inc.bykerz.wellness.domain.services.NotificationCommandService;
import nrg.inc.bykerz.wellness.infrastructure.persistence.jpa.repositories.NotificationRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NotificationCommandServiceImpl implements NotificationCommandService {

    private final NotificationRepository notificationRepository;
    private final ExternalVehiclesService externalVehicleService;

    public NotificationCommandServiceImpl(NotificationRepository notificationRepository, ExternalVehiclesService externalVehicleService) {
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

    @Override
    public Optional<Notification> handle(MarkNotificationAsReadCommand command) {
        var notificationOpt = notificationRepository.findById(command.notificationId());
        if (notificationOpt.isPresent()) {
            notificationOpt.get().markAsRead();
            notificationRepository.save(notificationOpt.get());
            return notificationOpt;
        }
        return Optional.empty();
    }
}
