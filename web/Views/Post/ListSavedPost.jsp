<%-- 
    Document   : ListSavedPost
    Created on : Nov 21, 2023, 11:47:32 PM
    Author     : Admin
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Bài viết đã lưu</title>
        <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css"/>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
    </head>
    <body>
        <%@ include file="../../asset/includes/User/HeaderUser.jsp" %>
        <%@ include file="../../asset/includes/User/NavbarUser.jsp" %>
        
        <section style="margin-left: 280px; height: calc(100vh - 133px); overflow-y: auto;">
            <c:set var="posts" value ="${requestScope.SAVED_POST_BY_USER}"/>
            <div class="p-4" style="margin-bottom: 42px;">
                <!-- Page Heading -->
                <div class="d-sm-flex align-items-center justify-content-between mb-4">
                    <h1 class="h3 mb-0 text-gray-800">Bài viết đã lưu của tôi</h1>
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
<!--                                    <th>Chế độ bài viết</th>-->
                                    <th>Hành động</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${posts}" var="p">
                                    <tr>
                                        <td>
                                            <img src="${p.img}" alt="alt" width="80px" height="80px" style="background-size: cover; object-fit: cover"/>
                                        </td>
                                        <td>${p.title}</td>
                                        <td>${p.postType.postTypeName}</td>
<!--                                        <td>
                                            <c:if test="${p.isPublic == true}">
                                                Công khai
                                            </c:if>
                                            <c:if test="${p.isPublic == false}">
                                                Riêng tư
                                            </c:if>
                                        </td>-->
                                        <td>
                                            <div class="d-flex">
                                                <a href="viewPostDetails?postId=${p.postId}" class="btn btn-outline-success me-2"><i class="fa-solid fa-eye"></i></a>
<!--                                                <form action="viewPostDetails" method="post">
                                                    <input type="hidden" value="${p.postId}" name="postId">
                                                    <button
                                                            class="btn btn-outline-success me-2">
                                                        <i class="fa-solid fa-eye"></i>
                                                    </button>
                                                </form>-->
                                            </div>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
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
