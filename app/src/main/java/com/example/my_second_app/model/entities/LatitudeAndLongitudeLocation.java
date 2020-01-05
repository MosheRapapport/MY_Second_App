package com.example.my_second_app.model.entities;

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

    public double getmLatitude() {
        return mLatitude;
    }

    public void setmLatitude(double mLatitude) {
        this.mLatitude = mLatitude;
    }

    public double getmLongitude() {
        return mLongitude;
    }

    public void setmLongitude(double mLongitude) {
        this.mLongitude = mLongitude;
    }

}
