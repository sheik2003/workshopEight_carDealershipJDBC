package com.ps;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LeaseContractDao {
    private final DataSource dataSource;

    public LeaseContractDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<LeaseContract> getAllLeaseContracts() {
        String sql = "SELECT * FROM lease_contracts";
        List<LeaseContract> contracts = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                // Build Vehicle object (use VIN only, or fetch rest if needed)
                Vehicle vehicle = new Vehicle();
                vehicle.setVin(rs.getInt("vin"));

                LeaseContract contract = new LeaseContract(
                        rs.getString("date_lease"),
                        rs.getString("customer_name"),
                        rs.getString("customer_email"),
                        vehicle
                );
                contract.setExpectedEndingValue(rs.getDouble("expected_end_value"));
                contract.setLeaseFee(rs.getDouble("lease_fee"));
                contract.setMonthlyPayment(rs.getDouble("monthly_payment"));
                contracts.add(contract);
            }
        } catch (SQLException e) {
            System.err.println("Error getting lease contracts: " + e.getMessage());
        }
        return contracts;
    }

    public LeaseContract getLeaseContractById(int id) {
        String sql = "SELECT * FROM lease_contracts WHERE lease_contract_id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    Vehicle vehicle = new Vehicle();
                    vehicle.setVin(rs.getInt("vin"));

                    LeaseContract contract = new LeaseContract(
                            rs.getString("date_lease"),
                            rs.getString("customer_name"),
                            rs.getString("customer_email"),
                            vehicle
                    );
                    contract.setExpectedEndingValue(rs.getDouble("expected_end_value"));
                    contract.setLeaseFee(rs.getDouble("lease_fee"));
                    contract.setMonthlyPayment(rs.getDouble("monthly_payment"));
                    return contract;
                }
            }
        } catch (SQLException e) {
            System.err.println("Error getting lease contract by ID: " + e.getMessage());
        }
        return null;
    }

    public boolean addLeaseContract(LeaseContract contract) {
        String sql = """
            INSERT INTO lease_contracts (
                vin, customer_name, customer_email, date_lease,
                expected_end_value, lease_fee, total_price, monthly_payment
            ) VALUES (?, ?, ?, ?, ?, ?, ?, ?)
        """;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setInt(1, contract.getVehicleSold().getVin());
            statement.setString(2, contract.getCustomerName());
            statement.setString(3, contract.getEmail());
            statement.setDate(4, Date.valueOf(contract.getDate()));
            statement.setDouble(5, contract.getExpectedEndingValue());
            statement.setDouble(6, contract.getLeaseFee());
            statement.setDouble(7, contract.getTotalPrice());
            statement.setDouble(8, contract.getMonthlyPayment());

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error adding lease contract: " + e.getMessage());
        }
        return false;
    }

    public boolean updateLeaseContract(LeaseContract contract, int leaseContractId) {
        String sql = """
            UPDATE lease_contracts SET
                vin = ?, customer_name = ?, customer_email = ?, date_lease = ?,
                expected_end_value = ?, lease_fee = ?, total_price = ?, monthly_payment = ?
            WHERE lease_contract_id = ?
        """;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, contract.getVehicleSold().getVin());
            statement.setString(2, contract.getCustomerName());
            statement.setString(3, contract.getEmail());
            statement.setDate(4, Date.valueOf(contract.getDate()));
            statement.setDouble(5, contract.getExpectedEndingValue());
            statement.setDouble(6, contract.getLeaseFee());
            statement.setDouble(7, contract.getTotalPrice());
            statement.setDouble(8, contract.getMonthlyPayment());
            statement.setInt(9, leaseContractId);

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error updating lease contract: " + e.getMessage());
        }
        return false;
    }

    public boolean deleteLeaseContract(int leaseContractId) {
        String sql = "DELETE FROM lease_contracts WHERE lease_contract_id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, leaseContractId);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error deleting lease contract: " + e.getMessage());
        }
        return false;
    }
}
