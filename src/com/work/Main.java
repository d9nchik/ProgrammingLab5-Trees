package com.work;

import java.io.File;

public class Main {

    public static void main(String[] args) {
        final Node node = CSVReader.readCSV(new File("src/com/work/ukraine_poi.csv"));
        //TODO: test ability of serializing structure
        System.out.println(node.getValues(
                new SimpleCirclePoint(50.60659,30.45436, 20_000)));
    }
}
