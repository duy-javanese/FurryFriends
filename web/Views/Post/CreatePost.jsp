<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Document</title>
        <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css"/>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
    </head>

    <body>

        <%@ include file="../../asset/includes/User/HeaderUser.jsp" %>
        <%@ include file="../../asset/includes/User/NavbarUser.jsp" %>
        <!-- Dashboard Products wrapper -->
        <section style="margin-left: 280px; height: calc(100vh - 133px); overflow-y: auto;">
            <div class="p-4" style="margin-bottom: 42px;">
                <!-- Page Heading -->
                <div class="d-sm-flex align-items-center justify-content-between mb-4">
                    <h1 class="h3 mb-0 text-gray-800">Tạo bài viết</h1>
                </div>
                <hr>
                <!-- Content Row -->
                <form action="createPost" method="post" enctype="multipart/form-data">
                    <div class="row m-5 rounded-3" style="background-color: papayawhip">
                        <div class="form-floating mt-5">
                            <select class="form-select" id="floatingSelect" aria-label="Floating label select example">
                                <option selected disabled value="">Danh mục bài viết</option>
                                <option value="1">One</option>
                                <option value="2">Two</option>
                                <option value="3">Three</option>
                            </select>
                            <label for="floatingSelect">Danh mục bài viết</label>
                        </div>
                        <div class="mb-3">
                            <label for="exampleFormControlInput1" class="form-label">Tiêu đề bài viết <span style="color: red">*</span></label>
                            <input type="text" class="form-control" id="exampleFormControlInput1" placeholder="Tiêu đề bài viết">
                        </div>
                        <div class="mb-3">
                            <label for="exampleFormControlTextarea1" class="form-label">Nội dung bài viết <span style="color: red">*</span></label>
                            <textarea class="form-control" id="exampleFormControlTextarea1" rows="3"></textarea>
                        </div>
                        <div class="mb-3">
                            <label for="formFile" class="form-label">Ảnh bài viết <span style="color: red">*</span></label>
                            <input class="form-control" type="file" id="formFile">
                        </div>
                        <div class="mb-3">
                            <label for="formFile" class="form-label">Chế độ bài viết <span style="color: red">*</span></label>
                            <br>
                            <input type="radio" name="isPublic" value="1">Công khai
                            <input type="radio" name="isPublic" value="1">Riêng tư
                        </div>
                    </div>
                    <div class="d-flex justify-content-center">
                        <button class="btn btn-outline-info">Tạo bài viết</button>
                    </div>
                </form>
            </div>
        </section>

        <%@ include file="../../asset/includes/User/FooterUser.jsp" %>

        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe"
        crossorigin="anonymous"></script>
        <script src="https://kit.fontawesome.com/8d39de38b8.js" crossorigin="anonymous"></script>
    </body>

</html>
