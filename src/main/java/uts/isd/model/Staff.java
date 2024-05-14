package uts.isd.model;

import java.util.*;

public class Staff extends User {

    private String department;

    public Staff(String _email, String _name, String _password, String _phone, String _city, String _country,
            String _role,String _department) {
        super(_email, _name, _password, _phone, _city, _country, _role);
        this.department = _department;
    }

    // Methods
    public void manageDeviceCollection() {

    }

    public void trackOrder() {

    }

    public void manageUsers() {

    }

    public List<AccessLog> viewAccessLogs() {
        return null;
    }

    // Getters And Setters

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

}
