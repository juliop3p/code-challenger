package insurance.quote.app.domain.service;

import insurance.quote.app.domain.entities.Offer;
import insurance.quote.app.domain.entities.Product;
import insurance.quote.app.domain.entities.Quote;
import insurance.quote.app.domain.exception.ValidationException;
import insurance.quote.app.domain.port.in.CreateInsuranceQuote;
import insurance.quote.app.domain.port.out.GeneratePolicy;
import insurance.quote.app.domain.port.out.GetCatalogProducts;
import insurance.quote.app.domain.port.out.SaveQuote;
import insurance.quote.app.utils.CoverageHelper;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GenerateQuoteService implements CreateInsuranceQuote {

  private final SaveQuote saveQuote;
  private final GetCatalogProducts getCatalogProducts;
  private final GeneratePolicy generatePolicy;

  @Override
  public String execute(insurance.quote.app.domain.entities.Quote quote) {
    Product product = getCatalogProducts.getProducts(quote.getProductId());
    Offer offer = getCatalogProducts.getOffers(quote.getOfferId());

    isValid(quote, product, offer);

    Quote response = saveQuote.execute(quote);

    generatePolicy.execute(response.getId());

    return response.getId();
  }

  private void isValid(insurance.quote.app.domain.entities.Quote quote, Product product, Offer offer) {
    if (product == null || !product.isActive()) {
      throw new ValidationException("Produto inativo.");
    }

    if (offer == null || !offer.isActive()) {
      throw new ValidationException("Oferta inativa.");
    }

    areCoveragesValid(CoverageHelper.convertListToMap(quote.getCoverages()), offer);

    areAssistancesValid(quote.getAssistances(), offer);

    isPremiumAmountValid(quote.getTotalMonthlyPremiumAmount(), offer);

    isCoverageTotalValid(CoverageHelper.convertListToMap(quote.getCoverages()),
        quote.getTotalCoverageAmount());
  }

  public boolean areCoveragesValid(Map<String, Double> coveragesDesired, Offer offer) {
    Map<String, Double> coveragesAvailable = offer.getCoverages();

    for (Map.Entry<String, Double> entry : coveragesDesired.entrySet()) {
      String coverageType = entry.getKey();
      double coverageAmount = entry.getValue();

      if (!coveragesAvailable.containsKey(coverageType)) {
        throw new ValidationException("Cobertura não disponível: " + coverageType);
      }

      double maxCoverageAmount = coveragesAvailable.get(coverageType);
      if (coverageAmount > maxCoverageAmount) {
        throw new ValidationException(
            "Valor da cobertura excede o permitido para: " + coverageType);
      }
    }

    return true;
  }

  public boolean areAssistancesValid(List<String> assistances, Offer offer) {
    Set<String> allowedAssistancesSet = new HashSet<>(offer.getAssistances());

    for (String assistance : assistances) {
      if (!allowedAssistancesSet.contains(assistance)) {
        throw new ValidationException("Assistência não permitida: " + assistance);
      }
    }

    return true;
  }

  public boolean isPremiumAmountValid(double premiumAmount, Offer offer) {
    if (premiumAmount < offer.getMinPremiumAmount()
        || premiumAmount > offer.getMaxPremiumAmount()) {
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

    if (Math.abs(calculatedTotalCoverageAmount - totalCoverageAmount)
        > 0.01) {
      throw new ValidationException(
          "O valor total das coberturas não corresponde ao valor total informado.");
    }

    return true;
  }

}
