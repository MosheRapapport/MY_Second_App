package com.example.my_second_app.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.my_second_app.entities.enums.PackStatus;
import com.example.my_second_app.entities.enums.PackType;
import com.example.my_second_app.entities.enums.PackWeight;

@Entity(tableName = "pack_show_table")
public class PackShow {
    @PrimaryKey(autoGenerate = true)
    private int iKey;
    private PackType packType;
    private PackWeight packWeight;
    private boolean packFragile;
    private PackStatus packStatus;
    private String deliveryName;
    private String address;
    private  String aKey;

    public PackShow() {
    }

    public PackShow(PackType packType, PackWeight packWeight, boolean packFragile, PackStatus packStatus, String deliveryName, String address, String aKey) {
        this.packType = packType;
        this.packWeight = packWeight;
        this.packFragile = packFragile;
        this.packStatus = packStatus;
        this.deliveryName = deliveryName;
        this.address = address;
        this.aKey = aKey;
    }

    public int getIKey() {
        return iKey;
    }

    public void setIKey(int iKey) {
        this.iKey = iKey;
    }

    public PackType getPackType() {
        return packType;
    }

    public void setPackType(PackType packType) {
        this.packType = packType;
    }

    public PackWeight getPackWeight() {
        return packWeight;
    }

    public void setPackWeight(PackWeight packWeight) {
        this.packWeight = packWeight;
    }

    public boolean getPackFragile() {
        return packFragile;
    }

    public void setPackFragile(boolean packFragile) {
        this.packFragile = packFragile;
    }

    public PackStatus getPackStatus() {
        return packStatus;
    }

    public void setPackStatus(PackStatus packStatus) {
        this.packStatus = packStatus;
    }

    public String getDeliveryName() {
        return deliveryName;
    }

    public void setDeliveryName(String deliveryName) {
        this.deliveryName = deliveryName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAKey() {
        return aKey;
    }

    public void setAKey(String aKey) {
        this.aKey = aKey;
    }
}
