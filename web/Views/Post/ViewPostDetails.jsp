<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Chi tiết bài viết</title>
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
                    <c:if test="${post.postType.postTypeId == 4}">
                        <select name="reportId" required="">
                            <option selected disabled>Lựa chọn lý do báo cáo</option>
                            <c:forEach items="${reports}" var="r">
                                <c:if test="${r.postType.postTypeId == 4}">
                                    <option value="${r.reportContentId}">${r.reportContent}</option>
                                </c:if>
                            </c:forEach>
                        </select>
                    </c:if>
                    <c:if test="${post.postType.postTypeId != 4}">
                        <select name="reportId" required="">
                            <option selected disabled>Lựa chọn lý do báo cáo</option>
                            <c:forEach items="${reports}" var="r">
                                <c:if test="${empty r.postType}">
                                    <option value="${r.reportContentId}">${r.reportContent}</option>
                                </c:if>
                            </c:forEach>
                        </select>
                    </c:if>
                    <br>
                    <label>Chi tiết:</label><br>
                    <textarea rows="5" cols="60" name="reason"></textarea>
                    <button class="btn btn-outline-danger" id="reportButton" onclick="validateReport(event)" >Báo cáo</button>
                </form>
            </div>
        </div>

        <!--================ Start Blog Post Area =================-->
        <section class="blog-post-area section-margin">
            <div class="container">
                <div class="row">
                    <div class="col-lg-8">
                        <div class="main_blog_details">
                            <img class="img-fluid" src="${post.img}" style="object-fit: cover" alt="" width="800px" height="300px">
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
                                            <form action="MainController" method="get">
                                                <input type="hidden" name="userId" value="${post.user.userId}">
                                                <button style="background: white; border: 0;" name="action" value="viewUserListPost"><h5>${post.user.username}</h5></button>
                                            </form>
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
                            <div class="d-flex" style="align-items: center; justify-content: space-between">
                                <div>
                                    <a href="likePost?postId=${post.postId}" class="ml-3
                                       <c:forEach items="${post.userLike}" var="ul">
                                           <c:if test="${ul.userId == sessionScope.account.userId}">
                                               text-danger
                                           </c:if>
                                       </c:forEach>
                                       "><i class="ti-thumb-up mr-1"></i>Thích</a>

                                    <c:if test="${sessionScope.account.userId != post.user.userId}">
                                        <a href="SavePostController?postId=${post.postId}" class="ml-3
                                           <c:forEach items="${post.userSave}" var="us">
                                               <c:if test="${us.userId == sessionScope.account.userId}">
                                                   text-danger
                                               </c:if>
                                           </c:forEach>
                                           "><i class="ti-heart mr-1"></i>Lưu</a>
                                    </c:if>

                                    <c:if test="${post.postType.postTypeId == 4}">
                                        <c:if test="${sessionScope.account.userId != post.user.userId}">
                                            <a href="interestPost?postId=${post.postId}" class="ml-3
                                           <c:forEach items="${post.userInterested}" var="ui">
                                               <c:if test="${ui.userId == sessionScope.account.userId}">
                                                   text-danger
                                               </c:if>
                                           </c:forEach>
                                           "><i class="ti-face-smile mr-1"></i>Quan tâm</a>
                                        </c:if>
                                    </c:if>
                                </div>
                                <c:if test="${sessionScope.account.userId != post.user.userId}">
                                    <div class="d-flex justify-content-end">
                                        <button id="openModalBtn" class="ml-3 btn btn-outline-danger"><i class="ti-flag-alt mr-1"></i>Báo cáo bài viết</button>
                                    </div>
                                </c:if>
                            </div>

                            <div style="margin-top :30px">
                                <c:if test="${post.postType.postTypeId == 4}">
                                    <p style="font-weight: bold">Giá bán:</p><p style="color: gold; font-weight: bold">${post.exchange.price}đ</p>
                                    <p style="color: green; font-weight: bold">Địa chỉ: ${post.exchange.address}</p>
                                </c:if>
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
                                                <form action="MainController" method="get">
                                                    <input type="hidden" name="userId" value="${c.user.userId}">
                                                    <button style="background: white; border: 0;" name="action" value="viewUserListPost"><h5>${c.user.username}</h5></button>
                                                </form>
                                                <p class="date">${c.createdDate}</p>
                                                <p class="comment">
                                                    ${c.commentValue}
                                                </p>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="pr-5 mb-2">
                                        <form action="replyComment" method="post">
                                            <p>Trả lời bình luận của ${c.user.username}:</p>
                                            <input value="${post.postId}" name="postId" type="hidden">
                                            <input value="${c.commentId}" name="parentId" type="hidden">
                                            <textarea name="comment" rows="3" class="form-control ml-4 mb-3" placeholder="Bình luận" required></textarea>
                                            <div class="justify-content-end d-flex">
                                                <button type="submit" class="btn btn-outline-secondary">Trả lời</button>
                                            </div>
                                        </form>
                                    </div>
                                    <c:forEach items="${c.childrens}" var="ch">
                                        <div class="comment-list left-padding">
                                            <div class="single-comment justify-content-between d-flex">
                                                <div class="user justify-content-between d-flex">
                                                    <div class="thumb">
                                                        <img src="${pageContext.request.contextPath}/asset/img/paw.png" alt="" height="42" width="42">
                                                    </div>
                                                    <div class="desc">
                                                        <form action="MainController" method="get">
                                                            <input type="hidden" name="userId" value="${ch.user.userId}">
                                                            <button style="background: white; border: 0;" name="action" value="viewUserListPost"><h5>${ch.user.username}</h5></button>
                                                        </form>
<!--                                                        <h5>${ch.user.username}</a></h5>-->
                                                        <p class="date">${ch.createdDate}</p>
                                                        <p class="comment">
                                                            ${ch.commentValue}
                                                        </p>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </c:forEach>
                            </div>	

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
                </div>
                <!-- End Blog Post Siddebar -->
            </div>
        </section>
        <!--================ End Blog Post Area =================-->

        <%@ include file="../../asset/includes/footer.jsp" %>
        <!--Script go to top, copy to every single page-->
        <script>
            function validateReport(event) {
                var selectElement = document.querySelector('select[name="reportId"]');
                var selectedOption = selectElement.options[selectElement.selectedIndex];
                var textareaValue = document.querySelector('textarea[name="reason"]').value.trim();

                if (selectedOption.value === "" || selectedOption.text === "Lựa chọn lý do báo cáo") {
                    alert("Hãy chọn một lý do");
                    event.preventDefault(); // Ngăn form submit nếu không hợp lệ
                }
                
                if (selectedOption.text === "Khác" && textareaValue === "") {
                    alert("Hãy mô tả lý do khác của bạn");
                    event.preventDefault(); // Ngăn form submit nếu không hợp lệ
                }
            }
        </script>
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