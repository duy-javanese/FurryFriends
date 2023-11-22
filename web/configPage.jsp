<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="DAO.InformationDAO" %>
<%@ page import="Model.Information" %>
<%@ page import="DAO.CategoryDAO" %>
<%@ page import="Model.Category" %>
<%@ page import="java.util.ArrayList" %>
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

            table {
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

            table input[type="text"] {
                width: auto;
                padding: 5px;
                margin-bottom: 0;
                border: none;
                border-radius: 0;
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

            .logo-input {
                padding: 10px;
                border: 1px solid #ccc;
                border-radius: 5px;
                width: 100%;
                margin-bottom: 20px;
            }

            .error-text {
                color: red;
            }
        </style>
    </head>
    <body>
        <%
        try {
            Information info = InformationDAO.getInfor();
            CategoryDAO categoryDAO = new CategoryDAO();
            ArrayList<Category> categories = categoryDAO.GetAllCategories();
        %>
        <h1>Admin Management</h1>
        <div id="menu">
            <ul>
                <li><a href="statisticsPage.jsp">Thống kê</a></li>
                <li><a href="configPage.jsp">Cấu hình hệ thống</a></li>
                <li><a href="addStaff.jsp">Thêm mới quản trị viên</a></li>
                <li><a href="staffManagement.jsp">Quản lí quản trị viên</a></li>
                <li><a href="memberManagement.jsp">Quản lí người dùng</a></li>
                <li><a href="logout">Đăng xuất</a></li>
            </ul>
        </div>
        <div>
            <form action="UpdateLogoController" method="post" enctype="multipart/form-data">
                <label for="logo">Logo:</label>
                <span class="error-text">${requestScope.LOGO_ERROR}</span>
                <input id="logo" class="logo-input" type="file" name="logo" accept="image/*" required=""/>
                <button type="submit" name="action" value="UpdateLogo">Cập nhật</button><br><br>
                <label>Đang hiển thị:</label>
                <img src="<%=info.getLogoPath()%>" alt="logo" width="250" height="250">
            </form>

            <form action="MainController" method="post">
                <label for="notification">Thông báo:</label>
                <textarea id="notification" name="notification" rows="2" required="" maxlength="250"></textarea>
                <button type="submit" name="action" value="SendNotification">Cập nhật</button><br><br>
                <label>Đang hiển thị:</label>
                <%
                    out.print(info.getNotification());
                %>
            </form>

            <form action="MainController" method="post">
                <label for="about">Về chúng tôi:</label>
                <textarea id="about" name="about" rows="2" required="" maxlength="250"></textarea>
                <button type="submit" name="action" value="UpdateAboutUs">Cập nhật</button><br><br>
                <label>Đang hiển thị:</label>
                <%
                    out.print(info.getAboutUs());
                %>
            </form>

            <form action="MainController" method="post">
                <label for="phoneNumber">Liên hệ:</label>
                <span class="error-text">${requestScope.PHONE_ERROR}</span>
                <input type="text" id="phoneNumber" name="phone" required="">
                <button type="submit" name="action" value="UpdateContact">Cập nhật</button><br><br>
                <label>Đang hiển thị:</label>
                <%
                    out.print(info.getContact());
                %>
            </form>

            <form action="MainController" method="post">
                <label for="category_name">Thêm mới danh mục:</label>
                <span class="error-text">${requestScope.CATEGORY_ERROR}</span>
                <input type="text" id="category_name" name="category_name" required="">
                <button type="submit" name="action" value="AddCategory">Thêm mới</button><br><br>
                <label>Danh mục hiện có:</label>
                <span class="error-text">${requestScope.DELETE_ERROR}</span>
            </form>

            <table border="1" class="category_table exclude-styles">
                <thead>
                    <tr>
                        <th>No</th>
                        <th>Danh mục</th>
                        <th>Cập nhật</th>
                        <th>Xoá</th>
                    </tr>
                </thead>
                <tbody>
                    <% 
                        for (int i = 0; i < categories.size(); i++) {
                            Category category = categories.get(i);
                    %>
                    <tr>
                        <td><%= i+1 %></td>
                <form action="MainController" method="POST">
                    <td>
                        <input type="text" name="category_name_update" value="<%= category.getCategoryName() %>" required="">
                    </td>
                    <td>
                        <input type="hidden" name="id" value="<%= category.getCategoryId() %>">
                        <button type="submit" name="action" value="UpdateCategory">Cập nhật</button>
                    </td>
                </form>
                <form action="MainController" method="POST">
                    <td>
                        <input type="hidden" name="id" value="<%= category.getCategoryId() %>">
                        <button type="submit" name="action" value="DeleteCategory">Xoá</button>
                    </td>
                </form>
                </tr>
                <% } %>
                </tbody>
            </table>
        </div>
        <%
    } catch (Exception e) {
        e.printStackTrace();
    }
        %>
    </body>
</html>
