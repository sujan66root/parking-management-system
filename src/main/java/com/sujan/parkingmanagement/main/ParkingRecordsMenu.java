package com.sujan.parkingmanagement.main;

import com.sujan.parkingmanagement.dao.IParkingRecordsDao;
import com.sujan.parkingmanagement.dao.ParkingRecordsDAO;
import com.sujan.parkingmanagement.model.ParkingRecords;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class ParkingRecordsMenu {
    private static final Scanner sc = new Scanner(System.in);
    private static final IParkingRecordsDao iParkingRecordsDao = new ParkingRecordsDAO();
    public static void ParkingRecordsOperations(Scanner sc){
        while (true) {
            System.out.println("Parking Records Operations");
            System.out.println("1. Add a parking record");
            System.out.println("2. Get a parking record by ID");
            System.out.println("3. Get all parking records");
            System.out.println("4. Update a parking record");
            System.out.println("5. Delete a parking record");
            System.out.println("6. Go back to main menu");
            System.out.println("7. Exit from system");
            System.out.println("8. Exit a vehicle from parking spaces");
            System.out.print("Enter the option you want to proceed with: ");
            int option = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (option) {
                case 1:
                    addParkingRecord();
                    break;
                case 2:
                    getParkingRecordById();
                    break;
                case 3:
                    getAllParkingRecords();
                    break;
                case 4:
                    updateParkingRecord();
                    break;
                case 5:
                    deleteParkingRecord();
                    break;
                case 6:
                    System.out.println("Go back to main menu");
                    return;
                case 7:
                    System.out.println("Exiting... ");
                    sc.close();
                    System.exit(0);
                case 8:
                    exitUpdateVehicle();
                    System.out.println("Exiting a vehicle...");
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
    public static void addParkingRecord(){
        System.out.println("Enter vehicle ID: ");
        int vehicleId = sc.nextInt();
        System.out.println("Enter space ID: ");
        int spaceId = sc.nextInt();
        System.out.println("Entry Time (yyyy-MM-dd HH:mm:ss): " + LocalDateTime.now());
        LocalDateTime entryTimeStr = LocalDateTime.now();
        double fee = 0;
        sc.nextLine(); // Consume newline
        ParkingRecords pr = new ParkingRecords();
        pr.setVehicle_id(vehicleId);
        pr.setSpace_id(spaceId);
        pr.setEntry_time(entryTimeStr);
        pr.setExit_time(null);
        pr.setFee(fee);
        iParkingRecordsDao.addParkingRecords(pr);

    }
    public static void getParkingRecordById(){
        System.out.println("Enter record ID: ");
        int id = sc.nextInt();
        sc.nextLine(); // Consume newline
        ParkingRecords pr = iParkingRecordsDao.getParkingRecordById(id);
        if (pr != null) {
            System.out.println(pr);
        } else {
            System.out.println("Record not found.");
        }
    }
    public static void getAllParkingRecords(){
        List<ParkingRecords> records = iParkingRecordsDao.getAllParkingRecords();
        if (records.isEmpty()) {
            System.out.println("No records found.");
        } else {
            for (ParkingRecords record : records) {
                System.out.println(record);
            }
        }
    }
    public static void updateParkingRecord(){
        System.out.println("Enter record ID to update: ");
        int id = sc.nextInt();
        sc.nextLine(); // Consume newline

        ParkingRecords record = iParkingRecordsDao.getParkingRecordById(id);
        if (record != null) {
            System.out.println("Enter new vehicle ID: ");
            record.setVehicle_id(sc.nextInt());
            System.out.println("Enter new space ID: ");
            record.setSpace_id(sc.nextInt());
            System.out.println("Exit Time: ");
            record.setExit_time(LocalDateTime.now());
            System.out.println("Enter new fee: ");
            record.setFee(iParkingRecordsDao.calculateParkingFee(record.getEntry_time(), LocalDateTime.now()));
            sc.nextLine(); // Consume newline

            iParkingRecordsDao.updateParkingRecord(record);
        } else {
            System.out.println("Record not found.");
        }
    }
    public static void deleteParkingRecord(){
        System.out.println("Enter record ID to delete: ");
        int id = sc.nextInt();
        sc.nextLine(); // Consume newline
        iParkingRecordsDao.deleteParkingRecord(id);
    }
    public static void exitUpdateVehicle(){
        System.out.println("Enter a vehicle id to exit: ");
        int id = sc.nextInt();
        sc.nextLine();

    }
}
