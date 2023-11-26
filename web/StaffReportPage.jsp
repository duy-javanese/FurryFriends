<%-- 
    Document   : StaffReportPage
    Created on : Nov 25, 2023, 10:37:24 PM
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
            .staff-report-menu{
                font-weight: bold;
                background: white;
                border : 0;
                font-size: large;
            }
            .staff-report-menu:hover{
                color: #FF8800;
            }

            .staff-report-box{
                margin: 30px 135px;
                padding: 10px;
                background: white;
                border-radius: 5px;
                border: 2px solid black;
            }
            .report-content-box{
                background: white;
                display: flex;
            }
            .report-reason-style{
                color: red;
                padding: 5px;
            }
        </style>
        <title>Báo cáo từ người</title>
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
                <c:set var="totalReportedPost" value ="${requestScope.TOTAL_REPORTED_POST}"/>
                <c:set var="totalReportedExchange" value ="${requestScope.TOTAL_REPORTED_EXCHANGE}"/>

                <h1 style="text-align: center; margin-top: 10% ; margin-bottom: 50px">DANH SÁCH CÁC BÁO CÁO TỪ NGƯỜI DÙNG</h1>

                <div style="background: white; margin: 0 135px; padding: 20px ; border-radius: 6px">
                    <form action="MainController">
                        <div style="display: flex; justify-content: space-around">
                            <button class="staff-report-menu" name="action" value="Get Reported Post">Bài viết (${totalReportedPost})</button>
                            <button class="staff-report-menu" name="action" value="Get Reported Exchange">Bài trao đổi (${totalReportedExchange})</button>
                            <button class="staff-report-menu" name="action" value="ReportProcessHitory">Lịch sử xử lí báo cáo</button>
                        </div>
                    </form>
                </div>
                <!--====================================================== List Reported Posts From Users ======================================================-->
                <c:set var="result" value ="${requestScope.REPORTED_POST_LIST}"/>
                <c:forEach items="${result}" var="r" varStatus="counter">
                    <c:if test="${not empty result}">
                        <div class="staff-report-box">
                            <h6 class="report-reason-style">${r.reportDate} : Bài viết này đã bị báo cáo với lý do "${r.reportContent.reportContent} <c:if test="${not empty r.reason}"> : ${r.reason}</c:if>"</h6>
                                <div class="report-content-box">
                                    <div><img src="${r.post.img}" class="img-box"></div>
                                <div style="margin-left: 15px ; width: 100%; display: flex; flex-direction: column; justify-content: space-between">
                                    <div style="display: flex; justify-content: space-between">
                                        <h4>${r.post.title}</h4>
                                        <h6 style="color: coral;">${r.post.postType.postTypeName}<c:if test="${not empty r.post.category.categoryName}"> : ${r.post.category.categoryName}</c:if></h6>
                                        </div>
                                        <div style="display: flex">
                                            <p style="color: #878787;">${r.post.user.username}</p>
                                    </div>
                                    <div>
                                        <c:if test="${fn:length(r.post.content) > 100}">
                                            <h6>${r.post.content.substring(0, 100)}...</h6>
                                        </c:if>
                                        <c:if test="${fn:length(r.post.content) < 100}">
                                            <h6>${r.post.content}</h6>
                                        </c:if>
                                    </div>
                                    <div style="display: flex; justify-content: space-between">
                                        <p>${r.post.datePost}</p>
                                        <form action="MainController">
                                            <input type="hidden" name="reportId" value="${r.reportId}">
                                            <input type="hidden" name="postType" value="${r.post.postType.postTypeId}">
                                            <button class="view-detail-button" name="action" value="ViewReportDetail">Xem chi tiết <i class="fa-solid fa-arrow-right" style="color: black;"></i></button>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:if>
                </c:forEach>
                <!--====================================================== List Reported Exchanges From Users ======================================================-->                
                <c:set var="result" value ="${requestScope.REPORTED_ECHANGE_LIST}"/>
                <c:forEach items="${result}" var="r" varStatus="counter">
                    <div class="staff-report-box">
                        <h6 class="report-reason-style">${r.reportDate} : Bài trao đổi này đã bị báo cáo với lý do "${r.reportContent.reportContent} <c:if test="${not empty r.reason}"> : ${r.reason}</c:if>"</h6>
                            <div class="report-content-box">
                                <div><img src="${r.exchange.post.img}" class="img-box"></div>
                            <div style="margin-left: 15px ; width: 100%; display: flex; flex-direction: column; justify-content: space-between">
                                <div style="display: flex; justify-content: space-between">
                                    <h4>${r.exchange.post.title}</h4>
                                    <h6 style="color: coral;">${r.exchange.post.postType.postTypeName}<c:if test="${not empty r.exchange.post.category.categoryName}"> : ${r.exchange.post.category.categoryName}</c:if></h6>
                                    </div>
                                    <h6 style="color: gold">${r.exchange.price} VNĐ</h6>
                                <div style="display: flex">
                                    <p style="color: #878787;">${r.exchange.post.user.username}</p>
                                </div>
                                <div>
                                    <c:if test="${fn:length(r.exchange.post.content) > 100}">
                                        <h6>${r.exchange.post.content.substring(0, 100)}...</h6>
                                    </c:if>
                                    <c:if test="${fn:length(r.exchange.post.content) < 100}">
                                        <h6>${r.exchange.post.content}</h6>
                                    </c:if>
                                </div>
                                <div style="display: flex; justify-content: space-between">
                                    <p>${r.exchange.post.datePost}</p>
                                    <form action="MainController">
                                        <input type="hidden" name="reportId" value="${r.reportId}">
                                        <input type="hidden" name="postType" value="${r.exchange.post.postType.postTypeId}">
                                        <button class="view-detail-button" name="action" value="ViewReportDetail">Xem chi tiết <i class="fa-solid fa-arrow-right" style="color: black;"></i></button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
                <!--====================================================== Process Report History ======================================================-->
                <c:set var="result" value ="${requestScope.PROCESS_REPORT_HISTORY}"/>
                <c:if test="${not empty result}">
                    <div style="margin-top: 75px">
                        <div class="page-content">
                            <h2 style="text-align: center">Lịch sử xử lí báo cáo từ người dùng:</h2>
                        </div>
                        <table  class="table project-list-table table-nowrap align-middle table-borderless">
                            <thead style="text-align: center">
                                <tr>
                                    <th scope="col">Bài viết</th>
                                    <th scope="col">Nội dung báo cáo</th>
                                    <th scope="col">Người báo cáo</th>
                                    <th scope="col">Ngày báo cáo</th>
                                    <th scope="col">Quản trị viên xử lí</th>
                                    <th scope="col">Ngày Xử lí</th>
                                    <th scope="col">Giải Pháp</th>
                                </tr>
                            </thead>
                            <tbody style="text-align: center">
                                <c:forEach items="${result}" var="r" varStatus="counter">
                                    <tr>
                                        <td>
                                            <img src="${r.post.img}" height="80px" width="80px" style="object-fit: cover; border: 1px solid black; border-radius: 6px">
                                            <h6 style="margin-top: 10px">${r.post.title}</h6>
                                        </td>
                                        <td>
                                            ${r.reportContent.reportContent} <c:if test="${not empty r.reason}"> : ${r.reason}</c:if>
                                            </td>
                                            <td>${r.reporter.username}</td>
                                        <td>${r.reportDate}</td>
                                        <td>${r.processStaff.username}</td>
                                        <td>${r.processDate}</td>
                                        <td>
                                            <c:if test="${r.reportStatus == 2}">
                                                Ẩn bài viết
                                            </c:if>
                                            <c:if test="${r.reportStatus == 3}">
                                                Từ chối báo cáo
                                            </c:if>
                                            <c:if test="${r.reportStatus == 4}">
                                                Cấm người dùng
                                            </c:if>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </c:if>
                </div>
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
