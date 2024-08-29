package insurance.quote.app.domain.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Offer {
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
