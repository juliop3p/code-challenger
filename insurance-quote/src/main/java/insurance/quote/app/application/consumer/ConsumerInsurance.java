package insurance.quote.app.application.consumer;

import insurance.quote.app.application.dto.PolicyCreatedConsumerDto;
import insurance.quote.app.domain.port.in.UpdateQuotationPolicy;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConsumerInsurance {

  private final UpdateQuotationPolicy updateQuotationPolicy;
  @KafkaListener(topics = "insurance-policy-created", groupId = "my-consumer-group")
  public void consumer(PolicyCreatedConsumerDto policyCreatedConsumerDto) throws InterruptedException {
    updateQuotationPolicy.execute(policyCreatedConsumerDto);
  }
}
