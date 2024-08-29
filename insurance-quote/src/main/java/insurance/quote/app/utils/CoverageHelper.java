package insurance.quote.app.utils;

import insurance.quote.app.domain.entities.Coverage;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CoverageHelper {

    public static Map<String, Double> convertListToMap(List<Coverage> coverages) {
        return coverages.stream()
                .collect(Collectors.toMap(Coverage::getName, Coverage::getAmount));
    }

    public static List<Coverage> convertMapToList(Map<String, Double> coveragesMap) {
        return coveragesMap.entrySet().stream()
                .map(entry -> {
                    Coverage coverage = new Coverage();
                    coverage.setName(entry.getKey());
                    coverage.setAmount(entry.getValue());
                    return coverage;
                })
                .collect(Collectors.toList());
    }
}