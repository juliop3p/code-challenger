package insurance.quote.app.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class QuoteCreatedResponseDto {
    public String message;
    public String idQuote;
}
