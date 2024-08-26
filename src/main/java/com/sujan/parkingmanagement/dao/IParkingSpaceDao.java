package com.sujan.parkingmanagement.dao;

import com.sujan.parkingmanagement.model.ParkingSpaces;
import java.util.List;

public interface IParkingSpaceDao {
    void addParkingSpace(ParkingSpaces ps);
    List<ParkingSpaces> getAllParkingSpaces();
    ParkingSpaces getParkingSpaceByID(int id);
    void deleteParkingSpace(int id);
    void updateParkingSpace(ParkingSpaces ps);
}
