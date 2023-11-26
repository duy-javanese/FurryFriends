<%-- 
    Document   : ViewOtherUserProfile
    Created on : Nov 24, 2023, 7:50:25 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Thông tin người dùng</title>
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
        
        <style>
            .user-infor-header{
                display: flex;
                align-items: center;
                margin-top: 30px;
                flex-direction: column;
                border-bottom: 1px solid;
            }
            .user-post-type-box{
                display: flex;
                justify-content: space-evenly;
                padding: 25px;
            }
            .user-profile-menu{
                font-weight: bold;
                background: white;
                border : 0;
            }
            .user-profile-menu:hover{
                color: #FF8800;
            }
        </style>
    </head>
    <body>
        <%@ include file="../../asset/includes/header.jsp" %>
        <c:set var="user" value ="${requestScope.OTHER_USER_PROFILE}"/>
        <c:set var="posts" value ="${requestScope.OTHER_USER_POST}"/>
        <c:set var="totalPost" value ="${requestScope.TOTAL_POST}"/>
        <c:set var="exchanges" value ="${requestScope.OTHER_USER_EXCHANGE}"/>
        <c:set var="totalExchange" value ="${requestScope.TOTAL_EXCHANGE}"/>
        <main class="site-main">
            <!--================ Start Blog Post Area =================-->
            <section class="blog-post-area section-margin mt-4">
                <div class="container">
                    <div class="user-infor-header">
                        <img class="rounded-circle" src="${pageContext.request.contextPath}/asset/img/paw.png" alt="" width="80px" height="80px">
                        <h3>${user.username}</h3>
                        <p>${user.address}</p>
                    </div>
                        <div>
                                <form action="MainController" method="POST">
                                    <div class="user-post-type-box">
                                        <input type="hidden" name="userId" value="${user.userId}">
                                        <button class="user-profile-menu" name="action" value="viewUserListPost">Bài viết đang hiển thị (${totalPost})</button>
                                        <button class="user-profile-menu" name="action" value="viewUserListExchange">Bài trao đổi đang hiển thị (${totalExchange})</button>
                                    </div>
                                </form>
                            
                            <div class="col-md-8 offset-md-2">
                                
                                <!-- Post List-->
                                <c:forEach items="${posts}" var="c">
                                <div class="single-recent-blog-post">
                                    <div class="thumb">
                                        <img class="img-fluid" src="${c.img}" alt="" width="800px" height="300px">
                                        <ul class="thumb-info">
                                            <li><a href="#"><i class="ti-user"></i>${c.user.role.roleName}</a></li>
                                            <li><a href="#"><i class="ti-notepad"></i>${c.datePost}</a></li>
                                            <li><a href="#"><i class="ti-themify-favicon"></i>${c.comments.size()} Bình luận</a></li>
                                        </ul>
                                    </div>
                                    <div class="d-flex gap-5">
                                        <a href="likePost?postId=${c.postId}" class="ml-3
                                           <c:forEach items="${c.userLike}" var="ul">
                                               <c:if test="${ul.userId == sessionScope.account.userId}">
                                                   text-danger
                                               </c:if>
                                           </c:forEach>
                                           "><i class="ti-thumb-up mr-1"></i>Thích</a>
                                           
                                        <c:if test="${sessionScope.account.userId != c.user.userId}">
                                               <a href="SavePostController?postId=${c.postId}" class="ml-3
                                            <c:forEach items="${c.userSave}" var="uS">
                                                <c:if test="${uS.userId == sessionScope.account.userId}">
                                                    text-danger
                                                </c:if>
                                            </c:forEach>
                                   "><i class="ti-heart mr-1"></i>Lưu</a>
                                        </c:if>
                                    </div>
                                    <div class="details mt-20">
                                        <a href="viewPostDetails?postId=${c.postId}">
                                            <h3>${c.title}</h3>
                                        </a>
                                        <!--                                        <p class="tag-list-inline">Tag: <a href="#">travel</a>, <a href="#">life style</a>, <a href="#">technology</a>, <a href="#">fashion</a></p>-->
                                        <p>${c.content.substring(0, 200)}...</p>
                                        <a class="button" href="viewPostDetails?postId=${c.postId}">Xem bài viết<i class="ti-arrow-right"></i></a>
                                    </div>
                                </div>
                            </c:forEach>
                                
                                <!-- Exchange List -->
                                <c:forEach items="${exchanges}" var="e">
                                <div class="single-recent-blog-post">
                                    <div class="thumb">
                                        <img class="img-fluid" src="${e.post.img}" alt="" width="800px" height="300px">
                                        <ul class="thumb-info">
                                            <li><a href="#"><i class="ti-user"></i>${e.post.user.role.roleName}</a></li>
                                            <li><a href="#"><i class="ti-notepad"></i>${e.post.datePost}</a></li>
                                            <li><a href="#"><i class="ti-themify-favicon"></i>${e.post.comments.size()} Bình luận</a></li>
                                        </ul>
                                    </div>
                                    <div class="d-flex gap-5">
                                        <a href="likePost?postId=${e.post.postId}" class="ml-3
                                           <c:forEach items="${e.post.userLike}" var="ul">
                                               <c:if test="${ul.userId == sessionScope.account.userId}">
                                                   text-danger
                                               </c:if>
                                           </c:forEach>
                                           "><i class="ti-thumb-up mr-1"></i>Thích</a>
                                           
                                        <c:if test="${sessionScope.account.userId != e.post.user.userId}">
                                               <a href="SavePostController?postId=${e.post.postId}" class="ml-3
                                            <c:forEach items="${e.post.userSave}" var="uS">
                                                <c:if test="${uS.userId == sessionScope.account.userId}">
                                                    text-danger
                                                </c:if>
                                            </c:forEach>
                                   "><i class="ti-heart mr-1"></i>Lưu</a>
                                        </c:if>
                                        
                                        <c:if test="${sessionScope.account.userId != e.post.user.userId}">
                                        <c:if test="${e.post.postType.postTypeId == 4}">
                                            <a href="interestPost?postId=${p.postId}" class="ml-3
                                               <c:forEach items="${p.userInterested}" var="ui">
                                                   <c:if test="${ui.userId == sessionScope.account.userId}">
                                                       text-danger
                                                   </c:if>
                                               </c:forEach>
                                               "><i class="ti-face-smile mr-1"></i>Quan tâm</a>
                                        </c:if>
                                        </c:if>
                                    </div>
                                    <div class="details mt-20">
                                        <a href="viewPostDetails?postId=${e.post.postId}">
                                            <h3>${e.post.title}</h3>
                                        </a>
                                        <!--                                        <p class="tag-list-inline">Tag: <a href="#">travel</a>, <a href="#">life style</a>, <a href="#">technology</a>, <a href="#">fashion</a></p>-->
                                        <p>${e.post.content.substring(0, 200)}...</p>
                                        <a class="button" href="viewPostDetails?postId=${e.post.postId}">Xem bài viết<i class="ti-arrow-right"></i></a>
                                    </div>
                                </div>
                            </c:forEach>
                            </div>
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
