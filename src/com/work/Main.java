package com.work;

import java.io.File;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
        if(args.length==5) {
            HashMap<String, String> hashMapInfo = new HashMap<>();
            for (int i = 0; i < args.length; i++) {
                String[] strInfo = args[i].split("=");
                hashMapInfo.put(strInfo[0], strInfo[1]);
            }
            final Node node = CSVReader.readCSV(new File(hashMapInfo.get("--db")));
            assert node != null;
            Node.outArray(node.getValues(new SimpleCirclePoint(Double.parseDouble(hashMapInfo.get("--lat")), Double.parseDouble(hashMapInfo.get("--long")), Double.parseDouble(hashMapInfo.get("--size")))), Integer.parseInt(hashMapInfo.get("--count")));
        }else {
            System.out.println("Данное количество элементов не может быть!");
        }
    }

}
