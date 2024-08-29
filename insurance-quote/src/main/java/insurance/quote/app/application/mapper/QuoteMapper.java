package insurance.quote.app.application.mapper;

import insurance.quote.app.application.dto.CustomerDto;
import insurance.quote.app.application.dto.QuoteRequestDto;
import insurance.quote.app.domain.entities.Customer;
import insurance.quote.app.domain.entities.Quote;
import insurance.quote.app.utils.CoverageHelper;
import org.springframework.stereotype.Component;

@Component
public class QuoteMapper {
    public Quote toDomain(QuoteRequestDto dto) {
        Quote quote = new Quote();
        quote.setProductId(dto.getProductId());
        quote.setOfferId(dto.getOfferId());
        quote.setCategory(dto.getCategory());
        quote.setTotalMonthlyPremiumAmount(dto.getTotalMonthlyPremiumAmount());
        quote.setTotalCoverageAmount(dto.getTotalCoverageAmount());
        quote.setCoverages(CoverageHelper.convertMapToList(dto.getCoverages()));
        quote.setAssistances(dto.getAssistances());
        quote.setCustomer(convertToEntity(dto.getCustomer()));
        return quote;
    }

    public Customer convertToEntity(CustomerDto dto) {
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
}
