package org.vmk.dep508.stream.lenses;

import org.vmk.dep508.stream.iris.Iris;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;

public class LenseDataSetHelper {
    private List<LenseData> dataSet;

    public LenseDataSetHelper(List<LenseData> dataSet) {
        this.dataSet = dataSet;
    }

    public Double getAverage(ToDoubleFunction<LenseData> func) {
        throw new NotImplementedException();
    }

    public List<Iris> filter(Predicate<LenseData> predicate) {
        throw new NotImplementedException();
    }

    public Double getAverageWithFilter(Predicate<LenseData> filter, ToDoubleFunction<LenseData> mapFunction) {
        throw new NotImplementedException();
    }

    public Map groupBy(Function groupFunction) {
        throw new NotImplementedException();
    }

    public Map maxFromGroupedBy(Function groupFunction, ToDoubleFunction<LenseData> obtainMaximisationValueFunction) {
        throw new NotImplementedException();
    }
}