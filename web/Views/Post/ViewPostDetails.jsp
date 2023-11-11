<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <link href="asset/css/Login/layout.css" rel="stylesheet" type="text/css"/>
        <link href="asset/css/Login/login.css" rel="stylesheet" type="text/css"/>
        <link href="asset/css/Login/layout.css" rel="stylesheet" type="text/css"/>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ"
              crossorigin="anonymous">
        <script src="https://kit.fontawesome.com/8d39de38b8.js" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="asset/vendors/bootstrap/bootstrap.min.css">
        <link rel="stylesheet" href="asset/vendors/fontawesome/css/all.min.css">
        <link rel="stylesheet" href="asset/vendors/themify-icons/themify-icons.css">
        <link rel="stylesheet" href="asset/vendors/linericon/style.css">
        <link rel="stylesheet" href="asset/vendors/owl-carousel/owl.theme.default.min.css">
        <link rel="stylesheet" href="asset/vendors/owl-carousel/owl.carousel.min.css">

        <link rel="stylesheet" href="asset/css/style.css">
    </head>

    <body>
        <%@ include file="../../asset/includes/header.jsp" %>

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

        <!-- The Modal -->
        <div id="myModal" class="modal">
            <div class="modal-content">
                <span class="close" id="closeModalBtn">&times;</span>
                <form action="reportPost" method="post">
                    <input value="${post.postId}" name="postId" type="hidden">
                    <select name="reportId" required="">
                        <option selected disabled>Lựa chọn lý do báo cáo</option>
                        <c:forEach items="${reports}" var="r">
                            <option value="${r.reportContentId}">${r.reportContent}</option>
                        </c:forEach>
                    </select>
                    <br>
                    <label>Lý do:</label><br>
                    <textarea rows="5" cols="60" name="reason"></textarea>
                    <button class="btn btn-outline-danger">Báo cáo</button>
                </form>
            </div>
        </div>

        <!--================ Start Blog Post Area =================-->
        <section class="blog-post-area section-margin">
            <div class="container">
                <div class="row">
                    <div class="col-lg-8">
                        <div class="main_blog_details">
                            <img class="img-fluid" src="${post.img}" alt="" width="800px" height="300px">
                            <a href="#"><h4>${post.title}</h4></a>
                            <div class="user_details">
                                <div class="float-left">
                                    <c:if test="${post.postType.postTypeId == 4}">
                                        <a href="#">${post.category.categoryName}</a>
                                    </c:if>
                                    <a href="#">${post.postType.postTypeName}</a>
                                </div>
                                <div class="float-right mt-sm-0 mt-3">
                                    <div class="media">
                                        <div class="media-body">
                                            <h5>${post.user.username}</h5>
                                            <p>${post.datePost}</p>
                                        </div>
                                        <div class="d-flex">
                                            <img width="42" height="42" src="${pageContext.request.contextPath}/asset/img/paw.png" alt="">
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <p>
                                ${post.content}
                            </p>
                            <div class="d-flex">
                                <a href="likePost?postId=${post.postId}" class="ml-3"><i class="ti-heart mr-1"></i>Thích</a>
                                <a href="interestPost?postId=${post.postId}" class="ml-3"><i class="ti-face-smile mr-1"></i>Quan tâm</a>
                            </div>
                            <div class="d-flex justify-content-end">
                                <button id="openModalBtn" class="ml-3 btn btn-outline-danger"><i class="ti-flag-alt mr-1"></i>Báo cáo bài viết</button>
                            </div>
                            <div class="news_d_footer flex-column flex-sm-row">
                                <a href="#"><span class="align-middle"><i class="ti-heart"></i></span>${totalLike}người thích</a>
                                <a class="justify-content-sm-center ml-sm-auto mt-sm-0 mt-2" href="#"><span class="align-middle"><i class="ti-themify-favicon"></i></span>${post.comments.size()} bình luận</a>
                                <div class="news_socail ml-sm-auto mt-sm-0 mt-2">
                                    <a href="#"><i class="fab fa-facebook-f"></i></a>
                                    <a href="#"><i class="fab fa-twitter"></i></a>
                                    <a href="#"><i class="fab fa-dribbble"></i></a>
                                    <a href="#"><i class="fab fa-behance"></i></a>
                                </div>
                            </div>
                        </div>


                        <!--                        <div class="navigation-area">
                                                    <div class="row">
                                                        <div class="col-lg-6 col-md-6 col-12 nav-left flex-row d-flex justify-content-start align-items-center">
                                                            <div class="thumb">
                                                                <a href="#"><img class="img-fluid" src="asset/img/blog/prev.jpg" alt=""></a>
                                                            </div>
                                                            <div class="arrow">
                                                                <a href="#"><span class="lnr text-white lnr-arrow-left"></span></a>
                                                            </div>
                                                            <div class="detials">
                                                                <p>Prev Post</p>
                                                                <a href="#"><h4>A Discount Toner</h4></a>
                                                            </div>
                                                        </div>
                                                        <div class="col-lg-6 col-md-6 col-12 nav-right flex-row d-flex justify-content-end align-items-center">
                                                            <div class="detials">
                                                                <p>Next Post</p>
                                                                <a href="#"><h4>Cartridge Is Better</h4></a>
                                                            </div>
                                                            <div class="arrow">
                                                                <a href="#"><span class="lnr text-white lnr-arrow-right"></span></a>
                                                            </div>
                                                            <div class="thumb">
                                                                <a href="#"><img class="img-fluid" src="asset/img/blog/next.jpg" alt=""></a>
                                                            </div>										
                                                        </div>									
                                                    </div>
                                                </div>-->

                        <div class="comments-area">
                            <h4>${post.comments.size()} Comments</h4>
                            <div class="comment-list">
                                <c:forEach var="c" items="${post.comments}">
                                    <div class="single-comment justify-content-between d-flex">
                                        <div class="user justify-content-between d-flex">
                                            <div class="thumb">
                                                <img src="${pageContext.request.contextPath}/asset/img/paw.png" alt="" height="42" width="42">
                                            </div>
                                            <div class="desc">
                                                <h5><a href="#">${c.user.username}</a></h5>
                                                <p class="date">${c.createdDate}</p>
                                                <p class="comment">
                                                    ${c.commentValue}
                                                </p>
                                            </div>
                                        </div>
                                        <!--                                    <div class="reply-btn">
                                                                                <a href="" class="btn-reply text-uppercase">reply</a> 
                                                                            </div>-->
                                    </div>
                                </c:forEach>
                            </div>	
                            <!--                            <div class="comment-list left-padding">
                                                            <div class="single-comment justify-content-between d-flex">
                                                                <div class="user justify-content-between d-flex">
                                                                    <div class="thumb">
                                                                        <img src="asset/img/blog/c2.jpg" alt="">
                                                                    </div>
                                                                    <div class="desc">
                                                                        <h5><a href="#">Elsie Cunningham</a></h5>
                                                                        <p class="date">December 4, 2017 at 3:12 pm </p>
                                                                        <p class="comment">
                                                                            Never say goodbye till the end comes!
                                                                        </p>
                                                                    </div>
                                                                </div>
                                                                <div class="reply-btn">
                                                                    <a href="" class="btn-reply text-uppercase">reply</a> 
                                                                </div>
                                                            </div>
                                                        </div>		                                             				-->
                        </div>
                        <div class="comment-form">
                            <h4>Tạo bình luận</h4>
                            <form action="comment" method="post">
                                <input value="${post.postId}" name="postId" type="hidden">
                                <div class="form-group">
                                    <textarea rows="5" cols="100" name="comment" placeholder="Bình luận" required=""></textarea>
                                </div>
                                <div class="d-flex justify-content-end">
                                    <button class="btn-outline-dark btn">Tạo bình luận</button>
                                </div>
                            </form>
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

        <%@ include file="../../asset/includes/footer.jsp" %>
        <!--Script go to top, copy to every single page-->
        <script>
            //Get the button
            let mybutton = document.getElementById("btn-back-to-top");

            // When the user scrolls down 20px from the top of the document, show the button
            window.onscroll = function () {
                scrollFunction();
            };

            function scrollFunction() {
                if (document.body.scrollTop > 20 || document.documentElement.scrollTop > 20) {
                    mybutton.style.display = "block";
                } else {
                    mybutton.style.display = "none";
                }
            }
            // When the user clicks on the button, scroll to the top of the document
            mybutton.addEventListener("click", backToTop);

            function backToTop() {
                document.body.scrollTop = 0;
                document.documentElement.scrollTop = 0;
            }

        </script>
        <script>
            // Get references to the modal and the trigger element
            var modal = document.getElementById("myModal");
            var openModalBtn = document.getElementById("openModalBtn");
            var closeModalBtn = document.getElementById("closeModalBtn");

// Open the modal when the trigger element is clicked
            openModalBtn.addEventListener("click", function () {
                modal.style.display = "block";
            });

// Close the modal when the close button is clicked
            closeModalBtn.addEventListener("click", function () {
                modal.style.display = "none";
            });

// Close the modal if the user clicks outside the modal content
            window.addEventListener("click", function (event) {
                if (event.target === modal) {
                    modal.style.display = "none";
                }
            });
        </script>
    </body>

</html>