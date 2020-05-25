package com.work;
import java.io.File;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        final Node node = CSVReader.readCSV(new File("src/com/work/ukraine_poi.csv"));
        assert node != null;
        outArray(node.getValues(new SimpleCirclePoint(49.4225,26.99596, 2000)),80);
    }

    public static void outArray(ArrayList<DistanceCalculable>arr,int count){
        for (int i = 0; i < count ; i++) {
            System.out.println(arr.get(i));
        }
    }
}
