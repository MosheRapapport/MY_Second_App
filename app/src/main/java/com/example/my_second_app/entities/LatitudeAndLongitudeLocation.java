package com.example.my_second_app.entities;

import android.location.Location;

public class LatitudeAndLongitudeLocation {
    private double mLatitude = 0.0;
    private double mLongitude = 0.0;

    public LatitudeAndLongitudeLocation() {

    }

    public LatitudeAndLongitudeLocation(Location location){
        mLatitude=location.getLatitude();
        mLongitude=location.getLongitude();
    }

    public double getMLatitude() {
        return mLatitude;
    }

    public void setMLatitude(double mLatitude) {
        this.mLatitude = mLatitude;
    }

    public double getMLongitude() {
        return mLongitude;
    }

    public void setMLongitude(double mLongitude) {
        this.mLongitude = mLongitude;
    }

}
