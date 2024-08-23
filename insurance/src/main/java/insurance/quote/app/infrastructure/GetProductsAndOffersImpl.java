package insurance.quote.app.infrastructure;

import insurance.quote.app.domain.interfaces.GetProductsAndOffers;
import insurance.quote.app.infrastructure.response.InsuranceProduct;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class GetProductsAndOffersImpl implements GetProductsAndOffers {

    @Override
    public InsuranceProduct execute() {
        try {
            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("https://api.example.com/data"))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new InsuranceProduct();
    }
}
