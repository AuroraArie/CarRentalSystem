<%-- 
    Document   : cart
    Created on : Mar 9, 2026, 2:39:52 AM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Hợp Đồng Của Bạn - CarRental</title>
    <link rel="stylesheet" href="../css/style.css">
    <style>
        .cart-section { background: white; padding: 2rem; border-radius: 8px; box-shadow: 0 4px 6px rgba(0,0,0,0.05); }
        .contract-info { margin-bottom: 2rem; padding-bottom: 1rem; border-bottom: 1px solid var(--border-color); }
        .cart-table { width: 100%; border-collapse: collapse; margin-bottom: 2rem; }
        .cart-table th, .cart-table td { padding: 12px; text-align: left; border-bottom: 1px solid var(--border-color); }
        .cart-table th { background-color: var(--secondary-color); color: var(--primary-color); }
        .cart-summary { text-align: right; font-size: 1.2rem; margin-bottom: 2rem; }
        .total-price { color: #e53935; font-weight: bold; font-size: 1.5rem; }
        .actions { display: flex; justify-content: space-between; align-items: center; }
        .btn-danger { background-color: #dc3545; }
        .btn-danger:hover { background-color: #c82333; }
    </style>
</head>
<body>
    <header>
        <a href="../index.jsp" class="logo">CarRental</a>
        <nav>
            <ul>
                <li><a href="../index.jsp">Tiếp tục tìm xe</a></li>
                <li><a href="my_contracts.jsp">Lịch sử thuê</a></li>
                <li><a href="../logout">Đăng xuất (${sessionScope.user.fullname})</a></li>
            </ul>
        </nav>
    </header>

    <div class="container">
        <div class="cart-section">
            <h2>Chi Tiết Hợp Đồng Dự Kiến</h2>
            
            <c:choose>
                <c:when test="${empty sessionScope.cart.items}">
                    <p style="margin-top: 1rem;">Bạn chưa chọn xe nào. <a href="../index.jsp" style="color: var(--primary-color);">Quay lại trang chủ</a> để tìm xe.</p>
                </c:when>
                <c:otherwise>
                    <div class="contract-info">
                        <p><strong>Ngày dự kiến lấy xe:</strong> ${sessionScope.cart.pickupDate}</p>
                        <p><strong>Ngày dự kiến trả xe:</strong> ${sessionScope.cart.returnDate}</p>
                        <p><i>*Lưu ý: Nếu muốn thuê xe thời điểm khác, vui lòng tạo một hợp đồng mới sau khi hoàn tất hợp đồng này.</i></p>
                    </div>

                    <table class="cart-table">
                        <thead>
                            <tr>
                                <th>Xe</th>
                                <th>Loại xe</th>
                                <th>Biển số</th>
                                <th>Tùy chọn Tài xế</th>
                                <th>Đơn giá/Ngày</th>
                                <th>Thao tác</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${sessionScope.cart.items}" var="item">
                                <tr>
                                    <td><strong>${item.car.name}</strong></td>
                                    <td>${item.car.carType.seats} chỗ</td>
                                    <td>${item.car.licensePlate}</td>
                                    <td>${item.needDriver ? 'Có tài xế' : 'Tự lái'}</td>
                                    <td>${item.car.pricePerDay} VNĐ</td>
                                    <td>
                                        <form action="../removeFromCart" method="POST" style="display:inline;">
                                            <input type="hidden" name="carId" value="${item.car.id}">
                                            <button type="submit" class="btn btn-danger" style="padding: 5px 10px; font-size: 0.8rem;">Xóa</button>
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>

                    <div class="cart-summary">
                        Tổng tiền dự kiến: <span class="total-price">${sessionScope.cart.totalPrice} VNĐ</span>
                    </div>

                    <div class="actions">
                        <a href="../index.jsp" class="btn" style="background-color: #6c757d;">Thêm xe khác (cùng thời gian)</a>
                        <form action="../createContract" method="POST">
                            <button type="submit" class="btn" style="background-color: #28a745; font-size: 1.1rem; padding: 12px 30px;">Tạo Hợp Đồng & Đặt Cọc</button>
                        </form>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</body>
</html>