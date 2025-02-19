<%-- 
    Document   : ownerHomePage
    Created on : Feb 5, 2025, 4:44:16 PM
    Author     : Lenovo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Trang chủ - Chủ phương tiện</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
    <style>
        body {
            min-height: 100vh;
            display: flex;
            flex-direction: column;
        }
        
        .content-wrapper {
            flex: 1 0 auto;
            margin-bottom: 2rem;
        }
        
        .footer {
            flex-shrink: 0;
            padding: 1rem 0;
        }
    </style>
</head>
<body>
    <!-- Wrap all content except footer in a div -->
    <div class="content-wrapper">
        <!-- Navbar -->
        <%@include file="../layout/owner_navbar.jsp" %>

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
                    <div class="card mb-4">
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

                    <!-- Lịch đăng kiểm -->
                    <div class="card mt-4">
                        <div class="card-header">
                            <h5 class="card-title mb-0">Lịch đăng kiểm của tôi</h5>
                        </div>
                        <div class="card-body">
                            <table class="table">
                                <thead>
                                    <tr>
                                        <th>Biển số xe</th>
                                        <th>Ngày đăng kiểm</th>
                                        <th>Trung tâm</th>
                                        <th>Trạng thái</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>AB-12345</td>
                                        <td>25/03/2024</td>
                                        <td>Trung tâm Đăng kiểm 50-02V</td>
                                        <td><span class="badge bg-warning">Chờ kiểm định</span></td>
                                    </tr>
                                    <tr>
                                        <td>AB-12345</td>
                                        <td>15/01/2024</td>
                                        <td>Trung tâm Đăng kiểm 50-02V</td>
                                        <td><span class="badge bg-success">Đã hoàn thành</span></td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

        <%@include file="../layout/owner_footer.jsp" %>
    <!-- Thêm Bootstrap Icons -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.2/font/bootstrap-icons.css">
        <!-- Thêm Bootstrap JS và Popper.js -->
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js"></script>
</body>
</html> 
