package com.ps;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class DealershipTest {

@Test
    void vehiclesByPriceShouldReturnPriceWithinSpecifiedRange() {

    //Arrange
    Dealership dealership =new Dealership("Test Dealer", "123 Road", "555-5555");
    dealership.addVehicle(new Vehicle(1001, 2020, "Toyota", "Camry", "Sedan", "Black", 45000, 15000.00, resultSet.getBoolean("sold")));
    dealership.addVehicle(new Vehicle(1002, 2018, "Honda", "Civic", "Sedan", "White", 60000, 12000.00, resultSet.getBoolean("sold")));
    dealership.addVehicle(new Vehicle(1003, 2022, "Ford", "Explorer", "SUV", "Red", 20000, 28000.00, resultSet.getBoolean("sold")));


    //Act
    ArrayList<Vehicle> results = dealership.vehiclesByPrice(10000, 16000);
    int expectedResultsNumber = 2;
    int actualResultsNumber = results.size();

    int expectedVinforVehicleSearchByPrice1 = 1001;
    int expectedVinforVehicleSearchByPrice2 = 1002;
    int actualVinFoundForSearchByPrice1 = results.get(0).getVin();
    int actualVinFoundForSearchByPrice2 = results.get(1).getVin();

    //Assert

    assertEquals(expectedResultsNumber,actualResultsNumber);
    assertEquals(expectedVinforVehicleSearchByPrice1,actualVinFoundForSearchByPrice1);
    assertEquals(expectedVinforVehicleSearchByPrice2,actualVinFoundForSearchByPrice2);
}

    @org.junit.jupiter.api.Test
    void vehiclesByMakeModelShouldReturnMakeAndModelSpecified() {

        //Arrange
        Dealership dealership =new Dealership("Test Dealer", "123 Road", "555-5555");
        dealership.addVehicle(new Vehicle(1001, 2020, "Toyota", "Camry", "Sedan", "Black", 45000, 15000.00, resultSet.getBoolean("sold")));
        dealership.addVehicle(new Vehicle(1002, 2018, "Honda", "Civic", "Sedan", "White", 60000, 12000.00, resultSet.getBoolean("sold")));
        dealership.addVehicle(new Vehicle(1003, 2022, "Ford", "Explorer", "SUV", "Red", 20000, 28000.00, resultSet.getBoolean("sold")));


        //Act
        ArrayList<Vehicle> results = dealership.vehiclesByMakeModel("Honda","Civic");
        String expectedMake = "Honda";
        String expectedModel = "Civic";
        String actualMake = results.get(0).getMake();
        String actualModel = results.get(0).getModel();


        assertEquals(expectedMake,actualMake);
        assertEquals(expectedModel,actualModel);
    }

    @org.junit.jupiter.api.Test
    void vehiclesByYearShouldReturnVehiclesWithinSpecifiedRange() {

        // Arrange
        Dealership dealership = new Dealership("Test Dealer", "123 Road", "555-5555");
        dealership.addVehicle(new Vehicle(1001, 2020, "Toyota", "Camry", "Sedan", "Black", 45000, 15000.00, resultSet.getBoolean("sold")));
        dealership.addVehicle(new Vehicle(1002, 2018, "Honda", "Civic", "Sedan", "White", 60000, 12000.00, resultSet.getBoolean("sold")));
        dealership.addVehicle(new Vehicle(1003, 2022, "Ford", "Explorer", "SUV", "Red", 20000, 28000.00, resultSet.getBoolean("sold")));

        // Act
        ArrayList<Vehicle> results = dealership.vehiclesByYear(2018, 2020);
        int expectedVin1 = 1001;
        int expectedVin2 = 1002;
        int actualVin1 = results.get(0).getVin();
        int actualVin2 = results.get(1).getVin();

        // Assert
        assertEquals(expectedVin1, actualVin1);
        assertEquals(expectedVin2, actualVin2);
    }



    @org.junit.jupiter.api.Test
    void vehiclesByColorShouldReturnVehiclesMatchingColor() {

        // Arrange
        Dealership dealership = new Dealership("Test Dealer", "123 Road", "555-5555");
        dealership.addVehicle(new Vehicle(1001, 2020, "Toyota", "Camry", "Sedan", "Black", 45000, 15000.00, resultSet.getBoolean("sold")));
        dealership.addVehicle(new Vehicle(1002, 2018, "Honda", "Civic", "Sedan", "White", 60000, 12000.00, resultSet.getBoolean("sold")));
        dealership.addVehicle(new Vehicle(1003, 2022, "Ford", "Explorer", "SUV", "Red", 20000, 28000.00, resultSet.getBoolean("sold")));

        // Act
        ArrayList<Vehicle> results = dealership.vehiclesByColor("Red");
        String expectedColor = "Red";
        String actualColor = results.get(0).getColor();

        // Assert
        assertEquals(expectedColor, actualColor);
    }


    @org.junit.jupiter.api.Test
    void vehiclesByMilageShouldReturnVehiclesWithinSpecifiedRange() {

        // Arrange
        Dealership dealership = new Dealership("Test Dealer", "123 Road", "555-5555");
        dealership.addVehicle(new Vehicle(1001, 2020, "Toyota", "Camry", "Sedan", "Black", 45000, 15000.00, resultSet.getBoolean("sold")));
        dealership.addVehicle(new Vehicle(1002, 2018, "Honda", "Civic", "Sedan", "White", 60000, 12000.00, resultSet.getBoolean("sold")));
        dealership.addVehicle(new Vehicle(1003, 2022, "Ford", "Explorer", "SUV", "Red", 20000, 28000.00, resultSet.getBoolean("sold")));

        // Act
        ArrayList<Vehicle> results = dealership.vehiclesByMilage(0, 50000);
        int expectedVin1 = 1001;
        int expectedVin2 = 1003;
        int actualVin1 = results.get(0).getVin();
        int actualVin2 = results.get(1).getVin();

        // Assert
        assertEquals(expectedVin1, actualVin1);
        assertEquals(expectedVin2, actualVin2);
    }

}