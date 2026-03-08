<%-- 
    Document   : login
    Created on : Mar 9, 2026, 2:38:26 AM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Đăng Nhập - CarRental</title>
    <link rel="stylesheet" href="css/style.css">
    <style>
        .auth-container { max-width: 400px; margin: 4rem auto; background: white; padding: 2rem; border-radius: 8px; box-shadow: 0 4px 6px rgba(0,0,0,0.1); }
        .auth-container h2 { text-align: center; margin-bottom: 1.5rem; color: var(--primary-color); }
        .auth-container .form-group { margin-bottom: 1rem; }
        .auth-container input { width: 100%; box-sizing: border-box; }
        .error-msg { color: #e53935; font-size: 0.9rem; margin-bottom: 1rem; text-align: center; }
        .auth-links { text-align: center; margin-top: 1rem; font-size: 0.9rem; }
        .auth-links a { color: var(--primary-color); text-decoration: none; }
    </style>
</head>
<body>
    <header>
        <a href="index.jsp" class="logo">CarRental</a>
        <nav>
            <ul>
                <li><a href="index.jsp">Trang chủ</a></li>
                <li><a href="register.jsp">Đăng ký</a></li>
            </ul>
        </nav>
    </header>

    <div class="container">
        <div class="auth-container">
            <h2>Đăng Nhập</h2>
            
            <c:if test="${not empty requestScope.error}">
                <div class="error-msg">${requestScope.error}</div>
            </c:if>

            <form action="login" method="POST">
                <div class="form-group">
                    <label for="username">Tên đăng nhập</label>
                    <input type="text" id="username" name="username" required placeholder="Nhập username...">
                </div>
                <div class="form-group">
                    <label for="password">Mật khẩu</label>
                    <input type="password" id="password" name="password" required placeholder="Nhập mật khẩu...">
                </div>
                <button type="submit" class="btn btn-full">Đăng Nhập</button>
            </form>
            
            <div class="auth-links">
                Chưa có tài khoản? <a href="register.jsp">Đăng ký ngay</a>
            </div>
        </div>
    </div>
</body>
</html>