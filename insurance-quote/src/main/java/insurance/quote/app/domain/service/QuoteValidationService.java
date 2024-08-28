package insurance.quote.app.domain.service;

import insurance.quote.app.domain.exception.ValidationException;
import insurance.quote.app.domain.model.Quote;
import insurance.quote.app.infrastructure.dto.OfferDto;
import insurance.quote.app.infrastructure.dto.ProductDto;
import insurance.quote.app.infrastructure.external.CatalogApiClient;
import insurance.quote.app.utils.CoverageHelper;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
public class QuoteValidationService {

    private final CatalogApiClient catalogApiClient;

    public QuoteValidationService(CatalogApiClient catalogApiClient) {
        this.catalogApiClient = catalogApiClient;
    }

    public boolean isValid(Quote quote) {

        ProductDto product = catalogApiClient.getProductById(quote.getProductId());

        if (product == null || !product.isActive()) {
            throw new ValidationException("Produto inativo.");
        }

        OfferDto offer = catalogApiClient.getOfferById(quote.getOfferId());

        if (offer == null || !offer.isActive()) {
            throw new ValidationException("Oferta inativa.");
        }

        // Valida coberturas
        areCoveragesValid(CoverageHelper.convertListToMap(quote.getCoverages()), offer);

        // Valida assistências
        areAssistancesValid(quote.getAssistances(), offer);

        // Valida valor do prêmio
        isPremiumAmountValid(quote.getTotalMonthlyPremiumAmount(), offer);

        // Valida total das coberturas
        isCoverageTotalValid(CoverageHelper.convertListToMap(quote.getCoverages()), quote.getTotalCoverageAmount());

        return true;
    }

    public boolean areCoveragesValid(Map<String, Double> coveragesDesired, OfferDto offer) {
        Map<String, Double> coveragesAvailable = offer.getCoverages();

        // Verifica se todas as coberturas desejadas estão na lista de coberturas disponíveis
        for (Map.Entry<String, Double> entry : coveragesDesired.entrySet()) {
            String coverageType = entry.getKey();
            double coverageAmount = entry.getValue();

            // Verifica se o tipo de cobertura está disponível
            if (!coveragesAvailable.containsKey(coverageType)) {
                throw new ValidationException("Cobertura não disponível: " + coverageType);
            }

            // Verifica se o valor da cobertura desejada não excede o valor máximo permitido
            double maxCoverageAmount = coveragesAvailable.get(coverageType);
            if (coverageAmount > maxCoverageAmount) {
                throw new ValidationException("Valor da cobertura excede o permitido para: " + coverageType);
            }
        }

        return true;
    }

    public boolean areAssistancesValid(List<String> assistances, OfferDto offer) {
        Set<String> allowedAssistancesSet = new HashSet<>(offer.getAssistances());

        // Verifica se todas as assistências estão permitidas
        for (String assistance : assistances) {
            if (!allowedAssistancesSet.contains(assistance)) {
                throw new ValidationException("Assistência não permitida: " + assistance);
            }
        }

        return true;
    }

    public boolean isPremiumAmountValid(double premiumAmount, OfferDto offer) {
        if (premiumAmount < offer.getMinPremiumAmount() || premiumAmount > offer.getMaxPremiumAmount()) {
            throw new ValidationException("Valor do prêmio fora dos limites permitidos: " +
                    offer.getMinPremiumAmount() + " - " + offer.getMaxPremiumAmount());
        }
        return true;
    }

    public boolean isCoverageTotalValid(Map<String, Double> coverages, double totalCoverageAmount) {
        double calculatedTotalCoverageAmount = 0.0;

        for (Double amount : coverages.values()) {
            calculatedTotalCoverageAmount += amount;
        }

        if (Math.abs(calculatedTotalCoverageAmount - totalCoverageAmount) > 0.01) { // Tolerância para erros de ponto flutuante
            throw new ValidationException("O valor total das coberturas não corresponde ao valor total informado.");
        }

        return true;
    }


}
