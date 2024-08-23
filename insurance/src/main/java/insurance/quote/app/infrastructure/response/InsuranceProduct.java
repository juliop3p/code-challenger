package insurance.quote.app.infrastructure.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class InsuranceProduct {
    private String id;
    private String productId;
    private String name;
    private LocalDateTime createdAt;
    private boolean active;
    private Map<String, Double> coverages;
    private List<String> assistances;
    private MonthlyPremiumAmount monthlyPremiumAmount;
}
