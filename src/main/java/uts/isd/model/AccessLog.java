package uts.isd.model;

import java.util.*;
import java.time.LocalDateTime;


public class AccessLog {

    private int logID;
    private int userID;
    private LocalDateTime timestamp;
    private String actionType;
    private String description;
  
    public AccessLog(int logID, int userID, LocalDateTime timestamp, String actionType, String description) {
      this.logID = logID;
      this.userID = userID;
      this.timestamp = timestamp;
      this.actionType = actionType;
      this.description = description;
    }
  
    public int getLogID() {
      return logID;
    }
  
    public void setLogID(int logID) {
      this.logID = logID;
    }
  
    public int getUserID() {
      return userID;
    }
  
    public void setUserID(int userID) {
      this.userID = userID;
    }
  
    public LocalDateTime getTimestamp() {
      return timestamp;
    }
  
    public void setTimestamp(LocalDateTime timestamp) {
      this.timestamp = timestamp;
    }
  
    public String getActionType() {
      return actionType;
    }
  
    public void setActionType(String actionType) {
      this.actionType = actionType;
    }
  
    public String getDescription() {
      return description;
    }
  
    public void setDescription(String description) {
      this.description = description;
    }

    public void logAccess() {

    }
  
    public List<AccessLog> retrieveLogsByUser() {

      return null;
    }
  
    public List<AccessLog> retrieveLogsByDate() {

      return null;
    }
  }