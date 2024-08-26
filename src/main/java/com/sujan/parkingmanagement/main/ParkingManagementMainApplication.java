package com.sujan.parkingmanagement.main;

import com.sujan.parkingmanagement.dao.IParkingSpaceDao;
import com.sujan.parkingmanagement.dao.IVehicleDao;
import com.sujan.parkingmanagement.dao.ParkingSpaceDAO;
import com.sujan.parkingmanagement.dao.VehiclesDAO;
import com.sujan.parkingmanagement.model.ParkingSpaces;
import com.sujan.parkingmanagement.model.vehicles;

import java.util.*;
import java.util.Scanner;

public class ParkingManagementMainApplication {
    //    creating instance of implemented abstract method of IvehicleDAO interface
//    abstract methods cant be instantiated so, instance of vehicles dao is needed and type of
//    abstract method and object
    private static final IParkingSpaceDao iParkingSpaceDao = new ParkingSpaceDAO();
    //    advantage of polymorphism is seen above
//    Polymorphism is a fundamental concept in object-oriented programming (OOP) that allows
//    objects of different classes to be treated as objects of a common superclass or interface.
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {
            System.out.println("Parking Management System");

            System.out.println("1. Vehicle Operations");
            System.out.println("2. Parking Spaces Operations");
            System.out.println("3. Parking Records");
            System.out.println("4. Exit");
            System.out.println("Enter the choice you want to proceed with: ");
            int mainChoice = sc.nextInt();
            sc.nextLine(); // Consume newline left-over

            switch (mainChoice) {
                case 1:
                    VehicleMenu.VehicleOperations(sc);
                    break;
                case 2:
                    ParkingSpacesMenu.ParkingSpacesOperations(sc);
                    break;
                case 3:
                    ParkingRecordsMenu.ParkingRecordsOperations(sc);
                    break;
                case 4:
                    sc.close();
                    System.exit(0);
                default:
                    System.out.println("Please enter from 1-4 options");
            }
        }
    }
}
