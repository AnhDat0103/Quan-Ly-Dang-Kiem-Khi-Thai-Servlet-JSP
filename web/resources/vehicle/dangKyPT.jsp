<%-- 
    Document   : dangKyPT
    Created on : Jan 28, 2025, 4:26:32 PM
    Author     : Lenovo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Xóa Phương Tiện</title>
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

            .content {
                flex: 1;
                padding: 60px 20px 60px;
                display: flex;
                justify-content: center;
                align-items: center;
            }
            .form-container {
                width: 100%;
                max-width: 500px;
                background: white;
                padding: 30px;
                border-radius: 12px;
                box-shadow: 0 4px 10px rgba(0, 0, 0, 0.2);
            }
            .btn-custom {
                background: linear-gradient(135deg, #007bff, #0056b3);
                color: white;
                font-weight: bold;
                border-radius: 8px;
            }
            .btn-custom:hover {
                background: linear-gradient(135deg, #0056b3, #003f7f);
            }
        </style>
    </head>
    <body>
        <%@include file="../layout/owner_navbar.jsp" %>

        <!-- Main Content -->
        <div class="content">
            <div class="form-container">
                <h2 class="text-center text-primary">Đăng Ký Phương Tiện</h2>
                <% String bug = (String) request.getAttribute("bug"); %>
                <% if (bug != null && !bug.isEmpty()) { %>
                <div class="alert alert-danger" role="alert"><%= bug %></div>
                <% } %>

                <form action="dang-ky-phuong-tien" method="POST" class="mt-3">
                    <div class="mb-3">
                        <label class="form-label">ID Chủ Sở Hữu:</label>
                        <input type="number" value="${sessionScope.currentUser.userId}" name="ownerID" class="form-control" readonly>
                    </div>
                    <div class="row">
                        <div class="col-md-6 mb-3">
                            <label class="form-label">Biển số xe:</label>
                            <input type="text" name="plateNumber" pattern="[0-9]{2}[A-Z]-[0-9]{5}" 
                                   title="Biển số xe phải có định dạng: 30A-12345" class="form-control" required>
                        </div>
                        <div class="col-md-6 mb-3">
                            <label class="form-label">Hãng Xe:</label>
                            <input type="text" name="brand" class="form-control" required>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6 mb-3">
                            <label class="form-label">Mẫu Xe:</label>
                            <input type="text" name="model" class="form-control" required>
                        </div>
                        <div class="col-md-6 mb-3">
                            <label class="form-label">Năm Sản Xuất:</label>
                            <input type="number" name="manufactureYear" class="form-control" required>
                        </div>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Số Động Cơ:</label>
                        <input type="text" name="engineNumber" class="form-control" required>
                    </div>
                    <button type="submit" class="btn btn-custom w-100">Đăng Ký</button>
                </form>
            </div>
        </div>

        <!-- Footer -->
        <%@include file="../layout/owner_footer.jsp"%>
    </body>
</html>