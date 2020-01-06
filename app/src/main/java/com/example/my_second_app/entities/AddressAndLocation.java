package com.example.my_second_app.entities;

import android.location.Location;

import androidx.room.Embedded;

public class AddressAndLocation {
    public LatitudeAndLongitudeLocation getmLatitudeAndLongitudeLocation() {
        return mLatitudeAndLongitudeLocation;
    }

    public void setMLatitudeAndLongitudeLocation(LatitudeAndLongitudeLocation mLatitudeAndLongitudeLocation) {
        this.mLatitudeAndLongitudeLocation = mLatitudeAndLongitudeLocation;
    }

    public String getMaddress() {
        return maddress;
    }

    public void setMaddress(String maddress) {
        this.maddress = maddress;
    }

    @Embedded
    private LatitudeAndLongitudeLocation mLatitudeAndLongitudeLocation;
    public LatitudeAndLongitudeLocation getMLatitudeAndLongitudeLocation() {
        return mLatitudeAndLongitudeLocation;
    }
    private String maddress;


    public AddressAndLocation(AddressAndLocation addressAndLocation) {
        mLatitudeAndLongitudeLocation=addressAndLocation.mLatitudeAndLongitudeLocation;
        maddress =addressAndLocation.maddress;
    }

    public AddressAndLocation() {
    }

    public AddressAndLocation(Location location, String address) {
        mLatitudeAndLongitudeLocation =new LatitudeAndLongitudeLocation( location);
        maddress = address;
    }


    public String getMAddress() {
        return maddress;
    }

    public void setMAddress(String address) {
        maddress = address;
    }
}
