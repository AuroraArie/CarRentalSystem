/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private String pickupDate;
    private String returnDate;
    private List<CartItem> items;

    public Cart() {
        this.items = new ArrayList<>();
    }

    public String getPickupDate() { return pickupDate; }
    public void setPickupDate(String pickupDate) { this.pickupDate = pickupDate; }
    public String getReturnDate() { return returnDate; }
    public void setReturnDate(String returnDate) { this.returnDate = returnDate; }
    public List<CartItem> getItems() { return items; }
    
    public void addItem(CartItem item) {
        // Kiểm tra nếu xe đã có trong giỏ hàng thì không thêm nữa
        for (CartItem i : items) {
            if (i.getCar().getId() == item.getCar().getId()) {
                return; 
            }
        }
        this.items.add(item);
    }

    public void removeItem(int carId) {
        items.removeIf(item -> item.getCar().getId() == carId);
    }

    public double getTotalPrice() {
        double total = 0;
        for (CartItem item : items) {
            total += item.getCar().getPricePerDay();
        }
        return total;
    }
}
