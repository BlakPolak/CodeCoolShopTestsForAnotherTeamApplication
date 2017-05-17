package com.codecool.shop.model;


public class User {

    private String firstName;
    private String lastName;
    private String adres;
    private String phone;
    private String email;
    private Integer id;

    public User(String firstName, String lastName, String adres, String phone, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.adres = adres;
        this.phone = phone;
        this.email = email;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAdres() {
        return adres;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public Integer getId() {
        return id;
    }
}
