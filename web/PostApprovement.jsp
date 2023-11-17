<%-- 
    Document   : PostApprovement
    Created on : Oct 15, 2023, 11:49:34 PM
    Author     : Admin
--%>

<%@ page import="DAO.InformationDAO" %>
<%@ page import="Model.Information" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en" class="color-sidebar sidebarcolor3 color-header headercolor5">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <link href="asset/staff-page/css/pace.min.css" rel="stylesheet" />
        <script src="asset/staff-page/js/pace.min.js"></script>

        <link href="asset/staff-page/css/bootstrap.min.css" rel="stylesheet">
        <link href="asset/staff-page/css/bootstrap-extended.css" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500&display=swap" rel="stylesheet">
        <link href="asset/staff-page/css/app.css" rel="stylesheet">
        <link href="asset/staff-page/css/icons.css" rel="stylesheet">

        <link rel="stylesheet" href="asset/staff-page/css/dark-theme.css" />
        <link rel="stylesheet" href="asset/staff-page/css/semi-dark.css" />
        <link rel="stylesheet" href="asset/staff-page/css/header-colors.css" />
        <script src="https://kit.fontawesome.com/337871dc56.js" crossorigin="anonymous"></script>
        <style>
            .listPendingPost{
                padding-top: 30px;
            }
             .listPendingPost table th, td {
                            border: 2px solid black;
            }

            .listPendingPost table {
                            border-collapse: collapse;
                            width: 1000px;
                            margin: 0 auto;
            }

            .listPendingPost th, td {
                            padding: 10px
            }

            .listPendingPost th{
                            text-align: center;
                            background: grey;
                            color: white;
            }

            .listPendingPost td{
                            background: white;
            }
            .post-box{
                background: white;
                margin: 30px 135px;
                border: 2px solid;
                border-radius: 5px;
                display: flex;
                padding: 10px;
            }
            .img-box{
                height: 100%;
                border: 1px solid;
                width: 200px;
                border-radius: 5px;
                object-fit: cover;
            }
        </style>
        <title>Duyệt bài viết</title>
    </head>
    <c:if test="${sessionScope.account == null}">
            <c:redirect url="loginPage.jsp"></c:redirect>
    </c:if>

    <c:if test="${not empty sessionScope.account and sessionScope.account.role.roleId ne 2}">
    <!-- Người dùng đã đăng nhập nhưng không phải là ADMIN -->
        <h1>Bạn không có quyền truy cập trang web này.</h1>
        <c:redirect url="HomePage.jsp"></c:redirect>
    </c:if>
    <body>
        <div class="wrapper">
            <!--sidebar wrapper -->
            <div class="sidebar-wrapper" data-simplebar="true">
                <div class="sidebar-header">
                     <%
        try {
            Information info = InformationDAO.getInfor();
%>
                    <div>
                        <%
    String logoPath = (info.getLogoPath() != null) ? info.getLogoPath() : "${pageContext.request.contextPath}/asset/img/furryfriends-1.png";
                    %>
                        <img class="rounded-circle" src="<%= logoPath %>" alt="" width="42px" height="42px">
                    </div>
                    <%
} catch (Exception e) {
e.printStackTrace();
}
%>
                    <div style="margin-left: 15px">
                        <h4 class="logo-text">Furry Friends</h4>
                    </div>
                </div>
                <!--navigation-->
                <ul class="metismenu" id="menu">
                    <form action="MainController">
<!--                        <button class="menuButton" name="action" value="Dashboard">Thống kê</button><br/>
                        <button class="menuButton" name="action" value="Get all user">Quản lí người dùng</button><br/>
                        <button class="selected-button" name="action" value="PostApprovement">Bài viết</button><br/>
                        <button class="menuButton" name="action" value="ExchangeApprovement">Bài trao đổi</button><br/>
                        <button class="menuButton" name="action" value="Get Reported Post">Báo cáo từ người dùng</button><br/>
                        <button class="menuButton" name="action" value="#">Thông tin cá nhân</button><br/>
                        <button class="menuButton" name="action" value="Logout">Đăng xuất</button><br/>-->
                        <a href="viewStatisticController">Thống kê</a>
                        <a href="GetAllUserController">Quản lí người dùng</a>
                        <a style="background: #ff9907"  href="GetPendingPost">Bài viết</a>
                        <a href="GetPendingExchange">Bài trao đổi</a>
