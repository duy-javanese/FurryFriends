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
        <%@ include file="asset/includes/header.jsp" %>
        <!-- Login wrapper -->
        <div class="d-flex justify-content-center mt-5 mb-5">
            <div class="login-wrapper shadow bg-body rounded">
                <div class="tab-content" id="accountTabContent">
                    <!--Login form-->
                    <div class="tab-pane fade show active body-login-wrapper" id="pills-login" role="tabpanel"
                         aria-labelledby="pills-login-tab">
                        <form action="login" method="post">
                            <c:if test="${isFail != null}">
                                <c:if test="${isFail == true}">
                                    <p style="color: red">Thông tin đăng nhập không chính xác.</p>
                                </c:if>
                            </c:if>
                            <h6>Tên đăng nhập <span class="text-danger">*</span></h6>
                            <input type="text" class="form-control mb-3" name="username" required
                                   placeholder="Nhập tên đăng nhập" aria-label="Username">
                            <h6>Mật khẩu <span class="text-danger">*</span></h6>
                            <input type="password" class="form-control mb-2" name="pwd" required
                                   placeholder="Nhập mật khẩu" aria-label="Username">
                            <div class="mb-3">
                                <a href="registerPage.jsp" class="forget-pass-btn">
                                    Đăng kí tài khoản
                                </a>
                            </div>
                            <button type="submit" class="btn btn-dark w-100 mb-2">Đăng nhập</button>
                            <div class="text-center commit-meeko mb-3">
                                Chúng tôi cam kết bảo mật và sẽ không bao giờ đăng<br />hay chia sẻ thông tin mà chưa có
                                được sự
                                đồng ý của bạn.
                            </div>
                            <div class=" d-flex justify-content-center position-relative mb-3">
                                <div class="login-other-way rounded-pill">
                                    hoặc đăng nhập qua
                                </div>
                            </div>
                        </form>
                        <form action="loginEmail" method="post">
                            <div class="group-btn-login-social mb-3">
                                <!-- Do login google here, change onclick and href  -->
                                <a class="social-login--google" href="https://accounts.google.com/o/oauth2/auth?scope=profile&redirect_uri=http://localhost:9999/loginEmail&response_type=code&client_id=583964261490-qa8kt76ed9evd3l597jn9hmsnrm6s97q.apps.googleusercontent.com&approval_prompt=force">
                                    <img width="129px" height="37px" alt="google-login-button"
                                         src="//bizweb.dktcdn.net/assets/admin/images/login/gp-btn.svg"></a>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <%@ include file="asset/includes/footer.jsp" %>
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
    </body>

</html>