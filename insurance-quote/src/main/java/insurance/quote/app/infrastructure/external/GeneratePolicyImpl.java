package insurance.quote.app.infrastructure.external;

import insurance.quote.app.domain.port.out.GeneratePolicy;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GeneratePolicyImpl implements GeneratePolicy {

  private final KafkaTemplate<String, String> kafkaTemplate;

  @Override
  public void execute(String quoteId) {
    kafkaTemplate.send("insurance-quote-received", quoteId);
  }
}
