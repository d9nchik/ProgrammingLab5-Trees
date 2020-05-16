package com.work;

import java.io.File;

public class Main {

    public static void main(String[] args) {
        final Node node = CSVReader.readCSV(new File("src/com/work/ukraine_poi.csv"));
        System.out.println(node.getValues(
                new SimpleCirclePoint(30.212, 35.872, 20_000)));
    }
}
