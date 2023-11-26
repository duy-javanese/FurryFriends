<%-- 
    Document   : HomePage
    Created on : Sep 29, 2023, 9:21:48 PM
    Author     : dell
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="DAO.InformationDAO" %>
<%@ page import="Model.Information" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>FurryFriends</title>
        <link rel="icon" href="img/Fevicon.png" type="image/png">

        <link rel="stylesheet" href="asset/vendors/bootstrap/bootstrap.min.css">
        <link rel="stylesheet" href="asset/vendors/fontawesome/css/all.min.css">
        <link rel="stylesheet" href="asset/vendors/themify-icons/themify-icons.css">
        <link rel="stylesheet" href="asset/vendors/linericon/style.css">
        <link rel="stylesheet" href="asset/vendors/owl-carousel/owl.theme.default.min.css">
        <link rel="stylesheet" href="asset/vendors/owl-carousel/owl.carousel.min.css">

        <link rel="stylesheet" href="asset/css/style.css">
    </head>
    <body>
        <%@ include file="asset/includes/header.jsp" %>
        <main class="site-main">
            <!--================Hero Banner start =================-->  
            <section class="mb-30px">
                <div class="container">
                    <div class="hero-banner"
                         style="background:url(asset/img/banner/banner1.jpeg) left center no-repeat;
                         background-size: cover"
                         >
                        <div class="hero-banner__content">
                            <h1>FurryFriends</h1>
                            <h3>Blog & Trading</h3>
                        </div>
                    </div>
                </div>
            </section>
            <!--================Hero Banner end =================-->  

            <!--================ Blog slider start =================-->  
            <section>
                <div class="container">
                    <div class="owl-carousel owl-theme blog-slider">
                        <c:forEach items="${posts}" var="p">
                            <div class="card blog__slide text-center">
                                <div class="blog__slide__img">
                                    <img class="card-img rounded-0" src="${p.img}" style="object-fit: cover" alt="" width="150px" height="150px"/>
                                </div>
                                <div class="blog__slide__content" onclick="redirect('viewPostDetails?postId=${p.postId}')">
                                    <a class="blog__slide__label" href="#">${p.postType.postTypeName}</a>
                                    <h3><a href="#">${p.title}</a></h3>
                                    <p>${p.content.substring(0, 100)}...</p>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </section>
            <!--================ Blog slider end =================-->  

            <!--================ Start Blog Post Area =================-->
            <section class="blog-post-area section-margin mt-4">
                <div class="container">
                    <div class="row">
                        <div class="col-lg-8">
                            <!-- Post List-->
                            <c:forEach items="${posts}" var="p">
                                <div class="single-recent-blog-post">
                                    <div class="thumb">
                                        <img class="img-fluid" src="${p.img}" alt="" width="800px" height="300px">
                                        <ul class="thumb-info">
                                            <li><a href="#"><i class="ti-user"></i>${p.user.username}</a></li>
                                            <li><a href="#"><i class="ti-notepad"></i>${p.datePost}</a></li>
                                            <li><a href="#"><i class="ti-themify-favicon"></i>${p.comments.size()} Bình luận</a></li>
                                        </ul>
                                    </div>
                                    <div class="d-flex gap-5">
                                        <a href="likePost?postId=${p.postId}" class="ml-3
                                           <c:forEach items="${p.userLike}" var="ul">
                                               <c:if test="${ul.userId == sessionScope.account.userId}">
                                                   text-danger
                                               </c:if>
                                           </c:forEach>
                                           "><i class="ti-thumb-up mr-1"></i>Thích</a>
                                           
                                        <c:if test="${sessionScope.account.userId != p.user.userId}">
                                               <a href="SavePostController?postId=${p.postId}" class="ml-3
                                            <c:forEach items="${p.userSave}" var="uS">
                                                <c:if test="${uS.userId == sessionScope.account.userId}">
                                                    text-danger
                                                </c:if>
                                            </c:forEach>
                                   "><i class="ti-heart mr-1"></i>Lưu</a>
                                        </c:if>
                                        
                                        <c:if test="${sessionScope.account.userId != p.user.userId}">
                                            <a href="interestPost?postId=${p.postId}" class="ml-3
                                               <c:forEach items="${p.userInterested}" var="ui">
                                                   <c:if test="${ui.userId == sessionScope.account.userId}">
                                                       text-danger
                                                   </c:if>
                                               </c:forEach>
                                               "><i class="ti-face-smile mr-1"></i>Quan tâm</a>
                                        </c:if>
                                    </div>
                                    <div class="details mt-20">
                                        <a href="viewPostDetails?postId=${p.postId}">
                                            <h3>${p.title}</h3>
                                        </a>
                                        <!--                                        <p class="tag-list-inline">Tag: <a href="#">travel</a>, <a href="#">life style</a>, <a href="#">technology</a>, <a href="#">fashion</a></p>-->
                                        <p>${p.content.substring(0, 200)}...</p>
                                        <a class="button" href="viewPostDetails?postId=${p.postId}">Xem bài viết<i class="ti-arrow-right"></i></a>
                                    </div>
                                </div>
                            </c:forEach>


                            <div class="row">
                                <div class="col-lg-12">
                                    <nav class="blog-pagination justify-content-center d-flex">
                                        <ul class="pagination">
                                            <%--For displaying Previous link except for the 1st page --%>
                                            <c:if test="${currentPage != 1}">
                                                <li class="page-item">
                                                    <a class="page-link" href="home?page=${currentPage - 1}" aria-label="Previous">
                                                        <span aria-hidden="true">&laquo;</span>
                                                    </a>
                                                </li>
                                            </c:if>

                                            <%--For displaying Page numbers. The when condition does not display
                                                        a link for the current page--%>
                                            <c:forEach begin="1" end="${noOfPages}" var="i">
                                                <c:choose>
                                                    <c:when test="${currentPage eq i}"> 
                                                        <li class="page-item"><a class="page-link bg-light" href="#">${i}</a></li>
                                                        </c:when>
                                                        <c:otherwise>
                                                        <li class="page-item"><a class="page-link" href="home?page=${i}">${i}</a></li>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </c:forEach>

                                            <%--For displaying Next link --%>
                                            <c:if test="${currentPage lt noOfPages}">
                                                <li class="page-item">
                                                    <a class="page-link" href="home?page=${currentPage + 1}" aria-label="Next">
                                                        <span aria-hidden="true">&raquo;</span>
                                                    </a>
                                                </li>
                                            </c:if>
                                        </ul>
                                    </nav>
                                </div>
                            </div>
                        </div>

                        <!-- Start Blog Post Siddebar -->
                        <%
        try {
            Information info = InformationDAO.getInfor();
                        %>
                        <div class="col-lg-4 sidebar-widgets">
                            <div class="widget-wrap">
                                <div class="single-sidebar-widget newsletter-widget">
                                    <h4 class="single-sidebar-widget__title">Thông báo</h4>
                                    <div class="form-group mt-30">
                                        <div class="col-autos">
                                            <strong><%= (info.getNotification() != null) ? info.getNotification() : "Không có thông báo" %></strong>
                                        </div>
                                    </div>
                                </div>


                                <div class="single-sidebar-widget post-category-widget">
                                    <h4 class="single-sidebar-widget__title">Loại bài viết</h4>
                                    <ul class="cat-list mt-20">
                                        <c:forEach items="${sessionScope.types}" var="t">
                                            <li>
                                                <a href="/findPostByPostType?postType=${t.postTypeId}" class="d-flex justify-content-between">
                                                    <p>${t.postTypeName}</p>
                                                </a>
                                            </li>
                                        </c:forEach>
                                    </ul>
                                </div>

                                
                            </div>
                        </div>
                        <%
        } catch (Exception e) {
            e.printStackTrace();
        }
                        %>
                    </div>
                    <!-- End Blog Post Siddebar -->
                </div>
            </section>
            <!--================ End Blog Post Area =================-->
        </main>
        <%@ include file="asset/includes/footer.jsp" %>
        <script src="asset/vendors/jquery/jquery-3.2.1.min.js"></script>
        <script src="asset/vendors/bootstrap/bootstrap.bundle.min.js"></script>
        <script src="asset/vendors/owl-carousel/owl.carousel.min.js"></script>
        <script src="asset/js/jquery.ajaxchimp.min.js"></script>
        <script src="asset/js/mail-script.js"></script>
        <script src="asset/js/main.js"></script>
    </body>
</html>
