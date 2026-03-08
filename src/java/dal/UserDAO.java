/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.*;
import java.util.*;
import model.User;

/**
 *
 * @author ADMIN
 */
public class UserDAO extends DBContext {
    public User login(String username, String password) {

        String sql = "SELECT * FROM Users WHERE Username=? AND Password=?";

        try {

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                return new User(
                        rs.getInt("UserID"),
                        rs.getString("Username"),
                        rs.getString("Password"),
                        rs.getString("FullName"),
                        rs.getString("Phone"),
                        rs.getString("Email"),
                        rs.getInt("RoleID")
                       
                );

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    
}
