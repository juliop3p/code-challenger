package insurance.quote.app.application.consumer;

import insurance.quote.app.domain.port.in.UpdatePolicyCotation;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;

@RequiredArgsConstructor
public class ConsumerInsurance {

  private final UpdatePolicyCotation updatePolicyCotation;
  @KafkaListener(topics = "insurance-topic")
  public void consumer(Object message) {
    System.out.println("Received message: " + message);
    updatePolicyCotation.execute();
  }
}
