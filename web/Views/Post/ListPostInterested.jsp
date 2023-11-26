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
        <style>
            *{
                margin: 0;
                padding: 0;
            }
            .rate {
                float: left;
                height: 46px;
                padding: 0 10px;
            }
            .rate:not(:checked) > input {
                position:absolute;
                top:-9999px;
            }
            .rate:not(:checked) > label {
                float:right;
                width:1em;
                overflow:hidden;
                white-space:nowrap;
                cursor:pointer;
                font-size:30px;
                color:#ccc;
            }
            .rate:not(:checked) > label:before {
                content: '★ ';
            }
            .rate > input:checked ~ label {
                color: #ffc700;
            }
            .rate:not(:checked) > label:hover,
            .rate:not(:checked) > label:hover ~ label {
                color: #deb217;
            }
            .rate > input:checked + label:hover,
            .rate > input:checked + label:hover ~ label,
            .rate > input:checked ~ label:hover,
            .rate > input:checked ~ label:hover ~ label,
            .rate > label:hover ~ input:checked ~ label {
                color: #c59b08;
            }
        </style>
    </head>

    <body>

        <%@ include file="../../asset/includes/User/HeaderUser.jsp" %>
        <%@ include file="../../asset/includes/User/NavbarUser.jsp" %>
        <!-- Dashboard Products wrapper -->
        <section style="margin-left: 280px; height: calc(100vh - 133px); overflow-y: auto;">
            <div class="p-4" style="margin-bottom: 42px;">
                <!-- Page Heading -->
                <div class="d-sm-flex align-items-center justify-content-between mb-4">
                    <h1 class="h3 mb-0 text-gray-800">Bài viết quan tâm</h1>
                </div>

                <!-- Content Row -->
                <div class="row">
                    <div class="table-responsive">
                        <table class="table align-middle">
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Ảnh</th>
                                    <th>User name</th>
                                    <th>Tiêu đề bài viết</th>
                                    <th>Phân loại bài viết</th>
                                    <th>Thời gian</th>
                                    <th>Chế độ bài viết</th>
                                    <th>Hành động</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${ip}" var="ip">
                                    <tr>
                                        <td>${ip.post.postId}</td>
                                        <td>
                                            <img src="${p.img}" alt="alt" width="80px" height="80px" style="background-size: cover; object-fit: cover"/>
                                        </td>
                                        <td>${ip.post.user.username}</td>
                                        <td>${ip.post.title}</td>
                                        <td>${ip.post.postType.postTypeName}</td>
                                        <td>${ip.datetime}</td>
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
                                                <form action="viewPostDetails" method="get">
                                                    <input type="hidden" value="${ip.post.postId}" name="postId">
                                                    <button type="submit"
                                                            class="btn btn-outline-success me-2">
                                                        <i class="fa-solid fa-eye"></i>
                                                    </button>
                                                </form>
                                                <c:if test="${ip.exchangeStatus.exchangeStatusId == 3 and ip.isRated == false}">
                                                    <button href="/admin-product-detail.html" type="button"
                                                            class="btn btn-outline-success me-2" data-bs-toggle="modal"
                                                            data-bs-target="#itemDetail_${ip.post.postId}">
                                                        Đánh giá
                                                    </button>
                                                </c:if>
                                            </div>
                                            <div class="modal fade" id="itemDetail_${ip.post.postId}" tabindex="-1"
                                                 aria-labelledby="exampleModalLabel" aria-hidden="true">
                                                <div class="modal-dialog modal-lg modal-dialog-centered">
                                                    <div class="modal-content" style="width: 350px">
                                                        <div class="modal-header">
                                                            <h5 class="modal-title" id="exampleModalLabel">Đánh giá giao dịch</h5>
                                                            <button type="button" class="btn-close" data-bs-dismiss="modal"
                                                                    aria-label="Close"></button>
                                                        </div>
                                                        <div class="modal-body">
                                                            <form action="ratePoint" method="post">
                                                                <div class="row">
                                                                    <input type="hidden" value="${ip.interestedPostId}" name="interestedPostId">
                                                                    <input type="hidden" value="${ip.post.user.username}" name="userRated">
                                                                    <div class="rate">
                                                                        <input type="radio" id="star5_${ip.interestedPostId}" name="rate" value="5" />
                                                                        <label for="star5_${ip.interestedPostId}" title="text">5 stars</label>
                                                                        <input type="radio" id="star4_${ip.interestedPostId}" name="rate" value="4" />
                                                                        <label for="star4_${ip.interestedPostId}" title="text">4 stars</label>
                                                                        <input type="radio" id="star3_${ip.interestedPostId}" name="rate" value="3" />
                                                                        <label for="star3_${ip.interestedPostId}" title="text">3 stars</label>
                                                                        <input type="radio" id="star2_${ip.interestedPostId}" name="rate" value="2" />
                                                                        <label for="star2_${ip.interestedPostId}" title="text">2 stars</label>
                                                                        <input type="radio" id="star1_${ip.interestedPostId}" name="rate" value="1" />
                                                                        <label for="star1_${ip.interestedPostId}" title="text">1 star</label>
                                                                    </div>
                                                                </div>
                                                                <div class="modal-footer">
                                                                    <button type="submit" class="btn btn-success">
                                                                        Đánh giá
                                                                    </button>
                                                                </div>
                                                            </form>
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
                                <a class="page-link" href="listPostInterested?page=${currentPage - 1}" aria-label="Previous">
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
                                    <li class="page-item"><a class="page-link" href="listPostInterested?page=${i}">${i}</a></li>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>

                        <%--For displaying Next link --%>
                        <c:if test="${currentPage lt noOfPages}">
                            <li class="page-item">
                                <a class="page-link" href="listPostInterested?page=${currentPage + 1}" aria-label="Next">
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
