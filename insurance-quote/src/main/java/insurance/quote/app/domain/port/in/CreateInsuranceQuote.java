package insurance.quote.app.domain.port.in;

import insurance.quote.app.domain.entities.Quote;

public interface CreateInsuranceQuote {
  String execute(Quote quote);
}
