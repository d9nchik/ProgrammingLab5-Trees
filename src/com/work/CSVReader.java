package com.work;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class CSVReader {
    private CSVReader() {
    }
    public static Node readCSV(File file) {
        try (Scanner input = new Scanner(new BufferedInputStream(new FileInputStream(file)))) {
            Node node = new Node();
            while (input.hasNext()) {
                String[] parts = Arrays.copyOf(input.nextLine().split(";"), 6);
                PointOnMap pointOnMap = new PointOnMap(Double.parseDouble(parts[0].replaceFirst(",", ".")),
                        Double.parseDouble(parts[1].replaceFirst(",", ".")),
                        parts[2], parts[3], parts[4], parts[5]);
                node.addNode(pointOnMap);
            }
            return node;
        }catch (FileNotFoundException ex) {
            System.out.println("File not found!");
        }
        return null;
    }
}
