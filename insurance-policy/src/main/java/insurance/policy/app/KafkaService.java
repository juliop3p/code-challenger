package insurance.policy.app;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class KafkaService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @KafkaListener(topics = "insurance-quote-received", groupId = "my-consumer-group")
    public void listen(String message) {
        // Recebe cotação com sucesso
        System.out.printf("Received message: %s%n", message);

        // Gera o id da Policy
        UUID guid = UUID.randomUUID();
        String guidString = guid.toString();

        // Envia o ID da Policy criada
        sendMessage("insurance-policy-created", guidString);
    }

    public void sendMessage(String topic, String message) {
        kafkaTemplate.send(topic, message);
    }

}
