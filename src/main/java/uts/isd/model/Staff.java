package uts.isd.model;

import java.util.*;

public class Staff extends User {

    // attributes
    private String department;
    private boolean status;

    // Alternate Constructor
    public Staff(String _email, String _name, String _password, String _phone, String _city, String _country,
            String _role,String _department, boolean _status) {
        super(_email, _name, _password, _phone, _city, _country, _role);
        this.department = _department;
        this.status = _status;
    }

    // Getters And Setters

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public boolean isActive() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}
