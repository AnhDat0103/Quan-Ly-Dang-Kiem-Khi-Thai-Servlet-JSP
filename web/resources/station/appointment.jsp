<%-- 
    Document   : appointment
    Created on : Feb 4, 2025, 9:45:34 PM
    Author     : DAT
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Quản lý Lịch hẹn - Trung tâm đăng kiểm</title>
        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">

        <!-- Font Awesome -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet">

        <!-- DataTables -->
        <link href="https://cdn.datatables.net/1.11.5/css/dataTables.bootstrap5.min.css" rel="stylesheet">

        <!-- Custom CSS -->
        <link href="resources/css/sb-admin-2.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.2/font/bootstrap-icons.css">

        <style>
            .hover-opacity:hover {
                opacity: 0.8;
                transition: opacity 0.2s ease-in-out;
            }

            footer h6 {
                border-bottom: 2px solid rgba(0,0,0,0.1);
                padding-bottom: 0.5rem;
                margin-bottom: 1rem;
            }
        </style>
    </head>
    <body>
        <!-- Navbar - giống như trang dashboard -->
        <%@include file="../layout/navbar.jsp" %>

        <div class="container mt-4">
            <!-- Tiêu đề và công cụ tìm kiếm -->
            <div class="row mb-4">
                <div class="col-md-6">
                    <h3>Quản lý Lịch hẹn</h3>
                </div>
                <div class="col-md-6">
                    <form action="quan-ly-lich-hen" method="GET">
                        <input type="hidden" name="action" value="tim-kiem">
                        <div class="d-flex gap-2">
                            <input type="text" class="form-control" placeholder="Tìm kiếm..." name="tu-khoa-tim-kiem" value="${searchKeyWord}" required>
                            <button type="submit" class="btn btn-success">
                                <i class="bi bi-search"></i>
                            </button>

                            <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#addAppointmentModal">
                                <i class="bi bi-plus-lg"></i> Thêm lịch hẹn
                            </button>
                        </div>
                    </form>
                </div>


            </div>

            <!-- Bộ lọc -->
            <div class="row mb-4">
                <div class="col-md-12">
                    <div class="card">
                        <div class="card-body">
                            <form action="quan-ly-lich-hen" method="GET">
                                <input type="hidden" name="action" value="loc-theo-thoi-gian">
                                <div class="row g-3">
                                    <div class="col-md-3">
                                        <label class="form-label">Từ ngày</label>
                                        <input type="date" class="form-control" name="start-date" value="${startDateKey}" required>
                                    </div>
                                    <div class="col-md-3">
                                        <label class="form-label">Đến ngày</label>
                                        <input type="date" class="form-control" name="end-date" value="${endDateKey}" required>
                                    </div>
                                    <div class="col-md-3">
                                        <label class="form-label">Trạng thái</label>
                                        <select class="form-select" name="trang-thai">
                                            <option value="all" ${statusFiltered == 'all' ? 'selected' : ''}>Tất cả</option>
                                            <option value="pending" ${statusFiltered == 'pending' ? 'selected' : ''}>Chờ xác nhận</option>
                                            <option value="pass" ${statusFiltered == 'pass' ? 'selected' : ''}>Đã Pass</option>
                                            <option value="not-pass" ${statusFiltered == 'not-pass' ? 'selected' : ''}>Chưa Pass</option>
                                        </select>
                                    </div>
                                    <div class="col-md-3">
                                        <label class="form-label">&nbsp;</label>
                                        <button class="btn btn-success d-block w-100">Lọc</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Bảng lịch hẹn -->
            <div class="card">
                <c:if test="${empty requestScope.listEmpty}">
                    <div class="card-body">
                        <table class="table table-hover">
                            <thead>
                                <tr>
                                    <th>Mã lịch hẹn</th>
                                    <th>Ngày hẹn</th>
                                    <th>Biển số xe</th>
                                    <th>Chủ xe</th>
                                    <th>Số điện thoại</th>
                                    <th>Trạng thái</th>
                                    <th>Thao tác</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${inspectionPedding}" var="r">
                                    <tr>
                                        <td>${r.recordId}</td>
                                        <td>${r.inspectionDate}</td>
                                        <td>${r.vehicle.plateNumber}</td>
                                        <td>${r.vehicle.owner.fullName}</td>
                                        <td>${r.vehicle.owner.phone}</td>
                                        <td><span class="badge modelResult">${r.result}</span></td>
                                        <td>
                                            <div class="btn-group">
                                                <button class="btn btn-sm btn-info view-detail" data-bs-toggle="modal" 
                                                        data-bs-target="#viewAppointmentModal"
                                                        data-inspectId="${r.recordId}"
                                                        data-plate="${r.vehicle.plateNumber}"
                                                        data-owner="${r.vehicle.owner.fullName}"
                                                        data-phone="${r.vehicle.owner.phone}"
                                                        data-inspectDate="${r.inspectionDate}"
                                                        data-result="${r.result}">
                                                    <i class="bi bi-eye"></i>
                                                </button>

                                                <button class="btn btn-sm btn-danger">
                                                    <i class="bi bi-trash"></i>
                                                </button>
                                            </div>
                                        </td>
                                    </tr>
                                </c:forEach>

                            </tbody>
                        </table>

                        <!-- Phân trang -->
                        <nav class="mt-3">
                            <ul class="pagination justify-content-center">
                                <li class="page-item ${currentPage != 1 ? '' : 'disabled'} ">
                                    <a class="page-link" href="quan-ly-lich-hen?action=${action}&trang-so=${currentPage - 1}&tu-khoa-tim-kiem=${searchKeyWord}&start-date=${startDateKey}&end-date=${endDateKey}&trang-thai=${statusFiltered}"><i class="bi bi-chevron-left"></i></a>
                                </li>
                                <c:forEach begin="1" end="${noOfPage}" var="i">
                                    <li class="page-item ${currentPage == i ? 'active' : ''}"><a class="page-link" href="quan-ly-lich-hen?action=${action}&trang-so=${i}&tu-khoa-tim-kiem=${searchKeyWord}&start-date=${startDateKey}&end-date=${endDateKey}&trang-thai=${statusFiltered}">${i}</a></li>
                                    </c:forEach>
                                <li class="page-item ${currentPage lt noOfPage ? '' : 'disabled'}">
                                    <a class="page-link" href="quan-ly-lich-hen?action=${action}&trang-so=${currentPage + 1}&tu-khoa-tim-kiem=${searchKeyWord}&start-date=${startDateKey}&end-date=${endDateKey}&trang-thai=${statusFiltered}"><i class="bi bi-chevron-right"></i></a>
                                </li>
                            </ul>
                        </nav>
                    </div>
                </c:if>
            </div>
            <c:if test="${not empty listEmpty}">
                <div class="alert alert-dark text-center" role="alert">
                    ${requestScope.listEmpty}
                </div>
            </c:if>

        </div>

        <!-- Modal Thêm lịch hẹn -->
        <div class="modal fade" id="addAppointmentModal" tabindex="-1">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">Thêm lịch hẹn mới</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                    </div>
                    <div class="modal-body">
                        <form>
                            <div class="mb-3">
                                <label class="form-label">Biển số xe</label>
                                <input type="text" class="form-control" required>
                            </div>
                            <div class="mb-3">
                                <label class="form-label">Chủ xe</label>
                                <input type="text" class="form-control" required>
                            </div>
                            <div class="mb-3">
                                <label class="form-label">Số điện thoại</label>
                                <input type="tel" class="form-control" required>
                            </div>
                            <div class="mb-3">
                                <label class="form-label">Ngày hẹn</label>
                                <input type="date" class="form-control" required>
                            </div>
                            <div class="mb-3">
                                <label class="form-label">Ghi chú</label>
                                <textarea class="form-control" rows="3"></textarea>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
                        <button type="button" class="btn btn-primary">Lưu lịch hẹn</button>
                    </div>
                </div>
            </div>
        </div>

        <!-- Modal Xem chi tiết -->
        <div class="modal fade" id="viewAppointmentModal" tabindex="-1">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">Chi tiết lịch hẹn</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                    </div>
                    <div class="modal-body">
                        <div class="row mb-3">
                            <div class="col-4"><strong>Mã lịch hẹn:</strong></div>
                            <div class="col-8" id="modalInspecId"></div>
                        </div>
                        <div class="row mb-3">
                            <div class="col-4"><strong>Biển số xe:</strong></div>
                            <div class="col-8" id="modalPlateNumber"></div>
                        </div>
                        <div class="row mb-3">
                            <div class="col-4"><strong>Chủ xe:</strong></div>
                            <div class="col-8" id="modalOwnerName"></div>
                        </div>
                        <div class="row mb-3">
                            <div class="col-4"><strong>Số điện thoại:</strong></div>
                            <div class="col-8" id="modalOwnerPhone"></div>
                        </div>
                        <div class="row mb-3">
                            <div class="col-4"><strong>Ngày hẹn:</strong></div>
                            <div class="col-8" id="modalDate"></div>
                        </div>
                        <div class="row mb-3">
                            <div class="col-4"><strong>Trạng thái:</strong></div>
                            <div class="col-8"><span class="badge modelResult"></span></div>
                        </div>
                        <div class="row mb-3">
                            <div class="col-4"><strong>Ghi chú:</strong></div>
                            <div class="col-8">Không có ghi chú</div>
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

        <!-- Scripts -->
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.4.1/jquery.easing.min.js"></script>
        <script src="https://cdn.datatables.net/1.11.5/js/jquery.dataTables.min.js"></script>
        <script src="https://cdn.datatables.net/1.11.5/js/dataTables.bootstrap5.min.js"></script>
        <script src="resources/js/sb-admin-2.min.js"></script>
        <script src="resources/js/appointments.js"></script>
        <script>
            document.addEventListener("DOMContentLoaded", function () {
                document.querySelectorAll(".view-detail").forEach(button => {
                    button.addEventListener("click", function () {
                        document.getElementById("modalInspecId").innerText = this.getAttribute("data-inspectId");
                        document.getElementById("modalOwnerName").innerText = this.getAttribute("data-owner");
                        document.getElementById("modalDate").innerText = this.getAttribute("data-inspectDate");
                        document.getElementById("modalOwnerPhone").innerText = this.getAttribute("data-phone");
                        document.getElementById("modalPlateNumber").innerText = this.getAttribute("data-plate");
                        const modalBadge = document.querySelector("#viewAppointmentModal .modelResult");
                        if (modalBadge) {
                            setBadge(modalBadge, this.getAttribute("data-result"));
                        }
                    });
                });
                document.querySelectorAll(".modelResult").forEach(span => {
                    setBadge(span, span.innerText);
                });
            });

            function setBadge(element, status) {
                element.innerText = status;
                if (status === "Pending") {
                    element.className = "badge bg-warning modelResult";
                } else {
                    element.className = "badge " + (status === "Pass" ? "bg-success" : "bg-danger") + " modelResult";
                }
            }
        </script>
    </body>
</html> 