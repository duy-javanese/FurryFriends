<%-- 
    Document   : ViewPendingExchangeDetail
    Created on : Nov 3, 2023, 3:52:04 PM
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
        <script>
            function validate(){
                var reason = document.getElementById('declineReason').value;
                if (reason == ""){
                    alert("Hãy chọn một lý do");
                    return false;
                } else {
                    return true;
                }
            }
        </script>
        <style>
            .pending-post-detail-title{
                font-size: 36px;
                font-weight: 700;
                margin-bottom: 20px;
                margin-top: 30px;
            }
        </style>
        <title>Chi tiết bài trao đổi</title>
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
                        <button class="menuButton" name="action" value="PostApprovement">Bài viết</button><br/>
                        <button class="selected-button" name="action" value="ExchangeApprovement">Bài trao đổi</button><br/>
                        <button class="menuButton" name="action" value="Get Reported Post">Báo cáo từ người dùng</button><br/>
                        <button class="menuButton" name="action" value="#">Thông tin cá nhân</button><br/>
                        <button class="menuButton" name="action" value="Logout">Đăng xuất</button><br/>-->
                        <a href="StaffPage.jsp">Thống kê</a>
                        <a href="GetAllUserController">Quản lí người dùng</a>
                        <a href="GetPendingPost">Bài viết</a>
                        <a style="background: #ff9907" href="GetPendingExchange">Bài trao đổi</a>
                        <a href="GetReportedPost">Báo cáo từ người dùng</a>
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
                <form action="GetPendingExchange">
                    <button class="return-button">Quay lại</button>
                </form>
                <div class="post-style">
                    <c:set var="e" value ="${requestScope.EXCHANGE}"/>
                        <c:if test="${not empty e.post.img}">
                             <img src="${e.post.img}" class="detail-img-box"> <br>        
                        </c:if>
                        <h4 class="pending-post-detail-title">${e.post.title}</h4>
                        <div style="display: flex; justify-content: space-between;">
                            <div style="display: flex">
                                <div class="d-flex">
                                                <img width="42" height="42" src="asset/img/blog/user-img.png" alt="">
                                </div>
                                <div style="margin-left: 15px">
                                    <h6>${e.post.user.username}</h6>
                                            <p>${e.post.datePost}</p>
                                </div>
                            </div>
                            <div>
                                <h6 style="color: #ff9e00">${e.post.postType.postTypeName} : ${e.post.category.categoryName}</h6>
                            </div>
                        </div>
                        
                        
                        
                        <p>${e.post.content}</p>
                       
                            <h5>Price:</h5><h5 style="color: red">${e.price} VNĐ</h5>
                        <form action="MainController" method="GET">
                            <input type="hidden" name="postId" value="${e.post.postId}">
                            <input type="hidden" name="postTypeId" value="${e.post.postType.postTypeId}">
                            <button class="approve-button" name="action" value="Approve">Approve</button>
                        </form><br>
                        <form name="declineform" action="MainController" onsubmit="return validate()" method="POST">
<!--                            <button class="decline-button" name="action" value="Decline">Decline</button>
                            <input class="search-User-box" type="text" name="declineReason" value="" placeholder="Lý do từ chối" required=""><br>-->
                            <select class="search-User-box" name="declineReason" id="declineReason">
                                <option value="">Chọn một lý do</option>
                                <option value="Giá không hợp lí">Giá không hợp lí</option>
                                <option value="Không trao đổi cụ thể">Không trao đổi cụ thể</option>
                                <option value="Thiếu thông tin">Thiếu thông tin</option>
                                <option value="Sử dụng hình ảnh từ trên mạng">Sử dụng hình ảnh từ trên mạng</option>
                                <option value="Sai chuyên mục">Sai chuyên mục</option>
                                <input class="decline-button" type="submit" name="action" value="Từ chối">
                            </select>
                            <input type="hidden" name="postTypeId" value="${e.post.postType.postTypeId}">
                            <input type="hidden" name="postId" value="${e.post.postId}">
                            <input type="hidden" name="postTypeId" value="${e.post.postType.postTypeId}">
                        </form>
                </div>
            </div>
            <!--end page-wrapper -->
        </div>
    </body>
</html>
