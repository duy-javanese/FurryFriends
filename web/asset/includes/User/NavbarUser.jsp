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
            <a href="dashBoard" class="nav-link">
                <svg class="bi me-2" width="16" height="16">
                <use xlink:href="#speedometer2"></use>
                </svg>
                Dashboard
            </a>
        </li>
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
                Tạo blog
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
            <a href="#" class="nav-link link-dark">
                <svg class="bi me-2" width="16" height="16">
                <use xlink:href="#grid"></use>
                </svg>
                Người quan tâm bài viết
            </a>
        </li>
        <li class="nav-item">
            <a href="#" class="nav-link link-dark">
                <svg class="bi me-2" width="16" height="16">
                <use xlink:href="#grid"></use>
                </svg>
                Bài viết quan tâm 
            </a>
        </li>
    </ul>
    <hr>
    <div class="dropdown">
        <a href="#" class="d-flex align-items-center link-dark text-decoration-none dropdown-toggle"
           id="dropdownUser2" data-bs-toggle="dropdown" aria-expanded="false">
            <img src="https://github.com/mdo.png" alt="" width="32" height="32" class="rounded-circle me-2">
            <strong>${sessionScope.account.username}</strong>
        </a>
        <ul class="dropdown-menu text-small shadow" aria-labelledby="dropdownUser2">
            <li><a class="dropdown-item" href="#">Sign out</a></li>
        </ul>
    </div>
</section>
