<%-- 
    Document   : manage_cars
    Created on : Mar 9, 2026, 2:41:37 AM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Quản Lý Thông Tin Xe - Manager</title>
    <link rel="stylesheet" href="../css/style.css">
    <style>
        .admin-section { background: white; padding: 2rem; border-radius: 8px; box-shadow: 0 4px 6px rgba(0,0,0,0.05); margin-top: 20px;}
        .header-actions { display: flex; justify-content: space-between; align-items: center; margin-bottom: 15px; }
        .admin-table { width: 100%; border-collapse: collapse; }
        .admin-table th, .admin-table td { padding: 10px; border: 1px solid var(--border-color); text-align: left; }
        .admin-table th { background-color: #343a40; color: white; }
        .btn-add { background-color: #28a745; color: white; padding: 8px 15px; text-decoration: none; border-radius: 4px; }
        .btn-edit { background-color: #ffc107; color: #333; padding: 5px 10px; text-decoration: none; border-radius: 4px; font-size: 0.85rem;}
        .btn-delete { background-color: #dc3545; color: white; padding: 5px 10px; text-decoration: none; border-radius: 4px; font-size: 0.85rem;}
    </style>
</head>
<body>
    <header>
        <a href="#" class="logo">Manager Portal</a>
        <nav>
            <ul>
                <li><a href="manage_cars.jsp" style="color: #ffd700;">Quản lý Xe</a></li>
                <li><a href="assign_driver.jsp">Phân công Tài xế</a></li>
                <li><a href="../logout">Đăng xuất</a></li>
            </ul>
        </nav>
    </header>

    <div class="container">
        <div class="admin-section">
            <div class="header-actions">
                <h2>Danh Sách Xe Trong Hệ Thống</h2>
                <a href="add_car.jsp" class="btn-add">+ Thêm Xe Mới</a>
            </div>
            
            <div style="overflow-x: auto;">
                <table class="admin-table">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Tên Xe</th>
                            <th>Biển Số</th>
                            <th>Loại Xe</th>
                            <th>Giá Thuê/Ngày</th>
                            <th>Trạng Thái</th>
                            <th>Thao Tác</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>1</td>
                            <td>Toyota Vios 2022</td>
                            <td>30A-111.22</td>
                            <td>4 chỗ</td>
                            <td>600,000đ</td>
                            <td><span style="color: green;">Sẵn sàng</span></td>
                            <td>
                                <a href="../editCar?id=1" class="btn-edit">Sửa</a>
                                <a href="../deleteCar?id=1" class="btn-delete" onclick="return confirm('Bạn có chắc muốn xóa xe này?');">Xóa</a>
                            </td>
                        </tr>
                        <tr>
                            <td>2</td>
                            <td>Ford Everest</td>
                            <td>51F-999.88</td>
                            <td>7 chỗ</td>
                            <td>1,200,000đ</td>
                            <td><span style="color: red;">Đang bảo dưỡng</span></td>
                            <td>
                                <a href="../editCar?id=2" class="btn-edit">Sửa</a>
                                <a href="../deleteCar?id=2" class="btn-delete" onclick="return confirm('Bạn có chắc muốn xóa xe này?');">Xóa</a>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</body>
</html>