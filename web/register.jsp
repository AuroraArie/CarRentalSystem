<%-- 
    Document   : register
    Created on : Mar 8, 2026, 8:16:03 PM
    Author     : ADMIN
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>
        <form action="register" method="post">

            Username
            <input type="text" name="username">

            Password
            <input type="password" name="password">

            <button type="submit">Register</button>

        </form>
    </body>
</html>
