package insurance.quote.app.infrastructure.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class OfferDto {
    @JsonProperty("id")
    private String id;

    @JsonProperty("product_id")
    private String productId;

    @JsonProperty("name")
    private String name;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("active")
    private boolean active;

    @JsonProperty("coverages")
    private Map<String, Double> coverages;

    @JsonProperty("assistances")
    private List<String> assistances;

    @JsonProperty("monthly_premium_amount")
    private Map<String, Double> monthlyPremiumAmount;

    public double getMinPremiumAmount() {
        return monthlyPremiumAmount.get("min_amount");
    }

    public double getMaxPremiumAmount() {
        return monthlyPremiumAmount.get("max_amount");
    }

    public double getSuggestedAmount() {
        return monthlyPremiumAmount.get("suggested_amount");
    }
}
