package com.sujan.parkingmanagement.dao;

import com.sujan.parkingmanagement.model.ParkingRecords;

import java.time.LocalDateTime;
import java.util.List;

public interface IParkingRecordsDao {
    void addParkingRecords(ParkingRecords pr);
    List<ParkingRecords>  getAllParkingRecords();
    ParkingRecords getParkingRecordById(int id);
    void updateParkingRecord(ParkingRecords pr);
    void deleteParkingRecord(int id);
    double calculateParkingFee(LocalDateTime entryTime, LocalDateTime exitTime);
    void exitUpdateVehicle(int id);
}
