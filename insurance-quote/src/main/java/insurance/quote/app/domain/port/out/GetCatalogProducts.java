package insurance.quote.app.domain.port.out;

import insurance.quote.app.domain.entities.Offer;
import insurance.quote.app.domain.entities.Product;

public interface GetCatalogProducts {
  Product obtemProdutos(String id);

  Offer obtemOfertas(String id);
}
