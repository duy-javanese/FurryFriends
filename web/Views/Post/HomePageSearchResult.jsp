<%-- 
    Document   : HomePageSearchResult
    Created on : Nov 19, 2023, 2:48:10 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Kết quả tìm kiếm</title>
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
        <c:set var="result" value ="${requestScope.SEARCH_POST_RESULT}"/>
        <main class="site-main">
            <!--================ Start Blog Post Area =================-->
        <section class="blog-post-area section-margin mt-4">
            <div class="container">
                <div class="row">
                    <div class="col-lg-8">
                         <c:if test="${not empty result}">
                            <h1>Kết quả tìm kiếm với tiêu đề : "${param.txtSearchValue}"</h1>
                        <!--Result Post List-->
                        <c:forEach items="${result}" var="p">
                                <div class="single-recent-blog-post">
                                    <div class="thumb">
                                        <img class="img-fluid" src="${p.img}" alt="" width="800px" height="300px">
                                        <ul class="thumb-info">
                                            <li><a href="#"><i class="ti-user"></i>${p.user.role.roleName}</a></li>
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
                                           "><i class="ti-heart mr-1"></i>Thích</a>
                                        <c:if test="${p.postType.postTypeId == 4}">
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
                                        <c:if test="${p.postType.postTypeId == 4}">
                                            <div class="d-flex gap-2">
                                                <c:if test="${p.exchange.isFree == false}">
                                                    <p style="font-weight: bold">Giá bán: </p><p style="color: gold; font-weight: bold">${p.exchange.price}đ</p>
                                                </c:if>
                                                <c:if test="${p.exchange.isFree == true}">
                                                    <div class="d-flex justify-content-center">
                                                        <p style="font-weight: bold; margin-top: 3%">Giá bán: </p>
                                                        <div class="rounded-pill bg-success p-2 ml-2" style="text-align: center; border-radius: 25px">
                                                            <span style="color: white">Miễn phí</span>
                                                        </div>
                                                    </div>
                                                </c:if>
                                            </div>
                                        </c:if>
                                        <p>${p.content.substring(0, 200)}...</p>
                                        <a class="button" href="viewPostDetails?postId=${p.postId}">Xem bài viết<i class="ti-arrow-right"></i></a>
                                    </div>
                                </div>
                            </c:forEach>
                         </c:if>
                        <c:if test="${empty result}">
                        <h1>Không có kết quả với nội dung bạn đã nhập :"${param.txtSearchValue}"</h1>
                    </c:if>
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
            </div>
        </section>
        <!--================ End Blog Post Area =================-->
        </main>
        <%@ include file="../../asset/includes/footer.jsp" %>
        <script src="asset/vendors/jquery/jquery-3.2.1.min.js"></script>
        <script src="asset/vendors/bootstrap/bootstrap.bundle.min.js"></script>
        <script src="asset/vendors/owl-carousel/owl.carousel.min.js"></script>
        <script src="asset/js/jquery.ajaxchimp.min.js"></script>
        <script src="asset/js/mail-script.js"></script>
        <script src="asset/js/main.js"></script>
    </body>
</html>
