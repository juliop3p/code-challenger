package insurance.quote.app.domain.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Coverage {

  private Long id;
  private String name;
  private Double amount;
  private Quote quote;
}
