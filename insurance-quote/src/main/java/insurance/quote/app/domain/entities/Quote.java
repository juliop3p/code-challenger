package insurance.quote.app.domain.entities;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Quote {

  private Long id;
  private String productId;
  private String offerId;
  private String category;
  private double totalMonthlyPremiumAmount;
  private double totalCoverageAmount;
  private List<Coverage> coverages;
  private List<String> assistances;
  private Customer customer;
}
