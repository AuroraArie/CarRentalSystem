<%-- 
    Document   : schedule
    Created on : Mar 9, 2026, 2:42:32 AM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Lịch Lái Xe - Driver</title>
    <link rel="stylesheet" href="../css/style.css">
    <style>
        .driver-section { background: white; padding: 2rem; border-radius: 8px; box-shadow: 0 4px 6px rgba(0,0,0,0.05); margin-top: 20px;}
        .schedule-table { width: 100%; border-collapse: collapse; margin-top: 15px; }
        .schedule-table th, .schedule-table td { padding: 12px; border: 1px solid var(--border-color); text-align: left; }
        .schedule-table th { background-color: #17a2b8; color: white; }
        .btn-accept { background-color: #ffc107; color: #333; padding: 6px 12px; border: none; border-radius: 4px; cursor: pointer; font-weight: bold;}
        .btn-complete { background-color: #28a745; color: white; padding: 6px 12px; border: none; border-radius: 4px; cursor: pointer; font-weight: bold;}
        .status-text { font-weight: bold; }
        .status-assigned { color: #dc3545; }
        .status-ongoing { color: #007bff; }
        .status-done { color: #28a745; }
    </style>
</head>
<body>
    <header>
        <a href="#" class="logo">Driver Portal</a>
        <nav>
            <ul>
                <li><a href="schedule.jsp" style="color: #ffd700;">Lịch Trình Của Tôi</a></li>
                <li><a href="../logout">Đăng xuất</a></li>
            </ul>
        </nav>
    </header>

    <div class="container">
        <div class="driver-section">
            <h2>Lịch Trình Công Việc</h2>
            <p>Xin chào, <strong>Nguyễn Văn Lái</strong>. Dưới đây là danh sách các chuyến xe được phân công cho bạn.</p>
            
            <div style="overflow-x: auto;">
                <table class="schedule-table">
                    <thead>
                        <tr>
                            <th>Mã HĐ</th>
                            <th>Khách Hàng</th>
                            <th>Thời Gian Phục Vụ</th>
                            <th>Thông Tin Xe</th>
                            <th>Trạng Thái</th>
                            <th>Thao Tác</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>#HD1005</td>
                            <td>Trần Thị B<br><small>0987654321</small></td>
                            <td>28/10/2023 08:00<br>Đến: 30/10/2023 18:00</td>
                            <td>Toyota Innova (30A-555.66)</td>
                            <td><span class="status-text status-assigned">Đã phân công</span></td>
                            <td>
                                <form action="../updateDriverStatus" method="POST">
                                    <input type="hidden" name="detailId" value="501">
                                    <input type="hidden" name="status" value="ACCEPTED">
                                    <button type="submit" class="btn-accept">Xác nhận nhận xe</button>
                                </form>
                            </td>
                        </tr>
                        
                        <tr>
                            <td>#HD0990</td>
                            <td>Công ty XYZ<br><small>0911222333</small></td>
                            <td>01/11/2023 07:00<br>Đến: 05/11/2023 20:00</td>
                            <td>Ford Transit 16 chỗ (29B-123.45)</td>
                            <td><span class="status-text status-ongoing">Đang phục vụ</span></td>
                            <td>
                                <form action="../updateDriverStatus" method="POST" onsubmit="return confirm('Bạn xác nhận chuyến đi đã hoàn tất an toàn?');">
                                    <input type="hidden" name="detailId" value="480">
                                    <input type="hidden" name="status" value="COMPLETED">
                                    <button type="submit" class="btn-complete">Báo cáo Hoàn thành</button>
                                </form>
                            </td>
                        </tr>

                        <tr style="background-color: #f8f9fa;">
                            <td>#HD0850</td>
                            <td>Lê Văn C<br><small>0909090909</small></td>
                            <td>15/09/2023 06:00<br>Đến: 16/09/2023 18:00</td>
                            <td>Toyota Innova (30A-555.66)</td>
                            <td><span class="status-text status-done">Đã hoàn thành</span></td>
                            <td>Không có</td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</body>
</html>