<%-- 
    Document   : EditPolicy
    Created on : Nov 3, 2023, 3:13:12 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <c:set var="policy" value ="${requestScope.POLICY}"/>
        
        <form action="MainController">
            <h1><input type="text" name="policyTitle" value="${policy.policyTitle}"></h1>
            <p><input style="width: 800px; height: 400px" type="text" name="policyDetail" value="${policy.policyDetail}"</p><br>
            <input type="hidden" name="policyId" value="${policy.policyId}">
            <button name="action" value="UpdatePolicy">Update</button>
        </form>
    </body>
</html>
