package insurance.quote.app.domain;

import insurance.quote.app.domain.exception.ProductInactiveException;
import insurance.quote.app.domain.interfaces.GetProductsAndOffers;
import insurance.quote.app.domain.model.SolicitationInsuranceQuote;
import insurance.quote.app.infrastructure.response.InsuranceProduct;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class InsuranceQuote {
    // domain: receber, consultar, salvar e validar
    private final GetProductsAndOffers getProductsAndOffers;

    public void execute(SolicitationInsuranceQuote solicitationInsuranceQuote) {
        InsuranceProduct response = getProductsAndOffers.execute();

        if(response.isActive()) {

        } else {
            throw new ProductInactiveException("Product is not active.");
        }

    }
}
