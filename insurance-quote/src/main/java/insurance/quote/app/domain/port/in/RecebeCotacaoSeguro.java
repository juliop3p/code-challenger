package insurance.quote.app.domain.port.in;

import insurance.quote.app.domain.entities.Quote;
import insurance.quote.app.infrastructure.model.QuoteEntity;

public interface RecebeCotacaoSeguro {
  QuoteEntity execute(Quote quote);
}
