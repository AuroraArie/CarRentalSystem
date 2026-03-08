/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ADMIN
 */
public class OrderDetail {
    private int orderDetailID;
    private int orderID;
    private int carID;
    private Integer driverID;
    private double price;

    public OrderDetail() {
    }

    public OrderDetail(int orderDetailID, int orderID, int carID, Integer driverID, double price) {
        this.orderDetailID = orderDetailID;
        this.orderID = orderID;
        this.carID = carID;
        this.driverID = driverID;
        this.price = price;
    }

    public int getOrderDetailID() {
        return orderDetailID;
    }

    public int getOrderID() {
        return orderID;
    }

    public int getCarID() {
        return carID;
    }

    public Integer getDriverID() {
        return driverID;
    }

    public double getPrice() {
        return price;
    }

    public void setOrderDetailID(int orderDetailID) {
        this.orderDetailID = orderDetailID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public void setCarID(int carID) {
        this.carID = carID;
    }

    public void setDriverID(Integer driverID) {
        this.driverID = driverID;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
    
}
