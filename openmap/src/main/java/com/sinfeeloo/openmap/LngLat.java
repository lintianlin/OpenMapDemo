package com.sinfeeloo.openmap;

/**
 * LngLat
 *
 * @author: mhj
 * @date: 2018/4/3 15:28
 * <p>
 * 经纬度点封装
 */
public class LngLat {
    private double longitude;//经度  
    private double lantitude;//维度  

    public LngLat() {
    }

    public LngLat(double longitude, double lantitude) {
        this.longitude = longitude;
        this.lantitude = lantitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLantitude() {
        return lantitude;
    }

    public void setLantitude(double lantitude) {
        this.lantitude = lantitude;
    }

    @Override
    public String toString() {
        return "LngLat{" +
                "longitude=" + longitude +
                ", lantitude=" + lantitude +
                '}';
    }
}  
