package uts.isd.model;

import java.util.*;
public class SystemAdmin extends User {
    
    private int adminID;
    private String accessPrivileges;
    
    public SystemAdmin(String _email, String _name, String _password, String _phone, String _city, String _country,
            String _role, int adminID, String accessPrivileges) {
        super(_email, _name, _password, _phone, _city, _country, _role);
        this.adminID = adminID;
        this.accessPrivileges = accessPrivileges;
    }

    public int getAdminID() {
        return adminID;
    }

    public void setAdminID(int adminID) {
        this.adminID = adminID;
    }

    public String getAccessPrivileges() {
        return accessPrivileges;
    }

    public void setAccessPrivileges(String accessPrivileges) {
        this.accessPrivileges = accessPrivileges;
    }

    
}
