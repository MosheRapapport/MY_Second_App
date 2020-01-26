package com.example.my_second_app.entities;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.example.my_second_app.entities.enums.PackStatus;
import com.example.my_second_app.entities.enums.PackType;
import com.example.my_second_app.entities.enums.PackWeight;
import com.google.firebase.database.Exclude;

import java.util.Date;

@Entity(tableName = "pack_table")
public class Pack {
    private PackType packType;
    private PackWeight packWeight;
    private boolean packFragile;
    @Embedded
    private Person recipient;
    @Ignore
    private AddressAndLocation storageLocation;
    private PackStatus packStatus;
    private Date deliveryDate;
    private Date receivedDate;
    private String deliveryName;



    @PrimaryKey(autoGenerate = true)
    @Exclude
    private int iKey;
    public int getIKey() {
        return iKey;
    }

    public void setIKey(int iKey) {
        this.iKey = iKey;
    }

    private  String aKey;

    public Pack() {

    }

    public Pack(PackType packType, PackWeight packWeight, boolean packFragile, Person recipient, AddressAndLocation storageLocation, PackStatus packStatus, Date deliveryDate, Date receivedDate, String deliveryName) {
        this.packType = packType;
        this.packWeight = packWeight;
        this.packFragile = packFragile;
        this.recipient = recipient;
        this.storageLocation = storageLocation;
        this.packStatus = packStatus;
        this.deliveryDate = deliveryDate;
        this.receivedDate = receivedDate;
        this.deliveryName = deliveryName;
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

    public boolean isPackFragile() {
        return packFragile;
    }

    public void setPackFragile(boolean packFragile) {
        this.packFragile = packFragile;
    }

    public Person getRecipient() {
        return recipient;
    }

    public void setRecipient(Person recipient) {
        this.recipient = recipient;
    }

    public AddressAndLocation getStorageLocation() {
        return storageLocation;
    }

    public void setStorageLocation(AddressAndLocation storageLocation) {
        this.storageLocation = storageLocation;
    }

    public PackStatus getPackStatus() { return packStatus; }

    public void setPackStatus(PackStatus packStatus) { this.packStatus = packStatus; }

    public Date getDeliveryDate() { return deliveryDate; }

    public void setDeliveryDate(Date deliveryDate) { this.deliveryDate = deliveryDate; }

    public Date getReceivedDate() { return receivedDate; }

    public void setReceivedDate(Date receivedDate) { this.receivedDate = receivedDate; }

    public String getDeliveryName() { return deliveryName; }

    public void setDeliveryName(String deliveryName) { this.deliveryName = deliveryName; }

    public String getAKey() { return aKey; }

    public void setAKey(String aKey) { this.aKey = aKey; }


}
