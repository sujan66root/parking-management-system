package com.sujan.parkingmanagement.dao;

import com.sujan.parkingmanagement.model.vehicles;

import java.util.List;

public interface IVehicleDao {
//    crud application for vehicles
//    for adding vehicles we need all attributes of vehicles
    void addVehicle(vehicles vehicle); // making object of vehicles model class that we made earlier

//    for retriving all details we need no parameters
    List<vehicles> getAllVehicles();

//    for updating we need all vehicle attribute
    void updateVehicles(vehicles vehicle);

//    id is neeeded for getting single or deleting the record
    void deleteVehicle(int id);
    vehicles getVehicle(int id);

//    now all these methods will be implemented in another class.
}
