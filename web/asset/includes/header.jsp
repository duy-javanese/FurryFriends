<%-- 
    Document   : header
    Created on : Oct 1, 2023, 9:31:26 PM
    Author     : dell
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="DAO.InformationDAO" %>
<%@ page import="Model.Information" %>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Sensive Blog - Home</title>
    <link rel="icon" href="img/Fevicon.png" type="image/png">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/asset/vendors/bootstrap/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/asset/vendors/fontawesome/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/asset/vendors/themify-icons/themify-icons.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/asset/vendors/linericon/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/asset/vendors/owl-carousel/owl.theme.default.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/asset/vendors/owl-carousel/owl.carousel.min.css">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/asset/css/style.css">

    <script src="https://kit.fontawesome.com/337871dc56.js" crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <style>
        /* The Modal (hidden by default) */
        .modal {
            display: none;
            position: fixed;
            z-index: 1;
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
            overflow: auto;
            background-color: rgba(0, 0, 0, 0.7);
        }

        /* Modal Content */
        .modal-content {
            background-color: #fff;
            margin: 15% auto;
            padding: 20px;
            border: 1px solid #888;
            width: 500px;
            background-color: white;
        }

        /* Close button */
        .close {
            position: absolute;
            right: 10px;
            top: 5px;
            color: #000;
            font-size: 20px;
        }
        .homepage-search{
            display: flex;
            justify-content: center;
            margin-bottom: 30px;
            position: relative;
        }
        .homepage-search-box{
            width: 450px;
            padding: 7px;
            border-radius: 5px;
        }
        .homepage-search-button{
            padding: 8px 12px;
            border-radius: 5px;
            border: 0;
            background: #ffc930;
        }
        .search-ajax-result{
            position: absolute;
            top: 50px;
            z-index: 10;
            display: flex;
            flex-direction: column;
            gap: 20px;
            background: white;
            width: 100%;
           
        }
        .each-ajax-result{
            display: flex;
            align-items: center;
            gap: 20px;
            width: 100%;
        }

    </style>
    <script>
        function showSearchResults() {
    var searchAjaxContent = document.getElementById('searchAjaxContent');
    searchAjaxContent.style.display = 'flex';
}
        
        function hideSearchResults() {
        var searchAjaxContent = document.getElementById('searchAjaxContent');
        var inputValue = document.getElementsByName('txtSearchValue')[0].value.trim();

        // Kiểm tra xem ô input có giá trị hay không
        if (inputValue === '') {
            // Nếu không, ẩn searchAjaxContent
            searchAjaxContent.style.display = "none";
        }
    }
        
        function searchByTitle(param) {
            var txtSearch = param.value;

            
            $.ajax({
                url: "/SearchAjaxHomePage",
                type: "get", //send it through get method
                data: {
                    txt: txtSearch
                },
                success: function (data) {
                    var row = document.getElementById("searchAjaxContent");
                    row.innerHTML = data;
                    
                    var searchResults = row.getElementsByClassName("each-ajax-result");
                    var maxHeight = 300;

                    if (searchResults.length > 3) { // Chỉ định số lượng tối đa trước khi hiển thị thanh cuộn
                        row.style.height = maxHeight + "px";
                        row.style.overflowY = "scroll";
                    } else {
                        row.style.height = "auto";
                        row.style.overflowY = "visible";
                    }
                },
                error: function (xhr) {
                    //Do Something to handle error
                }
            });
        }
    </script>
