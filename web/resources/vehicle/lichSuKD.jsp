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
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

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
            } /* Khu vực hiển thị thông tin chi tiết */
        .detail-container {
            display: none; /* Mặc định ẩn */
            position: fixed;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            width: 50%;
            max-width: 600px;
            background: white;
            box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.3);
            padding: 20px;
            z-index: 1050;
            border-radius: 8px;
        }
        .close-btn {
            float: right;
            font-size: 20px;
            cursor: pointer;
        }
        .overlay {
            display: none; /* Mặc định ẩn */
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background: rgba(0, 0, 0, 0.5);
            z-index: 1049;
        }
    </style>
</head>
<body>
    <div class="content-wrapper">
        <%@include file="../layout/owner_navbar.jsp"%>

        <div class="container mt-4">
            <div class="row justify-content-center">
                <div class="col-md-10">
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
                                            <tr>
                                                <td>${loop.index + 1}</td> 
                                                <td>${record.vehicle.plateNumber}</td>
                                                <td>${record.vehicle.brand}</td>
                                                <td>${record.vehicle.model}</td>
                                                <td>${record.inspectionDate}</td>
                                                <td>
                                                    <span class="badge 
                                                        <c:choose>
                                                            <c:when test="${record.result == 'Passed'}">bg-success</c:when>
                                                            <c:otherwise>bg-danger</c:otherwise>
                                                        </c:choose>">
                                                        ${record.result}
                                                    </span>
                                                </td>
                                                <td>${record.nextInspectionDate}</td>
                                                <td>
                                                    <button type="button" class="btn btn-info btn-view-detail"
                                                        data-plate="${record.vehicle.plateNumber}"
                                                        data-brand="${record.vehicle.brand}"
                                                        data-model="${record.vehicle.model}"
                                                        data-inspection="${record.inspectionDate}"
                                                        data-result="${record.result}"
                                                        data-next-inspection="${record.nextInspectionDate}"
                                                        data-co2="${record.co2Emission}"
                                                        data-hc="${record.hcEmission}"
                                                        data-comments="${record.comments}">
                                                        Xem Chi Tiết
                                                    </button>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
        <%@include file="../layout/owner_footer.jsp"%>
    </div>

    <!-- Overlay che mờ nền khi mở chi tiết -->
    <div class="overlay" id="overlay"></div>

    <!-- Khu vực hiển thị chi tiết -->
    <div class="detail-container" id="detail-container">
        <span class="close-btn" id="close-detail">&times;</span>
        <h4 class="text-center">Chi Tiết Kiểm Định</h4>
        <table class="table table-hover table-bordered mt-3">
            <tbody>
                <tr><td><strong>Tên phương tiện</strong></td><td id="detail-brand"></td></tr>
                <tr><td><strong>Biển số xe</strong></td><td id="detail-plate"></td></tr>
                <tr><td><strong>Ngày kiểm định</strong></td><td id="detail-inspection"></td></tr>
                <tr><td><strong>Nồng độ CO2 đo được</strong></td><td id="detail-co2"></td></tr>
                <tr><td><strong>Nồng độ HC đo được</strong></td><td id="detail-hc"></td></tr>
                <tr><td><strong>Kết quả kiểm định</strong></td><td id="detail-result"></td></tr>
                <tr><td><strong>Ngày kiểm định tiếp theo</strong></td><td id="detail-next-inspection"></td></tr>
                <tr><td><strong>Ghi chú</strong></td><td id="detail-comments"></td></tr>
            </tbody>
        </table>
    </div>

    <script>
        $(document).ready(function() {
            $(".btn-view-detail").click(function() {
                // Lấy dữ liệu từ button
                $("#detail-brand").text($(this).data("brand"));
                $("#detail-plate").text($(this).data("plate"));
                $("#detail-inspection").text($(this).data("inspection"));
                $("#detail-co2").text($(this).data("co2"));
                $("#detail-hc").text($(this).data("hc"));
                $("#detail-result").text($(this).data("result"));
                $("#detail-next-inspection").text($(this).data("next-inspection"));
                $("#detail-comments").text($(this).data("comments"));

                // Hiển thị bảng chi tiết
                $("#overlay").fadeIn();
                $("#detail-container").fadeIn();
            });

            // Đóng chi tiết
            $("#close-detail, #overlay").click(function() {
                $("#overlay").fadeOut();
                $("#detail-container").fadeOut();
            });
        });
    </script>

</body>
</html>
