<%-- 
    Document   : search_car
    Created on : Mar 9, 2026, 2:37:03 AM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Danh Sách Xe Khả Dụng</title>
    <link rel="stylesheet" href="../css/style.css">
</head>
<body>
    <header>
        <a href="../index.jsp" class="logo">CarRental</a>
        <nav>
            <ul>
                <li><a href="../index.jsp">Trang chủ</a></li>
                <li><a href="cart.jsp">Giỏ hàng/Hợp đồng (<span id="cart-count">0</span>)</a></li>
                <li><a href="../login.jsp">Đăng xuất</a></li>
            </ul>
        </nav>
    </header>

    <div class="container">
        <div class="search-section" style="padding: 1rem; margin-bottom: 2rem;">
            <h3>Kết quả tìm kiếm</h3>
            <p>Từ: <strong>20/10/2023 08:00</strong> - Đến: <strong>22/10/2023 18:00</strong> | Loại xe: <strong>7 chỗ</strong></p>
        </div>

        <div class="car-grid">
            <div class="car-card">
                <div class="car-image">Ảnh Xe Toyota Innova</div>
                <div class="car-details">
                    <h3>Toyota Innova 2022</h3>
                    <ul class="car-info-list">
                        <li><strong>Loại xe:</strong> 7 chỗ</li>
                        <li><strong>Biển số:</strong> 30A-123.45</li>
                        <li><strong>Mô tả:</strong> Rộng rãi, tiết kiệm nhiên liệu.</li>
                        <li><strong>Tùy chọn:</strong> <label><input type="checkbox" name="needDriver"> Cần tài xế</label></li>
                    </ul>
                    <div class="car-price">800,000 VNĐ / ngày</div>
                    <form action="addToCart" method="POST">
                        <input type="hidden" name="carId" value="1">
                        <button type="submit" class="btn btn-full">Thêm vào Hợp đồng</button>
                    </form>
                </div>
            </div>

            <div class="car-card">
                <div class="car-image">Ảnh Xe Ford Everest</div>
                <div class="car-details">
                    <h3>Ford Everest Titanium</h3>
                    <ul class="car-info-list">
                        <li><strong>Loại xe:</strong> 7 chỗ</li>
                        <li><strong>Biển số:</strong> 51F-999.99</li>
                        <li><strong>Mô tả:</strong> Động cơ mạnh mẽ, nội thất da cao cấp.</li>
                        <li><strong>Tùy chọn:</strong> <label><input type="checkbox" name="needDriver"> Cần tài xế</label></li>
                    </ul>
                    <div class="car-price">1,200,000 VNĐ / ngày</div>
                    <form action="addToCart" method="POST">
                        <input type="hidden" name="carId" value="2">
                        <button type="submit" class="btn btn-full">Thêm vào Hợp đồng</button>
                    </form>
                </div>
            </div>
            
            </div>
    </div>
</body>
</html>