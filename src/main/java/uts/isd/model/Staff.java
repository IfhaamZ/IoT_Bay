package uts.isd.model;

import java.util.*;

public class Staff extends User {
    
    private int staffID;
    private String role;
    private int accessLevel;
    
    public Staff(String _email, String _name, String _password, String _phone, String _city, String _country,
            String _role, int staffID, String role, int accessLevel) {
        super(_email, _name, _password, _phone, _city, _country, _role);
        this.staffID = staffID;
        this.role = role;
        this.accessLevel = accessLevel;
    }

    public int getStaffID() {
        return staffID;
    }

    public void setStaffID(int staffID) {
        this.staffID = staffID;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(int accessLevel) {
        this.accessLevel = accessLevel;
    }

    
}
