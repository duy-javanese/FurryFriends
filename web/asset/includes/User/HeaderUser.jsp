<%-- 
    Document   : HeaderAdmin
    Created on : Oct 8, 2023, 1:14:31 PM
    Author     : dell
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="DAO.InformationDAO" %>
<%@ page import="Model.Information" %>
<!-- This container belong to HEADER ADMIN; DONT copy to JSP, JUST INCLUDE -->
<header style="background-color: white;" class="d-flex flex-wrap justify-content-center py-3 border-bottom">
    <%
        try {
            Information info = InformationDAO.getInfor();
    %>
    <a href="home" class="d-flex align-items-center ms-4 mb-3 mb-md-0 me-md-auto text-dark text-decoration-none">
        <%
    String logoPath = (info.getLogoPath() != null) ? info.getLogoPath() : "${pageContext.request.contextPath}/asset/img/furryfriends-1.png";
        %>
        <img style="width: 200px; height: 100px; object-fit: contain;" src="<%= logoPath %>" alt="logo">
    </a>
    <%
    } catch (Exception e) {
    e.printStackTrace();
    }
    %>
    <div class="d-flex align-items-center gap-2 me-3">
        <!--         <li class="nav-item"><a href="#san-pham" class="nav-link link-dark">Products</a></li> 
                <div class="nav-item"><a href="/cart.html" class="nav-link link-dark">
                        <i class="fa-solid fa-cart-shopping"></i>
                        Cart: 2 products</a></div>
        
                 If do not login, show this      
                <a href="/login.html" type="button" class="btn btn-outline-none me-2">
                    <i class="fa-solid fa-user-plus me-1"></i>
                    Log in</a>-->

        <!-- If login, show this      -->
        <div class="dropdown">
            <a href="#" class="d-flex align-items-center link-dark text-decoration-none dropdown-toggle"
               id="dropdownUser2" data-bs-toggle="dropdown" aria-expanded="false">
                <i class="fa-solid fa-user"></i>
            </a>
            <ul class="dropdown-menu text-small shadow" aria-labelledby="dropdownUser2">
                <li><a class="dropdown-item" href="home">Trang chủ</a></li>
                <li><a class="dropdown-item" href="logout">Đăng xuất</a></li>
            </ul>
        </div>
    </div>
</header>