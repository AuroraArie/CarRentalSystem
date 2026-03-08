<%-- 
    Document   : rent
    Created on : Mar 8, 2026, 8:16:44 PM
    Author     : ADMIN
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Rent Page</title>
    </head>
    <body>
        <form action="rent" method="post">

            Car ID
            <input type="text" name="carID">

            Rent Date
            <input type="date" name="rentDate">

            Return Date
            <input type="date" name="returnDate">

            <button type="submit">Rent Car</button>

        </form>
    </body>
</html>
