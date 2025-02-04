<%-- 
    Document   : appointment
    Created on : Feb 4, 2025, 9:45:34 PM
    Author     : DAT
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                <div class="d-flex gap-2">
                    <input type="text" class="form-control" placeholder="Tìm kiếm...">
                    <button class="btn btn-success">
                        <i class="bi bi-search"></i>
                    </button>
                    <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#addAppointmentModal">
                        <i class="bi bi-plus-lg"></i> Thêm lịch hẹn
                    </button>
                </div>
            </div>
        </div>

        <!-- Bộ lọc -->
        <div class="row mb-4">
            <div class="col-md-12">
                <div class="card">
                    <div class="card-body">
                        <div class="row g-3">
                            <div class="col-md-3">
                                <label class="form-label">Từ ngày</label>
                                <input type="date" class="form-control">
                            </div>
                            <div class="col-md-3">
                                <label class="form-label">Đến ngày</label>
                                <input type="date" class="form-control">
                            </div>
                            <div class="col-md-3">
                                <label class="form-label">Trạng thái</label>
                                <select class="form-select">
                                    <option value="">Tất cả</option>
                                    <option value="pending">Chờ xác nhận</option>
                                    <option value="confirmed">Đã xác nhận</option>
                                    <option value="completed">Đã hoàn thành</option>
                                    <option value="cancelled">Đã hủy</option>
                                </select>
                            </div>
                            <div class="col-md-3">
                                <label class="form-label">&nbsp;</label>
                                <button class="btn btn-success d-block w-100">Lọc</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Bảng lịch hẹn -->
        <div class="card">
            <div class="card-body">
                <table class="table table-hover">
                    <thead>
                        <tr>
                            <th>Mã lịch hẹn</th>
                            <th>Ngày hẹn</th>
                            <th>Giờ hẹn</th>
                            <th>Biển số xe</th>
                            <th>Chủ xe</th>
                            <th>Số điện thoại</th>
                            <th>Trạng thái</th>
                            <th>Thao tác</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>APT001</td>
                            <td>20/03/2024</td>
                            <td>09:00</td>
                            <td>51F-123.45</td>
                            <td>Nguyễn Văn A</td>
                            <td>0901234567</td>
                            <td><span class="badge bg-warning">Chờ xác nhận</span></td>
                            <td>
                                <div class="btn-group">
                                    <button class="btn btn-sm btn-info" data-bs-toggle="modal" data-bs-target="#viewAppointmentModal">
                                        <i class="bi bi-eye"></i>
                                    </button>
                                    <button class="btn btn-sm btn-primary" data-bs-toggle="modal" data-bs-target="#editAppointmentModal">
                                        <i class="bi bi-pencil"></i>
                                    </button>
                                    <button class="btn btn-sm btn-danger">
                                        <i class="bi bi-trash"></i>
                                    </button>
                                </div>
                            </td>
                        </tr>
                        <!-- Thêm các dòng khác tương tự -->
                    </tbody>
                </table>

                <!-- Phân trang -->
                <nav class="mt-3">
                    <ul class="pagination justify-content-center">
                        <li class="page-item disabled">
                            <a class="page-link" href="#"><i class="bi bi-chevron-left"></i></a>
                        </li>
                        <li class="page-item active"><a class="page-link" href="#">1</a></li>
                        <li class="page-item"><a class="page-link" href="#">2</a></li>
                        <li class="page-item"><a class="page-link" href="#">3</a></li>
                        <li class="page-item">
                            <a class="page-link" href="#"><i class="bi bi-chevron-right"></i></a>
                        </li>
                    </ul>
                </nav>
            </div>
        </div>
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
                            <label class="form-label">Giờ hẹn</label>
                            <input type="time" class="form-control" required>
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
                        <div class="col-8">APT001</div>
                    </div>
                    <div class="row mb-3">
                        <div class="col-4"><strong>Biển số xe:</strong></div>
                        <div class="col-8">51F-123.45</div>
                    </div>
                    <div class="row mb-3">
                        <div class="col-4"><strong>Chủ xe:</strong></div>
                        <div class="col-8">Nguyễn Văn A</div>
                    </div>
                    <div class="row mb-3">
                        <div class="col-4"><strong>Số điện thoại:</strong></div>
                        <div class="col-8">0901234567</div>
                    </div>
                    <div class="row mb-3">
                        <div class="col-4"><strong>Ngày hẹn:</strong></div>
                        <div class="col-8">20/03/2024</div>
                    </div>
                    <div class="row mb-3">
                        <div class="col-4"><strong>Giờ hẹn:</strong></div>
                        <div class="col-8">09:00</div>
                    </div>
                    <div class="row mb-3">
                        <div class="col-4"><strong>Trạng thái:</strong></div>
                        <div class="col-8"><span class="badge bg-warning">Chờ xác nhận</span></div>
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
</body>
</html> 