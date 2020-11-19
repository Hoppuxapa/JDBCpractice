package com.kravchuk.jdbc.model;

import com.kravchuk.jdbc.config.DataTransferObject;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Customer implements DataTransferObject {

    private int id;
    private String firstName;
    private String lastName;

    public int getAge() {
        return age;
    }

    private int age;
    private LocalDate dateOfBirth;
    private LocalDate registerDay;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public LocalDate getRegisterDay() {
        return registerDay;
    }

    public void setRegisterDay(LocalDate registerDay) {
        this.registerDay = registerDay;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", dateOfBirth=" + dateOfBirth +
                ", registerDay=" + registerDay +
                '}';
    }
    }
