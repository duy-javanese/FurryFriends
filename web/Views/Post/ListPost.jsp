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
                    <h1 class="h3 mb-0 text-gray-800">Bài biết của tôi</h1>
                </div>

                <!-- Content Row -->
                <div class="row">
                    <div class="table-responsive">
                        <table class="table align-middle">
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Ảnh</th>
                                    <th>Tiêu đề bài viết</th>
                                    <th>Phân loại bài viết</th>
                                    <th>Chế độ bài viết</th>
                                    <th>Trạng thái bài viết</th>
                                    <th>Hành động</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${posts}" var="p">
                                    <tr>
                                        <td>${p.postId}</td>
                                        <td>
                                            <img src="${p.img}" alt="alt" width="80px" height="80px" style="background-size: cover"/>
                                        </td>
                                        <td>${p.title}</td>
                                        <td>${p.postType.postTypeName}</td>
                                        <td>
                                            <c:if test="${p.isPublic == true}">
                                                Công khai
                                            </c:if>
                                            <c:if test="${p.isPublic == false}">
                                                Riêng tư
                                            </c:if>
                                        </td>
                                        <td>
                                                <p style="
                                                            padding: 8px 0;
                                                            <c:if test="${p.status.postStatusId eq 4}">
                                                            background: red;
                                                             </c:if>
                                                            <c:if test="${p.status.postStatusId eq 3}">
                                                            background: red;
                                                             </c:if>
                                                            <c:if test="${p.status.postStatusId eq 2}">
                                                            background: #1bf227;
                                                             </c:if>
                                                            <c:if test="${p.status.postStatusId eq 1}">
                                                            background: #ffc930;
                                                             </c:if>
                                                            color: white;
                                                            text-align: center;
                                                            
                                                            border-radius: 30px;
                                                            margin: 0;
                                                            /* font-size: medium; */
                                                        ">
                                                    ${p.status.postStatusValue}
                                                </p>
                                        </td>
                                        <td>
                                            <div class="d-flex">
                                                <form action="viewPostDetails" method="post">
                                                    <input type="hidden" value="${p.postId}" name="postId">
                                                    <button type="submit"
                                                            class="btn btn-outline-success me-2">
                                                        <i class="fa-solid fa-eye"></i>
                                                    </button>
                                                </form>
                                                <form action="editPost" method="post">
                                                    <input type="hidden" value="${p.postId}" name="postId">
                                                    <input type="hidden" value="${p.postType.postTypeId}" name="postType">
                                                    <button type="submit"
                                                            class="btn btn-outline-success me-2">
                                                        <i class="fa-solid fa-pen"></i>
                                                    </button>
                                                </form>
                                            </div>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <div class="d-flex justify-content-center mt-1">
                <nav aria-label="Page navigation example col-12">
                    <ul class="pagination">
                        <%--For displaying Previous link except for the 1st page --%>
                        <c:if test="${currentPage != 1}">
                            <li class="page-item">
                                <a class="page-link" href="listPost?page=${currentPage - 1}" aria-label="Previous">
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
                                    <li class="page-item"><a class="page-link" href="listPost?page=${i}">${i}</a></li>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>

                        <%--For displaying Next link --%>
                        <c:if test="${currentPage lt noOfPages}">
                            <li class="page-item">
                                <a class="page-link" href="listPost?page=${currentPage + 1}" aria-label="Next">
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
