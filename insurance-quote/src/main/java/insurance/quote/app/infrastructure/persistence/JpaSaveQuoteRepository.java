package insurance.quote.app.infrastructure.persistence;

import insurance.quote.app.domain.entities.Quote;
import insurance.quote.app.domain.port.out.SaveQuote;
import insurance.quote.app.infrastructure.jpa.QuoteRepository;
import insurance.quote.app.infrastructure.mapper.MapQuote;
import insurance.quote.app.infrastructure.model.QuoteEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class JpaSaveQuoteRepository implements SaveQuote {

  private final QuoteRepository quoteRepository;


  @Override
  @Transactional
  public Quote execute(Quote quote) {
    QuoteEntity quoteEntity = MapQuote.mapQuoteToEntity(quote);
    QuoteEntity entity = quoteRepository.insert(quoteEntity);
    return MapQuote.mapEntityToQuote(entity);
  }
}
