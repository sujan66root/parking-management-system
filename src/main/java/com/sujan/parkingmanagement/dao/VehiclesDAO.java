package com.sujan.parkingmanagement.dao;

import com.sujan.parkingmanagement.DbConnection;
import com.sujan.parkingmanagement.model.vehicles;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VehiclesDAO implements IVehicleDao {
    @Override
    public void addVehicle(vehicles vehicle) {
        String sql = "insert into vehicles (type, liscence_plate) values (?,?)";
        try {
            Connection conn = DbConnection.getConnection();
            if (conn != null) {
                System.out.println("Database Connected Successfully");
//                A PreparedStatement is a Java interface used to execute parameterized SQL queries
                PreparedStatement stmt = conn.prepareStatement(sql);
//                setting parameters (?,?) for sql query
                stmt.setString(1, vehicle.getType());
                stmt.setString(2, vehicle.getLiscencePlate());
//                executing those query
                int rows_inserted = stmt.executeUpdate();
                if (rows_inserted > 0) {
                    System.out.println("A new vehicle was added successfully");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<vehicles> getAllVehicles() {
        String sql = "Select * from vehicles";
        List<vehicles> vehicles = new ArrayList<>();
        try {
            Connection conn = DbConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                vehicles v = new vehicles();
//                setting the values got from database into object
                v.setId(rs.getInt("id"));
                v.setType(rs.getString("type"));
                v.setLiscencePlate(rs.getString("liscence_plate"));
//                adding the vehicle object to the collection list
                vehicles.add(v);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehicles;
    }

    @Override
    public void updateVehicles(vehicles vehicle) {
        String sql = "update vehicles set type = ?, liscence_plate = ? where id = ?";
        try {
            Connection conn = DbConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, vehicle.getType());
            stmt.setString(2, vehicle.getLiscencePlate());
            stmt.setInt(3, vehicle.getId());
            int rows_affected = stmt.executeUpdate();
            if (rows_affected> 0){
                System.out.println("Data has been updated successfully");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteVehicle(int id) {
        String sql = "delete from vehicles where id = ?";
        try{
            Connection conn = DbConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1,id);
            int rows_affected = stmt.executeUpdate();
            if(rows_affected > 0){
                System.out.println("The record has been deleted successfully");
            }else{
                System.out.println("No vehicle with id" +id+"is found");
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public vehicles getVehicle(int id) {
        String sql = "select * from vehicles where id = ?";
        vehicles v = null;
        try {
            Connection conn = DbConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                v = new vehicles();
                v.setId(rs.getInt("id"));
                v.setType(rs.getString("type"));
                v.setLiscencePlate("liscence_plate");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return v;
    }
}
