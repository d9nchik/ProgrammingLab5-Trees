package com.work;
import java.io.File;
public class Main {

    public static void main(String[] args) {
        final Node node = CSVReader.readCSV(new File("src/com/work/ukraine_poi.csv"));
        assert node != null;
        System.out.println(node.getValues(new SimpleCirclePoint(Double.parseDouble(args[0]), Double.parseDouble(args[1]), Double.parseDouble(args[2]))));
    }
}
