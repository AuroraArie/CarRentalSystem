/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ADMIN
 */
public class Car {

    private int carID;
    private int carTypeID;
    private double pricePerDay;
    private String status;

    public Car() {
    }

    public Car(int carID, int carTypeID, double pricePerDay, String status) {
        this.carID = carID;
        this.carTypeID = carTypeID;
        this.pricePerDay = pricePerDay;
        this.status = status;
    }

    public int getCarID() {
        return carID;
    }

    public int getCarTypeID() {
        return carTypeID;
    }

    public double getPricePerDay() {
        return pricePerDay;
    }

    public String getStatus() {
        return status;
    }

    public void setCarID(int carID) {
        this.carID = carID;
    }

    public void setCarTypeID(int carTypeID) {
        this.carTypeID = carTypeID;
    }

    public void setPricePerDay(double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
