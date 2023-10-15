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
                    <div class="hero-banner">
                        <div class="hero-banner__content">
                            <h3>Tours & Travels</h3>
                            <h1>Amazing Places on earth</h1>
                            <h4>December 12, 2018</h4>
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
                            <div class="single-recent-blog-post">
                                <div class="thumb">
                                    <img class="img-fluid" src="asset/img/blog/blog1.png" alt="">
                                    <ul class="thumb-info">
                                        <li><a href="#"><i class="ti-user"></i>Admin</a></li>
                                        <li><a href="#"><i class="ti-notepad"></i>January 12,2019</a></li>
                                        <li><a href="#"><i class="ti-themify-favicon"></i>2 Comments</a></li>
                                    </ul>
                                </div>
                                <div class="details mt-20">
                                    <a href="blog-single.html">
                                        <h3>Woman claims husband wants to name baby girl
                                            after his ex-lover sparking.</h3>
                                    </a>
                                    <p class="tag-list-inline">Tag: <a href="#">travel</a>, <a href="#">life style</a>, <a href="#">technology</a>, <a href="#">fashion</a></p>
                                    <p>Over yielding doesn't so moved green saw meat hath fish he him from given yielding lesser cattle were fruitful lights. Given let have, lesser their made him above gathered dominion sixth. Creeping deep said can't called second. Air created seed heaven sixth created living</p>
                                    <a class="button" href="#">Read More <i class="ti-arrow-right"></i></a>
                                </div>
                            </div>

                            <div class="single-recent-blog-post">
                                <div class="thumb">
                                    <img class="img-fluid" src="asset/img/blog/blog2.png" alt="">
                                    <ul class="thumb-info">
                                        <li><a href="#"><i class="ti-user"></i>Admin</a></li>
                                        <li><a href="#"><i class="ti-notepad"></i>January 12,2019</a></li>
                                        <li><a href="#"><i class="ti-themify-favicon"></i>2 Comments</a></li>
                                    </ul>
                                </div>
                                <div class="details mt-20">
                                    <a href="blog-single.html">
                                        <h3>Woman claims husband wants to name baby girl
                                            after his ex-lover sparking.</h3>
                                    </a>
                                    <p class="tag-list-inline">Tag: <a href="#">travel</a>, <a href="#">life style</a>, <a href="#">technology</a>, <a href="#">fashion</a></p>
                                    <p>Over yielding doesn't so moved green saw meat hath fish he him from given yielding lesser cattle were fruitful lights. Given let have, lesser their made him above gathered dominion sixth. Creeping deep said can't called second. Air created seed heaven sixth created living</p>
                                    <a class="button" href="#">Read More <i class="ti-arrow-right"></i></a>
                                </div>
                            </div>

                            <div class="single-recent-blog-post">
                                <div class="thumb">
                                    <img class="img-fluid" src="asset/img/blog/blog3.png" alt="">
                                    <ul class="thumb-info">
                                        <li><a href="#"><i class="ti-user"></i>Admin</a></li>
                                        <li><a href="#"><i class="ti-notepad"></i>January 12,2019</a></li>
                                        <li><a href="#"><i class="ti-themify-favicon"></i>2 Comments</a></li>
                                    </ul>
                                </div>
                                <div class="details mt-20">
                                    <a href="blog-single.html">
                                        <h3>Tourist deaths in Costa Rica jeopardize safe dest
                                            ination reputation all time. </h3>
                                    </a>
                                    <p class="tag-list-inline">Tag: <a href="#">travel</a>, <a href="#">life style</a>, <a href="#">technology</a>, <a href="#">fashion</a></p>
                                    <p>Over yielding doesn't so moved green saw meat hath fish he him from given yielding lesser cattle were fruitful lights. Given let have, lesser their made him above gathered dominion sixth. Creeping deep said can't called second. Air created seed heaven sixth created living</p>
                                    <a class="button" href="#">Read More <i class="ti-arrow-right"></i></a>
                                </div>
                            </div>

                            <div class="single-recent-blog-post">
                                <div class="thumb">
                                    <img class="img-fluid" src="asset/img/blog/blog4.png" alt="">
                                    <ul class="thumb-info">
                                        <li><a href="#"><i class="ti-user"></i>Admin</a></li>
                                        <li><a href="#"><i class="ti-notepad"></i>January 12,2019</a></li>
                                        <li><a href="#"><i class="ti-themify-favicon"></i>2 Comments</a></li>
                                    </ul>
                                </div>
                                <div class="details mt-20">
                                    <a href="blog-single.html">
                                        <h3>Tourist deaths in Costa Rica jeopardize safe dest
                                            ination reputation all time.  </h3>
                                    </a>
                                    <p class="tag-list-inline">Tag: <a href="#">travel</a>, <a href="#">life style</a>, <a href="#">technology</a>, <a href="#">fashion</a></p>
                                    <p>Over yielding doesn't so moved green saw meat hath fish he him from given yielding lesser cattle were fruitful lights. Given let have, lesser their made him above gathered dominion sixth. Creeping deep said can't called second. Air created seed heaven sixth created living</p>
                                    <a class="button" href="#">Read More <i class="ti-arrow-right"></i></a>
                                </div>
                            </div>



                            <div class="row">
                                <div class="col-lg-12">
                                    <nav class="blog-pagination justify-content-center d-flex">
                                        <ul class="pagination">
                                            <li class="page-item">
                                                <a href="#" class="page-link" aria-label="Previous">
                                                    <span aria-hidden="true">
                                                        <i class="ti-angle-left"></i>
                                                    </span>
                                                </a>
                                            </li>
                                            <li class="page-item active"><a href="#" class="page-link">1</a></li>
                                            <li class="page-item"><a href="#" class="page-link">2</a></li>
                                            <li class="page-item">
                                                <a href="#" class="page-link" aria-label="Next">
                                                    <span aria-hidden="true">
                                                        <i class="ti-angle-right"></i>
                                                    </span>
                                                </a>
                                            </li>
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
                                    <h4 class="single-sidebar-widget__title">Catgory</h4>
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
