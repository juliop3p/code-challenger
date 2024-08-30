package insurance.quote.app.infrastructure.persistence;

import insurance.quote.app.domain.entities.Quote;
import insurance.quote.app.domain.port.out.UpdateQuote;
import insurance.quote.app.infrastructure.jpa.QuoteRepository;
import insurance.quote.app.infrastructure.mapper.MapQuote;
import insurance.quote.app.infrastructure.model.QuoteEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JpaUpdateQuoteRepository implements UpdateQuote {
  private final QuoteRepository quoteRepository;
  @Override
  public Quote execute(Quote quote) {
    QuoteEntity quoteEntity = MapQuote.mapQuoteToEntity(quote);
    quoteRepository.save(quoteEntity);
    return MapQuote.mapEntityToQuote(quoteEntity);
  }
}
