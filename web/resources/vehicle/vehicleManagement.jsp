<%-- 
    Document   : vehicleManagement
    Created on : Feb 5, 2025, 9:20:20 AM
    Author     : Lenovo
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "sql" uri = "http://java.sun.com/jsp/jstl/sql" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Trang Quản Lý Phương Tiện</title>
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
    <body>
        <%@include file="../layout/owner_navbar.jsp"%>
        <c:if test="${not empty message}">
            <div class="alert alert-info">${message}</div>
        </c:if>
        <!-- Nội dung chính -->
        <div class="container mt-4">
            <div class="row justify-content-center">
                <div class="col-md-10">
                    <div class="d-flex justify-content-between mb-3">
                        <a href="dang-ky-phuong-tien" class="btn btn-success">
                            <i class="bi bi-plus-lg"></i> Đăng ký phương tiện
                        </a>

                    </div>
                    <div class="card">
                        <div class="card-header">
                            <h4 class="card-title mb-0">Danh sách phương tiện</h4>
                        </div>
                        <div class="card-body">
                            <%-- Kiểm tra nếu có thông báo từ session --%>
                            <c:if test="${not empty sessionScope.deleteMessage}">
                                <div class="alert ${sessionScope.deleteSuccess ? 'alert-success' : 'alert-danger'} alert-dismissible fade show" role="alert">
                                    ${sessionScope.deleteMessage}
                                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                                </div>
                                <%-- Xóa thông báo sau khi hiển thị --%>
                                <c:remove var="deleteMessage" scope="session"/>
                                <c:remove var="deleteSuccess" scope="session"/>
                            </c:if>
                            <table class="table table-bordered text-center">
                                <thead class="table-light">
                                    <tr>
                                        <th>#</th>
                                        <th>Biển số xe</th>
                                        <th>Hãng xe</th>
                                        <th>Loại xe</th>
                                        <th>Trạng Thái</th>
                                        <th>Hành động</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="vehicle" items="${vehicleList}" varStatus="loop">
                                        <tr>
                                            <td>${loop.index + 1}</td> <!-- STT -->
                                            <td>${vehicle.plateNumber}</td>
                                            <td>${vehicle.brand}</td>
                                            <td>${vehicle.model}</td>
                                            <td>
                                                    <span class="badge 
                                                        <c:choose>
                                                            <c:when test="${vehicle.status == 'Pass'}">bg-success</c:when>
                                                            <c:otherwise>bg-danger</c:otherwise>
                                                        </c:choose>">
                                                        ${vehicle.status}
                                                    </span>
                                                </td>
                                            <td style="width: 120px;" class="text-center">
                                                <div class="d-flex justify-content-center gap-2">
                                                    <form action="xoa-phuong-tien" method="post" onsubmit="return confirm('Bạn có chắc chắn muốn xóa phương tiện này không?');">
                                                        <input type="hidden" name="plateNumber" value="${vehicle.plateNumber}">
                                                        <button type="submit" class="btn btn-danger btn-sm">
                                                            <i class="bi bi-trash"></i>
                                                        </button>
                                                    </form>
                                                    <form action="update-phuong-tien" method="get">
                                                        <input type="hidden" name="plateNumber" value="${vehicle.plateNumber}">
                                                        <button type="submit" class="btn btn-primary btn-sm" onclick="return confirm('Bạn có muốn cập nhật phương tiện này không?');">
                                                            <i class="bi bi-pencil"></i>
                                                        </button>
                                                    </form>
                                                </div>

                                            </td>

                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%@include file="../layout/owner_footer.jsp"%>

    </body>
</html>
