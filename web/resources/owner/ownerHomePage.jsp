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
            <%@include file="../layout/owner_navbar.jsp" %>
            <div class="container mt-4">
                <div class="row">
                    <div class="col-md-4">
                        <div class="card mb-4">
                            <div class="card-header bg-warning text-dark">
                                <h5 class="card-title mb-0">Thông báo</h5>
                            </div>
                            <c:if test="${not empty notificationsList}">
                                <div class="card-body">
                                    <table class="table table-bordered text-center" style="background-color: #fff9c4;">
                                        <tbody>
                                            <c:forEach var="record" items="${notificationsList}">
                                                <tr>
                                                    <td class="text-start ps-3">${record.message}</td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </c:if>
                            <c:if test="${empty notificationsList}">
                                <div class="card-body">
                                    <div class="alert alert-warning text-start ps-3" style="background-color: #fff9c4; color: #856404;">
                                        Không có thông báo mới.
                                    </div>
                                </div>
                            </c:if>
                        </div>
                    </div>

                    <div class="col-md-8">
                        <div class="card mb-4">
                            <div class="card-header">
                                <h5 class="card-title mb-0">Phương tiện của tôi</h5>
                            </div>
                            <div class="card-body">
                                <table class="table table-bordered text-center">
                                    <thead>
                                        <tr>
                                            <th>STT</th>
                                            <th>Biển Số Xe</th>
                                            <th>Hãng Xe</th>
                                            <th>Loại Xe</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="vehicle" items="${vehicleList}" varStatus="loop">
                                            <tr>
                                                <td>${loop.index + 1}</td> 
                                                <td>${vehicle.plateNumber}</td>
                                                <td>${vehicle.brand}</td>
                                                <td>${vehicle.model}</td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>

                        <!-- Lịch đăng kiểm -->
                        <div class="card">
                            <div class="card-header">
                                <h4 class="card-title mb-0">Lịch Sử Đăng Kiểm</h4>
                            </div>

                            <c:if test="${not empty historyList}"> 
                                <div class="card-body">
                                    <table class="table table-bordered text-center">
                                        <thead class="table-light">
                                            <tr>
                                                <th>#</th>
                                                <th>Biển Số Xe</th>
                                                <th>Thời Gian Đăng Kiểm</th>
                                                <th>Kết Quả</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach var="record" items="${historyList}" varStatus="loop">
                                                <tr style="background-color: ${record.result eq 'PENDING' ? 'yellow' : 'white'};">
                                                    <td>${loop.index + 1}</td> 
                                                    <td>${record.vehicle.plateNumber}</td>
                                                    <td>${record.inspectionDate}</td>
                                                    <td>${record.result}</td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>

                                </div>
                            </c:if>
                            <c:if test="${empty historyList}">
                                <div class="alert text-center">
                                    Bạn chưa đăng ký kiểm định
                                </div>  
                            </c:if>                    
                        </div>
                    </div>
                </div>
            </div>
            <%@include file="../layout/owner_footer.jsp" %>
            <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js"></script>
    </body>
</html>
