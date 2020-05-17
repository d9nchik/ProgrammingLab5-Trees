package com.work;

import java.io.Serializable;

public interface DistanceCalculable extends Serializable {
    static double harvesine(double lat1, double lon1, double lat2, double lon2) {
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

    /**
     * Additional information is here: http://www.movable-type.co.uk/scripts/latlong.html
     * */
    default double distance(DistanceCalculable distanceCalculable) {
        //We know lat and lon, so we will use "harvesine"
        return harvesine(getLat(), getLon(), getLat(), getLon());
    }

    double getLat();
    double getLon();
    double getRadius();
}
