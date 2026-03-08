<%-- 
    Document   : manage_orders
    Created on : Mar 9, 2026, 2:41:12 AM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Quản Lý Đơn Thuê Xe - Staff</title>
    <link rel="stylesheet" href="../css/style.css">
    <style>
        .admin-section { background: white; padding: 2rem; border-radius: 8px; box-shadow: 0 4px 6px rgba(0,0,0,0.05); margin-top: 20px;}
        .admin-table { width: 100%; border-collapse: collapse; margin-top: 15px; }
        .admin-table th, .admin-table td { padding: 10px; border: 1px solid var(--border-color); text-align: left; font-size: 0.95rem; }
        .admin-table th { background-color: var(--primary-color); color: white; }
        .action-form { display: inline-block; margin: 2px; }
        .btn-action { padding: 5px 10px; font-size: 0.8rem; border-radius: 4px; border: none; cursor: pointer; color: white; }
        .btn-approve { background-color: #28a745; }
        .btn-reject { background-color: #dc3545; }
        .btn-update { background-color: #17a2b8; }
    </style>
</head>
<body>
    <header>
        <a href="#" class="logo">Staff Portal</a>
        <nav>
            <ul>
                <li><a href="manage_orders.jsp" style="color: #ffd700;">Quản lý đơn hàng</a></li>
                <li><a href="../logout">Đăng xuất</a></li>
            </ul>
        </nav>
    </header>

    <div class="container">
        <div class="admin-section">
            <h2>Danh Sách Đơn Thuê Xe</h2>
            <div style="overflow-x: auto;">
                <table class="admin-table">
                    <thead>
                        <tr>
                            <th>Mã HĐ</th>
                            <th>Khách Hàng</th>
                            <th>Thời Gian Thuê</th>
                            <th>Tổng Tiền</th>
                            <th>Trạng Thái</th>
                            <th>Thao Tác Cập Nhật</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>#HD1001</td>
                            <td>Nguyễn Văn A<br><small>0901234567</small></td>
                            <td>20/10/2023 - 22/10/2023</td>
                            <td>4,000,000đ</td>
                            <td><strong>Chờ duyệt</strong></td>
                            <td>
                                <form action="../updateOrderStatus" method="POST" class="action-form">
                                    <input type="hidden" name="contractId" value="1001">
                                    <input type="hidden" name="action" value="APPROVE">
                                    <button type="submit" class="btn-action btn-approve">Chấp nhận</button>
                                </form>
                                <form action="../updateOrderStatus" method="POST" class="action-form">
                                    <input type="hidden" name="contractId" value="1001">
                                    <input type="hidden" name="action" value="REJECT">
                                    <button type="submit" class="btn-action btn-reject">Từ chối</button>
                                </form>
                            </td>
                        </tr>
                        
                        <tr>
                            <td>#HD1002</td>
                            <td>Trần Thị B<br><small>0987654321</small></td>
                            <td>25/10/2023 - 26/10/2023</td>
                            <td>1,200,000đ</td>
                            <td><strong>Chờ đặt cọc</strong></td>
                            <td>
                                <form action="../updateOrderStatus" method="POST" class="action-form">
                                    <input type="hidden" name="contractId" value="1002">
                                    <input type="hidden" name="action" value="DEPOSIT_RECEIVED">
                                    <button type="submit" class="btn-action btn-update">Đã nhận cọc</button>
                                </form>
                            </td>
                        </tr>

                        <tr>
                            <td>#HD0985</td>
                            <td>Lê Văn C<br><small>0911222333</small></td>
                            <td>15/10/2023 - 18/10/2023</td>
                            <td>3,500,000đ</td>
                            <td><strong>Đã đặt cọc</strong></td>
                            <td>
                                <form action="../updateOrderStatus" method="POST" class="action-form">
                                    <input type="hidden" name="contractId" value="0985">
                                    <input type="hidden" name="action" value="CAR_PICKED_UP">
                                    <button type="submit" class="btn-action btn-update">Khách đã lấy xe</button>
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
