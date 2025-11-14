package nrg.inc.bykerz.shared.infrastructure.keepalive;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Self-ping service to keep the application alive on free hosting platforms
 * that spin down inactive instances (like Render free tier).
 *
 * This service pings the health endpoint every 25 seconds to prevent the
 * instance from spinning down due to inactivity.
 *
 * Note: The /actuator/health endpoint is publicly accessible (no authentication required)
 * as configured in WebSecurityConfiguration.
 */
@Service
@ConditionalOnProperty(name = "keepalive.enabled", havingValue = "true")
public class KeepAliveService {

    private static final Logger logger = LoggerFactory.getLogger(KeepAliveService.class);

    private final RestTemplate restTemplate;
    private final String healthUrl;

    public KeepAliveService(
            RestTemplate restTemplate,
            @Value("${keepalive.url:http://localhost:8080/actuator/health}") String healthUrl) {
        this.restTemplate = restTemplate;
        this.healthUrl = healthUrl;
        logger.info("KeepAlive service initialized. Will ping: {}", healthUrl);
    }

    /**
     * Provides a RestTemplate bean for the KeepAlive service.
     * This RestTemplate doesn't include any authentication headers.
     */
    @Bean
    public RestTemplate keepAliveRestTemplate() {
        return new RestTemplate();
    }

    /**
     * Pings the health endpoint every 25 seconds (within the 30-second window)
     * to keep the service active on free hosting platforms.
     *
     * The health endpoint is publicly accessible, so no authentication token is required.
     */
    @Scheduled(fixedDelay = 25000, initialDelay = 30000)
    public void ping() {
        try {
            String response = restTemplate.getForObject(healthUrl, String.class);
            logger.debug("KeepAlive ping successful: {}", response);
        } catch (Exception e) {
            logger.warn("KeepAlive ping failed: {}", e.getMessage());
        }
    }
}

