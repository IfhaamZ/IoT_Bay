package uts.isd.model;

import java.io.Serializable;

public class Payment implements Serializable {
    // attributes for payment details
    protected String paymentID;
    protected String method;
    protected String cardNum;
    protected String expMonth;
    protected String expYear;
    protected String cvn;
    protected String GCNum;
    protected String pin;
    protected String paymentAmount;
    protected String paymentDate;

    public Payment() {
    }

    public Payment(String paymentID) {
        this.paymentID = paymentID;
    }

    // constructor to initialize a Payment object with the given attributes
    public Payment(String paymentID, String method, String cardNum, String expMonth, String expYear, String cvn,
            String GCNum, String pin, String paymentAmount, String paymentDate) {
        this(method, cardNum, expMonth, expYear, cvn, GCNum, pin, paymentAmount, paymentDate);
        this.paymentID = paymentID;
    }

    public Payment(String method, String cardNum, String expMonth, String expYear, String cvn,
            String GCNum, String pin, String paymentAmount, String paymentDate) {
        this.method = method;
        this.cardNum = cardNum;
        this.expMonth = expMonth;
        this.expYear = expYear;
        this.cvn = cvn;
        this.GCNum = GCNum;
        this.pin = pin;
        this.paymentAmount = paymentAmount;
        this.paymentDate = paymentDate;
    }

    // getter and setter methods for each attribute

    public String getPaymentID() {
        return this.paymentID;
    }

    public void setPaymentID(String paymentID) {
        this.paymentID = paymentID;
    }

    public String getMethod() {
        return this.method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getCardNum() {
        return this.cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public String getExpMonth() {
        return this.expMonth;
    }

    public void setExpMonth(String expMonth) {
        this.expMonth = expMonth;
    }

    public String getExpYear() {
        return this.expYear;
    }

    public void setExpYear(String expYear) {
        this.expYear = expYear;
    }

    public String getCVN() {
        return this.cvn;
    }

    public void setCVN(String cvn) {
        this.cvn = cvn;
    }

    public String getGCNum() {
        return this.GCNum;
    }

    public void setGCNum(String GCNum) {
        this.GCNum = GCNum;
    }

    public String getPIN() {
        return this.pin;
    }

    public void setPIN(String pin) {
        this.pin = pin;
    }

    public String getPaymentAmount() {
        return this.paymentAmount;
    }

    public void setPaymentAmount(String paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public String getPaymentDate() {
        return this.paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }
}