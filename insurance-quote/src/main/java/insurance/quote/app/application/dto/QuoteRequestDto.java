package insurance.quote.app.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class QuoteRequestDto {
    @JsonProperty("product_id")
    private String productId;

    @JsonProperty("offer_id")
    private String offerId;

    @JsonProperty("category")
    private String category;

    @JsonProperty("total_monthly_premium_amount")
    private double totalMonthlyPremiumAmount;

    @JsonProperty("total_coverage_amount")
    private double totalCoverageAmount;

    @JsonProperty("coverages")
    private Map<String, Double> coverages;

    @JsonProperty("assistances")
    private List<String> assistances;

    @JsonProperty("customer")
    private CustomerDto customer;
}
