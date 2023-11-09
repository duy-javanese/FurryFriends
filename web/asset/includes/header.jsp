<%-- 
    Document   : header
    Created on : Oct 1, 2023, 9:31:26 PM
    Author     : dell
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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

    </style>
</head>
<!--================Header Menu Area =================-->
<header class="header_area">
    <div class="main_menu">
        <nav class="navbar navbar-expand-lg navbar-light">
            <div class="container box_1620">
                <!-- Brand and toggle get grouped for better mobile display -->
                <a class="navbar-brand logo_h" href="${pageContext.request.contextPath}/home">
                    <img class="rounded-circle" src="${pageContext.request.contextPath}/asset/img/furryfriends-1.png" alt="" width="80px" height="80px">
                </a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <!-- Collect the nav links, forms, and other content for toggling -->
                <div class="collapse navbar-collapse offset" id="navbarSupportedContent">
                    <ul class="nav navbar-nav menu_nav justify-content-center">
                        <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/home">Trang chủ</a>
                        <li class="nav-item submenu dropdown">
                            <a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                               aria-expanded="false">Bài viết chia sẻ</a>
                            <ul class="dropdown-menu">
                                <!-- list post type -->
                                <li class="nav-item">
                                    <a class="nav-link" href="/findPostByPostType?postType=-1">
                                        Tất cả bài viết
                                    </a>
                                </li>
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
                        <li class="nav-item"><a class="nav-link" href="listProduct">Phụ kiện Chó Mèo</a>

                            <!--                        <li class="nav-item"><a class="nav-link" href="contact.html">Contact</a></li>-->
                            <c:if test="${sessionScope.account == null}">
                            <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/login">Đăng nhập</a></li>
                            </c:if>
                            <c:if test="${sessionScope.account != null}">
                            <li class="nav-item active submenu dropdown">
                                <a href="#" class="nav-link dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                                   aria-expanded="false">${sessionScope.account.username}</a>
                                <ul class="dropdown-menu">
                                    <li class="nav-item"><a class="nav-link" href="/userProfile">Thông tin cá nhân</a></li>
                                    <li class="nav-item"><a class="nav-link" href="listPost">Bài viết của tôi</a></li>
                                    <li class="nav-item"><a class="nav-link" href="createPost">Tạo bài viết</a></li>
                                    <li class="nav-item"><a class="nav-link" href="/logout">Đăng xuất</a></li>
                                </ul>
                            </li>
                        </c:if>
                    </ul>
                    <ul class="nav navbar-nav navbar-right navbar-social">
                        <li><a href="https://zalo.me" style="text-decoration: none; color: black"><span class="ti-themify-favicon"></span>Tin nhắn</a></li>
                    </ul>
                </div> 
            </div>
        </nav>
    </div>
</header>
<%@ include file="toast.jsp" %>
<!--================Header Menu Area =================-->
