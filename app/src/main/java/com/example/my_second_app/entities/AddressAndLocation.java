package com.example.my_second_app.entities;

import android.location.Location;

import androidx.room.Embedded;

public class AddressAndLocation {
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







    @Embedded
    private LatitudeAndLongitudeLocation mLatitudeAndLongitudeLocation;
    public LatitudeAndLongitudeLocation getMLatitudeAndLongitudeLocation() {
        return mLatitudeAndLongitudeLocation;
    }

    public void setMLatitudeAndLongitudeLocation(LatitudeAndLongitudeLocation mLatitudeAndLongitudeLocation) {
        this.mLatitudeAndLongitudeLocation = mLatitudeAndLongitudeLocation;
    }

    private String maddress;
    public String getMAddress() {
        return maddress;
    }

    public void setMAddress(String address) {
        maddress = address;
    }
}
