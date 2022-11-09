package org.vmk.dep508.stream.iris;


import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.maxBy;

public class IrisDataSetHelper {

    private List<Iris> dataSet;

    public IrisDataSetHelper(List<Iris> dataSet) {
        this.dataSet = dataSet;
    }

    public Double getAverage(ToDoubleFunction<Iris> func) {
        return dataSet
                .stream()
                .mapToDouble(func)
                .average()
                .orElseThrow(() -> {
                    throw new RuntimeException();
                });
    }

    public List<Iris> filter(Predicate<Iris> predicate) {
        return dataSet
                .stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }

    public Double getAverageWithFilter(Predicate<Iris> filter, ToDoubleFunction<Iris> mapFunction) {
        return dataSet
                .stream()
                .filter(filter)
                .mapToDouble(mapFunction)
                .average()
                .orElseThrow(() -> {
                    throw new RuntimeException();
                });
    }

    public <K> Map<K, List<Iris>> groupBy(Function<Iris, K> groupFunction) {
        return dataSet
                .stream()
                .collect(Collectors.groupingBy(groupFunction));
    }

    public <K> Map<K, Double> maxFromGroupedBy(Function<Iris, K> groupFunction, ToDoubleFunction<Iris> obtainMaximisationValueFunction) {
        return dataSet
                .stream()
                .collect(Collectors.groupingBy(
                        groupFunction,
                        collectingAndThen(
                                maxBy(Comparator.comparingDouble(obtainMaximisationValueFunction)),
                                iris -> obtainMaximisationValueFunction.applyAsDouble(iris.orElse(null))
                        )
                ));

    }
}
