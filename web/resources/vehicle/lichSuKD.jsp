<%-- 
    Document   : lichSuKD
    Created on : Feb 26, 2025, 5:04:43 PM
    Author     : Lenovo
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <body>
        <div class="content-wrapper">
            <!-- Navbar từ owner-dashboard -->
            <form action="lich-su" method="POST">
                <%@include file="../layout/owner_navbar.jsp"%>
                <div class="container mt-4">
                    <div class="row justify-content-center">
                        <div class="col-md-10">
                            <div class="d-flex justify-content-between mb-3">

                            </div>
                            <div class="card">
                                <div class="card-header">
                                    <h4 class="card-title mb-0">Lịch Sử Đăng Kiểm</h4>
                                </div>

                                <c:if test="${not empty historyList}"> <div class="card-body">
                                        <table class="table table-bordered text-center">
                                            <thead class="table-light">
                                                <tr>
                                                    <th>#</th>
                                                    <th>Biển Số Xe</th>
                                                    <th>Hãng Xe</th>
                                                    <th>Loại Xe</th>
                                                    <th>Thời Gian Đăng Kiểm</th>
                                                    <th>Kết Quả</th>
                                                    <th>Thời Gian Hết Hạn</th>
                                                    <th>Hành Động</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach var="record" items="${historyList}" varStatus="loop">
                                                    <tr style="background-color: ${record.result eq 'PENDING' ? 'yellow' : 'white'};">
                                                        <td>${loop.index + 1}</td> 
                                                        <td>${record.vehicle.plateNumber}</td>
                                                        <td>${record.vehicle.brand}</td>
                                                        <td>${record.vehicle.model}</td>
                                                        <td>${record.inspectionDate}</td>
                                                        <td>${record.result}</td>
                                                        <td>${record.nextInspectionDate}</td>
                                                        <td>
                                                            <button class="btn btn-info btn-sm">
                                                                <i class="bi bi-eye"></i> Xem chi tiết
                                                            </button>
                                                            <button class="btn btn-danger btn-sm">
                                                                <i class="bi bi-trash"></i>
                                                            </button>
                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>

                                    </div></c:if>
                                <c:if test="${empty historyList}">
                                    <div class="alert text-center">
                                        Bạn chưa đăng ký kiểm định
                                    </div>  
                                </c:if>
                            </div>
                        </div>
                    </div>
                </div>


                <!-- Footer từ owner-dashboard -->
                <%@include file="../layout/owner_footer.jsp"%>
            </form>
    </body>
</html>

