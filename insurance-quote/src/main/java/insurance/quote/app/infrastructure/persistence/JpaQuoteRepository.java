package insurance.quote.app.infrastructure.persistence;

import insurance.quote.app.domain.model.Quote;
import insurance.quote.app.domain.repository.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JpaQuoteRepository {

    private final QuoteRepository quoteRepository;

    @Autowired
    public JpaQuoteRepository(QuoteRepository quoteRepository) {
        this.quoteRepository = quoteRepository;
    }

    public Quote saveQuote(Quote quote) {
        return quoteRepository.save(quote);
    }
}
