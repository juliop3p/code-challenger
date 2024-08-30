package insurance.quote.app.infrastructure.persistence;

import insurance.quote.app.domain.entities.Quote;
import insurance.quote.app.domain.port.out.GetQuote;
import insurance.quote.app.infrastructure.jpa.QuoteRepository;
import insurance.quote.app.infrastructure.mapper.MapQuote;
import insurance.quote.app.infrastructure.model.QuoteEntity;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class JpaGetQuoteRepository implements GetQuote {

  private final QuoteRepository quoteRepository;

  @Override
  public Quote execute(String idQuote) {
    Optional<QuoteEntity> byId = quoteRepository.findById(idQuote);
    return MapQuote.mapEntityToQuote(byId.orElse(new QuoteEntity()));
  }
}
