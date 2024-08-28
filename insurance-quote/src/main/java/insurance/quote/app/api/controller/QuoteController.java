package insurance.quote.app.api.controller;

import insurance.quote.app.api.dto.QuoteRequestDto;
import insurance.quote.app.api.mapper.QuoteMapper;
import insurance.quote.app.application.usecase.CreateQuoteUseCase;
import insurance.quote.app.domain.model.Quote;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/quotes")
public class QuoteController {

    private final CreateQuoteUseCase createQuoteUseCase;
    private final QuoteMapper quoteMapper;

    public QuoteController(CreateQuoteUseCase createQuoteUseCase, QuoteMapper quoteMapper) {
        this.createQuoteUseCase = createQuoteUseCase;
        this.quoteMapper = quoteMapper;
    }

    @PostMapping
    public ResponseEntity<Quote> createQuote(@RequestBody QuoteRequestDto requestDto) {
        Quote quote = quoteMapper.toDomain(requestDto);
        Quote createdQuote = createQuoteUseCase.execute(quote);
        return ResponseEntity.ok(createdQuote);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Quote> getQuote(@PathVariable String id) {
        // Aqui pode-se adicionar lógica para buscar a cotação pelo ID
        return ResponseEntity.ok(null); // Placeholder
    }
}