<%-- 
    Document   : dangKyPT
    Created on : Jan 28, 2025, 4:26:32 PM
    Author     : Lenovo
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Thông Báo Phương Tiện Vi Phạm</title>
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
        <%@include file="../layout/police_navbar.jsp" %>

        <!-- Main Content -->
        <div class="content">
            <div class="form-container">
                <h2 class="text-center text-primary">Thông Báo</h2>
                <form action="thong-bao-canh-sat" method="post" class="mt-3">
                    <!-- Truyền ID của chủ phương tiện -->
                    <input type="hidden" name="ownerID" value="${owner.userId}">
                    <input type="hidden" name="violation" value="${violation}">
                    <!-- Tên chủ phương tiện -->
                    <div class="mb-3">
                        <label class="form-label">Tên Chủ Phương Tiện:</label>
                        <input type="text" class="form-control" value="${owner.fullName}" readonly>
                    </div>

                    <!-- Biển số xe -->
                    <div class="mb-3">
                        <label class="form-label">Biển Số Xe:</label>
                        <input type="text" name="plateNumber" class="form-control" value="${plateNumber}" readonly>
                    </div>

                    <!-- Nội dung thông báo -->
                    <div class="mb-3">
                        <label class="form-label">Nội Dung Thông Báo:</label>
                        <textarea name="messageContent" class="form-control" rows="3" required>${message}</textarea>
                    </div>
                        <c:if test="${not empty successMessage}">
                            <div style="color: green">${successMessage}</div> 
                        </c:if>
                        <c:if test="${not empty errorMessage}">
                        <div style="color: red">${errorMessage}</div> 
                        </c:if>
                    <!-- Nút gửi -->
                    <button type="submit" class="btn btn-custom w-100">Gửi Thông Báo</button>
                    <a href="trung-tam-canh-sat" class="btn btn-dark mt-2">Quay lại</a>
                </form>


            </div>
        </div>

        <!-- Footer -->
        <%@include file="../layout/police_footer.jsp"%>
    </body>
</html>