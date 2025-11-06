package nrg.inc.bykerz.wellness.domain.services;

import nrg.inc.bykerz.wellness.domain.model.commands.CreateNotificationCommand;

public interface NotificationCommandService {

    Long handle(CreateNotificationCommand createNotificationCommand);


}
