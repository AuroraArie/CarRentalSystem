/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import model.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO extends DBContext {

    // Hàm kiểm tra Đăng nhập
    public User login(String username, String password) {
        String sql = "SELECT * FROM Users WHERE username = ? AND password = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, username);
            st.setString(2, password); // Thực tế nên dùng MD5/SHA để mã hóa password
            ResultSet rs = st.executeQuery();
            
            if (rs.next()) {
                User u = new User();
                u.setId(rs.getInt("id"));
                u.setUsername(rs.getString("username"));
                u.setFullname(rs.getString("fullname"));
                u.setPhone(rs.getString("phone"));
                u.setRoleId(rs.getInt("role_id")); // 1: Admin, 2: Manager, 3: Staff, 4: Driver, 5: Customer
                return u;
            }
        } catch (SQLException e) {
            System.out.println("Lỗi hàm login: " + e.getMessage());
        }
        return null; // Đăng nhập thất bại
    }

    // Hàm Đăng ký tài khoản mới (Mặc định role_id = 5 cho Customer)
    public boolean register(User user) {
        String sql = "INSERT INTO Users (username, password, fullname, phone, role_id) VALUES (?, ?, ?, ?, 5)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, user.getUsername());
            st.setString(2, user.getPassword());
            st.setString(3, user.getFullname());
            st.setString(4, user.getPhone());
            
            int rowsAffected = st.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Lỗi hàm register: " + e.getMessage());
        }
        return false;
    }
    
    // Hàm kiểm tra username đã tồn tại chưa
    public boolean checkUserExist(String username) {
        String sql = "SELECT 1 FROM Users WHERE username = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, username);
            ResultSet rs = st.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.out.println("Lỗi hàm checkUserExist: " + e.getMessage());
        }
        return false;
    }
}