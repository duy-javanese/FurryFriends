<%-- 
    Document   : UserManagement
    Created on : Oct 7, 2023, 3:06:18 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en" class="color-sidebar sidebarcolor3 color-header headercolor5">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
        <style type="text/css">
            
        </style>
        <title>Manage members</title>
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
			</div>
			<!--navigation-->
			<ul class="metismenu" id="menu">
                            <form action="MainController">
<!--                                <button class="menuButton" name="action" value="Dashboard">Thống kê</button><br/>
                                <button class="selected-button" name="action" value="Get all user">Quản lí người dùng</button><br/>
                                <button class="menuButton" name="action" value="PostApprovement">Bài viết</button><br/>
                                <button class="menuButton" name="action" value="ExchangeApprovement">Bài trao đổi</button><br/>
                                <button class="menuButton" name="action" value="Get Reported Post">Báo cáo từ người dùng</button><br/>
                                <button class="menuButton" name="action" value="#">Thông tin cá nhân</button><br/>
                                <button class="menuButton" name="action" value="Logout">Đăng xuất</button><br/>-->
                                <a href="StaffPage.jsp">Thống kê</a>
                                <a style="background: #ff9907" href="GetAllUserController">Quản lí người dùng</a>
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
                <div class="userpage-wrapper">
                    
                    <div style=" align-items: center; display: flex; flex-direction: column; margin-top: 10%">
                        <h1 style="margin-bottom: 20px;">QUẢN LÍ NGƯỜI DÙNG</h1>
                        <div style="display: flex; margin-bottom: 20px;">
                            <form action="MainController">
                                    <input class="search-User-box" type="text" name="txtSearchValue" 
                                                       value="${param.txtSearchValue}" placeholder="Nhập tên người dùng"/>
<!--                                    <input class="user-page-button" type="submit" value="Search User" name="action" />-->
                                    <button class="user-page-button" value="Search User" name="action"><i class="fa-solid fa-magnifying-glass" style="color: #ffffff;"></i></button>
                                    
                                </form>
                                    <form action="MainController">
<!--                                        <input class="user-page-button" type="submit" value="Get all user" name="action" />-->
                                        <button class="user-page-button" type="submit" value="Get all user" name="action">Danh sách người dùng</button>
                                    </form>
                        </div>
                                
                    </div>
                    <div class="container">
                            <div class="row">
                                <div class="col-lg-12">
                                        <div class="table-responsive">
                                 <!--------------------------------User List-------------------------------->
                                            <c:set var="searchValue" value="${param.txtSearchValue}"/>
                                            <c:if test="${not empty searchValue}">
                                                <c:set var="result" value ="${requestScope.SEARCH_RESULT}"/>
                                                <c:if test="${not empty result}">
                                            <table  class="table project-list-table table-nowrap align-middle table-borderless">
                                                <thead style="text-align: center">
                                                    <tr>
                                                        <th scope="col">Tài khoản</th>
                                                        <th scope="col">Email</th>
                                                        <th scope="col">Số điện thoại</th>
                                                        <th scope="col">Địa chỉ</th>
                                                        <th scope="col">Điểm</th>
                                                        <th scope="col">Trạng thái</th>
                                                        <th scope="col" style="width: 200px;">Hành động</th>
                                                    </tr>
                                                </thead>
                                                <tbody style="text-align: center">
                                                    <c:forEach items="${result}" var="dto" varStatus="counter">
                                                        <form action="MainController" method="POST">
                                                    <tr>
                                                        <td><a href="#" class="text-body">${dto.username}</a></td>
                                                        <td>${dto.email}</td>
                                                        <td>${dto.phone}</td>
                                                        <td>${dto.address}</td>
                                                        <td>${dto.point}</td>
                                                         <c:if test="${not dto.status}">
                                                        <td style="color: red; font-weight: bold">
                                                                Dừng hoạt động
                                                            </c:if>
                                                            <c:if test="${dto.status}">
                                                        <td style="color: green; font-weight: bold">        
                                                                Đang hoạt động
                                                            </c:if>   
