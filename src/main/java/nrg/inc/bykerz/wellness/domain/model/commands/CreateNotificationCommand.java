package nrg.inc.bykerz.wellness.domain.model.commands;

import java.time.LocalDateTime;

public record CreateNotificationCommand(
        Long vehicleId,
        String title,
        String message,
        String type,
        String severity,
        LocalDateTime occurredAt
) {}
