package insurance.policy.app;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class CustomHealthIndicator implements HealthIndicator {

    @Override
    public Health health() {
        // Lógica para verificar a saúde do serviço
        boolean isHealthy = checkHealth();
        if (isHealthy) {
            return Health.up().withDetail("Custom", "Service is healthy").build();
        } else {
            return Health.down().withDetail("Custom", "Service is not healthy").build();
        }
    }

    private boolean checkHealth() {
        // Implementar lógica real de verificação de saúde
        return true;
    }
}
