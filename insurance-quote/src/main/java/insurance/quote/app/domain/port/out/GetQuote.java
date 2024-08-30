package insurance.quote.app.domain.port.out;

import insurance.quote.app.domain.entities.Quote;

public interface GetQuote {
  Quote execute(String idQuote);
}
