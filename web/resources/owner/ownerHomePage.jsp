<%-- 
    Document   : ownerHomePage
    Created on : Feb 5, 2025, 4:44:16 PM
    Author     : Lenovo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Trang chủ - Chủ phương tiện</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <!-- Navbar -->
    <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
        <div class="container">
            <a class="navbar-brand" href="#">Quản lý Đăng kiểm</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link active" href="#">Trang chủ</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="vehicles.jsp">Phương tiện của tôi</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="schedule.jsp">Đặt lịch kiểm định</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="history.jsp">Lịch sử kiểm định</a>
                    </li>
                </ul>
                <ul class="navbar-nav ms-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="profile.jsp">Hồ sơ</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="dang-xuat">Đăng xuất</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>

    <!-- Main content -->
    <div class="container mt-4">
        <div class="row">
            <!-- Thông báo -->
            <div class="col-md-4">
                <div class="card mb-4">
                    <div class="card-header">
                        <h5 class="card-title mb-0">Thông báo</h5>
                    </div>
                    <div class="card-body">
                        <div class="alert alert-warning">
                            Xe máy AB-12345 sắp đến hạn kiểm định (còn 7 ngày)
                        </div>
                    </div>
                </div>
            </div>

            <!-- Phương tiện -->
            <div class="col-md-8">
                <div class="card">
                    <div class="card-header">
                        <h5 class="card-title mb-0">Phương tiện của tôi</h5>
                    </div>
                    <div class="card-body">
                        <table class="table">
                            <thead>
                                <tr>
                                    <th>Biển số</th>
                                    <th>Loại xe</th>
                                    <th>Trạng thái</th>
                                    <th>Hạn kiểm định</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>AB-12345</td>
                                    <td>Honda Wave</td>
                                    <td><span class="badge bg-success">Đã đăng kiểm</span></td>
                                    <td>20/12/2024</td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html> 
