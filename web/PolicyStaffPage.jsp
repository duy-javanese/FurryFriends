<%-- 
    Document   : EditPolicy
    Created on : Nov 3, 2023, 1:56:13 PM
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
        <form action="MainController">
            <button name="action" value="GetPolicy">Get Policy</button>
            
        </form>
        
        <c:set var="result" value ="${requestScope.POLICY_LIST}"/>
        <c:forEach items="${result}" var="policy" varStatus="counter">
            <h1>${policy.policyTitle}</h1>
            <p>${policy.policyDetail}</p>
            <form action="MainController">
                <input type="hidden" name="policyId" value="${policy.policyId}">
            <button name="action" value="EditPolicy">Edit Policy</button>
            </form>
        </c:forEach>
            
    </body>
</html>
