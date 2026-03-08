/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

public class CartItem {

    private Car car;
    private boolean needDriver;

    public CartItem(Car car, boolean needDriver) {
        this.car = car;
        this.needDriver = needDriver;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public boolean isNeedDriver() {
        return needDriver;
    }

    public void setNeedDriver(boolean needDriver) {
        this.needDriver = needDriver;
    }
}
