package com.work;

import java.io.Serializable;

public class PointOnMap implements DistanceCalculable<PointOnMap>, Serializable {//Class should be immutable
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

    public double getLat() {
        return lat;
    }

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

    /**
     * Distance is the main part of our task
     */
    @Override
    public double distance(PointOnMap pointOnMap) {
        //We know lat and lon, so we will use "harvesine"
        return harvesine(lat, lon, pointOnMap.lat, pointOnMap.lon);
    }
/**
 * Additional information is here: http://www.movable-type.co.uk/scripts/latlong.html
* */
    private static double harvesine(double lat1, double lon1, double lat2, double lon2) {
        final double R = 6371e3; // metres
        final double Fi1 = lat1 * Math.PI / 180; // φ, λ in radians
        final double Fi2 = lat2 * Math.PI / 180;
        final double DFi = (lat2 - lat1) * Math.PI / 180;
        final double DLambda = (lon2 - lon1) * Math.PI / 180;

        final double A = Math.sin(DFi / 2) * Math.sin(DFi / 2) +
                Math.cos(Fi1) * Math.cos(Fi2) *
                        Math.sin(DLambda / 2) * Math.sin(DLambda / 2);
        final double c = 2 * Math.atan2(Math.sqrt(A), Math.sqrt(1 - A));

        return R * c; // in metres
    }
}
