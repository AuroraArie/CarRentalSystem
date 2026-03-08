<%-- 
    Document   : orderHistory
    Created on : Mar 8, 2026, 8:17:15 PM
    Author     : ADMIN
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h2>Your Orders</h2>

    <c:forEach items="${orders}" var="o">

        <p>Order ID: ${o}</p>

    </c:forEach>
</body>
</html>
