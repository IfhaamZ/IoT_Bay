package uts.isd.model;

import java.util.*;
import java.io.Serializable;

public class User implements Serializable {
    private String email;
    private String name;
    private String password;
    private String phone;
    private String city;
    private String country;
    private String role;

    public User(String _email, String _name, String _password, String _phone, String _city, String _country, String _role) {
        this.email = _email;
        this.name = _name;
        this.password = _password;
        this.phone = _phone;
        this.city = _city;
        this.country = _country;
        this.role = _role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    


}

	

	