<!--                        <a href="GetReportedPost">Báo cáo từ người dùng</a>-->
                        <a href="logout">Đăng xuất</a>
                    </form>		
                </ul>
                <!--end navigation-->
            </div>
            <!--end sidebar wrapper -->
            <!--start header -->
            <header>
                <div class="topbar d-flex align-items-center">
                                            <nav class="navbar navbar-expand">
                                                <div class="user-box dropdown">
                                                                    <a class="d-flex align-items-center nav-link dropdown-toggle dropdown-toggle-nocaret" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                                                            
                                                                            <div class="user-info ps-3">
                                                                                    <c:if test="${sessionScope.account != null}">
                                                                                        <p class="user-name mb-0">Hello ${sessionScope.account.username}</p>
                                                                                    </c:if>
                                                                            </div>
                                                                    </a>
                                                            </div>
                                            </nav>
                </div>
            </header>
            <!--end header -->
            <!--Start page-wrapper -->
            <div class="page-wrapper">
                <h1 style="text-align: center; margin-top: 10%">DANH SÁCH BÀI VIẾT ĐANG CHỜ DUYỆT</h1>
                <div style="margin-top: 50px; margin-left: 360px">
                    <form action="MainController" method="POST">
                        <input class="search-User-box" type="text" name="txtSearchValue" 
                            value="${param.txtSearchValue}" placeholder="Nhập tiêu đề bài viết"/>
                        <select class="category-selection" name="postTypeId">
                            <option value="0">Loại bài viết</option>
                            <c:forEach var="t" items="${sessionScope.types}">
                                <option value="${t.postTypeId}">${t.postTypeName}</option>
                            </c:forEach>
                        </select>
                        <button class="user-page-button" name="action" value="SearchPendingPost"><i class="fa-solid fa-magnifying-glass" style="color: #ffffff;"></i></button>
                    </form>
                </div>
                <c:set var="result" value ="${requestScope.PENDING_LIST}"/>
                <c:if test="${not empty result}">
                    <c:forEach items="${result}" var="p" varStatus="counter">
                        <div class="post-box">
                            <div><img src="${p.img}" class="img-box"></div>
                            <div style="margin-left: 15px ; width: 100%; display: flex; flex-direction: column; justify-content: space-between">
                                <div style="display: flex; justify-content: space-between">
                                    <h4>${p.title}</h4>
                                    <h6 style="color: coral;">${p.postType.postTypeName}</h6>
                                </div>
                                <div style="display: flex">
                                    <p style="color: #878787;">${p.user.username}</p>
                                </div>
                                <div>
                                    <c:if test="${fn:length(p.content) > 100}">
                                        <h6>${p.content.substring(0, 100)}...</h6>
                                    </c:if>
                                    <c:if test="${fn:length(p.content) < 100}">
                                        <h6>${p.content}</h6>
                                    </c:if>
                                </div>
                                <div style="display: flex; justify-content: space-between">
                                    <p>${p.datePost}</p>
                                    <form action="MainController">
                                            <input type="hidden" name="postId" value="${p.postId}">
                                            <button class="view-detail-button" name="action" value="ViewPendPostDetail">Xem chi tiết <i class="fa-solid fa-arrow-right" style="color: black;"></i></button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </c:if>
                <c:if test="${empty result}">
                    <h3 style="text-align: center; margin-top: 50px">"Hiện tại không có bài biết nào đang chờ duyệt"</h3>
                </c:if>
            </div>
            <!--end page-wrapper -->

        </div>
        <!--end wrapper-->
        <!-- Bootstrap JS -->
        <script src="asset/staff-page/js/bootstrap.bundle.min.js"></script>
        <!--plugins-->
        <script src="asset/staff-page/js/jquery.min.js"></script>
        <script src="asset/staff-page/plugins/simplebar/js/simplebar.min.js"></script>
        <script src="asset/staff-page/plugins/metismenu/js/metisMenu.min.js"></script>
        <script src="asset/staff-page/plugins/perfect-scrollbar/js/perfect-scrollbar.js"></script>
        <script src="asset/staff-page/plugins/apexcharts-bundle/js/apexcharts.min.js"></script>
        <script src="asset/staff-page/js/index5.js"></script>
        <!--app JS-->
        <script src="asset/staff-page/js/app.js"></script>
    </body>
</html>
