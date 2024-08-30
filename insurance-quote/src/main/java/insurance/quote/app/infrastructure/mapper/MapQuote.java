package insurance.quote.app.infrastructure.mapper;

import insurance.quote.app.domain.entities.Coverage;
import insurance.quote.app.domain.entities.Customer;
import insurance.quote.app.domain.entities.Quote;
import insurance.quote.app.infrastructure.model.CoverageEntity;
import insurance.quote.app.infrastructure.model.CustomerEntity;
import insurance.quote.app.infrastructure.model.QuoteEntity;
import java.util.List;
import java.util.stream.Collectors;

public class MapQuote {

  private MapQuote() {
  }

  public static QuoteEntity mapQuoteToEntity(Quote quote) {
    QuoteEntity quoteEntity = new QuoteEntity();
    quoteEntity.setId(quote.getId());
    quoteEntity.setProductId(quote.getProductId());
    quoteEntity.setInsurancePolicyId(quote.getInsurancePolicyId());
    quoteEntity.setOfferId(quote.getOfferId());
    quoteEntity.setCategory(quote.getCategory());
    quoteEntity.setTotalMonthlyPremiumAmount(quote.getTotalMonthlyPremiumAmount());
    quoteEntity.setTotalCoverageAmount(quote.getTotalCoverageAmount());
    quoteEntity.setCreatedAt(quote.getCreatedAt());
    quoteEntity.setUpdatedAt(quote.getUpdatedAt());

    List<CoverageEntity> listCoverageEntity = quote.getCoverages().stream()
        .map(coverage -> new CoverageEntity(coverage.getId(), coverage.getName(),
            coverage.getAmount())).collect(Collectors.toList());
    quoteEntity.setCoverages(listCoverageEntity);

    quoteEntity.setAssistances(quote.getAssistances());

    CustomerEntity customerEntity = getCustomerEntity(quote);
    quoteEntity.setCustomer(customerEntity);
    return quoteEntity;
  }

  private static CustomerEntity getCustomerEntity(Quote quote) {
    CustomerEntity customerEntity = new CustomerEntity();
    customerEntity.setId(quote.getCustomer().getId());
    customerEntity.setDocumentNumber(quote.getCustomer().getDocumentNumber());
    customerEntity.setName(quote.getCustomer().getName());
    customerEntity.setType(quote.getCustomer().getType());
    customerEntity.setGender(quote.getCustomer().getGender());
    customerEntity.setDateOfBirth(quote.getCustomer().getDateOfBirth());
    customerEntity.setEmail(quote.getCustomer().getEmail());
    customerEntity.setPhoneNumber(quote.getCustomer().getPhoneNumber());
    return customerEntity;
  }

  public static Quote mapEntityToQuote(QuoteEntity quoteEntity) {
    Quote quote = new Quote();
    quote.setId(quoteEntity.getId());
    quote.setProductId(quoteEntity.getProductId());
    quote.setInsurancePolicyId(quoteEntity.getInsurancePolicyId());
    quote.setOfferId(quoteEntity.getOfferId());
    quote.setCategory(quoteEntity.getCategory());
    quote.setTotalMonthlyPremiumAmount(quoteEntity.getTotalMonthlyPremiumAmount());
    quote.setTotalCoverageAmount(quoteEntity.getTotalCoverageAmount());
    quote.setCreatedAt(quoteEntity.getCreatedAt());
    quote.setUpdatedAt(quote.getUpdatedAt());

    List<Coverage> listCoverage = quoteEntity.getCoverages().stream()
        .map(coverageEntity -> new Coverage(coverageEntity.getId(), coverageEntity.getName(),
            coverageEntity.getAmount())).collect(Collectors.toList());
    quote.setCoverages(listCoverage);

    quote.setAssistances(quoteEntity.getAssistances());

    Customer customer = getCustomer(quoteEntity);
    quote.setCustomer(customer);

    return quote;
  }

  private static Customer getCustomer(QuoteEntity quoteEntity) {
    Customer customer = new Customer();
    customer.setId(quoteEntity.getCustomer().getId());
    customer.setDocumentNumber((quoteEntity.getCustomer().getDocumentNumber()));
    customer.setName((quoteEntity.getCustomer().getName()));
    customer.setType((quoteEntity.getCustomer().getType()));
    customer.setGender(quoteEntity.getCustomer().getGender());
    customer.setDateOfBirth(quoteEntity.getCustomer().getDateOfBirth());
    customer.setEmail(quoteEntity.getCustomer().getEmail());
    customer.setPhoneNumber(quoteEntity.getCustomer().getPhoneNumber());
    return customer;
  }

}
