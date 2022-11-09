package org.vmk.dep508.stream.iris;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class App {
    public static void main(String[] args) {
        App a = new App();
        a.test();
    }

    public void test() {

        List<Iris> irisList = readData(); //load data from file iris.data
        IrisDataSetHelper helper = new IrisDataSetHelper(irisList);

        //get average sepal width
        Double avgSepalLength = helper.getAverage(Iris::getSepalLength);
        System.out.println(avgSepalLength);

        //get average petal square - petal width multiplied on petal length
        Double avgPetalLength = helper.getAverage(Iris::getPetalLength);
        System.out.println(avgPetalLength);

        //get average petal square for flowers with sepal width > 4
        Double avgPetalSquare = helper.getAverageWithFilter(
                iris -> iris.getSepalWidth() > 4,
                iris -> iris.getPetalLength() * iris.getPetalWidth());
        System.out.println(avgPetalSquare);

        //get flowers grouped by Petal size (Petal.SMALL, etc.)
        Map<Petal, List<Iris>> groupsByPetalSize = helper.groupBy(Iris::classifyByPatel);
        System.out.println(groupsByPetalSize);

        //get max sepal width for flowers grouped by species
        Map<String, Double> maxSepalWidthForGroupsBySpecies = helper.maxFromGroupedBy(Iris::getSpecies, Iris::getSepalWidth);
        System.out.println(maxSepalWidthForGroupsBySpecies);
    }

    private List<Iris> readData() {
        try (Stream<String> lines = Files.lines(Paths.get("iris.data"))) {
            return lines
                    .map(this::getIris)
                    .collect(Collectors.toList());
        } catch (IOException | NumberFormatException e) {
            System.out.println(e.getLocalizedMessage());
            throw new RuntimeException(e);
        }
    }

    private Iris getIris(String line) {
        String[] mass = line.split("[,-]");
        return new Iris(Double.parseDouble(mass[0]),
                Double.parseDouble(mass[1]),
                Double.parseDouble(mass[2]),
                Double.parseDouble(mass[3]),
                mass[5]
        );
    }
}

