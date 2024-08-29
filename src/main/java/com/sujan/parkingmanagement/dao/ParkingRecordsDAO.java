package com.sujan.parkingmanagement.dao;

import com.sujan.parkingmanagement.DbConnection;
import com.sujan.parkingmanagement.model.ParkingRecords;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ParkingRecordsDAO implements IParkingRecordsDao {
    @Override
    public void addParkingRecords(ParkingRecords pr) {
        String sql = "Insert into parking_records (vehicle_id, space_id, entry_time, exit_time, fee) values(?,?,?,?,?)";
        try {
            Connection conn = DbConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, pr.getVehicle_id());
            stmt.setInt(2, pr.getSpace_id());
            stmt.setTimestamp(3, Timestamp.valueOf(pr.getEntry_time()));
            stmt.setTimestamp(4, null);
            stmt.setBigDecimal(5, null);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Parking record added successfully.");
            } else {
                System.out.println("Error adding parking record.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<ParkingRecords> getAllParkingRecords() {
        List<ParkingRecords> records = new ArrayList<>();
        String sql = "SELECT * FROM parking_records";
        try {
            Connection conn = DbConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                ParkingRecords record = new ParkingRecords();
                record.setId(rs.getInt("id"));
                record.setVehicle_id(rs.getInt("vehicle_id"));
                record.setSpace_id(rs.getInt("space_id"));
                record.setEntry_time(rs.getTimestamp("entry_time").toLocalDateTime());
                record.setExit_time(rs.getTimestamp("exit_time") != null ? rs.getTimestamp("exit_time").toLocalDateTime() : null);
                record.setFee(rs.getDouble("fee"));
                records.add(record);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return records;
    }

    @Override
    public ParkingRecords getParkingRecordById(int id) {
        String sql = "SELECT * FROM parking_records WHERE id = ?";
        try {
            Connection conn = DbConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                ParkingRecords record = new ParkingRecords();
                record.setId(rs.getInt("id"));
                record.setVehicle_id(rs.getInt("vehicle_id"));
                record.setSpace_id(rs.getInt("space_id"));
                record.setEntry_time(rs.getTimestamp("entry_time").toLocalDateTime());
                record.setExit_time(rs.getTimestamp("exit_time") != null ? rs.getTimestamp("exit_time").toLocalDateTime() : null);
                record.setFee(rs.getDouble("fee"));
                return record;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void updateParkingRecord(ParkingRecords pr) {
        String sql = "UPDATE parking_records SET vehicle_id = ?, space_id = ?, entry_time = ?, exit_time = ?, fee = ? WHERE id = ?";
        try {
            Connection conn = DbConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, pr.getVehicle_id());
            stmt.setInt(2, pr.getSpace_id());
            stmt.setTimestamp(3, Timestamp.valueOf(pr.getEntry_time()));
            stmt.setTimestamp(4, Timestamp.valueOf(pr.getExit_time()));
            stmt.setDouble(5, pr.getFee());
            stmt.setInt(6, pr.getId());
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Parking record updated successfully.");
            } else {
                System.out.println("Error updating parking record.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteParkingRecord(int id) {
        String sql = "Delete from parking_records where id = ?";
        try {
            Connection conn = DbConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            int rows_affected = stmt.executeUpdate();
            if (rows_affected > 0) {
                System.out.println("The record has been deleted");
            } else {
                System.out.println("There was error deleting the record");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public double calculateParkingFee(LocalDateTime entryDateTime, LocalDateTime exitDateTime) {
        long minutes = java.time.Duration.between(entryDateTime, exitDateTime).toMinutes();
        return minutes * 0.05; // 5 cents per minute
    }

    @Override
    public void exitUpdateVehicle(int vehicleId) {
        String sqlSelectVehicleToExit = "select * from parking_records where id = ?";
        String sqlUpdateParkingRecord = "update parking_records set exit_time = ?, fee = ? where id = ?";
        String sqlUpdateParkingSpaces = "update parking_spaces set is_occupied = false where id = ?";
        try (Connection conn = DbConnection.getConnection()) {
            PreparedStatement selectStmt = conn.prepareStatement(sqlSelectVehicleToExit);
            selectStmt.setInt(1, vehicleId);
            ResultSet rs = selectStmt.executeQuery();
            if (rs.next()) {
                int parking_record_id = rs.getInt("id");
                int parking_space_id = rs.getInt("space_id");

                // Update the parking record with exit time and fee
                LocalDateTime entry_time = rs.getTimestamp("entry_time").toLocalDateTime();
                LocalDateTime exit_time = LocalDateTime.now();
                PreparedStatement updateParkingRecordStmt = conn.prepareStatement(sqlUpdateParkingRecord);
                updateParkingRecordStmt.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
                updateParkingRecordStmt.setDouble(2, calculateParkingFee(entry_time, exit_time));
                updateParkingRecordStmt.setInt(3, parking_record_id);
                updateParkingRecordStmt.executeUpdate();
//                update the parking space to unoccupied
                PreparedStatement updateParkingSpaceStmt = conn.prepareStatement(sqlUpdateParkingSpaces);
                updateParkingSpaceStmt.setInt(1, parking_space_id);
                updateParkingSpaceStmt.executeUpdate();
                System.out.println("Vehicle exited. Parking record updated successfully.");
            }else {
                System.out.println("No active parking record found for vehicle ID: " + vehicleId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
