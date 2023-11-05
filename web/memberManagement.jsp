<%-- 
    Document   : memberManagement
    Created on : Nov 5, 2023, 11:18:20 PM
    Author     : DUY
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Management Page</title>
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

            form {
                padding-top: 10px;
                padding-bottom: 10px;
                border-radius: 5px;
                text-align: center;
            }

            table {
                width: 100%;
                border-collapse: collapse;
                background-color: #fff;
            }

            table th, table td {
                padding: 10px;
                border: 1px solid #ccc;
            }

            table th {
                background-color: #ff9933;
                color: #fff;
                font-weight: bold;
            }

        </style>
    </head>
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
            <form action="MainController" method="POST">
                <button type="submit" name="action" value="AdminGetAllUser">Lấy danh sách người dùng</button>
            </form>
            <table border="1">
                <thead>
                    <tr>
                        <th>No</th>
                        <th>Tên đăng nhập</th>
                        <th>Email</th>
                        <th>Số điện thoại</th>
                        <th>Địa chỉ</th>
                        <th>Trạng thái</th>
                        <th>Cập nhật</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="acc" varStatus="counter" items="${requestScope.LIST_USER}">
                        <tr>
                            <td>${counter.count}</td>
                            <td><c:out value="${acc.getUsername()}"></c:out></td>
                            <td><c:out value="${acc.getEmail()}"></c:out></td>
                            <td><c:out value="${acc.getPhone()}"></c:out></td>
                            <td><c:out value="${acc.getAddress()}"></c:out></td>
                                <td>
                                <c:choose>
                                    <c:when test="${acc.isStatus() eq true}">
                                        <span style="color: green; font-weight: bold;">Đang hoạt động</span>
                                    </c:when>
                                    <c:otherwise>
                                        <span style="color: red; font-weight: bold;">Đang bị cấm</span>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                <form class="updateStatus" action="MainController" method="post">
                                    <input type="hidden" name="id" value="${acc.getUserId()}">
                                    <input type="hidden" name="status" value="<c:if test='${acc.isStatus()}'>false</c:if><c:if test='${!acc.isStatus()}'>true</c:if>">
                                        <input type="hidden" name="action" value="AdminUpdateUserStatus">
                                        <button type="button" class="confirmButton">Đổi trạng thái</button>
                                    </form>
                                    <script>
                                        var confirmButtons = document.querySelectorAll('.confirmButton');
                                        confirmButtons.forEach(function (button) {
                                            button.addEventListener('click', function () {
                                                var form = button.closest('.updateStatus');
                                                var confirmed = confirm('Bạn muốn thay đổi trạng thái của tài khoản này?');
                                                if (confirmed) {
                                                    form.submit();
                                                }
                                            });
                                        });
                                    </script>
                                </td>
                            </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>
