<%-- 
    Document   : StaffPage
    Created on : Oct 10, 2023, 10:06:30 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="java.util.Calendar" %>
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
        
        <!--HighChart-->
        <script src="https://code.highcharts.com/highcharts.js"></script>
        <style>
            .pie-chart-Style{
                background: white;
                border: 1px solid;
                width: 40%;
                border-radius: 8px;
            }
            .big-box-Style{
                margin : 0 180px;
                margin-top: 50px; 
                background: white;
                border: 1px solid; 
                border-radius: 8px;
            }
        </style>
        <title>Staff Page</title>
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
<!--                                <button class="selected-button" name="action" value="Dashboard">Thống kê</button><br/>
                                <button class="menuButton" name="action" value="Get all user">Quản lí người dùng</button><br/>
                                <button class="menuButton" name="action" value="PostApprovement">Bài viết</button><br/>
                                <button class="menuButton" name="action" value="ExchangeApprovement">Bài trao đổi</button><br/>
                                <button class="menuButton" name="action" value="Get Reported Post">Báo cáo từ người dùng</button><br/>
                                <button class="menuButton" name="action" value="#">Thông tin cá nhân</button><br/>
                                <button class="menuButton" name="action" value="Logout">Đăng xuất</button><br/>-->
                                <a style="background: #ff9907" href="viewStatisticController">Thống kê</a>
                                <a href="GetAllUserController">Quản lí người dùng</a>
                                <a href="GetPendingPost">Bài viết</a>
                                <a href="GetPendingExchange">Bài trao đổi</a>
