package insurance.quote.app.infrastructure.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@ToString
public class QuoteEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;
  private String insurancePolicyId;
  private String productId;
  private String offerId;
  private String category;
  private double totalMonthlyPremiumAmount;
  private double totalCoverageAmount;

  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

  @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
  private List<CoverageEntity> coverages;

  private List<String> assistances;

  @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
  private CustomerEntity customer;

  @PrePersist
  protected void onCreate() {
    createdAt = LocalDateTime.now();
  }

  @PreUpdate
  protected void onUpdate() {
    updatedAt = LocalDateTime.now();
  }
}
