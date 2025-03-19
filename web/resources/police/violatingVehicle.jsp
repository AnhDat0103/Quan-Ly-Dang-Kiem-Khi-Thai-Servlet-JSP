<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <title>Danh Sách Phương Tiện</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" />
        <link href="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/style.min.css" rel="stylesheet" />
        <link href="./resources/css/styles.css" rel="stylesheet" />
        <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
        <style>
            /* Căn chỉnh lại bảng */
            .card {
                box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1);
                border-radius: 10px;
            }

            .card-header {
                font-weight: bold;
                text-align: center;
                font-size: 18px;
                background-color: #f8f9fa;
            }

            /* Canh giữa nội dung chính */
            .container-fluid {
                max-width: 90%;
                margin-top: 20px;
            }

            .btn-back {
                margin-bottom: 15px;
            }
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
    <body class="sb-nav-fixed">
        <%@include file="../layout/police_navbar.jsp"%>
        <div id="layoutSidenav_content">
            <main>
                <div class="container-fluid">
                    <div class="text-center">
                        <h2 class="mt-4">Danh Sách Phương Tiện Vi Phạm</h2>
                    </div>

                    <div class="card">
                        <div class="card-body">
                            <table id="datatablesSimple" class="table table-striped table-bordered">
                                <thead class="table-dark">
                                    <tr>
                                        <th>Tên Chủ Phương Tiện</th>
                                        <th>Biển Số Xe</th>
                                        <th>Hãng Xe</th>
                                        <th>Loại Xe</th>
                                        <th>Vi Phạm</th>
                                        <th>Hành Động</th>
                                    </tr>
                                </thead>
                                <c:forEach var="viola" items="${violatingPolice}">
                                    <tr>
                                        <td>${viola.user.fullName}</td>
                                        <td>${viola.vehicles.plateNumber}</td>
                                        <td>${viola.vehicles.brand}</td>
                                        <td>${viola.vehicles.model}</td>
                                        <td>${viola.vehicles.violationType}</td>
                                        <td>
                                            <a class="btn btn-warning btn-sm" href="thong-bao-canh-sat?ownerId=${viola.user.userId}&plateNumber=${viola.vehicles.plateNumber}&violation=${viola.vehicles.violationType}">
                                                <i class="fas fa-bell"></i> Thông báo Xử Phạt 
                                            </a>
                                        </td>

                                    </tr>
                                </c:forEach>

                            </table>
                        </div>
                    </div>

                    <div class="text-start ps-3">
                        <!-- Thông tin về các loại vi phạm -->
                        <div class="alert alert-info mt-3" role="alert">
                            <strong>Lỗi 1:</strong> Điều khiển xe không đủ hệ thống hoặc có đủ hệ thống nhưng không có tác dụng, không đúng tiêu chuẩn an toàn kỹ thuật (Đăng kiểm Fail quá 3 lần). <br>
                            <strong>Lỗi 2:</strong> Người sử dụng ô tô quá hạn đăng kiểm dưới 10 ngày có thể bị phạt hành chính 3-4 triệu đồng và bị tước quyền sử dụng giấy phép lái xe từ 1 đến 3 tháng.
                        </div>
                    </div>
                </div>
            </main>
        </div>
        <%@include file="../layout/police_footer.jsp" %>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="./resources/js/scripts.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/umd/simple-datatables.min.js" crossorigin="anonymous"></script>
        <script src="./resources/js/datatables-simple-demo.js"></script>
    </body>
</html>

