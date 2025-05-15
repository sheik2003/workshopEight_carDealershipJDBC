package com.ps;

public class Main {
    public static void main(String[] args) {
//        UserInterface userInterface = new UserInterface();
//        userInterface.display();

        Vehicle vehicle = new Vehicle(
                44901,           // VIN
                2012,            // Year
                "Honda",         // Make
                "Civic",         // Model
                "SUV",           // Type
                "Gray",          // Color
                103221,          // Odometer
                6995.00          // Price
        );

        // Create a sales contract (only pass what's needed to compute fields dynamically)
        SalesContract salecontract = new SalesContract(
                "20240515",              // date
                "John Doe",              // customer name
                "john@example.com",      // email
                vehicle,
                true                     // isFinanced
        );
        ContractFileManager.saveContract(salecontract);
        System.out.println("Contract saved successfully.");



        Vehicle testVehicle = new Vehicle(
                37846,                      // VIN
                2021,                       // Year
                "Chevrolet",                // Make
                "Silverado",                // Model
                "truck",                    // Type
                "Black",                    // Color
                2750,                       // Odometer
                31995.00                    // Price
        );

        LeaseContract testLease = new LeaseContract(
                "20210928",                 // Date
                "Zachary Westly",           // Customer Name
                "zach@texas.com",           // Email
                testVehicle                 // Vehicle object
        );
        ContractFileManager.saveContract(testLease);



        System.out.println("Contract saved successfully.");
    }
}