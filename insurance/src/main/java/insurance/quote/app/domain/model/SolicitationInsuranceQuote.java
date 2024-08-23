package insurance.quote.app.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class SolicitationInsuranceQuote {
    private String productId;
    private String offerId;
    private String category;
    private double totalMonthlyPremiumAmount;
    private double totalCoverageAmount;
    private Map<String, Double> coverages;
    private List<String> assistances;
    private Customer customer;
}
