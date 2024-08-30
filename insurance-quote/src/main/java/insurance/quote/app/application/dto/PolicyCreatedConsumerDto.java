package insurance.quote.app.application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PolicyCreatedConsumerDto {
  private String quoteId;
  private String policyId;
}
