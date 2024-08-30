package insurance.quote.app.infrastructure.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "coverages")
public class CoverageEntity {

  public CoverageEntity(String name, Double amount) {
    this.name = name;
    this.amount = amount;
  }

  @Id
  private String id;
  private String name;
  private Double amount;
}