package insurance.quote.app.domain.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {
    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("active")
    private boolean active;

    @JsonProperty("offers")
    private List<String> offers;
}
