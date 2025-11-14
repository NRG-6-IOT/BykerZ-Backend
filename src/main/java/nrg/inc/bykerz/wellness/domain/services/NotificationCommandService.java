package nrg.inc.bykerz.wellness.domain.services;

import nrg.inc.bykerz.wellness.domain.model.commands.CreateNotificationCommand;
import nrg.inc.bykerz.wellness.domain.model.commands.MarkNotificationAsReadCommand;
import nrg.inc.bykerz.wellness.domain.model.entities.Notification;

import java.util.Optional;

public interface NotificationCommandService {

    Long handle(CreateNotificationCommand createNotificationCommand);

    Optional<Notification> handle(MarkNotificationAsReadCommand markNotificationAsReadCommand);
}
