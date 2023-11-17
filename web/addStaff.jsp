<%-- 
    Document   : addStaff
    Created on : Oct 28, 2023, 1:34:39 PM
    Author     : DUY
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Staff Page</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f9f9f9;
                margin: 0;
                padding: 0;
            }

            h1 {
                text-align: center;
                background-color: #ff9933;
                color: #fff;
                padding: 10px;
                margin: 0;
            }

            #menu {
                background-color: #ff9933;
                color: #fff;
                padding: 10px;
            }

            ul {
                list-style: none;
                padding: 0;
                text-align: center;
            }

            li {
                display: inline;
                margin-right: 20px;
            }

            a {
                text-decoration: none;
                color: #fff;
            }

            a:hover {
                text-decoration: underline;
            }

            form {
                background-color: #fff;
                padding: 20px;
                margin: 20px;
                border: 1px solid #ccc;
                border-radius: 5px;
            }

            label {
                display: block;
                margin-bottom: 10px;
                font-weight: bold;
            }

            textarea,
            input[type="text"],
            input[type="password"],
            input[type="email"] {
                width: 100%;
                padding: 10px;
                margin-bottom: 20px;
                border: 1px solid #ccc;
                border-radius: 5px;
            }

            button {
                background-color: #ff9933;
                color: #fff;
                padding: 10px 20px;
                border: none;
                border-radius: 5px;
                cursor: pointer;
            }

            button:hover {
                background-color: #ff6600;
            }

            .error-text {
                color: red;
            }

            .success-text {
                color: green;
            }

        </style>
    </head>
    <c:if test="${sessionScope.account == null}">
            <c:redirect url="loginPage.jsp"></c:redirect>
    </c:if>

    <c:if test="${not empty sessionScope.account and sessionScope.account.role.roleId ne 1}">
    <!-- Người dùng đã đăng nhập nhưng không phải là ADMIN -->
        <h1>Bạn không có quyền truy cập trang web này.</h1>
        <c:redirect url="HomePage.jsp"></c:redirect>
    </c:if>
    <body>
        <h1>Admin Management</h1>
        <div id="menu">
            <ul>
                <li><a href="statisticsPage.jsp">Thống kê</a></li>
                <li><a href="configPage.jsp">Cấu hình hệ thống</a></li>
                <li><a href="addStaff.jsp">Thêm mới quản trị viên</a></li>
                <li><a href="staffManagement.jsp">Quản lí quản trị viên</a></li>
                <li><a href="memberManagement.jsp">Quản lí người dùng</a></li>
            </ul>
        </div>
        <div>
            <form action="MainController" method="post">
                <label for="username">Tên đăng nhập:</label>
                <span class="error-text">${requestScope.USERNAME_ERROR}</span>
                <input type="text" id="username" name="username" required>

                <label for="password">Mật khẩu:</label>
                <span class="error-text">${requestScope.PASSWORD_ERROR}</span>
                <input type="password" id="password" name="password" required>

                <label for="confirmPassword">Nhập lại mật khẩu:</label>
                <span class="error-text">${requestScope.CONFIRM_PASSWORD_ERROR}</span>
                <input type="password" id="confirmPassword" name="confirm_password" required>

                <label for="email">Email:</label>
                <span class="error-text">${requestScope.EMAIL_ERROR}</span>
                <input type="email" id="email" name="email" required>

                <label for="phone">Số điện thoại:</label>
                <span class="error-text">${requestScope.PHONE_ERROR}</span>
                <input type="text" id="phone" name="phone" required>

                <label for="address">Địa chỉ:</label>
                <input type="text" id="address" name="address" required>

                <button type="submit" name="action" value="AddStaff">Thêm quản trị viên</button>
                <span class="success-text">${requestScope.SUCCESS}</span>
            </form>
        </div>
    </body>
</html>

