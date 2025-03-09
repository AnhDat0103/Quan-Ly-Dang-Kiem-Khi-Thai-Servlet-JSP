<%-- 
    Document   : narbar_inspector
    Created on : Feb 10, 2025, 12:39:38 AM
    Author     : DUYEN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
 <nav class="navbar navbar-expand-lg navbar-dark bg-info">
        <div class="container">
            <a class="navbar-brand" href="#">Công nhân Kiểm định</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link active" href="#">Trang chủ</a>
                    </li>
                   
                    <li class="nav-item">
                        <a class="nav-link" href="inspection-history.jsp">Lịch sử kiểm định</a>
                    </li>
                </ul>
                <ul class="navbar-nav ms-auto">
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle d-flex align-items-center" href="#" id="userDropdown" role="button" data-bs-toggle="dropdown">
                            <img src="resources/images/${currentUser.avatar}" class="rounded-circle me-2" alt="Avatar" style="width: 32px; height: 32px;">
                            <span>${sessionScope.currentUser.fullName}</span>
                        </a>
                        <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="userDropdown">
                            <li><a class="dropdown-item" href="thong-tin-nguoi-kiem-dinh"><i class="fas fa-user fa-sm fa-fw me-2 text-gray-400"></i>Hồ sơ cá nhân</a></li>
                            <li><hr class="dropdown-divider"></li>
                            <li><a class="dropdown-item" href="dang-xuat"><i class="fas fa-sign-out-alt fa-sm fa-fw me-2 text-gray-400"></i>Đăng xuất</a></li>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
