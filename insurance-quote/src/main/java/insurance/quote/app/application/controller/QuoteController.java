package insurance.quote.app.application.controller;

import insurance.quote.app.application.dto.QuoteRequestDto;
import insurance.quote.app.application.mapper.QuoteMapper;
import insurance.quote.app.domain.entities.Quote;
import insurance.quote.app.domain.port.in.RecebeCotacaoSeguro;
import insurance.quote.app.infrastructure.model.QuoteEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/quotes")
@RequiredArgsConstructor
public class QuoteController {

  private final QuoteMapper quoteMapper;
  private final RecebeCotacaoSeguro recebeCotacaoSeguro;


  @PostMapping
  public ResponseEntity<QuoteEntity> createQuote(@RequestBody QuoteRequestDto requestDto) {
    Quote quote = quoteMapper.toDomain(requestDto);
    QuoteEntity createdQuote = recebeCotacaoSeguro.execute(quote);
    return ResponseEntity.ok(createdQuote);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Quote> getQuote(@PathVariable String id) {
    // Aqui pode-se adicionar lógica para buscar a cotação pelo ID
    return ResponseEntity.ok(null); // Placeholder
  }
}