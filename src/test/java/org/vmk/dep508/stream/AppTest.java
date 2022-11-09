package org.vmk.dep508.stream;

import org.hamcrest.collection.IsMapContaining;
import org.junit.jupiter.api.Test;
import org.vmk.dep508.stream.iris.Iris;
import org.vmk.dep508.stream.iris.IrisDataSetHelper;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit test for simple App.
 */
class AppTest {

    private static final double EPSILON = 0.000001d;

    @Test
    void checkGetAverage() {
        Iris iris1 = new Iris(0.1, 0.2, 0.3, 0.4, "iii");
        Iris iris2 = new Iris(1.1, 1.2, 1.3, 1.4, "iii");
        List<Iris> irises = Stream.of(iris1, iris2).collect(Collectors.toList());

        IrisDataSetHelper dataSetHelper = new IrisDataSetHelper(irises);
        assertEquals(0.8, dataSetHelper.getAverage(Iris::getPetalLength), EPSILON);
        assertEquals(0.6, dataSetHelper.getAverage(Iris::getSepalLength), EPSILON);
        assertEquals(0.7, dataSetHelper.getAverage(Iris::getSepalWidth), EPSILON);
    }

    @Test
    void checkFilter() {
        Iris iris1 = new Iris(0.1, 0.2, 0.3, 0.4, "iii");
        Iris iris2 = new Iris(1.1, 1.2, 1.3, 1.4, "iii");
        List<Iris> irises = Stream.of(iris1, iris2).collect(Collectors.toList());

        IrisDataSetHelper dataSetHelper = new IrisDataSetHelper(irises);
        assertThat(dataSetHelper.filter(iris -> iris.getPetalWidth() > 0.4), containsInAnyOrder(iris2));
        assertThat(dataSetHelper.filter(iris -> iris.getPetalWidth() > 0.1), containsInAnyOrder(iris2, iris1));
    }

    @Test
    void checkGetAverageWithFilter() {
        Iris iris1 = new Iris(0.1, 0.2, 0.3, 0.4, "iii");
        Iris iris2 = new Iris(1.1, 1.2, 1.3, 1.4, "iii");
        Iris iris3 = new Iris(2.1, 2.2, 2.3, 2.4, "iii");

        List<Iris> irises = Stream.of(iris1, iris2, iris3).collect(Collectors.toList());

        IrisDataSetHelper dataSetHelper = new IrisDataSetHelper(irises);
        assertEquals(3.86,
                dataSetHelper.getAverageWithFilter(iris -> iris.getPetalWidth() > 1, iris -> iris.getPetalWidth() * iris.getPetalWidth()),
                EPSILON);
    }

    @Test
    void checkGroupBy() {
        Iris iris1 = new Iris(0.1, 0.2, 0.3, 0.4, "iii");
        Iris iris2 = new Iris(1.1, 1.2, 1.3, 1.4, "iii");
        Iris iris3 = new Iris(2.1, 2.2, 2.3, 2.4, "ii123");

        List<Iris> irises = Stream.of(iris1, iris2, iris3).collect(Collectors.toList());
        IrisDataSetHelper dataSetHelper = new IrisDataSetHelper(irises);

        Map<String, List<Iris>> grouped = dataSetHelper.groupBy(Iris::getSpecies);
        assertThat(grouped, IsMapContaining.hasEntry(is("iii"), containsInAnyOrder(iris1, iris2)));
        assertThat(grouped, IsMapContaining.hasEntry(is("ii123"), containsInAnyOrder(iris3)));
    }

    @Test
    void checkMaxGroupBy() {
        Iris iris1 = new Iris(0.1, 0.2, 0.3, 0.4, "iii");
        Iris iris2 = new Iris(1.1, 1.2, 1.3, 1.4, "iii");
        Iris iris3 = new Iris(2.1, 2.2, 2.3, 2.4, "ii123");

        List<Iris> irises = Stream.of(iris1, iris2, iris3).collect(Collectors.toList());
        IrisDataSetHelper dataSetHelper = new IrisDataSetHelper(irises);

        Map<String, Double> grouped = dataSetHelper.maxFromGroupedBy(Iris::getSpecies, Iris::getPetalWidth);
        assertThat(grouped, IsMapContaining.hasEntry(is("iii"), is(1.4)));
        assertThat(grouped, IsMapContaining.hasEntry(is("ii123"), is(2.4)));
    }
}
