package com.work;
import java.io.File;

public class Main {

    public static void main(String[] args) {
        final Node node = CSVReader.readCSV(new File("src/com/work/ukraine_poi.csv"));
        assert node != null;
        Node.outArray(node.getValues(new SimpleCirclePoint(49.4225,26.99596, 2000)),80);
    }


}
