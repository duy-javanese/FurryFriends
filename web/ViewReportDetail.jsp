<%-- 
    Document   : ViewReportDetail
    Created on : Nov 5, 2023, 11:49:43 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
        
        <title>Chi tiết bài báo cáo</title>
    </head>
    <body>
        <div class="wrapper">
            <!--sidebar wrapper -->
            <div class="sidebar-wrapper" data-simplebar="true">
                <div class="sidebar-header">
                    <div>
                        <img class="rounded-circle" src="/asset/img/furryfriends-1.png" alt="" width="42px" height="42px">
                    </div>
                    <div style="margin-left: 15px">
                        <h4 class="logo-text">Furry Friends</h4>
                    </div>
                    <div class="toggle-icon ms-auto"><i class='bx bx-first-page'></i>
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
                        <a href="StaffPage.jsp">Thống kê</a>
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
                                                                            <img src="assets/images/avatars/avatar-2.png" class="user-img" alt="user avatar">
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
                <form action="GetReportedPost">
                    <button class="return-button">Quay lại</button>
                </form>
                <c:set var="r" value ="${requestScope.REPORT}"/>
                <div class="post-style">
                                    <div style="display: flex">
                                        <p style="color: #00ff33">${r.reporter.username} </p>
                                        <p style="margin-left: 5px">đã báo cáo bài viết này với lí do: </p>
                                        <p style="color: red;margin-left: 5px">${r.reason}</p>
                                    </div>
                        <div style="display: flex">
                                <div class="d-flex">
                                                <img width="42" height="42" src="asset/img/blog/user-img.png" alt="">
                                </div>
                            <div style="margin-left: 15px">
                                    <h6>${r.post.user.username}</h6>
                                    <p>${r.post.datePost}</p>
                                </div>
                        </div>
                        <h6>${r.post.title}</h6>
                        <p>${r.post.content}</p>
                        <c:if test="${not empty r.post.img}">
                            <img class="img-fluid" src="${r.post.img}" alt="" width="800px" height="300px"> <br>         
                        </c:if>
                            <div style="display: flex; margin-top: 10px">
                                <form action="MainController">
                                    <input type="hidden" name="reportType" value="${r.reportType}">
                                    <input type="hidden" name="postId" value="${r.post.postId}">
                                    <input type="hidden" name="reportId" value="${r.reportId}">
                                    <button class="hide-post-button" name="action" value="HidePost">Ẩn bài viết này</button>
                                </form>
                                <form action="MainController">
                                    <input type="hidden" name="reportId" value="${r.reportId}">
                                    <input type="hidden" name="reportType" value="${r.reportType}">
                                    <input type="hidden" name="userId" value="${r.post.user.userId}">
                                    <button class="ban-user-button" name="action" value="Ban">Ban người dùng này</button>
                                </form>   
                            </div>
                             
                    </div>
            </div>
            <!--end page-wrapper -->
        </div>
    </body>
</html>
