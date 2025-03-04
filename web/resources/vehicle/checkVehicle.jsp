<%-- 
    Document   : checkVehicle
    Created on : Feb 27, 2025, 10:09:07 AM
    Author     : Lenovo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Đăng ký lịch kiểm định</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
        <style>
            body {
                min-height: 100vh;
                display: flex;
                flex-direction: column;
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
    <%@include file="../layout/owner_navbar.jsp"%>
    <form action="kiem-tra-tai-khoan" method="GET">
        <div class="container mt-5">
            <div class="row justify-content-center">
                <div class="col-md-6">
                    <div class="card shadow-lg border-0 rounded-4">
                        <div class="card-body text-center p-4">
                            <h4 class="fw-bold text-danger">Bạn chưa đăng ký phương tiện!</h4>
                            <p class="text-muted">Hãy đăng ký phương tiện để tiếp tục sử dụng hệ thống.</p>
                            <div class="d-flex justify-content-center gap-3 mt-4">
                                <a href="chu-phuong-tien" class="btn btn-outline-secondary px-4">Bỏ qua</a>
                                <a href="dang-ky-phuong-tien" class="btn btn-danger px-4">Đăng Ký Ngay</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </form>
    <!-- Footer từ owner-dashboard -->
    <%@include file="../layout/owner_footer.jsp"%>
</body>
</html>
