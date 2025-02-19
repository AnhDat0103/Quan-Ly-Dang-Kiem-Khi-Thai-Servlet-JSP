<%-- 
    Document   : navbar
    Created on : Jan 31, 2025, 11:52:33 PM
    Author     : DAT
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<nav class="navbar navbar-expand-lg navbar-dark bg-success">
    <div class="container">
        <a class="navbar-brand" href="trung-tam-dang-kiem">Trung Tâm Đăng kiểm</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav me-auto">
                <li class="nav-item">
                    <a class="nav-link active" href="trung-tam-dang-kiem">Trang chủ</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="quan-ly-lich-hen">Lịch hẹn</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="bao-cao-kiem-dinh">Báo cáo</a>
                </li>
            </ul>
            <!-- Thêm phần Profile và Đăng xuất -->
            <ul class="navbar-nav">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle active" href="profile.html" id="navbarDropdown" role="button" data-bs-toggle="dropdown">
                        <img class="sidebar-avatar rounded-circle" src="resources/images/avatar.png" 
                             style="width: 24px; height: 24px; margin-right: 4px;"> ${currentUser.fullName}
                    </a>
                    <ul class="dropdown-menu dropdown-menu-end">
                        <li><a class="dropdown-item" href="thong-tin-ca-nhan">Hồ sơ cá nhân</a></li>
                        <li><hr class="dropdown-divider"></li>
                        <li><a class="dropdown-item" href="dang-xuat">Đăng xuất</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</nav>