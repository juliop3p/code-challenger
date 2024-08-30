package insurance.policy.app;

import java.util.UUID;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaService {

  private final KafkaTemplate<String, PolicyCreated> kafkaTemplate;

  public KafkaService(KafkaTemplate<String, PolicyCreated> kafkaTemplate) {
    this.kafkaTemplate = kafkaTemplate;
  }

  @KafkaListener(topics = "insurance-quote-received", groupId = "my-consumer-group")
  public void listen(String message) {
    // Recebe cotação com sucesso
    System.out.printf("Received message: %s%n", message);

    // Gera o id da Policy
    UUID guid = UUID.randomUUID();
    String guidString = guid.toString();

    PolicyCreated policyCreated = new PolicyCreated();
    policyCreated.setQuoteId(message);
    policyCreated.setPolicyId(guidString);
    // Envia o ID da Policy criada
    sendMessage("insurance-policy-created", policyCreated);
  }

  public void sendMessage(String topic, PolicyCreated message) {
    kafkaTemplate.send(topic, message);
  }

}
