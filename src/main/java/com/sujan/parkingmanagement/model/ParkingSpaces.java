package com.sujan.parkingmanagement.model;

public class ParkingSpaces {
    private int id;
    private String space_number;
    private boolean is_occupied;
    private int vehicle_id;

    public boolean isIs_occupied() {
        return is_occupied;
    }

    public void setIs_occupied(boolean is_occupied) {
        this.is_occupied = is_occupied;
    }

    public String getSpace_number() {
        return space_number;
    }

    public void setSpace_number(String space_number) {
        this.space_number = space_number;
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

    public String toString() {
        return "ParkingSpace{" +
                "id=" + id +
                ", spaceNumber='" + space_number + '\'' +
                ", isOccupied=" + is_occupied +
                ", vehicleId=" + vehicle_id +
                '}';
    }
}
