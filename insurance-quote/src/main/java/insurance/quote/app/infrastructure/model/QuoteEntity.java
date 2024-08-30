package insurance.quote.app.infrastructure.model;

import org.springframework.data.annotation.Id;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@ToString
@Document(collection = "quotes")
public class QuoteEntity {

  @Id
  private String id;
  private String insurancePolicyId;
  private String productId;
  private String offerId;
  private String category;
  private double totalMonthlyPremiumAmount;
  private double totalCoverageAmount;

  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

  private List<CoverageEntity> coverages;

  private List<String> assistances;

  private CustomerEntity customer;

  public QuoteEntity() {
    this.createdAt = LocalDateTime.now();
    this.updatedAt = LocalDateTime.now();
  }

  public void prePersist() {
    this.createdAt = LocalDateTime.now();
  }

  public void preUpdate() {
    this.updatedAt = LocalDateTime.now();
  }
}
