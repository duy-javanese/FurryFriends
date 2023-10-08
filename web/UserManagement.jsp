<%-- 
    Document   : UserManagement
    Created on : Oct 7, 2023, 3:06:18 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage members</title>
    </head>
    <body>
        <h1>User Management</h1>
        <form action="MainController">
            Search User <input type="text" name="txtSearchValue" 
                                value="${param.txtSearchValue}" />
            <input type="submit" value="Search User" name="action" />
        </form> <br>
        <c:set var="searchValue" value="${param.txtSearchValue}"/>
    <c:if test="${not empty searchValue}">
        <c:set var="result" value ="${requestScope.SEARCH_RESULT}"/>
        <c:if test="${not empty result}">
            <table border="1">
                <thead>
                    <tr>
                        <th>Username</th>
                        <th>Email</th>
                        <th>Phone number</th>
                        <th>Address</th>
                        <th>Point</th>
                        <th>Status</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach items="${result}" var="dto" varStatus="counter">
                <form action="MainController" method="POST">
                    <tr>
                                    <td>
                                        ${dto.username}
                                    </td>
                                    <td>
                                        ${dto.email}
                                    </td>
                                    <td>
                                        ${dto.phone}
                                    </td>
                                    <td>
                                        ${dto.address}
                                    </td>
                                    <td>
                                        ${dto.point}
                                    </td>
                                    <td>
                                        <select name="txtStatus">
                                            <option ${(dto.status) ?  "selected": ""} value="true">Active</option>
                                            <option ${(not dto.status) ?  "selected": ""} value="false">BAN</option>
                                        </select>
                                    </td>
                                    <td>
                                        <input type="hidden" name="txtID"
                                        value="${dto.userId}" />
                                        <input type="hidden"  name="lastSearchValue" 
                                        value="${searchValue}"/>
                                        <input type="submit" value="Update user status" name="action" />
                                    </td>
                                </tr>
                </form>
                </c:forEach>
                </tbody>
            </table>
        </c:if>
        <c:if test="${empty result}">

        </c:if>
    </c:if>
    </body>
</html>
