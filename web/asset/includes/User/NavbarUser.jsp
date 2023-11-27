<%-- 
    Document   : NavbarAdmin
    Created on : Oct 8, 2023, 1:14:19 PM
    Author     : dell
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!-- Nav bar ADMIN wrapper, DONOT COPY THIS, JUST INCLUDE -->
<section class="position-absolute d-flex flex-column flex-shrink-0 p-3 bg-light"
         style="width: 280px; height: calc(100vh - 133px);background-color: bisque">
    <ul class="nav nav-pills flex-column mb-auto">
        <li class="nav-item">
            <a href="userProfile" class="nav-link link-dark">
                <svg class="bi me-2" width="16" height="16">
                <use xlink:href="#grid"></use>
                </svg>
                Thông tin cá nhân
            </a>
        </li>
<!--        <li class="nav-item">
            <a href="listProduct" class="nav-link link-dark">
                <svg class="bi me-2" width="16" height="16">
                <use xlink:href="#grid"></use>
                </svg>
                Sản phẩm của tôi
            </a>
        </li>-->
        <li class="nav-item">
            <a href="listPost" class="nav-link link-dark">
                <svg class="bi me-2" width="16" height="16">
                <use xlink:href="#grid"></use>
                </svg>
                Bài viết của tôi
            </a>
        </li>
        <li class="nav-item">
            <a href="createPost" class="nav-link link-dark">
                <svg class="bi me-2" width="16" height="16">
                <use xlink:href="#grid"></use>
                </svg>
                Tạo bài chia sẻ
            </a>
        </li>
        <li class="nav-item">
            <a href="createExchange" class="nav-link link-dark">
                <svg class="bi me-2" width="16" height="16">
                <use xlink:href="#grid"></use>
                </svg>
                Tạo bài viết trao đổi
            </a>
        </li>
        <li class="nav-item">
            <a href="/listUserInterested" class="nav-link link-dark">
                <svg class="bi me-2" width="16" height="16">
                <use xlink:href="#grid"></use>
                </svg>
                Người quan tâm bài viết
            </a>
        </li>
        <li class="nav-item">
            <a href="/listPostInterested" class="nav-link link-dark">
                <svg class="bi me-2" width="16" height="16">
                <use xlink:href="#grid"></use>
                </svg>
                Bài viết quan tâm 
            </a>
        </li>
        <li class="nav-item">
            <a href="/ListSavePostController" class="nav-link link-dark">
                <svg class="bi me-2" width="16" height="16">
                <use xlink:href="#grid"></use>
                </svg>
                Bài viết đã lưu 
            </a>
        </li>
    </ul>
</section>