</head>
<!--================Header Menu Area =================-->
<header class="header_area">
    <div class="main_menu" >
        <nav class="navbar navbar-expand-lg navbar-light">
            <div class="container box_1620">
                <!-- Brand and toggle get grouped for better mobile display -->
                <%
        try {
            Information info = InformationDAO.getInfor();
                %>
                <a class="navbar-brand logo_h" href="${pageContext.request.contextPath}/home">
                    <%
    String logoPath = (info.getLogoPath() != null) ? info.getLogoPath() : "${pageContext.request.contextPath}/asset/img/furryfriends-1.png";
                    %>
                    <img class="rounded-circle" src="<%= logoPath %>" alt="" width="80px" height="80px">
                </a>
                <%
} catch (Exception e) {
e.printStackTrace();
}
                %>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <!-- Collect the nav links, forms, and other content for toggling -->
                <div class="collapse navbar-collapse offset" id="navbarSupportedContent">
                    <ul class="nav navbar-nav menu_nav justify-content-center">
                        <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/home">Trang chủ</a>
                        <li class="nav-item">
                            <a class="nav-link" href="/findPostByPostType?postType=-1">
                                Tất cả các bài viết
                            </a>
                        </li>
                        <li class="nav-item submenu dropdown">
                            <a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                               aria-expanded="false">Bài viết chia sẻ</a>
                            <ul class="dropdown-menu">
                                <c:forEach var="t" items="${sessionScope.types}">
                                    <c:if test="${sessionScope.PostExchange != t.postTypeId}">
                                        <li class="nav-item">
                                            <a class="nav-link" href="/findPostByPostType?postType=${t.postTypeId}">
                                                ${t.postTypeName}
                                            </a>
                                        </li>
                                    </c:if>
                                </c:forEach>
                            </ul>
                        </li>
                        <li class="nav-item submenu dropdown">
                            <a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                               aria-expanded="false">Bài viết trao đổi</a>
                            <ul class="dropdown-menu">
                                <!-- list post type -->
                                <li class="nav-item">
                                    <a class="nav-link" href="/findPostByPostType?postType=${sessionScope.PostExchange}">
                                        Tất cả bài viết
                                    </a>
                                </li>
                                <c:forEach var="c" items="${sessionScope.categories}">
                                    <li class="nav-item">
                                        <a class="nav-link" href="findPostByPostTypeAndCategory?postType=${sessionScope.PostExchange}&categoryId=${c.categoryId}">
                                            ${c.categoryName}
                                        </a>
                                    </li>
                                </c:forEach>
                            </ul>
                        </li>
                        <c:if test="${sessionScope.account == null}">
                            <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/login">Đăng nhập</a></li>
                            </c:if>
                            <c:if test="${sessionScope.account != null}">
                            <li class="nav-item active submenu dropdown">
                                <a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                                   aria-expanded="false">${sessionScope.account.username}</a>
                                <ul class="dropdown-menu">
                                    <li class="nav-item"><a class="nav-link" href="userProfile">Thông tin cá nhân</a></li>
                                    <li class="nav-item"><a class="nav-link" href="listPost">Bài viết của tôi</a></li>
                                    <li class="nav-item"><a class="nav-link" href="createPost">Tạo bài viết</a></li>
                                    <li class="nav-item"><a class="nav-link" href="/logout">Đăng xuất</a></li>
                                </ul>
                            </li>
                        </c:if>
                    </ul>
                    <ul class="nav navbar-nav navbar-right navbar-social">
                        <li><a href="https://zalo.me" style="text-decoration: none; color: black"><span class="ti-themify-favicon"></span> Tin nhắn</a></li>
                    </ul>
                </div> 
            </div>
        </nav>
        <div class="homepage-search">
            <div style="position: relative">
                <form action="MainController" method="POST">
                    <input oninput="searchByTitle(this)" onblur="hideSearchResults()" onclick="showSearchResults()"  class="homepage-search-box" type="text" name="txtSearchValue" 
                           value="${param.txtSearchValue}" placeholder="Nhập nội dung tìm kiếm"/>
                    <button class="homepage-search-button" name="action" value="Home Page Search"><i class="fa-solid fa-magnifying-glass" style="color: #ffffff;"></i></button>
                </form>
                <div id="searchAjaxContent" class="search-ajax-result" >
                    <div class="each-ajax-result">
                        
                    </div>
                </div>
            </div>
        </div>
    </div>
</header>
<%@ include file="toast.jsp" %>
<!--================Header Menu Area =================-->
