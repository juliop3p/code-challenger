package insurance.quote.app.domain.interfaces;

import insurance.quote.app.infrastructure.response.InsuranceProduct;

public interface GetProductsAndOffers {
    InsuranceProduct execute();
}
