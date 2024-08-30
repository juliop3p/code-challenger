package insurance.quote.app.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class QuoteResponseDto {
    @JsonProperty("id")
    private String id;

    @JsonProperty("insurance_policy_id")
    private String insurancePolicyId;

    @JsonProperty("product_id")
    private String productId;

    @JsonProperty("offer_id")
    private String offerId;

    @JsonProperty("category")
    private String category;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;

    @JsonProperty("total_monthly_premium_amount")
    private double totalMonthlyPremiumAmount;

    @JsonProperty("total_coverage_amount")
    private double totalCoverageAmount;

    @JsonProperty("coverages")
    private Map<String, Double> coverages;

    @JsonProperty("assistances")
    private List<String> assistances;

    @JsonProperty("customer")
    private CustomerResponseDto customer;
}
