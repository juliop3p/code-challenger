package insurance.quote.app.domain;

import insurance.quote.app.domain.model.SolicitationInsuranceQuote;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class InsuranceQuoteTest {

    final InsuranceQuote setup() {
        return new InsuranceQuote();
    }

    @Test
    @DisplayName("Should Validate Insurance Request")
    void testOne() {
        InsuranceQuote setup = setup();
        setup.execute(new SolicitationInsuranceQuote());

    }
}