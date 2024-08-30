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
public class CustomerEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;
  private String documentNumber;
  private String name;
  private String type;
  private String gender;
  private String dateOfBirth;
  private String email;
  private long phoneNumber;
}
