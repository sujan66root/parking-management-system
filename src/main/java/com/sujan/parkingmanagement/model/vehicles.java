package com.sujan.parkingmanagement.model;

public class vehicles {
    private int id;
    private String type;
    private String liscence_plate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLiscencePlate() {
        return liscence_plate;
    }

    public void setLiscencePlate(String liscence_plate) {
        this.liscence_plate = liscence_plate;
    }
    public String toString() {
        return "{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", liscencePlate='" + liscence_plate + '\'' +
                '}';
    }
}
