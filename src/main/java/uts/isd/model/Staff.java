package uts.isd.model;

import java.util.*;

public class Staff {

    private int staffID;
    private String role;
    private int accessLevel;
  
    public Staff(int staffID, String role, int accessLevel) {
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
  
    public void manageDeviceCollection() {

    }
  
    public void trackOrder() {
      
    }
  
    public void manageUsers() {
      
    }
  
    public List<AccessLog> viewAccessLogs() {
      return null;
    }
  }
