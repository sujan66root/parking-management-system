package com.sujan.parkingmanagement.model;

import java.util.Date;

public class ParkingRecords {
    private int id;
    private int space_id;
    private int vehicle_id;
    private double fee;
    private Date entry_time;
    private Date exit_time;

    public int getSpace_id() {
        return space_id;
    }

    public void setSpace_id(int space_id) {
        this.space_id = space_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVehicle_id() {
        return vehicle_id;
    }

    public void setVehicle_id(int vehicle_id) {
        this.vehicle_id = vehicle_id;
    }

    public Date getEntry_time() {
        return entry_time;
    }

    public void setEntry_time(Date entry_time) {
        this.entry_time = entry_time;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public Date getExit_time() {
        return exit_time;
    }

    public void setExit_time(Date exit_time) {
        this.exit_time = exit_time;
    }
    public String toString(){
        return "ParkingRecord{" +
                "id=" + id +
                ", vehicleId=" + vehicle_id +
                ", spaceId=" + space_id +
                ", entryTime=" + entry_time +
                ", exitTime=" + exit_time +
                ", fee=" + fee +
                '}';
    }
}
