package com.sujan.parkingmanagement.dao;

import com.sujan.parkingmanagement.DbConnection;
import com.sujan.parkingmanagement.model.ParkingSpaces;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ParkingSpaceDAO implements IParkingSpaceDao {
    @Override
    public void addParkingSpace(ParkingSpaces ps) {
        String sql = "insert into parking_spaces (space_number, is_occupied, vehicle_id) values (?,?,?)";
        try {
            Connection conn = DbConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1,ps.getSpace_number());
            stmt.setBoolean(2, ps.isIs_occupied());
            stmt.setInt(3, ps.getVehicle_id());
            int rows_affected = stmt.executeUpdate();
            if(rows_affected>0){
                System.out.println("Parking spaces has been added succesfully");
            }else{
                System.out.println("There was an error in adding parking space");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<ParkingSpaces> getAllParkingSpaces() {
        String sql ="select * from parking_spaces";
        List<ParkingSpaces> ParkingSpace = new ArrayList<>();
        try {
            Connection conn = DbConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                ParkingSpaces ps = new ParkingSpaces();
                ps.setId(rs.getInt("id"));
                ps.setSpace_number(rs.getString("space_number"));
                ps.setIs_occupied(rs.getBoolean("is_occupied"));
                ps.setVehicle_id(rs.getInt("vehicle_id"));
                ParkingSpace.add(ps);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return ParkingSpace;
    }

    @Override
    public ParkingSpaces getParkingSpaceByID(int id) {
        String sql = "Select * from parking_spaces where id = ?";
        ParkingSpaces ps = null;
        try{
            Connection conn = DbConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1,id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                ps = new ParkingSpaces();
                ps.setId(rs.getInt("id"));
                ps.setSpace_number(rs.getString("space_number"));
                ps.setIs_occupied(rs.getBoolean("is_occupied"));
                ps.setVehicle_id(rs.getInt("vehicle_id"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return ps;
    }

    @Override
    public void deleteParkingSpace(int id) {
        String sql = "delete from parking_spaces where id = ?";
        try{
            Connection conn = DbConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            int rows_affected = stmt.executeUpdate();
            if(rows_affected > 0){
                System.out.println("the record has been deleted");
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void updateParkingSpace(ParkingSpaces ps) {
        String sql = "update parking_spaces set space_number = ?, is_occupied = ?, vehicle_id = ? where id = ?";
        try{
            Connection conn = DbConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, ps.getSpace_number());
            stmt.setBoolean(2, ps.isIs_occupied());
            stmt.setInt(3, ps.getVehicle_id());
            stmt.setInt(4, ps.getVehicle_id());
            int rows_affected = stmt.executeUpdate();
            if(rows_affected > 0){
                System.out.println("The record has been updated");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
