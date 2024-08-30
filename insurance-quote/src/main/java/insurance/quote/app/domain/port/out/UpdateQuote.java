package insurance.quote.app.domain.port.out;

import insurance.quote.app.domain.entities.Quote;

public interface UpdateQuote {
  Quote execute(Quote quote);
}
