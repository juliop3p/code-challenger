package insurance.quote.app.domain.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Customer {

  private String id;
  private String documentNumber;
  private String name;
  private String type;
  private String gender;
  private String dateOfBirth;
  private String email;
  private long phoneNumber;
}
