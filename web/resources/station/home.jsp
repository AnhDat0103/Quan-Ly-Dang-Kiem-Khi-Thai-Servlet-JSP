<%-- 
    Document   : home
    Created on : Jan 31, 2025, 11:43:02 PM
    Author     : DAT
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Quản lý Kiểm định - Trung tâm đăng kiểm</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
        <style>
            html, body {
                height: 100%;
                margin: 0;
                display: flex;
                flex-direction: column;
            }

            body > .container {
                flex: 1 0 auto;
            }

            footer {
                flex-shrink: 0;
            }

            .hover-opacity:hover {
                opacity: 0.8;
                transition: opacity 0.2s ease-in-out;
            }

            footer h5 {
                border-bottom: 2px solid rgba(255,255,255,0.1);
                padding-bottom: 0.5rem;
                margin-bottom: 1rem;
            }
        </style>
    </head>
    <body>
        <!-- Navbar -->
        <%@include file="../layout/navbar.jsp" %>

        <!-- Main content -->
        <div class="container mt-4">
            <div class="row">
                <!-- Thống kê -->
                <div class="col-md-4">
                    <div class="card mb-4">
                        <h5 class="card-header">Hôm nay</h5>
                        <div class="card-body">   
                            <div class="row">
                                <div class="col">
                                    <div class="text-muted">Lịch hẹn</div>
                                    <h3>${requestScope.InspecRecordsSum}</h3>
                                </div>
                                <div class="col">
                                    <div class="text-muted">Đã kiểm định</div>
                                    <h3>${requestScope.InspecedRecordsSum}</h3>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- Danh sách xe cần kiểm định -->
                <div class="col-md-8">
                    <div class="card">
                        <div class="card-header">
                            <div class="d-flex justify-content-between align-items-center">
                                <h5 class="card-title mb-0">Kết quả kiểm định</h5>    
                            </div>
                        </div>
                        <c:choose>
                            <c:when test="${empty requestScope.InspecedtionRecords}">
                                <div class="alert text-center errorEmpty" role="alert">
                                    Hiện chưa có đăng kiểm nào được tìm thấy.
                                </div>
                            </c:when>
                            <c:otherwise>
                                <div class="card-body">
                                    <table class="table">
                                        <thead>
                                            <tr>
                                                <th>Ngày</th>
                                                <th>Biển số</th>
                                                <th>Chủ xe</th>
                                                <th>Kết quả</th>
                                                <th>Trạng thái</th>
                                                <th>Chi tiết</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach var="record" items="${InspecedtionRecords}">
                                                <tr>
                                                    <td>${record.inspectionDate}</td>
                                                    <td>${record.vehicle.plateNumber}</td>
                                                    <td>${record.vehicle.owner.fullName}</td>
                                                    <td><span class="badge resultStatus">${record.result}</span></td>
                                                    <td><span class="badge bg-success">Đã kiểm định</span></td>
                                                    <td>
                                                        <button class="btn btn-sm btn-info view-detail" data-bs-toggle="modal" 
                                                                data-bs-target="#detailModal"
                                                                data-plate="${record.vehicle.plateNumber}"
                                                                data-owner="${record.vehicle.owner.fullName}"
                                                                data-date="${record.inspectionDate}"
                                                                data-engine="${record.vehicle.engineNumber}"
                                                                data-result="${record.result}"
                                                                data-model="${record.vehicle.model}"
                                                                data-comment="${record.comments}">
                                                            <i class="bi bi-eye"></i> Xem
                                                        </button>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>

                                <nav class="mt-3">
                                    <ul class="pagination justify-content-center">
                                        <li class="page-item ${currentPage != 1 ? '' : 'disabled'}">
                                            <a class="page-link" href="trung-tam-dang-kiem?trang-so=${currentPage - 1}"><i class="bi bi-chevron-left"></i></a>
                                        </li>
                                        <c:forEach begin="1" end="${noOfPage}" var="i">
                                            <li class="page-item ${currentPage == i ? 'active' : ''}"><a class="page-link" href="trung-tam-dang-kiem?trang-so=${i}">${i}</a></li>
                                            </c:forEach>
                                        <li class="page-item ${currentPage lt noOfPage ? '' : 'disabled'}">
                                            <a class="page-link" href="trung-tam-dang-kiem?trang-so=${currentPage + 1}"><i class="bi bi-chevron-right"></i></a>
                                        </li>
                                    </ul>
                                </nav>
                            </c:otherwise>
                        </c:choose>

                    </div>
                </div>
            </div>
        </div>

        <!-- Modal nhập thông tin khi stationId null -->

        <div class="modal fade" id="stationModal" tabindex="-1" aria-labelledby="stationModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="stationModalLabel">Cập nhật nơi làm việc</h5>
                    </div>
                    <form action="cap-nhat-thong-tin" method="POST">
                        <div class="modal-body">
                            <input type="hidden" name="action" value="change-location">
                            <div class="mb-3">
                                <label class="form-label">Cơ sở làm việc</label>
                                <select class="form-control" name="inspecStation" required>
                                    <option value="">-- Chọn cơ sở --</option>
                                    <c:forEach var="s" items="${requestScope.stations}">
                                        <option value="${s.stationId}">${s.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="submit" class="btn btn-primary">Lưu</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <!-- Modal Chi tiết kiểm định -->
        <div class="modal fade" id="detailModal" tabindex="-1">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">Chi tiết kiểm định</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                    </div>
                    <div class="modal-body">
                        <div class="row mb-3">
                            <div class="col-md-6">
                                <h6>Thông tin chung</h6>
                                <table class="table table-bordered">
                                    <tr>
                                        <th>Chủ xe:</th>
                                        <td id="modalOwner"></td>
                                    </tr>
                                    <tr>
                                        <th>Hãng xe:</th>
                                        <td id="modalModel"></td>
                                    </tr>
                                    <tr>
                                        <th>Ngày kiểm định:</th>
                                        <td id="modalDate"></td>
                                    </tr>
                                    <tr>
                                        <th>Biển số:</th>
                                        <td id="modalPlateNumber"></td>
                                    </tr>
                                    <tr>
                                        <th>Số máy:</th>
                                        <td id="modalEngine"></td>
                                    </tr>
                                </table>
                            </div>
                            <div class="col-md-6">
                                <h6>Kết quả kiểm tra</h6>
                                <table class="table table-bordered">
                                    <tr>
                                        <th>Hệ thống phanh:</th>
                                        <td><span class="badge bg-success">Pass</span></td>
                                    </tr>
                                    <tr>
                                        <th>Hệ thống lái:</th>
                                        <td><span class="badge bg-success">Pass</span></td>
                                    </tr>
                                    <tr>
                                        <th>Khí thải:</th>
                                        <td><span id="modelResult" class="badge"></span></td>
                                    </tr>
                                    <tr>
                                        <th>Đèn chiếu sáng:</th>
                                        <td><span class="badge bg-success">Pass</span></td>
                                    </tr>
                                    <tr>
                                        <th>Gầm xe:</th>
                                        <td><span class="badge bg-success">Pass</span></td>
                                    </tr>
                                </table>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-12">
                                <h6>Ghi chú</h6>
                                <textarea class="form-control" rows="3" id="modelComment" readonly></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
                    </div>
                </div>
            </div>
        </div>

        <!-- Footer -->
        <%@include file="../layout/footer.jsp" %>

        <!-- Thêm Bootstrap Icons -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.2/font/bootstrap-icons.css">
        <!-- Thêm Bootstrap JS và Popper.js -->
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js"></script>
        <script>
            document.addEventListener("DOMContentLoaded", function () {
                document.querySelectorAll(".view-detail").forEach(button => {
                    button.addEventListener("click", function () {
                        document.getElementById("modalModel").innerText = this.getAttribute("data-model");
                        document.getElementById("modalOwner").innerText = this.getAttribute("data-owner");
                        document.getElementById("modalDate").innerText = this.getAttribute("data-date");
                        document.getElementById("modalEngine").innerText = this.getAttribute("data-engine");
                        document.getElementById("modalPlateNumber").innerText = this.getAttribute("data-plate");
                        document.getElementById("modelComment").innerText = this.getAttribute("data-comment");

                        // Cập nhật trạng thái kiểm tra
                        setBadge("modelResult", this.getAttribute("data-result"));
                    });
                });
                document.querySelectorAll(".resultStatus").forEach(span => {
                    setBadge1(span, span.innerText);
                });
                let stationId = "${currentUser.inspectionStation.stationId}";
                console.log("Station ID:", stationId);
                if (!stationId || stationId === "0") {
                    let stationModal = new bootstrap.Modal(document.getElementById("stationModal"));
                    stationModal.show();
                }
            });

// Hàm cập nhật màu sắc cho badge
            function setBadge(elementId, status) {
                const element = document.getElementById(elementId);
                element.innerText = status;
                if (status === 'Accepted') {
                    element.className = "badge bg-info text-dark modelResult";
                } else {
                    element.className = "badge " + (status === "Pass" ? "bg-success" : "bg-danger");
                }
            }
            function setBadge1(element, status) {
                element.innerText = status;
                if (status === 'Accepted') {
                    element.className = "badge bg-info text-dark modelResult";
                } else {
                    element.className = "badge " + (status === "Pass" ? "bg-success" : "bg-danger");
                }
            }
        </script>
    </body>
</html>