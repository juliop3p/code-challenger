package insurance.quote.app.infrastructure.external;

import insurance.quote.app.domain.exception.ValidationException;
import insurance.quote.app.infrastructure.dto.OfferDto;
import insurance.quote.app.infrastructure.dto.ProductDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class CatalogApiClient {

    private final RestTemplate restTemplate;
    private static final String CATALOG_SERVICE_URL = "http://localhost:1080/api";

    public CatalogApiClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ProductDto getProductById(String productId) {
        String url = CATALOG_SERVICE_URL + "/products/" + productId;

        try {
            ResponseEntity<ProductDto> response = restTemplate.getForEntity(url, ProductDto.class);

            if (response.getStatusCode() == HttpStatus.OK && response.hasBody()) {
                return response.getBody();
            } else {
                throw new ValidationException("Produto não encontrado para o ID: " + productId);
            }
        } catch (HttpClientErrorException.NotFound e) {
            throw new ValidationException("Produto não encontrado para o ID: " + productId);
        } catch (HttpClientErrorException e) {
            throw new RuntimeException("Erro HTTP ao buscar produto com ID: " + productId + ". Status: " + e.getStatusCode(), e);
        } catch (Exception e) {
            throw new RuntimeException("Erro inesperado ao buscar produto com ID: " + productId, e);
        }
    }

    public OfferDto getOfferById(String offerId) {
        String url = CATALOG_SERVICE_URL + "/offers/" + offerId;

        try {
            ResponseEntity<OfferDto> response = restTemplate.getForEntity(url, OfferDto.class);

            if (response.getStatusCode() == HttpStatus.OK && response.hasBody()) {
                return response.getBody();
            } else {
                throw new ValidationException("Oferta não encontrada para o ID: " + offerId);
            }
        } catch (HttpClientErrorException.NotFound e) {
            throw new ValidationException("Oferta não encontrada para o ID: " + offerId);
        } catch (HttpClientErrorException e) {
            throw new RuntimeException("Erro HTTP ao buscar oferta com ID: " + offerId + ". Status: " + e.getStatusCode(), e);
        } catch (Exception e) {
            throw new RuntimeException("Erro inesperado ao buscar oferta com ID: " + offerId, e);
        }
    }
}