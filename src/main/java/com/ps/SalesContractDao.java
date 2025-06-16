package com.ps;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SalesContractDao {
    private final DataSource dataSource;

    public SalesContractDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<SalesContract> getAllSalesContracts() {
        String sql = "SELECT * FROM sales_contracts";
        List<SalesContract> contracts = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                // Create Vehicle object with VIN only (or load from DAO as needed)
                Vehicle vehicle = new Vehicle();
                vehicle.setVin(rs.getInt("VIN"));

                SalesContract contract = new SalesContract(
                        rs.getString("date_sold"),
                        rs.getString("customer_name"),
                        rs.getString("customer_email"),
                        vehicle,
                        rs.getBoolean("is_financed")
                );
                // set extra values from database
                contract.setSalesTaxAmount(rs.getDouble("sales_tax_amount"));
                contract.setRecordingFee(rs.getDouble("recording_fee"));
                contract.setProcessingFee(rs.getDouble("processing_fee"));
                contract.setMonthlyPayment(rs.getDouble("monthly_payment"));

                contracts.add(contract);
            }
        } catch (SQLException e) {
            System.err.println("Error getting sales contracts: " + e.getMessage());
        }
        return contracts;
    }

    public boolean addSalesContract(SalesContract contract) {
        String sql = """
            INSERT INTO sales_contracts (
                VIN, customer_name, customer_email, date_sold, sale_price,
                sales_tax_amount, recording_fee, processing_fee, is_financed, monthly_payment, total_price
            ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setInt(1, contract.getVehicleSold().getVin());
            statement.setString(2, contract.getCustomerName());
            statement.setString(3, contract.getEmail());
            statement.setDate(4, Date.valueOf(contract.getDate())); // contract.getDate() should be String "yyyy-MM-dd"
            statement.setDouble(5, contract.getVehicleSold().getPrice());
            statement.setDouble(6, contract.getSalesTaxAmount());
            statement.setDouble(7, contract.getRecordingFee());
            statement.setDouble(8, contract.getProcessingFee());
            statement.setBoolean(9, contract.isFinanced());
            statement.setDouble(10, contract.getMonthlyPayment());
            statement.setDouble(11, contract.getTotalPrice());

            int rowsAffected = statement.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error adding sales contract: " + e.getMessage());
        }
        return false;
    }

    public SalesContract getSalesContractById(int id) {
        String sql = "SELECT * FROM sales_contracts WHERE id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    Vehicle vehicle = new Vehicle();
                    vehicle.setVin(rs.getInt("VIN"));

                    SalesContract contract = new SalesContract(
                            rs.getString("date_sold"),
                            rs.getString("customer_name"),
                            rs.getString("customer_email"),
                            vehicle,
                            rs.getBoolean("is_financed")
                    );
                    contract.setSalesTaxAmount(rs.getDouble("sales_tax_amount"));
                    contract.setRecordingFee(rs.getDouble("recording_fee"));
                    contract.setProcessingFee(rs.getDouble("processing_fee"));
                    contract.setMonthlyPayment(rs.getDouble("monthly_payment"));
                    return contract;
                }
            }
        } catch (SQLException e) {
            System.err.println("Error getting sales contract by ID: " + e.getMessage());
        }
        return null;
    }

    public boolean updateSalesContract(SalesContract contract, int id) {
        String sql = """
            UPDATE sales_contracts SET
                VIN = ?, customer_name = ?, customer_email = ?, date_sold = ?, sale_price = ?,
                sales_tax_amount = ?, recording_fee = ?, processing_fee = ?, is_financed = ?, monthly_payment = ?, total_price = ?
            WHERE id = ?
        """;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, contract.getVehicleSold().getVin());
            statement.setString(2, contract.getCustomerName());
            statement.setString(3, contract.getEmail());
            statement.setDate(4, Date.valueOf(contract.getDate()));
            statement.setDouble(5, contract.getVehicleSold().getPrice());
            statement.setDouble(6, contract.getSalesTaxAmount());
            statement.setDouble(7, contract.getRecordingFee());
            statement.setDouble(8, contract.getProcessingFee());
            statement.setBoolean(9, contract.isFinanced());
            statement.setDouble(10, contract.getMonthlyPayment());
            statement.setDouble(11, contract.getTotalPrice());
            statement.setInt(12, id);

            int rowsAffected = statement.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error updating sales contract: " + e.getMessage());
        }
        return false;
    }

    public boolean deleteSalesContract(int id) {
        String sql = "DELETE FROM sales_contracts WHERE id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error deleting sales contract: " + e.getMessage());
        }
        return false;
    }
}
