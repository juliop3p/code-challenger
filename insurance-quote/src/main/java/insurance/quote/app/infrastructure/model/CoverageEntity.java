package insurance.quote.app.infrastructure.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
public class CoverageEntity {

  public CoverageEntity(String name, Double amount) {
    this.name = name;
    this.amount = amount;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String name;
  private Double amount;
}
