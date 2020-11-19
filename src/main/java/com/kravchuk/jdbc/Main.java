package com.kravchuk.jdbc;

import com.kravchuk.jdbc.dao.CustomerDao;
import com.kravchuk.jdbc.model.Customer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Main {
    static final String DATABASE_URL = "jdbc:postgresql://localhost/shop";
    static final String JDBC_DRIVER = "org.postgresql.Driver";

    static final String USER = "postgres";
    static final String PASSWORD = "postgres";

    public static void main(String[] args) throws ClassNotFoundException, SQLException {


        Connection connection;


        try {
            Class.forName(JDBC_DRIVER);
            DriverManager.registerDriver(new org.postgresql.Driver());
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
            CustomerDao dao = new CustomerDao(connection);


            Customer customer = new Customer();
            customer.setId(1);
            customer.setFirstName("Ivan");
            customer.setLastName("Pupkin");
            customer.setAge(20);
            customer.setDateOfBirth(LocalDate.parse(("10/10/2000"), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            customer.setRegisterDay(LocalDate.parse(("20/05/2019"), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            dao.createOrUpdate(customer);


            System.out.println(dao.getById(1).toString());

            dao.remove(1);


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}