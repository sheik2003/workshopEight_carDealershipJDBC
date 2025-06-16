package com.ps;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehicleDao {
    private final DataSource dataSource;

    public VehicleDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Vehicle> getAllVehicles() {
        String sql = "SELECT * FROM vehicles";
        List<Vehicle> vehicles = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Vehicle vehicle = new Vehicle(
                        resultSet.getInt("VIN"),
                        resultSet.getInt("year"),
                        resultSet.getString("make"),
                        resultSet.getString("model"),
                        resultSet.getString("vehicleType"),
                        resultSet.getString("color"),
                        resultSet.getInt("mileage"),
                        resultSet.getDouble("price"),
                        resultSet.getBoolean("sold")
                );
                vehicles.add(vehicle);
            }
        } catch (SQLException e) {
            System.err.println("Error getting vehicles: " + e.getMessage());
        }

        return vehicles;
    }

    public Vehicle getVehicleByVin(int vin) {
        String sql = "SELECT * FROM vehicles WHERE VIN = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, vin);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Vehicle(
                            resultSet.getInt("VIN"),
                            resultSet.getInt("year"),
                            resultSet.getString("make"),
                            resultSet.getString("model"),
                            resultSet.getString("vehicleType"),
                            resultSet.getString("color"),
                            resultSet.getInt("mileage"),
                            resultSet.getDouble("price"),
                            resultSet.getBoolean("sold")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error getting vehicle by VIN: " + e.getMessage());
        }

        return null;
    }

    public boolean addVehicle(Vehicle vehicle) {
        String sql = "INSERT INTO vehicles (year, make, model, vehicleType, color, mileage, price, sold) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setInt(1, vehicle.getYear());
            statement.setString(2, vehicle.getMake());
            statement.setString(3, vehicle.getModel());
            statement.setString(4, vehicle.getVehicleType());
            statement.setString(5, vehicle.getColor());
            statement.setInt(6, vehicle.getOdometer());
            statement.setDouble(7, vehicle.getPrice());
            statement.setBoolean(8, vehicle.isSold());

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                try (ResultSet keys = statement.getGeneratedKeys()) {
                    if (keys.next()) {
                        vehicle.setVin(keys.getInt(1));
                    }
                }
                return true;
            }

        } catch (SQLException e) {
            System.err.println("Error adding vehicle: " + e.getMessage());
        }

        return false;
    }

    public boolean updateVehicle(Vehicle vehicle) {
        String sql = """
            UPDATE vehicles
            SET year = ?, make = ?, model = ?, vehicleType = ?, color = ?, mileage = ?, price = ?, sold = ?
            WHERE VIN = ?
            """;

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, vehicle.getYear());
            statement.setString(2, vehicle.getMake());
            statement.setString(3, vehicle.getModel());
            statement.setString(4, vehicle.getVehicleType());
            statement.setString(5, vehicle.getColor());
            statement.setInt(6, vehicle.getOdometer());
            statement.setDouble(7, vehicle.getPrice());
            statement.setBoolean(8, vehicle.isSold());
            statement.setInt(9, vehicle.getVin());

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Error updating vehicle: " + e.getMessage());
        }

        return false;
    }

    public boolean deleteVehicle(int vin) {
        String sql = "DELETE FROM vehicles WHERE VIN = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, vin);

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Error deleting vehicle: " + e.getMessage());
        }

        return false;
    }
}
