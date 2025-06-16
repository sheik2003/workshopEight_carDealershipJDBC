package com.ps;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DealershipDAO {

    private DataSource dataSource;

    public DealershipDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    public List<Dealership> getAll(){
        List<Dealership> dealerships = new ArrayList<>();

        String query = "SELECT * FROM dealerships;";

        try(
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery();
        ){
            if(resultSet.next()){
                do{
                    Dealership dealership = dealershipParser(resultSet);
                    dealerships.add(dealership);
                } while(resultSet.next());
            } else {
                System.out.println("Error getting DealerShips");
            }
        } catch(SQLException e){
            e.printStackTrace();
        }

        return dealerships;
    }




    public Dealership getDealershipById(int dealershipId) {
        String sql = "SELECT * FROM dealerships WHERE dealership_id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, dealershipId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Dealership(
                            resultSet.getInt("dealership_id"),
                            resultSet.getString("name"),
                            resultSet.getString("address"),
                            resultSet.getString("phone")
                    );
                }
            }

        } catch (SQLException e) {
            System.err.println("Error getting dealership by ID: " + e.getMessage());
        }

        return null;
    }

    public boolean addDealership(Dealership dealership) {
        String sql = "INSERT INTO dealerships (name, address, phone) VALUES (?, ?, ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, dealership.getName());
            statement.setString(2, dealership.getAddress());
            statement.setString(3, dealership.getPhone());

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                try (ResultSet keys = statement.getGeneratedKeys()) {
                    if (keys.next()) {
                        dealership.setDealershipId(keys.getInt(1));
                    }
                }
                return true;
            }

        } catch (SQLException e) {
            System.err.println("Error adding dealership: " + e.getMessage());
        }

        return false;
    }


    public boolean updateDealership(Dealership dealership) {
        String sql = "UPDATE dealerships SET name = ?, address = ?, phone = ? WHERE dealership_id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, dealership.getName());
            statement.setString(2, dealership.getAddress());
            statement.setString(3, dealership.getPhone());
            statement.setInt(4, dealership.getDealershipId());

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Error updating dealership: " + e.getMessage());
        }

        return false;
    }

    public boolean deleteDealership(int dealershipId) {
        String sql = "DELETE FROM dealerships WHERE dealership_id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, dealershipId);

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Error deleting dealership: " + e.getMessage());
        }

        return false;
    }




private Dealership dealershipParser(ResultSet resultSet) throws SQLException {
        String dealership_id = resultSet.getString("dealership_id");
        String name = resultSet.getString("name");
        String address = resultSet.getString("address");
        String phone = resultSet.getString(("phone"));

        return new Dealership(dealership_id, address, phone);
    }



}
