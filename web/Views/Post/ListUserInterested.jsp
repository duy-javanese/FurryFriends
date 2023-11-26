<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                    <h1 class="h3 mb-0 text-gray-800">Bài viết của tôi</h1>
                </div>

                <!-- Content Row -->
                <div class="row">
                    <div class="table-responsive">
                        <table class="table align-middle">
                            <thead>
                                <tr>

                                    <th>Ảnh</th>
                                    <th>Tiêu đề bài viết</th>
                                    <th>Phân loại bài viết</th>
                                    <th>Chế độ bài viết</th>
                                    <th>Hành động</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${ip}" var="ip">
                                    <tr>

                                        <td>
                                            <img src="${ip.post.img}" alt="alt" width="80px" height="80px" style="background-size: cover; object-fit: cover"/>
                                        </td>
                                        <td>${ip.post.title}</td>
                                        <td>${ip.post.postType.postTypeName}</td>
                                        <td>
                                            <c:if test="${ip.post.isPublic == true}">
                                                Công khai
                                            </c:if>
                                            <c:if test="${ip.post.isPublic == false}">
                                                Riêng tư
                                            </c:if>
                                        </td>
                                        <td>
                                            <div class="d-flex">
                                                <button href="/admin-product-detail.html" type="button"
                                                        class="btn btn-outline-success me-2" data-bs-toggle="modal"
                                                        data-bs-target="#itemDetail_${ip.post.postId}">
                                                    <i class="fa-solid fa-eye"></i>
                                                </button>
                                                <!--Modal View Detail-->
                                                <div class="modal fade" id="itemDetail_${ip.post.postId}" tabindex="-1"
                                                     aria-labelledby="exampleModalLabel" aria-hidden="true">
                                                    <div class="modal-dialog modal-lg modal-dialog-centered">
                                                        <div class="modal-content">
                                                            <div class="modal-header">
                                                                <h5 class="modal-title" id="exampleModalLabel">Chi tiết</h5>
                                                                <button type="button" class="btn-close" data-bs-dismiss="modal"
                                                                        aria-label="Close"></button>
                                                            </div>
                                                            <div class="modal-body">
                                                                <div class="row">
                                                                    <div class="table-responsive">
                                                                        <table class="table align-middle">
                                                                            <thead>
                                                                                <tr>
                                                                                    <th>Ảnh</th>
                                                                                    <th>User name</th>
                                                                                    <th>Số điện thoại</th>
                                                                                    <th>Trạng thái</th>
                                                                                    <th>Thời gian</th>
                                                                                    <th>Trạng thái giao dịch</th>
                                                                                    <th>Hành động</th>
                                                                                </tr>
                                                                            </thead>
                                                                            <tbody>
                                                                                <c:forEach items="${ip.userInterested}" var="ui">
                                                                                    <tr>
                                                                                        <td><img src="${pageContext.request.contextPath}/asset/img/paw.png"
                                                                                                 alt="Generic placeholder image" class="img-fluid img-thumbnail mt-4 mb-2"
                                                                                                 style="width: 150px; z-index: 1"></td>

                                                                                        <td>${ui.username}</td>
                                                                                        <td>${ui.phone}</td>
                                                                                        <td>
                                                                                            <c:if test="${ui.status == true}">
                                                                                                Hoạt động
                                                                                            </c:if>
                                                                                            <c:if test="${ui.status == false}">
                                                                                                Vô hiệu hóa
                                                                                            </c:if>
                                                                                        </td>
                                                                                        <td>${ip.datetime}</td>
                                                                                        <td>
                                                                                            <c:if test="${ui.status == true}">
                                                                                                <form action="contactUser" method="post">
                                                                                                    <button type="submit" class="btn btn-outline-light d-lg-flex align-items-center align-middle" style="width: 150px">
                                                                                                        <img src="/img/home/zalo.png"
                                                                                                             alt="Generic placeholder image" class="img-fluid img-thumbnail"
                                                                                                             style="width: 40px;height: 40; z-index: 1">
                                                                                                        <p style="padding-top: 10%; padding-left: 5%">Liên hệ</p>
                                                                                                    </button>
                                                                                                </form>
                                                                                            </c:if>
                                                                                        </td>
                                                                                    </tr>
                                                                                </c:forEach>
                                                                            </tbody>
                                                                        </table>
                                                                    </div>
                                                                </div>
                                                                <div class="modal-footer">
                                                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">
                                                                        Đóng
                                                                    </button>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <div class="d-flex justify-content-center">
                <nav aria-label="Page navigation example col-12">
                    <ul class="pagination">
                        <%--For displaying Previous link except for the 1st page --%>
                        <c:if test="${currentPage != 1}">
                            <li class="page-item">
                                <a class="page-link" href="listUserInterested?page=${currentPage - 1}" aria-label="Previous">
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
                                    <li class="page-item"><a class="page-link" href="listUserInterested?page=${i}">${i}</a></li>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>

                        <%--For displaying Next link --%>
                        <c:if test="${currentPage lt noOfPages}">
                            <li class="page-item">
                                <a class="page-link" href="listUserInterested?page=${currentPage + 1}" aria-label="Next">
                                    <span aria-hidden="true">&raquo;</span>
                                </a>
                            </li>
                        </c:if>
                    </ul>
                </nav>
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