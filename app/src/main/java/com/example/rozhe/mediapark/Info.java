package com.example.rozhe.mediapark;

import com.google.gson.annotations.SerializedName;

public class Info {

    @SerializedName("id")
    private int id;

    @SerializedName("plateNumber")
    private String plateNumber;

    @SerializedName("location")
    private Location location;

    @SerializedName("model")
    private Model model;

    @SerializedName("batteryPercentage")
    private int batteryPercentage;

    @SerializedName("batteryEstimatedDistance")
    private double batteryEstimatedDistance;

    @SerializedName("distance")
    private float distance;

    @SerializedName("isCharging")
    private boolean isCharging;

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public int getBatteryPercentage() {
        return batteryPercentage;
    }

    public void setBatteryPercentage(int batteryPercentage) {
        this.batteryPercentage = batteryPercentage;
    }

    public double getBatteryEstimatedDistance() {
        return batteryEstimatedDistance;
    }

    public void setBatteryEstimatedDistance(double batteryEstimatedDistance) {
        this.batteryEstimatedDistance = batteryEstimatedDistance;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public boolean isCharging() {
        return isCharging;
    }

    public void setCharging(boolean charging) {
        isCharging = charging;
    }

    public Location getLocation() {
        return location;
    }


    public void setLocation(Location location) {
        this.location = location;
    }
}

