package com.work;

public class PointOnMap implements DistanceCalculable {
    private final double lat;
    private final double lon;
    private final String type;
    private final String subType;
    private final String name;
    private final String address;

    public PointOnMap(double lat, double lon, String type, String subType, String name, String address) {
        this.lat = lat;
        this.lon = lon;
        this.type = type;
        this.subType = subType;
        this.name = name;
        this.address = address;
    }

    @Override
    public double getLat() {
        return lat;
    }

    @Override
    public double getLon() {
        return lon;
    }

    public String getType() {
        return type;
    }

    public String getSubType() {
        return subType;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public double getRadius() {
        return 0;
    }

    @Override
    public String toString() {
        return ("Точка на карте: "+"\nШирота: " + lat + "\nДолгота: " + lon + "\nТип: " + type + "\nПодтип: " + subType + "\nНазвание: " + name + "\nАдрес: " + address);
    }
}