<!--                                                            <select name="txtStatus">
                                                                                    <option ${(dto.status) ?  "selected": ""} value="true">Hoạt động</option>
                                                                                    <option ${(not dto.status) ?  "selected": ""} value="false">BAN</option>
                                                                                </select>-->
                                                        </td>
                                                        <td>
                                                            <input type="hidden" name="txtID" value="${dto.userId}" />
                                                            <input type="hidden"  name="lastSearchValue" value="${searchValue}"/>
                                                            <c:if test="${not dto.status}">
                                                                <input type="hidden" name="txtStatus" value="true" />
                                                            </c:if>
                                                            <c:if test="${dto.status}">
                                                                <input type="hidden" name="txtStatus" value="false" />
                                                            </c:if>
                                                            <button class="ban-user-button" name="action" value="Update user status">Cập nhật trạng thái</button>
                                                        </td>
                                                    </tr>
                                                        </form>
                                                        </c:forEach>
                                                </tbody>
                                            </table>
                                            </c:if>
                                                <c:if test="${empty result}">
                                                    <div class="page-content">
                                                        <h3>Không có người dùng với tên "${searchValue}" :'(</h3>
                                                    </div>
                                                </c:if>
                                            </c:if>
                                            
                                 <!--------------------------------User List-------------------------------->
                                            <c:set var="result" value ="${requestScope.LIST_USER}"/>
                                                <c:if test="${not empty result}">
                                                    <div class="page-content">
                                                        <h2>Danh sách người dùng:</h2>
                                                    </div>
                                            <table  class="table project-list-table table-nowrap align-middle table-borderless">
                                                <thead style="text-align: center">
                                                    <tr>
                                                        <th scope="col">Tài khoản</th>
                                                        <th scope="col">Email</th>
                                                        <th scope="col">Số điện thoại</th>
                                                        <th scope="col">Địa chỉ</th>
                                                        <th scope="col">Điểm</th>
                                                        <th scope="col">Trạng thái</th>
                                                        <th scope="col" style="width: 200px;">Hành động</th>
                                                    </tr>
                                                </thead>
                                                <tbody style="text-align: center">
                                                    <c:forEach items="${result}" var="dto" varStatus="counter">
                                                        <form action="MainController" method="POST">
                                                    <tr>
                                                        <td><a href="#" class="text-body">${dto.username}</a></td>
                                                        <td>${dto.email}</td>
                                                        <td>${dto.phone}</td>
                                                        <td>${dto.address}</td>
                                                        <td>${dto.point}</td>
                                                            <c:if test="${not dto.status}">
                                                        <td style="color: red; font-weight: bold">
                                                                Dừng hoạt động
                                                            </c:if>
                                                            <c:if test="${dto.status}">
                                                        <td style="color: green; font-weight: bold">        
                                                                Đang hoạt động
                                                            </c:if>
<!--                                                            <select name="txtStatus">
                                                                                    <option ${(dto.status) ?  "selected": ""} value="true">Hoạt động</option>
                                                                                    <option ${(not dto.status) ?  "selected": ""} value="false">BAN</option>
                                                                                </select>-->
                                                        </td>
                                                        <td>
                                                            <c:if test="${not dto.status}">
                                                                <input type="hidden" name="txtStatus" value="true" />
                                                            </c:if>
                                                            <c:if test="${dto.status}">
                                                                <input type="hidden" name="txtStatus" value="false" />
                                                            </c:if>
                                                            <input type="hidden" name="txtID" value="${dto.userId}" />
                                                            <input type="hidden"  name="lastSearchValue" value="${searchValue}"/>
                                                            <button class="ban-user-button" name="action" value="Update user status">Cập nhật trạng thái</button>
                                                        </td>
                                                    </tr>
                                                        </form>
                                                        </c:forEach>
                                                </tbody>
                                            </table>
                                            </c:if>
                                        </div>
                                </div>
                            </div>
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
