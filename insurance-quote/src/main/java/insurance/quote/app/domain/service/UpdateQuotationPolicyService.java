package insurance.quote.app.domain.service;

import insurance.quote.app.application.dto.PolicyCreatedConsumerDto;
import insurance.quote.app.domain.entities.Quote;
import insurance.quote.app.domain.exception.ValidationException;
import insurance.quote.app.domain.port.in.UpdateQuotationPolicy;
import insurance.quote.app.domain.port.out.GetQuote;
import insurance.quote.app.domain.port.out.UpdateQuote;
import java.util.Objects;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UpdateQuotationPolicyService implements UpdateQuotationPolicy {

  private final GetQuote getQuote;
  private final UpdateQuote updateQuote;

  @Override
  @Transactional(readOnly = true)
  public void execute(PolicyCreatedConsumerDto policyCreatedConsumerDto)
      throws InterruptedException {
    Quote quote = getQuote.execute(policyCreatedConsumerDto.getQuoteId());
    if (Objects.isNull(quote)) {
      throw new ValidationException("Cotação não encontrada!");
    }

    quote.setInsurancePolicyId(UUID.randomUUID().toString());

    updateQuote.execute(quote);
  }
}
