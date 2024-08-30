package insurance.quote.app.domain.service;

import insurance.quote.app.domain.entities.Quote;
import insurance.quote.app.domain.port.in.GetInsuranceQuote;
import insurance.quote.app.domain.port.out.GetQuote;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetInsuranceQuoteService implements GetInsuranceQuote {

  private final GetQuote getQuote;

  @Override
  public Quote execute(String id) {
    return getQuote.execute(id);
  }
}
