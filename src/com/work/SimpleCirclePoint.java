package com.work;

import java.io.Serializable;

public class SimpleCirclePoint implements DistanceCalculable{
    private final double lat;
    private final double lon;
    private final double radius;

    public SimpleCirclePoint(double lat, double lon, double radius) {
        this.lat = lat;
        this.lon = lon;
        this.radius = radius;
    }

    @Override
    public double getLat() {
        return lat;
    }

    @Override
    public double getLon() {
        return lon;
    }

    public double getRadius() {
        return radius;
    }
}
