package insurance.quote.app.domain.port.in;

import insurance.quote.app.application.dto.PolicyCreatedConsumerDto;

public interface UpdateQuotationPolicy {
  void execute(PolicyCreatedConsumerDto policyCreatedConsumerDto) throws InterruptedException;
}
