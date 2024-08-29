package insurance.quote.app.infrastructure.external;

import insurance.quote.app.domain.port.out.GerarPolicy;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GerarPolicyImpl implements GerarPolicy {

  private final KafkaTemplate<String, Object> kafkaTemplate;

  @Override
  public void execute() {
    kafkaTemplate.send("insurance-topic", "evento test");
  }
}
