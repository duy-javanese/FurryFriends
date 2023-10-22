<%-- 
    Document   : adminPage
    Created on : Oct 22, 2023, 3:45:15 PM
    Author     : DUY
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Page</title>
</head>
<body>
    <h1>Welcome to the Admin Page</h1>

    <!-- Form to add a new user -->
    <form action="MainController" method="POST">
        <label for="username">Username: </label>
        <input type="text" name="username" required><br>
        ${requestScope.USERNAME_ERROR}<br>

        <label for="password">Password: </label>
        <input type="password" name="password" required><br>
        
        <label for="confirm_password">Confirm password: </label>
        <input type="password" name="confirm_password" required><br>
        ${requestScope.PASSWORD_ERROR}<br>
        
        <label for="email">Email: </label>
        <input type="email" name="email" required><br>
        ${requestScope.EMAIL_ERROR}<br>
        
        <label for="phone">Phone: </label>
        <input type="tel" name="phone" required><br>
        ${requestScope.PHONE_ERROR}<br>
        
        <label for="address">Address: </label>
        <input type="text" name="address" required><br>

        <button type="submit" name="action" value="AddStaff">Thêm quản trị viên</button>
    </form>
</body>
</html>
