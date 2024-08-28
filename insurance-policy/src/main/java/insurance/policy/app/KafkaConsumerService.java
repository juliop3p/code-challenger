package insurance.policy.app;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    private final KafkaProducerService kafkaProducerService;

    public KafkaConsumerService(KafkaProducerService kafkaProducerService) {
        this.kafkaProducerService = kafkaProducerService;
    }

    @KafkaListener(topics = "insurance-topic", groupId = "my-group")
    public void listen(String message) {
        System.out.println("Received message: " + message);
        kafkaProducerService.sendResponse("response-topic");
    }
}