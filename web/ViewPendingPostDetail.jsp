<%-- 
    Document   : ViewPendingPostDetail
    Created on : Nov 2, 2023, 1:12:40 PM
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
        <title>Chi tiết bài viết</title>
    </head>
    <body>
        <div class="wrapper">
            <!--sidebar wrapper -->
            <div class="sidebar-wrapper" data-simplebar="true">
                <div class="sidebar-header">
                    <div>
                        <img src="asset/images/logo-icon.png" class="logo-icon" alt="logo icon">
                    </div>
                    <div>
                        <h4 class="logo-text">Furry Friends</h4>
                    </div>
                    <div class="toggle-icon ms-auto"><i class='bx bx-first-page'></i>
                    </div>
                </div>
                <!--navigation-->
                <ul class="metismenu" id="menu">
                    <form action="MainController">
                        <button class="menuButton" name="action" value="Dashboard">Thống kê</button><br/>
                        <button class="menuButton" name="action" value="Get all user">Quản lí người dùng</button><br/>
                        <button class="selected-button" name="action" value="PostApprovement">Bài viết</button><br/>
                        <button class="menuButton" name="action" value="ExchangeApprovement">Bài trao đổi</button><br/>
                        <button class="menuButton" name="action" value="Get Reported Post">Báo cáo từ người dùng</button><br/>
                        <button class="menuButton" name="action" value="#">Thông tin cá nhân</button><br/>
                        <button class="menuButton" name="action" value="Logout">Đăng xuất</button><br/>
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
                <div class="post-style">
                    <c:set var="p" value ="${requestScope.POST}"/>
                    
                        <div>
                                            <h5 style="color: #ff9e00">${p.postType.postTypeName}</h5>
                            </div>
                            <table>
                                <tbody>
                                    <tr>
                                        <td>
                                            <div class="d-flex">
                                                <img width="42" height="42" src="asset/img/blog/user-img.png" alt="">
                                            </div>
                                        </td>
                                        <td>
                                            <h6>${p.user.username}</h6>
                                            <p>${p.datePost}</p>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        <h6>${p.title}</h6>
                        <p>${p.content}</p>
                        <c:if test="${not empty p.img}">
                            <img class="img-fluid" src="${p.img}" alt="" width="800px" height="300px"> <br>         
                        </c:if>
                        <form action="MainController" method="GET">
                            <input type="hidden" name="postId" value="${p.postId}">
                            <button class="approve-button" name="action" value="Approve">Chấp nhận</button>
                        </form><br>
                        <form name="declineform" action="MainController" onsubmit="return validate()" method="GET">
                            <button class="decline-button" name="action" value="Decline">Từ chối</button>
                            <input class="search-User-box" type="text" name="declineReason" value="" placeholder="Lý do từ chối" required=""><br>
<!--                            <select class="search-User-box" id="declineReason">
                                <option value="">Chọn một lý do</option>
                                <option value="Bôi nhọ cá nhân, tổ chức, nhà nước">Bôi nhọ cá nhân, tổ chức, nhà nước</option>
                                <option value="Ảnh đồi trụy">Ảnh đồi trụy</option>
                                <option value="Sai chuyên mục">Sai chuyên mục</option>
                                
                                <input class="decline-button" type="submit" name="action" value="Decline">
                            </select>-->
                            <input type="hidden" name="postId" value="${p.postId}">

                        </form>
                </div>
            </div>
            <!--end page-wrapper -->
        </div>
    </body>
</html>
