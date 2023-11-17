<%-- 
    Document   : statisticsPage
    Created on : Oct 28, 2023, 11:27:52 AM
    Author     : DUY
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="DAO.InformationDAO"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Statistics Page</title>
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

            #menu ul {
                list-style: none;
                padding: 0;
                text-align: center;
                margin-left: 0;
            }

            #menu li {
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

            #content {
                background-color: #fff;
                border: 1px solid #ccc;
                border-radius: 5px;
                padding: 20px;
            }

            #users {
                background-color: #fff;
                border: 1px solid #ccc;
                border-radius: 5px;
                padding: 20px;
                margin: 10px;
            }

            #staffs {
                background-color: #fff;
                border: 1px solid #ccc;
                border-radius: 5px;
                padding: 20px;
                margin: 10px;
            }

            #posts {
                background-color: #fff;
                border: 1px solid #ccc;
                border-radius: 5px;
                padding: 20px;
                margin: 10px;
            }

            #users h2 {
                background-color: #fff;
                color: #333;
                padding: 10px;
                font-family: Arial, sans-serif;
                border-bottom: 1px solid #ccc;
                margin: 0;
            }

            #staffs h2 {
                background-color: #fff;
                color: #333;
                padding: 10px;
                font-family: Arial, sans-serif;
                border-bottom: 1px solid #ccc;
                margin: 0;
            }

            #posts h2 {
                background-color: #fff;
                color: #333;
                padding: 10px;
                font-family: Arial, sans-serif;
                border-bottom: 1px solid #ccc;
                margin: 0;
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
        <div id="users">
            <h2>Thành viên</h2>
            <ul>
                Số lượng người dùng:
                <%
        try {
            InformationDAO dao = new InformationDAO();
            int userCount = dao.countUsers();
            out.println(userCount);
        } catch (Exception e) {
            e.printStackTrace();
            out.println("N/A");
        }
                %>
                <li style="margin-left: 30px; color: green">
                    Đang hoạt động:
                    <%
            try {
                InformationDAO dao = new InformationDAO();
                int userCount = dao.countActiveUsers();
                out.println(userCount);
            } catch (Exception e) {
                e.printStackTrace();
                out.println("N/A");
            }
                    %>
                </li>
                <li style="margin-left: 30px; color: red">
                    Đang bị cấm:
                    <%
            try {
                InformationDAO dao = new InformationDAO();
                int userCount = dao.countBannedUsers();
                out.println(userCount);
            } catch (Exception e) {
                e.printStackTrace();
                out.println("N/A");
            }
                    %>
                </li>
            </ul>
        </div>
        <div id="staffs">
            <h2>Quản trị viên</h2>
            <ul>

                Số lượng quản trị viên:
                <%
    try {
        InformationDAO dao = new InformationDAO();
        int staffCount = dao.countStaffs();
        out.println(staffCount);
    } catch (Exception e) {
        e.printStackTrace();
        out.println("N/A");
    }
                %>

                <li style="margin-left: 30px; color: green">
                    Đang hoạt động:
                    <%
            try {
                InformationDAO dao = new InformationDAO();
                int staffCount = dao.countActiveStaffs();
                out.println(staffCount);
            } catch (Exception e) {
                e.printStackTrace();
                out.println("N/A");
            }
                    %>
                </li>
                <li style="margin-left: 30px; color: red">
                    Dừng hoạt động:
                    <%
            try {
                InformationDAO dao = new InformationDAO();
                int staffCount = dao.countInactiveStaffs();
                out.println(staffCount);
            } catch (Exception e) {
                e.printStackTrace();
                out.println("N/A");
            }
                    %>
                </li>
            </ul>
        </div>
        <div id="posts">
            <h2>Bài đăng</h2>
            <ul>
                Số lượng bài viết:
                <%
                try {
                    InformationDAO dao = new InformationDAO();
                    int postCount = dao.countPosts();
                    out.println(postCount);
                } catch (Exception e) {
                    e.printStackTrace();
                    out.println("N/A");
                }
                %>
                <li style="margin-left: 30px">
                    Bài viết chia sẻ:
                    <%
            try {
                InformationDAO dao = new InformationDAO();
                int postCount = dao.countBlogs();
                out.println(postCount);
            } catch (Exception e) {
                e.printStackTrace();
                out.println("N/A");
            }
                    %>
                </li>
                <li style="margin-left: 30px">
                    Bài viết trao đổi:
                    <%
            try {
                InformationDAO dao = new InformationDAO();
                int postCount = dao.countExchangePosts();
                out.println(postCount);
            } catch (Exception e) {
                e.printStackTrace();
                out.println("N/A");
            }
                    %>
                </li>
            </ul> 
        </div>
    </body>
</html>
