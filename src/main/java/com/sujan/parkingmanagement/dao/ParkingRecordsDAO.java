package com.sujan.parkingmanagement.dao;

import com.sujan.parkingmanagement.DbConnection;
import com.sujan.parkingmanagement.model.ParkingRecords;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ParkingRecordsDAO implements IParkingRecordsDao {
    @Override
    public void addParkingRecords(ParkingRecords pr) {
        String sql = "Insert into parking_records vehicle_id, space_id, entry_time, exit_time, fee values(?,?,?,?,?)";
        try {
            Connection conn = DbConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, pr.getVehicle_id());
            stmt.setInt(2, pr.getSpace_id());
            stmt.setTimestamp(3, new Timestamp(pr.getEntry_time().getTime()));
            stmt.setTimestamp(4, new Timestamp(pr.getExit_time().getTime()));
            stmt.setDouble(5, pr.getFee());
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
                record.setEntry_time(rs.getTimestamp("entry_time"));
                record.setExit_time(rs.getTimestamp("exit_time"));
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
                record.setEntry_time(rs.getTimestamp("entry_time"));
                record.setExit_time(rs.getTimestamp("exit_time"));
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
            stmt.setTimestamp(3, new Timestamp(pr.getEntry_time().getTime()));
            stmt.setTimestamp(4, new Timestamp(pr.getExit_time().getTime()));
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
            if(rows_affected > 0){
                System.out.println("The record has been deleted");
            }else{
                System.out.println("There was error deleting the record");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
