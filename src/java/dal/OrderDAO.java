/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.*;
import model.Order;
import java.util.*;

/**
 *
 * @author ADMIN
 */
public class OrderDAO extends DBContext {

    public void createOrder(Order o) {

        String sql = "INSERT INTO Orders(UserID,RentDate,ReturnDate,Status,DepositAmount) VALUES (?,?,?,?,?)";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            
            ps.setInt(1, o.getUserID());
            ps.setDate(2, new java.sql.Date(o.getRentDate().getTime()));
            ps.setDate(3, new java.sql.Date(o.getReturnDate().getTime()));
            ps.setString(4, o.getStatus());
            ps.setDouble(5, o.getDepositAmount());

            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
