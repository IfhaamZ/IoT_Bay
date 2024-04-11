package uts.isd;

import java.io.Serializable;

public class User implements Serializable {
    private String email;
    private String name;
    private String password;
    private String phone;
    private String city;
    private String country;
    private String role;

    public User(String email, String name, String password, String phone, String city, String country, String role) {
        this.name = name;
        this.password = password;
        this.phone = phone;
        this.city = city;
        this.country = country;
        this.role = role;
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

	

	
