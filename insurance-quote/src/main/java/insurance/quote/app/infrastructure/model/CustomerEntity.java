package insurance.quote.app.infrastructure.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "customers")
public class CustomerEntity {
  @Id
  private String id;
  private String documentNumber;
  private String name;
  private String type;
  private String gender;
  private String dateOfBirth;
  private String email;
  private long phoneNumber;
}