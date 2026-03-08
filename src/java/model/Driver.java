/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ADMIN
 */
public class Driver {

    private int driverID;
    private int userID;
    private String fullName;
    private String phone;
    private String status;

    public Driver() {
    }

    public Driver(int driverID, int userID, String fullName, String phone, String status) {
        this.driverID = driverID;
        this.userID = userID;
        this.fullName = fullName;
        this.phone = phone;
        this.status = status;
    }

    public int getDriverID() {
        return driverID;
    }

    public int getUserID() {
        return userID;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPhone() {
        return phone;
    }

    public String getStatus() {
        return status;
    }

    public void setDriverID(int driverID) {
        this.driverID = driverID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
