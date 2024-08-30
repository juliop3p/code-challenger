package insurance.quote.app.infrastructure.external;

import insurance.quote.app.domain.entities.Offer;
import insurance.quote.app.domain.entities.Product;
import insurance.quote.app.domain.exception.ValidationException;
import insurance.quote.app.domain.port.out.GetCatalogProducts;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class CatalogApiClientImpl implements GetCatalogProducts {

  private static final String CATALOG_SERVICE_URL = "http://localhost:1080/api";
  private final RestTemplate restTemplate;


  @Override
  public Product getProducts(String id) {
    String url = CATALOG_SERVICE_URL + "/products/" + id;

    try {
      ResponseEntity<Product> response = restTemplate.getForEntity(url, Product.class);

      if (response.getStatusCode() == HttpStatus.OK && response.hasBody()) {
        return response.getBody();
      } else {
        throw new ValidationException("Produto não encontrado para o ID: " + id);
      }
    } catch (HttpClientErrorException.NotFound e) {
      throw new ValidationException("Produto não encontrado para o ID: " + id);
    } catch (HttpClientErrorException e) {
      throw new RuntimeException(
          "Erro HTTP ao buscar produto com ID: " + id + ". Status: " + e.getStatusCode(), e);
    } catch (Exception e) {
      throw new RuntimeException("Erro inesperado ao buscar produto com ID: " + id, e);
    }
  }

  @Override
  public Offer getOffers(String id) {
    String url = CATALOG_SERVICE_URL + "/offers/" + id;

    try {
      ResponseEntity<Offer> response = restTemplate.getForEntity(url, Offer.class);

      if (response.getStatusCode() == HttpStatus.OK && response.hasBody()) {
        return response.getBody();
      } else {
        throw new ValidationException("Oferta não encontrada para o ID: " + id);
      }
    } catch (HttpClientErrorException.NotFound e) {
      throw new ValidationException("Oferta não encontrada para o ID: " + id);
    } catch (HttpClientErrorException e) {
      throw new RuntimeException(
          "Erro HTTP ao buscar oferta com ID: " + id + ". Status: " + e.getStatusCode(), e);
    } catch (Exception e) {
      throw new RuntimeException("Erro inesperado ao buscar oferta com ID: " + id, e);
    }
  }
}