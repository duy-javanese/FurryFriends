<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register</title>
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
        <!-- Form đăng ký -->
        <div class="tab-pane fade show active body-register-wrapper" id="pills-register" role="tabpanel"
             aria-labelledby="pills-register-tab">
            <form action="MainController" method="POST">
                <!-- Thêm các trường nhập liệu cho tên người dùng, mật khẩu và thông tin đăng ký khác -->
                <h6>Tên người dùng <span class="text-danger">*</span></h6>
                ${requestScope.USERNAME_ERROR}
                <input type="text" class="form-control mb-3" name="username" required
                       placeholder="Nhập tên người dùng" aria-label="Username">
                <h6>Mật khẩu <span class="text-danger">*</span></h6>
                <input type="password" class="form-control mb-2" name="password" required
                       placeholder="Nhập mật khẩu" aria-label="Password">
                <!-- Trường nhập lại mật khẩu -->
                <h6>Nhập lại mật khẩu <span class="text-danger">*</span></h6>
                ${requestScope.PASSWORD_ERROR}
                <input type="password" class="form-control mb-2" name="confirm_password" required
                       placeholder="Nhập lại mật khẩu" aria-label="Confirm Password">
                <h6>Email <span class="text-danger">*</span></h6>
                ${requestScope.EMAIL_ERROR}
                <input type="email" class="form-control mb-3" name="email" required
                       placeholder="Nhập email" aria-label="Email">
                <h6>Số điện thoại <span class="text-danger">*</span></h6>
                ${requestScope.PHONE_ERROR}
                <input type="tel" class="form-control mb-3" name="phone" required
                       placeholder="Nhập số điện thoại" aria-label="Phone">
                <h6>Địa chỉ <span class="text-danger">*</span></h6>
                <input type="text" class="form-control mb-3" name="address" required
                       placeholder="Nhập địa chỉ" aria-label="Address">
                <button type="submit" class="btn btn-dark w-100 mb-2" name="action" value="Register">Đăng ký</button>
            </form>
        </div>
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
