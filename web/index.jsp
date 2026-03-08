<%-- 
    Document   : index
    Created on : Mar 9, 2026, 2:36:26 AM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Hệ Thống Cho Thuê Xe Ô Tô</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <header>
        <a href="index.jsp" class="logo">CarRental</a>
        <nav>
            <ul>
                <li><a href="index.jsp">Trang chủ</a></li>
                <li><a href="customer/my_contracts.jsp">Hợp đồng của tôi</a></li>
                <li><a href="login.jsp">Đăng nhập</a></li>
                <li><a href="register.jsp">Đăng ký</a></li>
            </ul>
        </nav>
    </header>

    <div class="container">
        <section class="search-section">
            <h2>Tìm Kiếm Xe Cho Thuê</h2>
            <form action="search" method="GET" class="search-form">
                <div class="form-group">
                    <label for="carType">Loại xe</label>
                    <select name="carType" id="carType">
                        <option value="ALL">Tất cả</option>
                        <option value="4">4 chỗ</option>
                        <option value="5">5 chỗ</option>
                        <option value="7">7 chỗ</option>
                    </select>
                </div>
                
                <div class="form-group">
                    <label for="pickupDate">Ngày giờ dự kiến thuê</label>
                    <input type="datetime-local" name="pickupDate" id="pickupDate" required>
                </div>
                
                <div class="form-group">
                    <label for="returnDate">Ngày giờ dự kiến trả</label>
                    <input type="datetime-local" name="returnDate" id="returnDate" required>
                </div>
                
                <div class="form-group">
                    <button type="submit" class="btn">Tìm Kiếm Ngay</button>
                </div>
            </form>
        </section>
        
        </div>
</body>
</html>
