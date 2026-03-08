/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import model.Car;
import model.CarType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarDAO extends DBContext {

    // Hàm lấy danh sách tất cả các xe (Cho Manager quản lý)
    public List<Car> getAllCars() {
        List<Car> list = new ArrayList<>();
        // JOIN bảng Cars với bảng CarTypes để lấy số chỗ ngồi
        String sql = "SELECT c.id, c.name, c.license_plate, c.price_per_day, c.status, " +
                     "t.id as type_id, t.seats " +
                     "FROM Cars c INNER JOIN CarTypes t ON c.type_id = t.id";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Car car = new Car();
                car.setId(rs.getInt("id"));
                car.setName(rs.getString("name"));
                car.setLicensePlate(rs.getString("license_plate"));
                car.setPricePerDay(rs.getDouble("price_per_day"));
                car.setStatus(rs.getString("status"));
                
                CarType type = new CarType();
                type.setId(rs.getInt("type_id"));
                type.setSeats(rs.getInt("seats"));
                car.setCarType(type);
                
                list.add(car);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi hàm getAllCars: " + e.getMessage());
        }
        return list;
    }

    // Hàm tìm kiếm xe khả dụng theo Loại xe (4, 5, 7 chỗ)
    // Lưu ý: Để check chính xác ngày mượn/trả trống lịch, cần dùng câu query NOT IN bảng ContractDetails
    public List<Car> searchAvailableCars(String seatsStr) {
        List<Car> list = new ArrayList<>();
        String sql = "SELECT c.id, c.name, c.license_plate, c.price_per_day, c.status, t.seats " +
                     "FROM Cars c INNER JOIN CarTypes t ON c.type_id = t.id " +
                     "WHERE c.status = 'AVAILABLE' ";
                     
        if (seatsStr != null && !seatsStr.equals("ALL")) {
            sql += "AND t.seats = ? ";
        }
        
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            if (seatsStr != null && !seatsStr.equals("ALL")) {
                st.setInt(1, Integer.parseInt(seatsStr));
            }
            
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Car car = new Car();
                car.setId(rs.getInt("id"));
                car.setName(rs.getString("name"));
                car.setLicensePlate(rs.getString("license_plate"));
                car.setPricePerDay(rs.getDouble("price_per_day"));
                car.setStatus(rs.getString("status"));
                
                CarType type = new CarType();
                type.setSeats(rs.getInt("seats"));
                car.setCarType(type);
                
                list.add(car);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi hàm searchAvailableCars: " + e.getMessage());
        }
        return list;
    }
    // --- CÁC HÀM DÀNH CHO MANAGER QUẢN LÝ XE ---

    // 1. Thêm xe mới vào hệ thống
    public boolean insertCar(Car car) {
        String sql = "INSERT INTO Cars (name, license_plate, price_per_day, type_id, status) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, car.getName());
            st.setString(2, car.getLicensePlate());
            st.setDouble(3, car.getPricePerDay());
            st.setInt(4, car.getCarType().getId());
            st.setString(5, car.getStatus()); // 'Sẵn sàng', 'Đang bảo dưỡng', v.v.
            
            int affectedRows = st.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            System.out.println("Lỗi hàm insertCar: " + e.getMessage());
        }
        return false;
    }

    // 2. Cập nhật thông tin xe
    public boolean updateCar(Car car) {
        String sql = "UPDATE Cars SET name = ?, license_plate = ?, price_per_day = ?, type_id = ?, status = ? WHERE id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, car.getName());
            st.setString(2, car.getLicensePlate());
            st.setDouble(3, car.getPricePerDay());
            st.setInt(4, car.getCarType().getId());
            st.setString(5, car.getStatus());
            st.setInt(6, car.getId()); // ID của xe cần update
            
            int affectedRows = st.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            System.out.println("Lỗi hàm updateCar: " + e.getMessage());
        }
        return false;
    }

    // 3. Xóa xe khỏi hệ thống
    public boolean deleteCar(int id) {
        String sql = "DELETE FROM Cars WHERE id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            
            int affectedRows = st.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            System.out.println("Lỗi hàm deleteCar: " + e.getMessage());
            // Lưu ý: Nếu xe này đã từng có trong hợp đồng (bảng ContractDetails), 
            // SQL Server sẽ báo lỗi khóa ngoại (Foreign Key constraint). 
            // Trong thực tế, người ta thường dùng cột is_deleted (xóa mềm) thay vì DELETE hẳn.
        }
        return false;
    }
}