package insurance.quote.app.domain.model;

import insurance.quote.app.api.dto.CustomerDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@Entity
@Table(name = "quotes")
public class Quote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productId;
    private String offerId;
    private String category;
    private double totalMonthlyPremiumAmount;
    private double totalCoverageAmount;

    @OneToMany(mappedBy = "quote", cascade = CascadeType.ALL)
    private List<Coverage> coverages;

    private List<String> assistances;

    @ManyToOne(cascade = CascadeType.ALL)
    private Customer customer;
}
