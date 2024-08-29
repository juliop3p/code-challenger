package insurance.quote.app.domain.port.out;

import insurance.quote.app.domain.entities.Quote;
import insurance.quote.app.infrastructure.model.QuoteEntity;

public interface SaveQuote {
  QuoteEntity execute(Quote quote);
}
