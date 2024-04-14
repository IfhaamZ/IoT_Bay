package uts.isd.model;

public class SystemAdmin {

    private int adminID;
    private String accessPrivileges;
  
    public SystemAdmin(int adminID, String accessPrivileges) {
      this.adminID = adminID;
      this.accessPrivileges = accessPrivileges;
    }
  
    public boolean createStaffAccount() {
      
      return true; 
    }
  
    public boolean removeStaffAccount() {
      return true; 
    }
  
    public boolean updateSystemSettings() {
      return true; 
    }
  
    public void auditSystem() {

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