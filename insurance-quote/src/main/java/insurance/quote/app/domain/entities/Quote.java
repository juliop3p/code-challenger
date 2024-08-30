package insurance.quote.app.domain.entities;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Quote {

  private String id;
  private String insurancePolicyId;
  private String productId;
  private String offerId;
  private String category;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
  private double totalMonthlyPremiumAmount;
  private double totalCoverageAmount;
  private List<Coverage> coverages;
  private List<String> assistances;
  private Customer customer;
}
