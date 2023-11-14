<%-- 
    Document   : changePassword
    Created on : Nov 15, 2023, 12:37:32 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Đổi mật khẩu</title>
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
        <div class="d-flex justify-content-center mt-5 mb-5">
            <div class="login-wrapper shadow bg-body rounded">
                <div class="tab-content" id="accountTabContent">
                    <!--Login form-->
                    <div class="tab-pane fade show active body-login-wrapper" id="pills-login" role="tabpanel"
                         aria-labelledby="pills-login-tab">
                        <form action="MainController" method="post">
                            <h6>Nhập mật khẩu cũ <span class="text-danger">*</span></h6>
                            <input type="password" class="form-control mb-3" name="oldpassword" required
                                   aria-label="Username">
                            <h6>Nhập mật khẩu mới <span class="text-danger">*</span></h6>
                            <input type="password" class="form-control mb-2" name="newpwd1" required
                                    aria-label="Username">
                            <h6>Nhập lại mật khẩu mới <span class="text-danger">*</span></h6>
                            <input type="password" class="form-control mb-2" name="newpwd2" required
                                   p aria-label="Username">
                            
                            <button type="submit" class="btn btn-dark w-100 mb-2" name="action" value="ChangePWD">Đổi mật khẩu</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
