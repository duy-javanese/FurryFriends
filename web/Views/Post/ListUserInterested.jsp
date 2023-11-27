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
                                    <th>Trạng thái giao dịch</th>
                                    <th>Hành động</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${posts}" var="post">
                                    <tr>

                                        <td>
                                            <img src="${post.img}" alt="alt" width="80px" height="80px" style="background-size: cover; object-fit: cover"/>
                                        </td>
                                        <td>${post.title}</td>
                                        <td>${post.postType.postTypeName}</td>
                                        <td>
                                            <c:if test="${post.isPublic == true}">
                                                Công khai
                                            </c:if>
                                            <c:if test="${post.isPublic == false}">
                                                Riêng tư
                                            </c:if>
                                        </td>
                                        <td>
                                            <c:if test="${post.exchange.isFinish == false}">
                                                Đang chờ giao dịch
                                            </c:if>
                                            <c:if test="${post.exchange.isFinish == true}">
                                                Giao dịch hoàn thành
                                            </c:if>
                                        </td>
                                        <td>
                                            <div class="d-flex">
                                                <button href="/admin-product-detail.html" type="button"
                                                        class="btn btn-outline-success me-2" data-bs-toggle="modal"
                                                        data-bs-target="#itemDetail_${post.postId}">
                                                    <i class="fa-solid fa-eye"></i>
                                                </button>
                                                <!--Modal View Detail-->
                                                <div class="modal fade" id="itemDetail_${post.postId}" tabindex="-1"
                                                     aria-labelledby="exampleModalLabel" aria-hidden="true">
                                                    <div class="modal-dialog modal-xl modal-dialog-centered w-100">
                                                        <div class="modal-content w-100">
                                                            <div class="modal-header">
                                                                <h5 class="modal-title" id="exampleModalLabel">Chi tiết</h5>
                                                                <button type="button" class="btn-close" data-bs-dismiss="modal"
                                                                        aria-label="Close"></button>
                                                            </div>
                                                            <div class="modal-body w-100">
                                                                <div class="row">
                                                                    <div>
                                                                        <table class="table align-middle">
                                                                            <thead>
                                                                                <tr>
                                                                                    <th>Ảnh</th>
                                                                                    <th>Người dùng</th>
                                                                                    <th>Điểm giao dịch</th>
                                                                                    <th>Số điện thoại</th>
                                                                                    <th>Trạng thái</th>
                                                                                    <th>Thời gian</th>
                                                                                    <th style="width: 150px">Trạng thái giao dịch</th>
                                                                                    <th style="width: 200px">Hành động</th>
                                                                                </tr>
                                                                            </thead>
                                                                            <tbody>
                                                                                <c:forEach items="${post.userInterested}" var="ui">
                                                                                    <tr>
                                                                                        <td><img src="${pageContext.request.contextPath}/asset/img/paw.png"
                                                                                                 alt="Generic placeholder image" class="img-fluid img-thumbnail mt-4 mb-2"
                                                                                                 style="width: 150px; z-index: 1"></td>

                                                                                        <td>${ui.user.username}</td>
                                                                                        <td>${ui.user.point}</td>
                                                                                        <td>${ui.user.phone}</td>
                                                                                        <td>
                                                                                            <c:if test="${ui.user.status == true}">
                                                                                                Hoạt động
                                                                                            </c:if>
                                                                                            <c:if test="${ui.user.status == false}">
                                                                                                Vô hiệu hóa
                                                                                            </c:if>
                                                                                        </td>
                                                                                        <td>${ui.datetime}</td>
                                                                                        <td style="width: 150px">
                                                                                            <c:if test="${ui.exchangeStatus.exchangeStatusId == 3}">
                                                                                                <div class="rounded-pill bg-success p-2" style="text-align: center">
                                                                                                    <span style="color: white">${ui.exchangeStatus.exchangeStatusValue}</span>
                                                                                                </div>
                                                                                            </c:if>
                                                                                            <c:if test="${ui.exchangeStatus.exchangeStatusId == 2}">
                                                                                                <div class="rounded-pill p-2" style="text-align: center; background-color: yellow">
                                                                                                    <span style="color: black">${ui.exchangeStatus.exchangeStatusValue}</span>
                                                                                                </div>
                                                                                            </c:if>
                                                                                            <c:if test="${ui.exchangeStatus.exchangeStatusId == 1}">
                                                                                                <div class="rounded-pill bg-info p-2" style="text-align: center; border-width: 2px">
                                                                                                    <span style="color: black">${ui.exchangeStatus.exchangeStatusValue}</span>
                                                                                                </div>
                                                                                            </c:if>
                                                                                        </td>
                                                                                        <td>
                                                                                            <div class="d-flex gap-3">
                                                                                                <c:if test="${post.exchange.isFinish == false}">
                                                                                                    <c:if test="${ui.user.status == true && ui.exchangeStatus.exchangeStatusId != 3}">
                                                                                                        <form action="contactUser" method="post">
                                                                                                            <input type="hidden" name="userPhone" value="${ui.user.phone}">
                                                                                                            <input type="hidden" name="postId" value="${post.postId}">
                                                                                                            <input type="hidden" name="userId" value="${ui.user.userId}">
                                                                                                            <button type="submit" class="btn btn-outline-info d-lg-flex align-items-center align-middle" style="width: 150px">
                                                                                                                <img src="/img/home/zalo.png"
                                                                                                                     alt="Generic placeholder image" class="img-fluid img-thumbnail"
                                                                                                                     style="width: 40px;height: 40; z-index: 1">
                                                                                                                <p style="padding-top: 10%; padding-left: 5%">Liên hệ</p>
                                                                                                            </button>
                                                                                                        </form>
                                                                                                    </c:if>
                                                                                                    <c:if test="${ui.user.status == true && ui.exchangeStatus.exchangeStatusId == 2}">
                                                                                                        <form action="finishExchange" method="post">
                                                                                                            <input type="hidden" name="exchangeId" value="${post.exchange.exchangeId}">
                                                                                                            <input type="hidden" name="postId" value="${post.postId}">
                                                                                                            <input type="hidden" name="userId" value="${ui.user.userId}">
                                                                                                            <button class="btn btn-success" type="submit">
                                                                                                                Hoàn thành
                                                                                                            </button>
                                                                                                        </form>
                                                                                                    </c:if>
                                                                                                </c:if>
                                                                                                <c:if test="${post.exchange.isFinish == true}">
                                                                                                    <div class="rounded-pill bg-success" style="text-align: center">
                                                                                                        <span style="color: white">Giao dịch này đã được hoàn thành</span>
                                                                                                    </div>
                                                                                                </c:if>
                                                                                            </div>
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