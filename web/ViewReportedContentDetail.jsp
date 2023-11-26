<%-- 
    Document   : ViewReportedContentDetail
    Created on : Nov 25, 2023, 11:47:30 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="DAO.InformationDAO" %>
<%@ page import="Model.Information" %>
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
            .report-reason-style{
                color: red;
                padding: 5px;
            }
            .process-report-button{
                border: 0;
                border-radius: 5px;
                color: white;
                font-size: large;
                background: #ffc930;
                padding: 5px;
            }
        </style>
        <title>Chi tiết báo cáo</title>
    </head>

    <body>
        <c:if test="${sessionScope.account == null}">
            <c:redirect url="loginPage.jsp"></c:redirect>
        </c:if>
        <c:if test="${not empty sessionScope.account and sessionScope.account.role.roleId ne 2}">
            <!-- Người dùng đã đăng nhập nhưng không phải là ADMIN -->
            <h1>Bạn không có quyền truy cập trang web này.</h1>
            <c:redirect url="HomePage.jsp"></c:redirect>
        </c:if>

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
                                                <button class="menuButton" name="action" value="PostApprovement">Bài viết</button><br/>
                                                <button class="selected-button" name="action" value="ExchangeApprovement">Bài trao đổi</button><br/>
                                                <button class="menuButton" name="action" value="Get Reported Post">Báo cáo từ người dùng</button><br/>
                                                <button class="menuButton" name="action" value="#">Thông tin cá nhân</button><br/>
                                                <button class="menuButton" name="action" value="Logout">Đăng xuất</button><br/>-->
                        <a href="viewStatisticController">Thống kê</a>
                        <a href="GetAllUserController">Quản lí người dùng</a>
                        <a href="GetPendingPost">Bài viết</a>
                        <a href="GetPendingExchange">Bài trao đổi</a>
                        <a style="background: #ff9907" href="GetReportedPost">Báo cáo từ người dùng</a>
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
                <!--====================================================== Reported Posts Detail ======================================================-->
                <c:set var="reP" value ="${requestScope.REPORTED_POST}"/>
                <c:if test="${not empty reP}">
                    <form action="GetReportedPost">
                        <button class="return-button">Quay lại</button>
                    </form>
                    <div class="post-style">
                        <c:if test="${not empty reP.post.img}">
                            <img src="${reP.post.img}" class="detail-img-box"> <br>         
                        </c:if>
                        <!--                            <div>
                                                                    <h5 style="color: #ff9e00">${p.postType.postTypeName}</h5>
                                                    </div>-->
                        <h4 class="pending-post-detail-title">${reP.post.title}</h4>
                        <div style="display: flex; justify-content: space-between;">
                            <div style="display: flex">
                                <div class="d-flex">
                                    <img width="42" height="42" src="${pageContext.request.contextPath}/asset/img/paw.png" alt="">
                                </div>
                                <div style="margin-left: 15px">
                                    <h6>${reP.post.user.username}</h6>
                                    <p>${reP.post.datePost}</p>
                                </div>
                            </div>
                            <div>
                                <h6 style="color: coral;">${reP.post.postType.postTypeName} 
                                    <c:if test="${not empty reP.post.category.categoryName}"> : ${reP.post.category.categoryName}</c:if></h6>
                            </div>
                        </div>
                        <p>${reP.post.content}</p>
                        <h6 class="report-reason-style">${reP.reportDate} : "${reP.reporter.username}" đã báo cáo bài viết với lý do "${reP.reportContent.reportContent} <c:if test="${not empty reP.reason}"> : ${reP.reason}</c:if>"</h6>
                            <div >
                                <form action="MainController">
                                    <input type="hidden" name="postId" value="${reP.post.postId}">
                                    <input type="hidden" name="reportId" value="${reP.reportId}">
                                    <input type="hidden" name="postType" value="${reP.post.postType.postTypeId}">
                                    <input type="hidden" name="userId" value="${reP.post.user.userId}">
                                    <input type="hidden" name="staffId" value="${sessionScope.account.userId}">
                                    <div style="display: flex; gap : 15px">
                                        <button class="process-report-button" name="action" value="HideReportedContent">Ẩn bài viết này</button>
                                        <button class="process-report-button" name="action" value="RejectReportedContent">Từ chối báo cáo</button>
                                        <button class="process-report-button" name="action" value="BanReportedUser">Cấm người dùng nầy</button>
                                    </div>
                                </form>
                            </div>
                    </div>
                </c:if>


                <!--====================================================== Reported Posts Detail ======================================================-->     
                <c:set var="reE" value ="${requestScope.REPORTED_EXCHANGE}"/>
                <c:if test="${not empty reE}">
                    <form action="GetReportedExchange">
                        <button class="return-button">Quay lại</button>
                    </form>
                    <div class="post-style">

                        <c:if test="${not empty reE.exchange.post.img}">
                            <img src="${reE.exchange.post.img}" class="detail-img-box"> <br>         
                        </c:if>
                        <!--                            <div>
                                                                    <h5 style="color: #ff9e00">${p.postType.postTypeName}</h5>
                                                    </div>-->
                        <h4 class="pending-post-detail-title">${reE.exchange.post.title}</h4>
                        <div style="display: flex; justify-content: space-between;">
                            <div style="display: flex">
                                <div class="d-flex">
                                    <img width="42" height="42" src="${pageContext.request.contextPath}/asset/img/paw.png" alt="">
                                </div>
                                <div style="margin-left: 15px">
                                    <h6>${reE.exchange.post.user.username}</h6>
                                    <p>${reE.exchange.post.datePost}</p>
                                </div>
                            </div>
                            <div>
                                <h6 style="color: coral;">${reE.exchange.post.postType.postTypeName}
                                <c:if test="${not empty reE.exchange.post.category.categoryName}"> : ${reE.exchange.post.category.categoryName}</c:if></h6>
                            </div>
                        </div>
                        <p>${reE.exchange.post.content}</p>
                        <h5>Giá : </h5><h5 style="color: red">${reE.exchange.price} VNĐ</h5>
                        <h6 class="report-reason-style">${reE.reportDate} : ${reE.reporter.username} đã báo cáo bài trao đổi với lý do "${reE.reportContent.reportContent} <c:if test="${not empty reE.reason}"> : ${reE.reason}</c:if>"</h6>
                        <div>
                                <form action="MainController">
                                    <input type="hidden" name="postId" value="${reE.exchange.post.postId}">
                                    <input type="hidden" name="reportId" value="${reE.reportId}">
                                    <input type="hidden" name="postType" value="${reE.exchange.post.postType.postTypeId}">
                                    <input type="hidden" name="userId" value="${reE.exchange.post.user.userId}">
                                    <input type="hidden" name="staffId" value="${sessionScope.account.userId}">
                                    <div style="display: flex; gap : 15px">
                                        <button class="process-report-button" name="action" value="HideReportedContent">Ẩn bài viết này</button>
                                        <button class="process-report-button" name="action" value="RejectReportedContent">Từ chối báo cáo</button>
                                        <button class="process-report-button" name="action" value="BanReportedUser">Cấm người dùng nầy</button>
                                    </div>
                                </form>
                            </div>
                        </div>
                </c:if>
            </div>
            <!--end page-wrapper -->
        </div>
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
