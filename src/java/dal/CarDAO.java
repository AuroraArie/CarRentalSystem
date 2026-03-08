/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.*;
import java.util.*;
import model.Car;

/**
 *
 * @author ADMIN
 */
public class CarDAO extends DBContext {

    public List<Car> getAllCars() {

        List<Car> list = new ArrayList<>();

        String sql = "SELECT * FROM Cars";

        try {

            PreparedStatement ps = connection.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Car c = new Car(
                        rs.getInt("CarID"),
                        rs.getInt("CarTypeID"),
                        rs.getDouble("PricePerDay"),
                        rs.getString("Status")
                );

                list.add(c);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
    

}
