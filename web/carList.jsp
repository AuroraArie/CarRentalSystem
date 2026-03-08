<%-- 
    Document   : carList
    Created on : Mar 8, 2026, 7:28:27 PM
    Author     : ADMIN
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>List Car</title>
    </head>
    <body>
        <table border="1">

            <tr>
                <th>ID</th>
                <th>TypeID</th>
                <th>Price</th>
                <th>Status</th>
                <th>Action</th>
            </tr>

            <c:forEach items="${carList}" var="c">

                <tr>
                    <td>${c.carID}</td>
                    <td>${c.carTypeID}</td>
                    <td>${c.pricePerDay}</td>
                    <td>${c.status}</td>

                    <td>
                        <a href="rent?carID=${c.carID}">Rent</a>
                    </td>

                </tr>

            </c:forEach>

        </table>
    </body>
</html>
