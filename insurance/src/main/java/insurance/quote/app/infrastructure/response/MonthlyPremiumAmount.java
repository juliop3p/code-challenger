package insurance.quote.app.infrastructure.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MonthlyPremiumAmount {
    private double maxAmount;
    private double minAmount;
    private double suggestedAmount;
}
