package com.work;

import java.io.File;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
        HashMap<String, String> hashMapInfo = new HashMap<>();
        hashMapInfo.put("--db", "src/com/work/ukraine_poi.csv");
        hashMapInfo.put("--lat", "50.2529");
        hashMapInfo.put("--long", "28.66578");
        hashMapInfo.put("--size", "2000");
        hashMapInfo.put("--count","100000000");
        for (int i = 0; i < args.length; i++) {
            String[] strInfo = args[i].split("=");
            hashMapInfo.put(strInfo[0], strInfo[1]);
        }
        final Node node = CSVReader.readCSV(new File(hashMapInfo.get("--db")));
        assert node != null;
        Node.outArray(node.getValues(new SimpleCirclePoint(Double.parseDouble(hashMapInfo.get("--lat")), Double.parseDouble(hashMapInfo.get("--long")), Double.parseDouble(hashMapInfo.get("--size")))), Integer.parseInt(hashMapInfo.get("--count")));
    }
}
