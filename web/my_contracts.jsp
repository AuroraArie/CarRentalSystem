<%-- 
    Document   : my_contracts
    Created on : Mar 9, 2026, 2:40:45 AM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Lịch Sử Hợp Đồng - CarRental</title>
    <link rel="stylesheet" href="../css/style.css">
    <style>
        .contracts-section { background: white; padding: 2rem; border-radius: 8px; box-shadow: 0 4px 6px rgba(0,0,0,0.05); }
        .contracts-section h2 { margin-bottom: 1.5rem; color: var(--primary-color); }
        
        .table-responsive { overflow-x: auto; }
        .data-table { width: 100%; border-collapse: collapse; margin-bottom: 1rem; }
        .data-table th, .data-table td { padding: 12px 15px; text-align: left; border-bottom: 1px solid var(--border-color); }
        .data-table th { background-color: var(--secondary-color); font-weight: bold; }
        .data-table tr:hover { background-color: #f1f5f9; }

        /* CSS cho các trạng thái hợp đồng */
        .status-badge { padding: 5px 10px; border-radius: 20px; font-size: 0.85rem; font-weight: bold; color: white; display: inline-block; text-align: center; }
        .status-pending { background-color: #ffc107; color: #333; } /* Chờ duyệt / Chờ đặt cọc */
        .status-deposited { background-color: #17a2b8; } /* Đã đặt cọc / Chờ lấy xe */
        .status-active { background-color: #007bff; }    /* Đang mượn xe */
        .status-completed { background-color: #28a745; } /* Đã hoàn thành */
        .status-rejected { background-color: #dc3545; }  /* Bị từ chối / Đã hủy */

        .btn-sm { padding: 6px 12px; font-size: 0.85rem; background-color: #6c757d; }
        .btn-sm:hover { background-color: #5a6268; }
    </style>
</head>
<body>
    <header>
        <a href="../index.jsp" class="logo">CarRental</a>
        <nav>
            <ul>
                <li><a href="../index.jsp">Trang chủ</a></li>
                <li><a href="cart.jsp">Giỏ hàng</a></li>
                <li><a href="my_contracts.jsp" style="color: #ffd700;">Hợp đồng của tôi</a></li>
                <li><a href="../logout">Đăng xuất (${sessionScope.user.username})</a></li>
            </ul>
        </nav>
    </header>

    <div class="container">
        <div class="contracts-section">
            <h2>Danh Sách Hợp Đồng Của Bạn</h2>
            
            <c:choose>
                <c:when test="${empty requestScope.contractList}">
                    <p>Bạn chưa có hợp đồng thuê xe nào. <a href="../index.jsp" style="color: var(--primary-color); font-weight: bold;">Đặt xe ngay!</a></p>
                </c:when>
                <c:otherwise>
                    <div class="table-responsive">
                        <table class="data-table">
                            <thead>
                                <tr>
                                    <th>Mã Hợp Đồng</th>
                                    <th>Ngày Lấy Xe</th>
                                    <th>Ngày Trả Xe</th>
                                    <th>Số Lượng Xe</th>
                                    <th>Tổng Tiền</th>
                                    <th>Trạng Thái</th>
                                    <th>Thao Tác</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td><strong>#HD1001</strong></td>
                                    <td>20/10/2023 08:00</td>
                                    <td>22/10/2023 18:00</td>
                                    <td>2 xe</td>
                                    <td>4,000,000 VNĐ</td>
                                    <td><span class="status-badge status-pending">Chờ đặt cọc</span></td>
                                    <td>
                                        <a href="../contractDetail?id=1001" class="btn btn-sm">Xem chi tiết</a>
                                    </td>
                                </tr>

                                <tr>
                                    <td><strong>#HD0985</strong></td>
                                    <td>25/11/2023 07:00</td>
                                    <td>26/11/2023 20:00</td>
                                    <td>1 xe</td>
                                    <td>1,200,000 VNĐ</td>
                                    <td><span class="status-badge status-deposited">Đã đặt cọc</span></td>
                                    <td>
                                        <a href="../contractDetail?id=0985" class="btn btn-sm">Xem chi tiết</a>
                                    </td>
                                </tr>

                                <tr>
                                    <td><strong>#HD0512</strong></td>
                                    <td>01/09/2023 09:00</td>
                                    <td>05/09/2023 17:00</td>
                                    <td>3 xe</td>
                                    <td>15,000,000 VNĐ</td>
                                    <td><span class="status-badge status-completed">Đã thanh toán</span></td>
                                    <td>
                                        <a href="../contractDetail?id=0512" class="btn btn-sm">Xem chi tiết</a>
                                    </td>
                                </tr>
                                
                                <tr>
                                    <td><strong>#HD0888</strong></td>
                                    <td>15/09/2023 08:00</td>
                                    <td>16/09/2023 18:00</td>
                                    <td>1 xe</td>
                                    <td>800,000 VNĐ</td>
                                    <td><span class="status-badge status-rejected">Đã hủy/Từ chối</span></td>
                                    <td>
                                        <a href="../contractDetail?id=0888" class="btn btn-sm">Xem chi tiết</a>
                                    </td>
                                </tr>

                            </tbody>
                        </table>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</body>
</html>