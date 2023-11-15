<%-- 
    Document   : ReportApprovement
    Created on : Oct 27, 2023, 9:42:21 PM
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
        <style type="text/css">
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
        </style>
        <title>Báo cáo của người dùng</title>
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
<!--                        <button class="menuButton" name="action" value="Dashboard">Thống kê</button><br/>
                        <button class="menuButton" name="action" value="Get all user">Quản lí người dùng</button><br/>
                        <button class="menuButton" name="action" value="PostApprovement">Bài viết</button><br/>
                        <button class="menuButton" name="action" value="ExchangeApprovement">Bài trao đổi</button><br/>
                        <button class="selected-button" name="action" value="Get Reported Post">Báo cáo từ người dùng</button><br/>
                        <button class="menuButton" name="action" value="#">Thông tin cá nhân</button><br/>-->
                        <a href="viewStatisticController">Thống kê</a>
                        <a href="GetAllUserController">Quản lí người dùng</a>
                        <a href="GetPendingPost">Bài viết</a>
                        <a href="GetPendingExchange">Bài trao đổi</a>
<!--                        <a style="background: #ff9907" href="GetReportedPost">Báo cáo từ người dùng</a>-->
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
                <h1 style="text-align: center; margin-top: 10%">XỬ LÍ BÁO CÁO TỪ NGƯỜI DÙNG</h1>
                <form action="MainController" method="POST">
                    <div style="display: flex; justify-content: center; margin-top: 50px">
                        <button class="not-chosen-button" name="action" value="Get Reported Post">Bài viết</button>
                        <button class="chosen-button" name="action" value="Get Reported Exchange">Bài trao đổi</button>
                        <button class="not-chosen-button" name="action" value="Get Reported Comment">Bình luận</button>
                    </div>
                </form>
                
                <c:set var="result" value ="${requestScope.REPORTED_ECHANGE_LIST}"/>
                <c:if test="${not empty result}">
                    <div class="listPendingPost">
                        <table>
                        <thead>
                            <tr>
                                <th>
                                    Loại báo cáo
                                </th>
                                <th>
                                    Nội dung
                                </th>
                                <th>
                                    Ngày báo cáo
                                </th>
                                <th>
                                    Hành động
                                </th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${result}" var="r" varStatus="counter">
                                <tr>
                                    <td>
                                        <c:if test="${r.reportType eq 'Post'}">
                                            Bài viết
                                        </c:if>
                                    </td>
                                    <td>
                                        <div >
                                            <p style="color: #00ff33">${r.reporter.username} </p>
                                            <p style="margin-left: 5px">đã báo cáo bài viết này với lí do </p>
                                            <p style="color: red;margin-left: 5px"> ${r.reportContent.reportContent} <c:if test="${not empty r.reason}">: ${r.reason}</c:if> </p>
                                        </div>
                                    </td>
                                    <td>
                                        ${r.reportDate}
                                    </td>
                                    <td>
                                        <form action="MainController">
                                            <input type="hidden" name="reportId" value="${r.reportId}">
                                            <button class="view-detail-button" name="action" value="ViewReportDetail">Xem chi tiết</button>
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    </div>
                </c:if>
                <c:if test="${ empty result}">
                    <h3 style="text-align: center; margin-top: 50px">"Hiện tại không còn báo cáo nào khác từ người dùng"</h3>
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
