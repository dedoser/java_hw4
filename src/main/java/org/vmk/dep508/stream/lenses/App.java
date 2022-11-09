
package org.vmk.dep508.stream.lenses;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.stream.Collectors;

public class App {
    public static void main(String[] args) throws IOException {
        App app = new App();
        app.test();
    }

    public void test() throws IOException {
        LenseDataSetHelper helper = new LenseDataSetHelper(Files
                .lines(Paths.get("lenses.data"))
                .map(LenseData::parse).collect(Collectors.toList()));

        //get average sepal width
        Double avgSetalLength = null;
        System.out.println(avgSetalLength);

        //get average petal square - petal width multiplied on petal length
        Double avgPetalLength = null;
        System.out.println(avgPetalLength);

        //get average petal square for flowers with sepal width > 4
        Double avgPetalSquare = null;
        System.out.println(avgPetalSquare);

        //get flowers grouped by Petal size (Petal.SMALL, etc.)
        Map groupsByPetalSize = null;
        System.out.println(groupsByPetalSize);

        //get max sepal width for flowers grouped by species
        Map maxSepalWidthForGroupsBySpecies = null;
        System.out.println(maxSepalWidthForGroupsBySpecies);
    }
}