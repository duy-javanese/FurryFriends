<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="DAO.InformationDAO" %>
<%@ page import="Model.Information" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Config Page</title>
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
            input[type="text"] {
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
        </style>
    </head>
    <body>
        <%
        try {
            Information info = InformationDAO.getInfor();
        %>
        <h1>Admin Management</h1>
        <div id="menu">
            <ul>
                <li><a href="statisticsPage.jsp">Thống kê</a></li>
                <li><a href="configPage.jsp">Cấu hình hệ thống</a></li>
                <li><a href="addStaff.jsp">Thêm mới quản trị viên</a></li>
                <li><a href="staffManagement.jsp">Quản lí quản trị viên</a></li>
            </ul>
        </div>
        <div>
            <form action="MainController" method="post">
                <label for="about">About Us:</label>
                <textarea id="about" name="about" rows="4" required></textarea>
                <button type="submit" name="action" value="UpdateAboutUs">Cập nhật</button><br><br>
                <label>Đang hiển thị:</label>
                <%
                    out.print(info.getAboutUs());
                %>
            </form>

            <form action="MainController" method="post">
                <label for="phoneNumber">Contact:</label>
                ${requestScope.ERROR}
                <input type="text" id="phoneNumber" name="phone" required="">
                <button type="submit" name="action" value="UpdateContact">Cập nhật</button><br><br>
                <label>Đang hiển thị:</label>
                <%
                    out.print(info.getPhone());
                %>
            </form>
        </div>
        <%
    } catch (Exception e) {
        e.printStackTrace();
    }
        %>
    </body>
</html>
