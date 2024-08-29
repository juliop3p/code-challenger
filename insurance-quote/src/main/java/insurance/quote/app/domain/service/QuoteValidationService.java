package insurance.quote.app.domain.service;

import insurance.quote.app.domain.entities.Offer;
import insurance.quote.app.domain.entities.Product;
import insurance.quote.app.domain.entities.Quote;
import insurance.quote.app.domain.exception.ValidationException;
import insurance.quote.app.domain.port.in.RecebeCotacaoSeguro;
import insurance.quote.app.domain.port.out.GerarPolicy;
import insurance.quote.app.domain.port.out.GetCatalogProducts;
import insurance.quote.app.domain.port.out.SaveQuote;
import insurance.quote.app.infrastructure.model.QuoteEntity;
import insurance.quote.app.utils.CoverageHelper;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuoteValidationService implements RecebeCotacaoSeguro {

  private final SaveQuote saveQuote;
  private final GetCatalogProducts getCatalogProducts;
  private final GerarPolicy gerarPolicy;

  @Override
  public QuoteEntity execute(Quote quote) {
    Product product = getCatalogProducts.obtemProdutos(quote.getProductId());
    Offer offer = getCatalogProducts.obtemOfertas(quote.getOfferId());

    if (product == null || !product.isActive()) {
      throw new ValidationException("Produto inativo.");
    }

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
    isCoverageTotalValid(CoverageHelper.convertListToMap(quote.getCoverages()),
        quote.getTotalCoverageAmount());

    gerarPolicy.execute();
    return saveQuote.execute(quote);
  }

  public boolean areCoveragesValid(Map<String, Double> coveragesDesired, Offer offer) {
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
        throw new ValidationException(
            "Valor da cobertura excede o permitido para: " + coverageType);
      }
    }

    return true;
  }

  public boolean areAssistancesValid(List<String> assistances, Offer offer) {
    Set<String> allowedAssistancesSet = new HashSet<>(offer.getAssistances());

    // Verifica se todas as assistências estão permitidas
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
        > 0.01) { // Tolerância para erros de ponto flutuante
      throw new ValidationException(
          "O valor total das coberturas não corresponde ao valor total informado.");
    }

    return true;
  }

}
