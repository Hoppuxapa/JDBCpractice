package com.kravchuk.jdbc.dao;

import com.kravchuk.jdbc.config.DataAccessObject;
import com.kravchuk.jdbc.model.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDao extends DataAccessObject<Customer> {
    private Connection connection;

    private String INSERT = "INSERT into customer_info(id, first_name, last_name, age, birth_day, register_day)" +
            " VALUES(?, ?, ?, ?, ?, ?);";

    private String getCustomer = "select * from customer_info where id =?";

    private String getAllCustomers = "SELECT* FROM customer_info";

    private String deleteById = "DELETE FROM customer_info WHERE id =?";

    public CustomerDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void createOrUpdate(Customer customer) {
        try (PreparedStatement statement = connection.prepareStatement(INSERT)) {
            statement.setInt(1, customer.getId());
            statement.setString(2, customer.getFirstName());
            statement.setString(3, customer.getLastName());
            statement.setInt(4, customer.getAge());
            statement.setObject(5, customer.getDateOfBirth());
            statement.setObject(6, customer.getRegisterDay());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /*@Override
    public void update(Customer customer) {
        try (PreparedStatement statement = connection.prepareStatement(INSERT)) {
            statement.setInt(1, customer.getId());
            statement.setString(2, customer.getFirstName());
            statement.setString(3, customer.getLastName());
            statement.setObject(4, customer.getDateOfBirth());
            statement.setObject(5, customer.getRegisterDay());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/

    @Override
    public Customer getById(int id) {
        Customer customer = new Customer();
        try (PreparedStatement statement = connection.prepareStatement(getCustomer)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                customer.setFirstName(resultSet.getString(2));
                customer.setLastName(resultSet.getString(3));
                customer.setAge(resultSet.getInt(4));
                customer.setDateOfBirth(resultSet.getDate(5).toLocalDate());
                customer.setRegisterDay(resultSet.getDate(6).toLocalDate());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }

    @Override
    public List<Customer> getAll() {
        List<Customer> customers = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(getAllCustomers)) {
            ResultSet resultSet = statement.executeQuery(getAllCustomers);
            while (resultSet.next()) {
                Customer customer = new Customer();
                customer.setId(resultSet.getInt(1));
                customer.setFirstName(resultSet.getString(2));
                customer.setLastName(resultSet.getString(3));
                customer.setAge(resultSet.getInt(4));
                customer.setDateOfBirth(resultSet.getDate(5).toLocalDate());
                customer.setRegisterDay(resultSet.getDate(6).toLocalDate());
                customers.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    @Override
    public void remove(int id) {
        try (PreparedStatement statement = connection.prepareStatement(deleteById)) {
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
