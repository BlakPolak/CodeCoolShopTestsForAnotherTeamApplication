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

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!User.class.isAssignableFrom(obj.getClass())) {
            return false;
        }
        final User other = (User) obj;
        if ((this.id == null) ? (other.id != null) : !this.id.equals(other.id)) {
            return false;
        }
        if ((this.firstName == null) ? (other.firstName != null) : !this.firstName.equals(other.firstName)) {
            return false;
        }
        if ((this.lastName == null) ? (other.lastName != null) : !this.lastName.equals(other.lastName)) {
            return false;
        }
        if ((this.adres == null) ? (other.adres != null) : !this.adres.equals(other.adres)) {
            return false;
        }

        if ((phone == null) ? (phone != null) : !this.phone.equals(other.phone)) {
            return false;
        }

        if ((email == null) ? (email != null) : !this.email.equals(other.email)) {
            return false;
        }
        return true;
    }

}
