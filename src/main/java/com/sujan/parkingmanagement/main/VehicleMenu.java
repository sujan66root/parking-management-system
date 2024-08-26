package com.sujan.parkingmanagement.main;

import com.sujan.parkingmanagement.dao.IVehicleDao;
import com.sujan.parkingmanagement.dao.VehiclesDAO;
import com.sujan.parkingmanagement.model.vehicles;

import java.util.List;
import java.util.Scanner;

public class VehicleMenu {
    private static final IVehicleDao iVehicleDao = new VehiclesDAO();
    private static final Scanner sc = new Scanner(System.in);
    protected static void VehicleOperations(Scanner sc) {
        while (true) {
            System.out.println("1. Add Vehicle");
            System.out.println("2. Get Vehicle by ID");
            System.out.println("3. Get All Vehicles");
            System.out.println("4. Update Vehicle");
            System.out.println("5. Delete Vehicle");
            System.out.println("6. Go Back to Main Menu");
            System.out.println("7: Exit");
            System.out.println("Enter any of the choice above you want to proceed with");

            int vehicleChoice = sc.nextInt();
            sc.nextLine(); //go to new line

            switch (vehicleChoice) {
                case 1:
                    addVehicle();
                    break;
                case 2:
                    getVehicleById();
                    break;
                case 3:
                    getAllVehicles();
                    break;
                case 4:
                    updateVehicle();
                    break;
                case 5:
                    deleteVehicle();
                    break;
                case 6:
                    return;
                case 7:
                    System.out.println("Exiting the application");
                    sc.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Please enter number from 1-6");
            }
        }

    }

    private static void addVehicle() {
        System.out.println("Enter Vehicle Type: ");
        String type = sc.nextLine();
        System.out.println("Enter Liscence plate: ");
        String liscence_plate = sc.nextLine();
        vehicles v = new vehicles();
        v.setType(type);
        v.setLiscencePlate(liscence_plate);
        iVehicleDao.addVehicle(v);
    }

    private static void getVehicleById() {
        System.out.println("Enter the vehicle ID you want the data: ");
        int id = sc.nextInt();
        vehicles v = iVehicleDao.getVehicle(id);
        if (v != null) {
            System.out.println("The vehicle details are: " + v);
        } else {
            System.out.println("The vehicle detail with " + id + " weren't found ");
        }
    }

    private static void getAllVehicles() {
        List<vehicles> v = iVehicleDao.getAllVehicles();
        if (v != null) {
            System.out.println("All vehicles are: " + v);
        } else {
            System.out.println("There are no vehicles");
        }
    }

    private static void updateVehicle() {
        System.out.println("Enter vehicle id you want to update:");
        int id = sc.nextInt();
//        first check if the vehicle exists with that entered id through this method
        vehicles vehicleToUpdate = iVehicleDao.getVehicle(id);
        if (vehicleToUpdate != null) {
            System.out.println("Enter vehicle type:");
            String type = sc.nextLine();
            System.out.println("Enter Liscence plate:");
            String liscence_plate = sc.nextLine();
            vehicles v = new vehicles();
//            creating object and storing user entered data into that object
            v.setId(id);
            v.setType(type);
            v.setLiscencePlate(liscence_plate);
//            calling the update function and passing vehicle object
            iVehicleDao.updateVehicles(v);
        } else {
            System.out.println("There is no vehicle of that id");
        }
    }

    private static void deleteVehicle() {
        System.out.println("Enter the vehicle id you want to delete: ");
        int id = sc.nextInt();
        iVehicleDao.deleteVehicle(id);
    }
}
