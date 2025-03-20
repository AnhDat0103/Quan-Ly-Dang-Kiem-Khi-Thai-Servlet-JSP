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
        <div class="container mt-5">
            <h4 class="text-center mb-4">Tra Cứu Phương Tiện</h4>
            <div class="d-flex justify-content-center">
                <table class="table table-bordered w-auto" style="background-color: #f8f9fa; border-radius: 8px;">
                    <form action="danh-sach-phuong-tien" method="get">
                        <tbody>
                            <tr>
                                <th><label for="plateNumber" class="form-label">Biển kiểm soát</label></th>
                                <td>
                                    <input type="text" class="form-control" id="plateNumber" name="plateNumber"
                                           placeholder="VD: 30A12345" required value="${param.plateNumber}">
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2" class="text-center">
                                    <button type="submit" class="btn btn-danger w-50">Tra cứu</button>
                                </td>

                            </tr>
                        </tbody>
                    </form>
                </table>
            </div>

            <!-- Nếu đã nhập và không có kết quả -->
            <c:if test="${not empty notFound}">
                <div class="text-center text-danger mt-4">
                    <h5>${notFound}</h5>
                </div>
            </c:if>

            <c:if test="${not empty policeList}">
                <div class="container-fluid">
                    <div class="text-center">
                        <h2 class="mt-4">Thông Tin Phương Tiện</h2>
                    </div>
                    <div class="card mt-3 mb-5">
                        <div class="card-body">
                            <table class="table table-striped table-bordered">
                                <thead class="table-dark">
                                    <tr>
                                        <th>Biển Số Xe</th>
                                        <th>Tên Chủ Phương Tiện</th>
                                        <th>Hãng Xe</th>
                                        <th>Loại Xe</th>
                                        <th>Ngày Sản Xuất</th>
                                        <th>Mã Số Động Cơ</th>
                                        <th>Trạng Thái</th>
                                        <th>Hành Động</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="police" items="${policeList}">
                                        <tr>
                                            <td>${police.vehicles.plateNumber}</td>
                                            <td>${police.user.fullName}</td>
                                            <td>${police.vehicles.brand}</td>
                                            <td>${police.vehicles.model}</td>
                                            <td>${police.vehicles.manufactureYear}</td>
                                            <td>${police.vehicles.engineNumber}</td>
                                            <td>${police.vehicles.status}</td> 
                                            <td>
                                                <a href="thong-bao-canh-sat?plateNumber=${police.vehicles.plateNumber}" class="btn btn-danger ${police.vehicles.status eq 'Fail' ? ' ' : 'd-none'}">Xử Phạt</a>
                                            </td>

                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </c:if>

        </div>

        <!-- Nếu tìm thấy danh sách phương tiện -->


        <%@include file="../layout/police_footer.jsp" %>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="./resources/js/scripts.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/umd/simple-datatables.min.js" crossorigin="anonymous"></script>
        <script src="./resources/js/datatables-simple-demo.js"></script>
    </body>
</html>
