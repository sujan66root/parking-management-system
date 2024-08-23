package com.sujan.parkingmanagement;

import java.sql.Connection;
import java.sql.SQLException;

public class TestConnection {
    public static void main(String[] args) {
        try{
            Connection conn = DbConnection.getConnection();
            if (conn != null){
                System.out.println("Database Connected Successfully");
            }else{
                System.out.println("Failed to connect to database");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
