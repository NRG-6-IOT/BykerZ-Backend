package nrg.inc.bykerz.wellness.interfaces.rest.resources;

public record CreateNotificationResource(

        Long vehicleId,
        String title,
        String message,
        String type,
        String severity

) {
}
