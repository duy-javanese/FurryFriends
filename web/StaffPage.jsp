<%-- 
    Document   : StaffPage
    Created on : Oct 10, 2023, 10:06:30 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <style type="text/css">
        </style>
        <title>Staff Page</title>
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
<!--                                <button class="selected-button" name="action" value="Dashboard">Thống kê</button><br/>
                                <button class="menuButton" name="action" value="Get all user">Quản lí người dùng</button><br/>
                                <button class="menuButton" name="action" value="PostApprovement">Bài viết</button><br/>
                                <button class="menuButton" name="action" value="ExchangeApprovement">Bài trao đổi</button><br/>
                                <button class="menuButton" name="action" value="Get Reported Post">Báo cáo từ người dùng</button><br/>
                                <button class="menuButton" name="action" value="#">Thông tin cá nhân</button><br/>
                                <button class="menuButton" name="action" value="Logout">Đăng xuất</button><br/>-->
                                <a style="background: #ff9907" href="StaffPage.jsp">Thống kê</a>
                                <a href="GetAllUserController">Quản lí người dùng</a>
                                <a href="GetPendingPost">Bài viết</a>
                                <a href="GetPendingExchange">Bài trao đổi</a>
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
