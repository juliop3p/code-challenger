package insurance.quote.app.infrastructure.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class QuoteEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String productId;
  private String offerId;
  private String category;
  private double totalMonthlyPremiumAmount;
  private double totalCoverageAmount;
  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<CoverageEntity> coverages;
  private List<String> assistances;

  @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private CustomerEntity customer;
}
