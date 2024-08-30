package insurance.quote.app.application.mapper;

import insurance.quote.app.application.dto.CustomerResponseDto;
import insurance.quote.app.application.dto.QuoteResponseDto;
import insurance.quote.app.application.dto.QuoteRequestDto;
import insurance.quote.app.domain.entities.Customer;
import insurance.quote.app.domain.entities.Quote;
import insurance.quote.app.utils.CoverageHelper;
import org.springframework.stereotype.Component;

@Component
public class QuoteMapper {
    public Quote toQuoteDomain(QuoteRequestDto dto) {
        Quote quote = new Quote();
        quote.setProductId(dto.getProductId());
        quote.setOfferId(dto.getOfferId());
        quote.setCategory(dto.getCategory());
        quote.setTotalMonthlyPremiumAmount(dto.getTotalMonthlyPremiumAmount());
        quote.setTotalCoverageAmount(dto.getTotalCoverageAmount());
        quote.setCoverages(CoverageHelper.convertMapToList(dto.getCoverages()));
        quote.setAssistances(dto.getAssistances());
        quote.setCustomer(toCustomerDomain(dto.getCustomer()));
        return quote;
    }

    public Customer toCustomerDomain(CustomerResponseDto dto) {
        Customer customer = new Customer();
        customer.setDocumentNumber(dto.getDocumentNumber());
        customer.setName(dto.getName());
        customer.setType(dto.getType());
        customer.setGender(dto.getGender());
        customer.setDateOfBirth(dto.getDateOfBirth());
        customer.setEmail(dto.getEmail());
        customer.setPhoneNumber(dto.getPhoneNumber());
        return customer;
    }

    public QuoteResponseDto toQuoteDto(Quote quote) {
        QuoteResponseDto quoteResponseDto = new QuoteResponseDto();
        quoteResponseDto.setId(quote.getId());
        quoteResponseDto.setInsurancePolicyId(quote.getInsurancePolicyId());
        quoteResponseDto.setProductId(quote.getProductId());
        quoteResponseDto.setOfferId(quote.getOfferId());
        quoteResponseDto.setCategory(quote.getCategory());
        quoteResponseDto.setCreatedAt(quote.getCreatedAt());
        quoteResponseDto.setUpdatedAt(quote.getUpdatedAt());
        quoteResponseDto.setTotalMonthlyPremiumAmount(quote.getTotalMonthlyPremiumAmount());
        quoteResponseDto.setTotalCoverageAmount(quote.getTotalCoverageAmount());
        quoteResponseDto.setCoverages(CoverageHelper.convertListToMap(quote.getCoverages()));
        quoteResponseDto.setAssistances(quote.getAssistances());
        quoteResponseDto.setCustomer(toCustomerDto(quote.getCustomer()));

        return quoteResponseDto;
    }

    public CustomerResponseDto toCustomerDto(Customer customer) {
        CustomerResponseDto customerResponseDto = new CustomerResponseDto();
        customerResponseDto.setDocumentNumber(customer.getDocumentNumber());
        customerResponseDto.setName(customer.getName());
        customerResponseDto.setType(customer.getType());
        customerResponseDto.setGender(customer.getGender());
        customerResponseDto.setDateOfBirth(customer.getDateOfBirth());
        customerResponseDto.setEmail(customer.getEmail());
        customerResponseDto.setPhoneNumber(customer.getPhoneNumber());
        return customerResponseDto;
    }
}
