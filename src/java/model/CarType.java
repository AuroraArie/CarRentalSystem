/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ADMIN
 */
public class CarType {
    private int carTypeID;
    private String typeName;
    private int seats;

    public CarType() {
    }

    public CarType(int carTypeID, String typeName, int seats) {
        this.carTypeID = carTypeID;
        this.typeName = typeName;
        this.seats = seats;
    }

    public int getCarTypeID() {
        return carTypeID;
    }

    public String getTypeName() {
        return typeName;
    }

    public int getSeats() {
        return seats;
    }

    public void setCarTypeID(int carTypeID) {
        this.carTypeID = carTypeID;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }
    
    
}
