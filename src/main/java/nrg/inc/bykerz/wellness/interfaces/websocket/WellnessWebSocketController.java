package nrg.inc.bykerz.wellness.interfaces.websocket;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nrg.inc.bykerz.wellness.domain.model.entities.Notification;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
@RequiredArgsConstructor
public class WellnessWebSocketController {

    private final SimpMessagingTemplate messagingTemplate;

    /**
     * Envía notificación a un vehículo específico
     */
    public void sendNotificationToVehicle(Long vehicleId, Notification notification) {
        String destination = "/topic/vehicle/" + vehicleId + "/alerts";
        messagingTemplate.convertAndSend(destination, notification);
        log.info("WebSocket alert sent to vehicle {}: {}", vehicleId, notification.getTitle());
    }

    /**
     * Envía notificación global a todos los vehículos
     */
    public void sendGlobalAlert(Notification notification) {
        String destination = "/topic/global/alerts";
        messagingTemplate.convertAndSend(destination, notification);
        log.info("🌍 Global WebSocket alert sent: {}", notification.getTitle());
    }

    /**
     * Envía notificación por tipo de alerta
     */
    public void sendAlertByType(String alertType, Notification notification) {
        String destination = "/topic/alerts/" + alertType.toLowerCase();
        messagingTemplate.convertAndSend(destination, notification);
        log.info("🔔 {} alert sent via WebSocket: {}", alertType, notification.getTitle());
    }
}
