package insurance.quote.app.application.usecase;

import insurance.quote.app.domain.exception.ValidationException;
import insurance.quote.app.domain.model.Quote;
import insurance.quote.app.domain.repository.QuoteRepository;
import insurance.quote.app.domain.service.QuoteValidationService;
import insurance.quote.app.infrastructure.persistence.JpaQuoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreateQuoteUseCase {
    private final JpaQuoteRepository quoteRepository;
    private final QuoteValidationService quoteValidationService;

    public Quote execute(Quote quote) {
        // Validar a cotação
        if (!quoteValidationService.isValid(quote)) {
            throw new ValidationException("Invalid quote");
        }

        // Persistir cotação no banco
        return quoteRepository.saveQuote(quote);
    }
}
