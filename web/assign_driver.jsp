<%-- 
    Document   : assign_driver
    Created on : Mar 9, 2026, 2:41:57 AM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Phân Công Tài Xế - Manager</title>
    <link rel="stylesheet" href="../css/style.css">
    <style>
        .admin-section { background: white; padding: 2rem; border-radius: 8px; box-shadow: 0 4px 6px rgba(0,0,0,0.05); margin-top: 20px;}
        .admin-table { width: 100%; border-collapse: collapse; margin-top: 15px; }
        .admin-table th, .admin-table td { padding: 10px; border: 1px solid var(--border-color); text-align: left; }
        .admin-table th { background-color: #343a40; color: white; }
        .assign-form { display: flex; gap: 10px; align-items: center; }
        .assign-form select { padding: 5px; border-radius: 4px; border: 1px solid #ccc; }
        .btn-assign { background-color: #007bff; color: white; padding: 6px 12px; border: none; border-radius: 4px; cursor: pointer; }
    </style>
</head>
<body>
    <header>
        <a href="#" class="logo">Manager Portal</a>
        <nav>
            <ul>
                <li><a href="manage_cars.jsp">Quản lý Xe</a></li>
                <li><a href="assign_driver.jsp" style="color: #ffd700;">Phân công Tài xế</a></li>
                <li><a href="../logout">Đăng xuất</a></li>
            </ul>
        </nav>
    </header>

    <div class="container">
        <div class="admin-section">
            <h2>Yêu Cầu Cần Phân Công Tài Xế</h2>
            <p>Danh sách các xe trong hợp đồng khách yêu cầu có tài xế.</p>
            
            <div style="overflow-x: auto;">
                <table class="admin-table">
                    <thead>
                        <tr>
                            <th>Mã HĐ</th>
                            <th>Thông Tin Xe</th>
                            <th>Thời Gian Lịch Trình</th>
                            <th>Tài Xế Hiện Tại</th>
                            <th>Phân Công Mới</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>#HD1005</td>
                            <td>Toyota Innova (30A-555.66)</td>
                            <td>28/10/2023 - 30/10/2023</td>
                            <td><span style="color: #dc3545;">Chưa có</span></td>
                            <td>
                                <form action="../assignDriver" method="POST" class="assign-form">
                                    <input type="hidden" name="contractDetailId" value="501">
                                    <select name="driverId" required>
                                        <option value="">-- Chọn tài xế rảnh --</option>
                                        <option value="101">Nguyễn Văn Lái (Sẵn sàng)</option>
                                        <option value="102">Trần Bác Tài (Sẵn sàng)</option>
                                    </select>
                                    <button type="submit" class="btn-assign">Gán Tài Xế</button>
                                </form>
                            </td>
                        </tr>
                        <tr>
                            <td>#HD0990</td>
                            <td>Ford Transit 16 chỗ (29B-123.45)</td>
                            <td>01/11/2023 - 05/11/2023</td>
                            <td>Nguyễn Văn Lái</td>
                            <td>
                                <form action="../assignDriver" method="POST" class="assign-form">
                                    <input type="hidden" name="contractDetailId" value="480">
                                    <select name="driverId">
                                        <option value="101" selected>Nguyễn Văn Lái</option>
                                        <option value="103">Lê Lái Xe</option>
                                    </select>
                                    <button type="submit" class="btn-assign">Đổi Tài Xế</button>
                                </form>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</body>
</html>