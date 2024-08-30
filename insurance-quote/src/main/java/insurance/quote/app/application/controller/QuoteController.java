package insurance.quote.app.application.controller;

import insurance.quote.app.application.dto.QuoteCreatedResponseDto;
import insurance.quote.app.application.dto.QuoteResponseDto;
import insurance.quote.app.application.dto.QuoteRequestDto;
import insurance.quote.app.application.mapper.QuoteMapper;
import insurance.quote.app.domain.entities.Quote;
import insurance.quote.app.domain.port.in.GetInsuranceQuote;
import insurance.quote.app.domain.port.in.CreateInsuranceQuote;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
  private final CreateInsuranceQuote createInsuranceQuote;
  private final GetInsuranceQuote getInsuranceQuote;


  @PostMapping
  public ResponseEntity<QuoteCreatedResponseDto> createQuote(@RequestBody QuoteRequestDto requestDto) {
    Quote quote = quoteMapper.toQuoteDomain(requestDto);
    String quoteId = createInsuranceQuote.execute(quote);
    QuoteCreatedResponseDto quoteCreatedResponseDto = new QuoteCreatedResponseDto("Cotação recebida com sucesso", quoteId);
    return new ResponseEntity<>(quoteCreatedResponseDto, HttpStatus.CREATED);
  }

  @GetMapping("/{id}")
  public ResponseEntity<QuoteResponseDto> getQuote(@PathVariable String id) {
    Quote quote = getInsuranceQuote.execute(id);
    return ResponseEntity.ok(quoteMapper.toQuoteDto(quote));
  }
}