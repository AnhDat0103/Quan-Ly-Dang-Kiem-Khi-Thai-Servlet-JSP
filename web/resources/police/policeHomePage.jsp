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
        <title>Quản Lý CSGT</title>
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
                margin: 0;
            }

            .navbar {
                position: fixed;
                top: 0;
                left: 0;
                width: 100%;
                z-index: 1030;
            }

            .content-wrapper {
                flex: 1;
                padding-top: 0px; /* Đảm bảo nội dung không bị navbar che */
                padding-bottom: 0px; /* Tránh bị footer che */
            }

            /* Footer nhỏ gọn hơn */
            .footer {
                position: fixed;
                bottom: 0;
                width: 100%;
                background-color: #f8f9fa;
                padding: 0.5rem 0; /* Giảm padding để footer nhỏ hơn */
                box-shadow: 0 -2px 5px rgba(0, 0, 0, 0.1);
                font-size: 0.9rem; /* Chữ nhỏ hơn */
            }
        </style>
    </head>
    <body>
        <div class="content-wrapper">
            <%@include file="../layout/police_navbar.jsp" %>
            <div class="container mt-4">
                <div class="row">
                    <!-- Phần Tin Tức -->
                    <div class="col-md-4">
                        <div class="card mb-4 shadow-sm">
                            <div class="card-header bg-warning text-dark">
                                <h5 class="card-title mb-0">Tin Tức</h5>
                            </div>
                            <div class="card mb-4">
                                <div class="card-header bg-warning text-dark">
                                    <h5 class="card-title mb-0"><i class="bi bi-info-circle-fill"></i> Thông Báo</h5>
                                </div>
                                <div class="card-body">
                                    <p><strong>Nghị định 166/2024/NĐ-CP:</strong>  
                                        Các cơ sở đăng kiểm phải tuân thủ nghiêm túc, minh bạch và khách quan theo quy định pháp luật.</p>

                                    <h6 class="text-danger mt-3"><i class="bi bi-exclamation-triangle-fill"></i> Mức phạt quá hạn đăng kiểm:</h6>
                                    <ul>
                                        <li><strong>Dưới 1 tháng:</strong> Phạt 3-4 triệu, tước GPLX 1-3 tháng.</li>
                                        <li><strong>Từ 1 tháng trở lên:</strong> Phạt 4-6 triệu, tước GPLX 1-3 tháng.</li>
                                    </ul>

                                    <p class="text-success mt-3"><i>🚓 Hãy đăng kiểm đúng hạn để bảo vệ bản thân và cộng đồng.</i></p>
                                </div>
                            </div>

                        </div>
                    </div>

                    <!-- Phần Thống kê -->
                    <div class="col-md-8">
                        <div class="card mb-4 shadow-sm">
                            <div class="card-header bg-primary text-white">
                                <h5 class="card-title mb-0">Thống Kê Nhanh</h5>
                            </div>
                            <div class="card-body">
                                <table class="table table-bordered text-center">
                                    <thead class="table-light">
                                        <tr>
                                            <th>Tổng Phương Tiện</th>
                                            <th>Phương Tiện Vi Phạm</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <td><c:out value="${vehicleCount}" /></td>
                                            <td><c:out value="${bannedCount}" /></td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <%@include file="../layout/police_footer.jsp" %>
        </div>

        <!-- Bootstrap JS và Popper -->
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js"></script>
    </body>
</html> 
