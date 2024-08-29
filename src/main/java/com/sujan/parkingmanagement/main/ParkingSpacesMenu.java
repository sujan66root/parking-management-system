package com.sujan.parkingmanagement.main;

import com.sujan.parkingmanagement.dao.IParkingSpaceDao;
import com.sujan.parkingmanagement.dao.IVehicleDao;
import com.sujan.parkingmanagement.dao.ParkingSpaceDAO;
import com.sujan.parkingmanagement.dao.VehiclesDAO;
import com.sujan.parkingmanagement.model.ParkingSpaces;
import com.sujan.parkingmanagement.model.vehicles;

import java.util.List;
import java.util.Scanner;

public class ParkingSpacesMenu {
    private static final Scanner sc = new Scanner(System.in);
    private static final IParkingSpaceDao iParkingSpaceDao =  new ParkingSpaceDAO();
    private static final IVehicleDao iVehicleDao = new VehiclesDAO();
    protected static void ParkingSpacesOperations(Scanner sc) {
        while (true) {
            System.out.println("Parking Spaces Operations");
            System.out.println("1. Add a parking space");
            System.out.println("2. Get all parking spaces");
            System.out.println("3. Update a parking space");
            System.out.println("4. Get a parking space by Id");
            System.out.println("5. Delete a parking space");
            System.out.println("6. Back to Main menu");
            System.out.println("7. Exit");
            System.out.println("Enter the option you want to proceed with");
            int parkingChoice = sc.nextInt();
            sc.nextLine();

            switch (parkingChoice) {
                case 1:
                    addParkingSpace();
                    break;
                case 2:
                    getAllParkingSpaces();
                    break;
                case 3:
                    updateParkingSpace();
                    break;
                case 4:
                    getParkingSpace();
                    break;
                case 5:
                    deleteParkingSpace();
                    break;
                case 6:
                    return;
                case 7:
                    sc.close();
                    System.exit(0);
                default:
                    System.out.println("Select options from 1 - 7");
            }

        }
    }

    private static void addParkingSpace() {
        System.out.println("Enter space number: ");
        String space_number = sc.nextLine();
        System.out.print("Is the space occupied (true/false)? ");
        boolean is_occupied = Boolean.parseBoolean(sc.nextLine());
        System.out.println("Enter vehicle id: (o if none)");
        System.out.println("List of vehicles available are: ");
        List<vehicles> availableVehicles = iVehicleDao.getAllVehicles();
        for(vehicles v: availableVehicles){
            System.out.println(v);
        }
        int vehicle_id = sc.nextInt();
        sc.nextLine(); // Consume the leftover newline character

        ParkingSpaces ps = new ParkingSpaces();
        ps.setSpace_number(space_number);
        ps.setIs_occupied(is_occupied);
        ps.setVehicle_id(vehicle_id);
        iParkingSpaceDao.addParkingSpace(ps);
    }

    private static void getAllParkingSpaces() {
        List<ParkingSpaces> parkingSpacesList = iParkingSpaceDao.getAllParkingSpaces();
        if (!parkingSpacesList.isEmpty()) {
            System.out.println("The list of all parking spaces are: " + parkingSpacesList);
        } else {
            System.out.println("There are no parking spaces.");
        }
    }

    private static void getParkingSpace() {
        System.out.print("Enter Parking Space ID: ");
        int id = sc.nextInt();

        ParkingSpaces ps = iParkingSpaceDao.getParkingSpaceByID(id);
        if (ps != null) {
            System.out.println(ps);
        } else {
            System.out.println("Parking space not found.");
        }
    }

    private static void updateParkingSpace() {
        System.out.println("Enter the parking space id you want to update:");
        int id = sc.nextInt();
        ParkingSpaces parkingSpaceToUpdate = iParkingSpaceDao.getParkingSpaceByID(id);
        if (parkingSpaceToUpdate != null) {
            System.out.println("Enter the space number if you want it to be updated");
            String space_number = sc.nextLine();
            System.out.println("Enter if the space is occupied (True/false)");
            boolean is_occupied = sc.nextBoolean();
            System.out.println("Enter the vehicle id you want to update");
            int vehicle_id = sc.nextInt();
            parkingSpaceToUpdate.setSpace_number(space_number);
            parkingSpaceToUpdate.setIs_occupied(is_occupied);
            parkingSpaceToUpdate.setVehicle_id(vehicle_id);
            iParkingSpaceDao.updateParkingSpace(parkingSpaceToUpdate);
            System.out.println("The records has been updated");
        } else {
            System.out.println("The records werent updated");
        }
    }

    private static void deleteParkingSpace() {
        System.out.println("Enter the parking space you want to delete");
        int id = sc.nextInt();
        iParkingSpaceDao.deleteParkingSpace(id);
        System.out.println("Records deleted successfully");
    }

}
