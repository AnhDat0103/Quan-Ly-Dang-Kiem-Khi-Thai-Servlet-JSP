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
                    <div class="card-body">
                        <h5 class="card-title">Hôm nay</h5>
                        <div class="row">
                            <div class="col">
                                <div class="text-muted">Lịch hẹn</div>
                                <h3>15</h3>
                            </div>
                            <div class="col">
                                <div class="text-muted">Đã kiểm định</div>
                                <h3>8</h3>
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
                            <h5 class="card-title mb-0">Lịch kiểm định</h5>
                            <div class="current-time">
                                <strong>Hôm nay: </strong>
                                <span id="current-time"></span>
                            </div>
                        </div>
                    </div>
                    <div class="card-body">
                        <table class="table">
                            <thead>
                                <tr>
                                    <th>Giờ hẹn</th>
                                    <th>Biển số</th>
                                    <th>Chủ xe</th>
                                    <th>Kết quả</th>
                                    <th>Trạng thái</th>
                                    <th>Chi tiết</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>09:00</td>
                                    <td>AB-12345</td>
                                    <td>Nguyễn Văn A</td>
                                    <td><span class="badge bg-success">Đạt</span></td>
                                    <td><span class="badge bg-warning">Chờ kiểm định</span></td>
                                    <td>
                                        <button class="btn btn-sm btn-info " data-bs-toggle="modal" data-bs-target="#detailModal">
                                            <i class="bi bi-eye"></i> Xem
                                        </button>
                                    </td>
                                </tr>
                                <tr>
                                    <td>09:00</td>
                                    <td>AB-12345</td>
                                    <td>Nguyễn Văn A</td>
                                    <td><span class="badge bg-success">Đạt</span></td>
                                    <td><span class="badge bg-warning">Chờ kiểm định</span></td>
                                    <td>
                                        <button class="btn btn-sm btn-info " data-bs-toggle="modal" data-bs-target="#detailModal">
                                            <i class="bi bi-eye"></i> Xem
                                        </button>
                                    </td>
                                </tr>
                                <tr>
                                    <td>09:00</td>
                                    <td>AB-12345</td>
                                    <td>Nguyễn Văn A</td>
                                    <td><span class="badge bg-success">Đạt</span></td>
                                    <td><span class="badge bg-warning">Chờ kiểm định</span></td>
                                    <td>
                                        <button class="btn btn-sm btn-info " data-bs-toggle="modal" data-bs-target="#detailModal">
                                            <i class="bi bi-eye"></i> Xem
                                        </button>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Modal Chi tiết kiểm định -->
    <div class="modal fade" id="detailModal" tabindex="-1">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">Chi tiết kiểm định - Biển số: AB-12345</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                </div>
                <div class="modal-body">
                    <div class="row mb-3">
                        <div class="col-md-6">
                            <h6>Thông tin chung</h6>
                            <table class="table table-bordered">
                                <tr>
                                    <th>Chủ xe:</th>
                                    <td>Nguyễn Văn A</td>
                                </tr>
                                <tr>
                                    <th>Loại xe:</th>
                                    <td>Ô tô con</td>
                                </tr>
                                <tr>
                                    <th>Ngày kiểm định:</th>
                                    <td>18/03/2024</td>
                                </tr>
                                <tr>
                                    <th>Số khung:</th>
                                    <td>XXXXXXXXXXXXXX</td>
                                </tr>
                                <tr>
                                    <th>Số máy:</th>
                                    <td>YYYYYYYYYYYYYY</td>
                                </tr>
                            </table>
                        </div>
                        <div class="col-md-6">
                            <h6>Kết quả kiểm tra</h6>
                            <table class="table table-bordered">
                                <tr>
                                    <th>Hệ thống phanh:</th>
                                    <td><span class="badge bg-success">Đạt</span></td>
                                </tr>
                                <tr>
                                    <th>Hệ thống lái:</th>
                                    <td><span class="badge bg-success">Đạt</span></td>
                                </tr>
                                <tr>
                                    <th>Khí thải:</th>
                                    <td><span class="badge bg-success">Đạt</span></td>
                                </tr>
                                <tr>
                                    <th>Đèn chiếu sáng:</th>
                                    <td><span class="badge bg-success">Đạt</span></td>
                                </tr>
                                <tr>
                                    <th>Gầm xe:</th>
                                    <td><span class="badge bg-success">Đạt</span></td>
                                </tr>
                            </table>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-12">
                            <h6>Ghi chú</h6>
                            <textarea class="form-control" rows="3" readonly>Xe đạt tiêu chuẩn kiểm định. Cần bảo dưỡng định kỳ theo khuyến cáo của nhà sản xuất.</textarea>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-primary">In kết quả</button>
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
    
    <!-- Script hiển thị thời gian hiện tại -->
    <script>
        function updateCurrentTime() {
            const now = new Date();
            const dateString = now.toLocaleDateString('vi-VN', {
                weekday: 'long',
                year: 'numeric',
                month: 'long',
                day: 'numeric'
            });
            const timeString = now.toLocaleTimeString('vi-VN', {
                hour: '2-digit',
                minute: '2-digit',
                second: '2-digit'
            });
            document.getElementById('current-time').textContent = `${dateString} ${timeString}`;
        }
        
        // Cập nhật thời gian mỗi giây
        updateCurrentTime();
        setInterval(updateCurrentTime, 1000);
    </script>
</body>
</html>