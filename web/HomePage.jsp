<%-- 
    Document   : HomePage
    Created on : Sep 29, 2023, 9:21:48 PM
    Author     : dell
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                            <h3>Blog & Trading</h3>
                            <h1>Pet trading platform</h1>
                            <h4>October 1, 2023</h4>
                        </div>
                    </div>
                </div>
            </section>
            <!--================Hero Banner end =================-->  

            <!--================ Blog slider start =================-->  
            <section>
                <div class="container">
                    <div class="owl-carousel owl-theme blog-slider">
                        <div class="card blog__slide text-center">
                            <div class="blog__slide__img">
                                <img class="card-img rounded-0" src="asset/img/banner/banner1.jpeg" alt=""/>
                            </div>
                            <div class="blog__slide__content">
                                <a class="blog__slide__label" href="#">Fashion</a>
                                <h3><a href="#">New york fashion week's continued the evolution</a></h3>
                                <p>2 days ago</p>
                            </div>
                        </div>
                        <div class="card blog__slide text-center">
                            <div class="blog__slide__img">
                                <img class="card-img rounded-0" src="asset/img/blog/blog-slider/blog-slide2.png" alt="">
                            </div>
                            <div class="blog__slide__content">
                                <a class="blog__slide__label" href="#">Fashion</a>
                                <h3><a href="#">New york fashion week's continued the evolution</a></h3>
                                <p>2 days ago</p>
                            </div>
                        </div>
                        <div class="card blog__slide text-center">
                            <div class="blog__slide__img">
                                <img class="card-img rounded-0" src="asset/img/blog/blog-slider/blog-slide3.png" alt="">
                            </div>
                            <div class="blog__slide__content">
                                <a class="blog__slide__label" href="#">Fashion</a>
                                <h3><a href="#">New york fashion week's continued the evolution</a></h3>
                                <p>2 days ago</p>
                            </div>
                        </div>
                        <div class="card blog__slide text-center">
                            <div class="blog__slide__img">
                                <img class="card-img rounded-0" src="asset/img/blog/blog-slider/blog-slide1.png" alt="">
                            </div>
                            <div class="blog__slide__content">
                                <a class="blog__slide__label" href="#">Fashion</a>
                                <h3><a href="#">New york fashion week's continued the evolution</a></h3>
                                <p>2 days ago</p>
                            </div>
                        </div>
                        <div class="card blog__slide text-center">
                            <div class="blog__slide__img">
                                <img class="card-img rounded-0" src="asset/img/blog/blog-slider/blog-slide2.png" alt="">
                            </div>
                            <div class="blog__slide__content">
                                <a class="blog__slide__label" href="#">Fashion</a>
                                <h3><a href="#">New york fashion week's continued the evolution</a></h3>
                                <p>2 days ago</p>
                            </div>
                        </div>
                        <div class="card blog__slide text-center">
                            <div class="blog__slide__img">
                                <img class="card-img rounded-0" src="asset/img/blog/blog-slider/blog-slide3.png" alt="">
                            </div>
                            <div class="blog__slide__content">
                                <a class="blog__slide__label" href="#">Fashion</a>
                                <h3><a href="#">New york fashion week's continued the evolution</a></h3>
                                <p>2 days ago</p>
                            </div>
                        </div>
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
                                            <li><a href="#"><i class="ti-user"></i>${p.user.role.roleName}</a></li>
                                            <li><a href="#"><i class="ti-notepad"></i>${p.datePost}</a></li>
                                            <li><a href="#"><i class="ti-themify-favicon"></i>2 Bình luận</a></li>
                                        </ul>
                                    </div>
                                    <div class="d-flex gap-5">
                                        <a href="likePost?postId=${p.postId}" class="ml-3"><i class="ti-heart"></i>Thích</a>
                                        <a href="interestPost?postId=${p.postId}" class="ml-3"><i class="ti-face-smile"></i>Quan tâm</a>
                                    </div>
                                    <div class="details mt-20">
                                        <a href="viewPostDetails?postId=${p.postId}">
                                            <h3>${p.title}</h3>
                                        </a>
                                        <!--                                        <p class="tag-list-inline">Tag: <a href="#">travel</a>, <a href="#">life style</a>, <a href="#">technology</a>, <a href="#">fashion</a></p>-->
                                        <p>${p.content.substring(0, 200)}...</p>
                                        <a class="button" href="viewPostDetails?postId=${p.postId}">Read More <i class="ti-arrow-right"></i></a>
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
                                                    <a class="page-link" href="img?page=${currentPage - 1}" aria-label="Previous">
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
                                                        <li class="page-item"><a class="page-link" href="img?page=${i}">${i}</a></li>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </c:forEach>

                                            <%--For displaying Next link --%>
                                            <c:if test="${currentPage lt noOfPages}">
                                                <li class="page-item">
                                                    <a class="page-link" href="img?page=${currentPage + 1}" aria-label="Next">
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
                        <div class="col-lg-4 sidebar-widgets">
                            <div class="widget-wrap">
                                <div class="single-sidebar-widget newsletter-widget">
                                    <h4 class="single-sidebar-widget__title">Newsletter</h4>
                                    <div class="form-group mt-30">
                                        <div class="col-autos">
                                            <input type="text" class="form-control" id="inlineFormInputGroup" placeholder="Enter email" onfocus="this.placeholder = ''"
                                                   onblur="this.placeholder = 'Enter email'">
                                        </div>
                                    </div>
                                    <button class="bbtns d-block mt-20 w-100">Subcribe</button>
                                </div>


                                <div class="single-sidebar-widget post-category-widget">
                                    <h4 class="single-sidebar-widget__title">Post Type</h4>
                                    <ul class="cat-list mt-20">
                                        <li>
                                            <a href="#" class="d-flex justify-content-between">
                                                <p>Technology</p>
                                                <p>(03)</p>
                                            </a>
                                        </li>
                                        <li>
                                            <a href="#" class="d-flex justify-content-between">
                                                <p>Software</p>
                                                <p>(09)</p>
                                            </a>
                                        </li>
                                        <li>
                                            <a href="#" class="d-flex justify-content-between">
                                                <p>Lifestyle</p>
                                                <p>(12)</p>
                                            </a>
                                        </li>
                                        <li>
                                            <a href="#" class="d-flex justify-content-between">
                                                <p>Shopping</p>
                                                <p>(02)</p>
                                            </a>
                                        </li>
                                        <li>
                                            <a href="#" class="d-flex justify-content-between">
                                                <p>Food</p>
                                                <p>(10)</p>
                                            </a>
                                        </li>
                                    </ul>
                                </div>

                                <div class="single-sidebar-widget popular-post-widget">
                                    <h4 class="single-sidebar-widget__title">Popular Post</h4>
                                    <div class="popular-post-list">
                                        <div class="single-post-list">
                                            <div class="thumb">
                                                <img class="card-img rounded-0" src="asset/img/blog/thumb/thumb1.png" alt="">
                                                <ul class="thumb-info">
                                                    <li><a href="#">Adam Colinge</a></li>
                                                    <li><a href="#">Dec 15</a></li>
                                                </ul>
                                            </div>
                                            <div class="details mt-20">
                                                <a href="blog-single.html">
                                                    <h6>Accused of assaulting flight attendant miktake alaways</h6>
                                                </a>
                                            </div>
                                        </div>
                                        <div class="single-post-list">
                                            <div class="thumb">
                                                <img class="card-img rounded-0" src="asset/img/blog/thumb/thumb2.png" alt="">
                                                <ul class="thumb-info">
                                                    <li><a href="#">Adam Colinge</a></li>
                                                    <li><a href="#">Dec 15</a></li>
                                                </ul>
                                            </div>
                                            <div class="details mt-20">
                                                <a href="blog-single.html">
                                                    <h6>Tennessee outback steakhouse the
                                                        worker diagnosed</h6>
                                                </a>
                                            </div>
                                        </div>
                                        <div class="single-post-list">
                                            <div class="thumb">
                                                <img class="card-img rounded-0" src="asset/img/blog/thumb/thumb3.png" alt="">
                                                <ul class="thumb-info">
                                                    <li><a href="#">Adam Colinge</a></li>
                                                    <li><a href="#">Dec 15</a></li>
                                                </ul>
                                            </div>
                                            <div class="details mt-20">
                                                <a href="blog-single.html">
                                                    <h6>Tennessee outback steakhouse the
                                                        worker diagnosed</h6>
                                                </a>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="single-sidebar-widget tag_cloud_widget">
                                    <h4 class="single-sidebar-widget__title">Popular Post</h4>
                                    <ul class="list">
                                        <li>
                                            <a href="#">project</a>
                                        </li>
                                        <li>
                                            <a href="#">love</a>
                                        </li>
                                        <li>
                                            <a href="#">technology</a>
                                        </li>
                                        <li>
                                            <a href="#">travel</a>
                                        </li>
                                        <li>
                                            <a href="#">software</a>
                                        </li>
                                        <li>
                                            <a href="#">life style</a>
                                        </li>
                                        <li>
                                            <a href="#">design</a>
                                        </li>
                                        <li>
                                            <a href="#">illustration</a>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
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