<!--                                <a href="GetReportedPost">Báo cáo từ người dùng</a>-->
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
                    <h1 style="text-align: center; margin-top: 100px">BÁO CÁO THỐNG KÊ</h1>
                    <c:set var="lastYear" value ="${requestScope.LIST_POST_OF_LAST_YEAR}"/>
                    <c:set var="lastYearTotal" value ="${requestScope.LAST_YEAR_TOTAL_POST}"/>
                    <c:set var="thisYear" value ="${requestScope.LIST_POST_OF_THIS_YEAR}"/>
                    <c:set var="thisYearTotal" value ="${requestScope.THIS_YEAR_TOTAL_POST}"/>
                    
                    <c:set var="FirsPtd" value ="${requestScope.FIRST_POST_TYPE_DETAIL}"/>
                    <c:set var="SecondPtd" value ="${requestScope.SECOND_POST_TYPE_DETAIL}"/>
                    <c:set var="ThirdPtd" value ="${requestScope.THIRD_POST_TYPE_DETAIL}"/>
                    <c:set var="ForthPtd" value ="${requestScope.FORTH_POST_TYPE_DETAIL}"/>
                    
                    <c:set var="TotalPendingP" value ="${requestScope.TOTAL_PENDING_POST}"/>
                    <c:set var="TotalApprovedP" value ="${requestScope.TOTAL_APPROVED_POST}"/>
                    <c:set var="TotalRejectedP" value ="${requestScope.TOTAL_REJECTED_POST}"/>
                    
                    <c:set var="ActiveUser" value ="${requestScope.ACTIVE_USER}"/>
                    <c:set var="BannedUser" value ="${requestScope.BANNED_USER}"/>
                    <c:set var="ActiveStaff" value ="${requestScope.ACTIVE_STAFF}"/>
                    <c:set var="BannedStaff" value ="${requestScope.BANNED_STAFF}"/>
                    
                    <c:set var="FirCate" value ="${requestScope.FIRST_CATEGORY_DETAIL}"/>
                    <c:set var="SecCate" value ="${requestScope.SECOND_CATEGORY_DETAIL}"/>
                    <c:set var="ThiCate" value ="${requestScope.THIRD_CATEGORY_DETAIL}"/>
                    
                    <c:set var="mlp" value ="${requestScope.MOST_LIKED_POST}"/>
                    <c:set var="mlpt" value ="${requestScope.MOST_LIKED_POST_TOTAL}"/>
                    <c:set var="mcp" value ="${requestScope.MOST_COMMENTED_POST}"/>
                    <c:set var="mip" value ="${requestScope.MOST_INTERACTION_POST}"/>
                    
                    <c:set var="finishedExchangeQuantity" value ="${requestScope.FINISHED_EXCHANGE_QUANTITY}"/>
                    <div class="big-box-Style">
                        <h4 style="color: coral; text-align: center; margin-top: 30px; margin-bottom: 30px">SỐ LƯỢNG BÀI VIẾT</h4>
                        <figure class="highcharts-figure">
                            <div id="numOfPost"></div>
                        </figure>
                        <div style="display: flex; justify-content: space-evenly ; margin-bottom: 20px">
                            <h5> Năm trước : ${lastYearTotal} bài</h5>
                            <h5> Năm nay : ${thisYearTotal} bài</h5>
                        </div>
                        <div style="display: flex; justify-content:  space-around; margin: 15px">
                            <table>
                                <tbody>
                                    <tr>
                                        <td>
                                            <h5 style="color: #1bf227">Bài đã phê duyệt :</h5>
                                        </td>
                                        <td>
                                            <h5 style="color: #1bf227">${TotalApprovedP}</h5>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <h5 style="color: #ffc930">Bài đang chờ phê duyệt :</h5>
                                        </td>
                                        <td>
                                            <h5 style="color: #ffc930">${TotalPendingP}</h5>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <h5 style="color: red">Bài đã từ chối phê duyệt :</h5>
                                        </td>
                                        <td>
                                            <h5 style="color: red">${TotalRejectedP}</h5>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <div style ="display: flex; justify-content: space-evenly; margin-top: 50px">
                        <div class="pie-chart-Style">
                            <h4 style="color: coral; text-align: center; margin-top: 30px; margin-bottom: 30px">CÁC LOẠI BÀI VIẾT</h4>
                                <figure class="highcharts-figure">
                                    <div id="postTypeDetail"></div>
                                    <h5></h5>
                                </figure>
                        </div>
                        <div class="pie-chart-Style">
                            <h4 style="color: coral; text-align: center; margin-top: 30px; margin-bottom: 30px">CÁC LOẠI TRAO ĐỔI</h4>
                            <figure class="highcharts-figure">
                                <div id="categoryDetail"></div>
                                <h6 style="text-align: center">Số bài trao đổi đã hoàn thành: ${finishedExchangeQuantity}</h6>
                            </figure>
                        </div>
                    </div>
                    <div class="big-box-Style" style="display: flex; justify-content: space-around">
                        <div>
                            <h4 style="color: coral; text-align: center; margin-top: 30px; margin-bottom: 30px">THÀNH VIÊN <i class="fa-solid fa-users"></i></h4>
                            <h4>Đang hoạt động : ${ActiveUser}</h4><br>
                            <h4>Đã dừng hoạt động : ${BannedUser}</h4><br>
                        </div>
                        <div>
                            <h4 style="color: coral; text-align: center; margin-top: 30px; margin-bottom: 30px">QUẢN TRỊ VIÊN <i class="fa-solid fa-users-gear"></i></h4>
                            <h4>Đang hoạt động : ${ActiveStaff}</h4><br>
                            <h4>Đã dừng hoạt động : ${BannedStaff}</h4><br>
                        </div>
                    </div>
                        <div>
                            <h4 style="color: coral; text-align: center; margin-top: 30px; margin-bottom: 30px">CÁC DANH HIỆU</h4>
                            <h5 style="margin-left: 135px; margin-top: 30px; margin-bottom: 30px">BÀI VIẾT CÓ NHIỀU LƯỢT THÍCH NHẤT : ${mlpt}</h5>
                            <div class="post-box">
                                <div><img src="${mlp.img}" class="img-box"></div>
                                <div style="margin-left: 15px ; width: 100%; display: flex; flex-direction: column; justify-content: space-between">
                                        <div style="display: flex; justify-content: space-between">
                                            <h4>${mlp.title}</h4>
                                            <h6 style="color: coral;">${mlp.postType.postTypeName}</h6>
                                        </div>
                                        <div style="display: flex">
                                            <p style="color: #878787;">${mlp.user.username}</p>
                                        </div>
                                        <div>
                                            <c:if test="${fn:length(mlp.content) > 100}">
                                                <h6>${mlp.content.substring(0, 100)}...</h6>
                                            </c:if>
                                            <c:if test="${fn:length(mlp.content) < 100}">
                                                <h6>${mlp.content}</h6>
                                            </c:if>
                                        </div>
                                        <div style="display: flex; justify-content: space-between">
                                            <p>${mlp.datePost}</p>
                                            <form action="MainController">
                                                    <input type="hidden" name="postId" value="${mlp.postId}">
                                                    <button class="view-detail-button" name="action" value="ViewPostDetail">Xem chi tiết <i class="fa-solid fa-arrow-right" style="color: black;"></i></button>
                                            </form>
                                        </div>
                                </div>
                            </div>
                            <h5 style="margin-left: 135px; margin-top: 30px; margin-bottom: 30px">BÀI VIẾT CÓ NHIỀU LƯỢT BÌNH LUẬN NHẤT : ${mcp.quantity}</h5>
                                <div class="post-box">
                                    <div><img src="${mcp.post.img}" class="img-box"></div>
                                    <div style="margin-left: 15px ; width: 100%; display: flex; flex-direction: column; justify-content: space-between">
                                            <div style="display: flex; justify-content: space-between">
                                                <h4>${mcp.post.title}</h4>
                                                <h6 style="color: coral;">${mcp.post.postType.postTypeName}</h6>
                                            </div>
                                            <div style="display: flex">
                                                <p style="color: #878787;">${mcp.post.user.username}</p>
                                            </div>
                                            <div>
                                                <c:if test="${fn:length(mcp.post.content) > 100}">
                                                    <h6>${mcp.post.content.substring(0, 100)}...</h6>
                                                </c:if>
                                                <c:if test="${fn:length(mlp.content) < 100}">
                                                    <h6>${mcp.post.content}</h6>
                                                </c:if>
                                            </div>
                                            <div style="display: flex; justify-content: space-between">
                                                <p>${mcp.post.datePost}</p>
                                                <form action="MainController">
                                                        <input type="hidden" name="postId" value="${mcp.post.postId}">
                                                        <button class="view-detail-button" name="action" value="ViewPostDetail">Xem chi tiết <i class="fa-solid fa-arrow-right" style="color: black;"></i></button>
                                                </form>
                                            </div>
                                    </div>
                                </div>
                            <h5 style="margin-left: 135px; margin-top: 30px; margin-bottom: 30px">BÀI VIẾT CÓ NHIỀU LƯỢT TƯƠNG TÁC NHẤT : ${mip.quantity}</h5>
                                <div class="post-box">
                                    <div><img src="${mip.post.img}" class="img-box"></div>
                                    <div style="margin-left: 15px ; width: 100%; display: flex; flex-direction: column; justify-content: space-between">
                                            <div style="display: flex; justify-content: space-between">
                                                <h4>${mip.post.title}</h4>
                                                <h6 style="color: coral;">${mip.post.postType.postTypeName}</h6>
                                            </div>
                                            <div style="display: flex">
                                                <p style="color: #878787;">${mip.post.user.username}</p>
                                            </div>
                                            <div>
                                                <c:if test="${fn:length(mip.post.content) > 100}">
                                                    <h6>${mip.post.content.substring(0, 100)}...</h6>
                                                </c:if>
                                                <c:if test="${fn:length(mip.post.content) < 100}">
                                                    <h6>${mip.post.content}</h6>
                                                </c:if>
                                            </div>
                                            <div style="display: flex; justify-content: space-between">
                                                <p>${mip.post.datePost}</p>
                                                <form action="MainController">
                                                        <input type="hidden" name="postId" value="${mip.post.postId}">
                                                        <button class="view-detail-button" name="action" value="ViewPostDetail">Xem chi tiết <i class="fa-solid fa-arrow-right" style="color: black;"></i></button>
                                                </form>
                                            </div>
                                    </div>
                                </div>
                        </div>
                </div>
                <!--end page-wrapper -->
                
        </div>
        <!--end wrapper-->
        
        <!--HighChart-->
        <script>
            <!-------------------------------- Category Detail -------------------------------->
            Highcharts.chart('categoryDetail', {
                chart: {
                    type: 'pie'
                },
                title: {
                    text: ''
                },
                tooltip: {
                    valueSuffix: ' bài'
                },
                subtitle: {
                    text:
                    ''
                },
                plotOptions: {
                    series: {
                        allowPointSelect: true,
                        cursor: 'pointer',
                        dataLabels: [{
                            enabled: true,
                            distance: 20
                        }, {
                            enabled: true,
                            distance: -40,
                            format: '{point.percentage:.1f}%',
                            style: {
                                fontSize: '1.2em',
                                textOutline: 'none',
                                opacity: 0.7
                            },
                            filter: {
                                operator: '>',
                                property: 'percentage',
                                value: 10
                            }
                        }]
                    }
                },
                series: [
                    {
                        name: 'Số lượng ',
                        colorByPoint: true,
                        data: [
                            {
                                name: 'Chó',
                                y: ${FirCate}
                            },
                            {
                                name: 'Mèo',
                                y: ${SecCate}
                            },
                            {
                                name: 'Dịch vụ và dụng cụ',
                                y: ${ThiCate}
                            }
                        ]
                    }
                ]
            });
            
            <!-------------------------------- Post Type Detail -------------------------------->
            Highcharts.chart('postTypeDetail', {
                chart: {
                    type: 'pie'
                },
                title: {
                    text: ''
                },
                tooltip: {
                    valueSuffix: ' bài'
                },
                subtitle: {
                    text:
                    ''
                },
                plotOptions: {
                    series: {
                        allowPointSelect: true,
                        cursor: 'pointer',
                        dataLabels: [{
                            enabled: true,
                            distance: 20
                        }, {
                            enabled: true,
                            distance: -40,
                            format: '{point.percentage:.1f}%',
                            style: {
                                fontSize: '1.2em',
                                textOutline: 'none',
                                opacity: 0.7
                            },
                            filter: {
                                operator: '>',
                                property: 'percentage',
                                value: 10
                            }
                        }]
                    }
                },
                series: [
                    {
                        name: 'Số lượng ',
                        colorByPoint: true,
                        data: [
                            {
                                name: 'Sức khỏe cún cưng',
                                y: ${FirsPtd}
                            },
                            {
                                name: 'Cung cấp thông tin thú cưng',
                                y: ${SecondPtd}
                            },
                            {
                                name: 'Mẹo nuôi chó mèo',
                                y: ${ThirdPtd}
                            },
                            {
                                name: 'Bài viết trao đổi',
                                y: ${ForthPtd}
                            }
                        ]
                    }
                ]
            });
            
            <!--------------------------------Num of posts created this year and last year -------------------------------->
            // Create a new Date object
            var currentDate = new Date();

            // Get the current day as a number (0-6, where 0 is Sunday)
            var currentYear = currentDate.getFullYear();

                        Highcharts.chart('numOfPost', {

                title: {
                    text: '',
                    align: 'left'
                },

                subtitle: {
                    text: '',
                    align: 'left'
                },

                yAxis: {
                    title: {
                        text: 'Số lượng bài viết'
                    }
                },

                xAxis: {
                    categories: ['Tháng 1', 'Tháng 2', 'Tháng 3', 'Tháng 4', 'Tháng 5', 'Tháng 6', 'Tháng 7', 'Tháng 8', 'Tháng 9', 'Tháng 10', 'Tháng 11', 'Tháng 12']
                },

                legend: {
                    layout: 'vertical',
                    align: 'right',
                    verticalAlign: 'middle'
                },

//                plotOptions: {
//                    series: {
//                        label: {
//                            connectorAllowed: false
//                        },
//                        pointStart: "Sunday"
//                    }
//                },

                series: [{
                    name: currentYear-1,
                    data: ${lastYear}
                }, {
                    name: currentYear,
                    data: ${thisYear}
                }],

                responsive: {
                    rules: [{
                        condition: {
                            maxWidth: 500
                        },
                        chartOptions: {
                            legend: {
                                layout: 'horizontal',
                                align: 'center',
                                verticalAlign: 'bottom'
                            }
                        }
                    }]
                }

            });

        </script>
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
