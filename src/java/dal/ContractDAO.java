/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import model.Contract;
import model.ContractDetail;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ContractDAO extends DBContext {

    // 1. Hàm tạo hợp đồng mới (Bao gồm lưu bảng Contracts và bảng ContractDetails)
    public boolean createContract(Contract contract, List<ContractDetail> details) {
        String sqlContract = "INSERT INTO Contracts (customer_id, pickup_date, return_date, total_price, status, created_at) "
                           + "VALUES (?, ?, ?, ?, 'PENDING', GETDATE())";
        String sqlDetail = "INSERT INTO ContractDetails (contract_id, car_id, need_driver, driver_id, price) "
                         + "VALUES (?, ?, ?, NULL, ?)";
                         
        try {
            // Tắt auto-commit để bắt đầu Transaction
            connection.setAutoCommit(false);
            
            // Bước 1: Insert vào bảng Contracts và lấy ra ID của hợp đồng vừa tạo
            PreparedStatement stContract = connection.prepareStatement(sqlContract, Statement.RETURN_GENERATED_KEYS);
            stContract.setInt(1, contract.getCustomerId());
            stContract.setString(2, contract.getPickupDate()); // Định dạng YYYY-MM-DD HH:MM:SS
            stContract.setString(3, contract.getReturnDate());
            stContract.setDouble(4, contract.getTotalPrice());
            
            int affectedRows = stContract.executeUpdate();
            if (affectedRows == 0) {
                connection.rollback();
                return false;
            }
            
            // Lấy Contract ID vừa sinh ra
            ResultSet generatedKeys = stContract.getGeneratedKeys();
            int contractId = 0;
            if (generatedKeys.next()) {
                contractId = generatedKeys.getInt(1);
            } else {
                connection.rollback();
                return false;
            }
            
            // Bước 2: Dùng Contract ID vừa lấy được để Insert danh sách các xe vào ContractDetails
            PreparedStatement stDetail = connection.prepareStatement(sqlDetail);
            for (ContractDetail detail : details) {
                stDetail.setInt(1, contractId);
                stDetail.setInt(2, detail.getCarId());
                stDetail.setBoolean(3, detail.isNeedDriver());
                stDetail.setDouble(4, detail.getPrice()); // Giá thuê của xe đó tại thời điểm mượn
                stDetail.addBatch(); // Gom các câu lệnh lại để thực thi một lần cho nhanh
            }
            
            stDetail.executeBatch(); // Thực thi mảng insert details
            
            // Nếu mọi thứ suôn sẻ, commit để lưu vĩnh viễn vào Database
            connection.commit();
            return true;
            
        } catch (SQLException e) {
            System.out.println("Lỗi Transaction tạo hợp đồng: " + e.getMessage());
            try {
                // Nếu có bất kỳ lỗi nào xảy ra, rollback lại toàn bộ (không tạo hợp đồng nữa)
                if (connection != null) connection.rollback();
            } catch (SQLException ex) {
                System.out.println("Lỗi Rollback: " + ex.getMessage());
            }
        } finally {
            try {
                // Bật lại auto-commit cho các tác vụ khác
                if (connection != null) connection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    // 2. Hàm lấy lịch sử hợp đồng của một Khách hàng (dùng cho my_contracts.jsp)
    public List<Contract> getContractsByCustomerId(int customerId) {
        List<Contract> list = new ArrayList<>();
        String sql = "SELECT id, pickup_date, return_date, total_price, status, created_at "
                   + "FROM Contracts WHERE customer_id = ? ORDER BY created_at DESC";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, customerId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Contract c = new Contract();
                c.setId(rs.getInt("id"));
                c.setCustomerId(customerId);
                c.setPickupDate(rs.getString("pickup_date"));
                c.setReturnDate(rs.getString("return_date"));
                c.setTotalPrice(rs.getDouble("total_price"));
                c.setStatus(rs.getString("status"));
                c.setCreatedAt(rs.getString("created_at"));
                list.add(c);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi lấy lịch sử hợp đồng: " + e.getMessage());
        }
        return list;
    }

    // 3. Hàm lấy tất cả hợp đồng (dùng cho Nhân viên - Staff / manage_orders.jsp)
    public List<Contract> getAllContracts() {
        List<Contract> list = new ArrayList<>();
        String sql = "SELECT id, customer_id, pickup_date, return_date, total_price, status, created_at "
                   + "FROM Contracts ORDER BY created_at DESC";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Contract c = new Contract();
                c.setId(rs.getInt("id"));
                c.setCustomerId(rs.getInt("customer_id")); // Có thể JOIN với bảng Users để lấy tên khách hàng
                c.setPickupDate(rs.getString("pickup_date"));
                c.setReturnDate(rs.getString("return_date"));
                c.setTotalPrice(rs.getDouble("total_price"));
                c.setStatus(rs.getString("status"));
                c.setCreatedAt(rs.getString("created_at"));
                list.add(c);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi lấy danh sách tất cả hợp đồng: " + e.getMessage());
        }
        return list;
    }

    // 4. Hàm cập nhật trạng thái hợp đồng (Nhân viên thao tác: Duyệt, Cọc, Lấy xe, Hoàn thành)
    public boolean updateContractStatus(int contractId, String newStatus) {
        String sql = "UPDATE Contracts SET status = ? WHERE id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, newStatus);
            st.setInt(2, contractId);
            int affectedRows = st.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            System.out.println("Lỗi cập nhật trạng thái hợp đồng: " + e.getMessage());
        }
        return false;
    }
    
    // 5. Hàm cập nhật trạng thái chuyến đi của tài xế (Dùng trong DriverScheduleServlet)
    public boolean updateDriverTaskStatus(int detailId, String newStatus) {
        // Câu lệnh SQL cập nhật trạng thái. 
        // Lưu ý: Trong CSDL, bảng ContractDetails cần có thêm cột 'driver_status' (kiểu VARCHAR/NVARCHAR)
        String sql = "UPDATE ContractDetails SET driver_status = ? WHERE id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, newStatus);
            st.setInt(2, detailId);
            
            int affectedRows = st.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            System.out.println("Lỗi cập nhật trạng thái chuyến đi của tài xế: " + e.getMessage());
        }
        return false;
    }
}
