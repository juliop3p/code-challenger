package insurance.quote.app.infrastructure.persistence;

import insurance.quote.app.domain.entities.Quote;
import insurance.quote.app.domain.port.out.SaveQuote;
import insurance.quote.app.infrastructure.jpa.QuoteRepository;
import insurance.quote.app.infrastructure.model.CoverageEntity;
import insurance.quote.app.infrastructure.model.CustomerEntity;
import insurance.quote.app.infrastructure.model.QuoteEntity;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JpaQuoteRepository implements SaveQuote {

  private final QuoteRepository quoteRepository;

  @Override
  public QuoteEntity execute(Quote quote) {

    QuoteEntity quoteEntity = mapQuoteToEntity(quote);
    QuoteEntity save = quoteRepository.save(quoteEntity);
    return save;
  }

  private QuoteEntity mapQuoteToEntity(Quote quote) {
    QuoteEntity quoteEntity = new QuoteEntity();
    quoteEntity.setProductId(quote.getProductId());
    quoteEntity.setOfferId(quote.getOfferId());
    quoteEntity.setCategory(quote.getCategory());
    quoteEntity.setTotalMonthlyPremiumAmount(quote.getTotalMonthlyPremiumAmount());
    quoteEntity.setTotalCoverageAmount(quote.getTotalCoverageAmount());

    List<CoverageEntity> listCoverageEntity = quote.getCoverages().stream()
        .map(coverage -> new CoverageEntity(coverage.getName(),
            coverage.getAmount())).collect(Collectors.toList());
    quoteEntity.setCoverages(listCoverageEntity);

    quoteEntity.setAssistances(quote.getAssistances());

    CustomerEntity customerEntity = new CustomerEntity();
    customerEntity.setName(quote.getCustomer().getName());
    quoteEntity.setCustomer(customerEntity);
    return quoteEntity;
  }
}
