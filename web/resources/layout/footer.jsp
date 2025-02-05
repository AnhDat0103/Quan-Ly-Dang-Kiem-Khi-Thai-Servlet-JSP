<%-- 
    Document   : footer
    Created on : Jan 31, 2025, 11:49:24 PM
    Author     : DAT
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
    <footer class="bg-light text-dark mt-5 border-top">
        <div class="container py-3">
            <div class="row">
                <div class="col-md-4 mb-3">
                    <h6 class="text-success fw-bold">Trung tâm Đăng kiểm</h6>
                    <p class="mb-1 small"><i class="bi bi-geo-alt-fill me-2"></i>${currentUser.inspectionStation.address}</p>
                    <p class="mb-1 small"><i class="bi bi-telephone-fill me-2"></i>${currentUser.inspectionStation.phone}</p>
                    <p class="mb-1 small"><i class="bi bi-envelope-fill me-2"></i>${currentUser.inspectionStation.email}</p>
                </div>
                <div class="col-md-4 mb-3">
                    <h6 class="text-success fw-bold">Giờ làm việc</h6>
                    <p class="mb-1 small">Thứ Hai - Thứ Sáu: 7:30 - 17:30</p>
                    <p class="mb-1 small">Thứ Bảy: 8:00 - 12:00</p>
                    <p class="mb-1 small">Chủ Nhật: Nghỉ</p>
                </div>
                <div class="col-md-4 mb-3">
                    <h6 class="text-success fw-bold">Liên kết nhanh</h6>
                    <ul class="list-unstyled">
                        <li class="mb-1"><a href="#" class="text-muted text-decoration-none small hover-opacity"><i class="bi bi-chevron-right me-1"></i>Đặt lịch hẹn</a></li>
                        <li class="mb-1"><a href="#" class="text-muted text-decoration-none small hover-opacity"><i class="bi bi-chevron-right me-1"></i>Tra cứu kết quả</a></li>
                        <li class="mb-1"><a href="#" class="text-muted text-decoration-none small hover-opacity"><i class="bi bi-chevron-right me-1"></i>Hướng dẫn thủ tục</a></li>
                        <li class="mb-1"><a href="#" class="text-muted text-decoration-none small hover-opacity"><i class="bi bi-chevron-right me-1"></i>Câu hỏi thường gặp</a></li>
                    </ul>
                </div>
            </div>
        </div>
        <div class="border-top py-2">
            <div class="container text-center">
                <small class="text-muted">&copy; 2025 Trung tâm Đăng kiểm. Tất cả quyền được bảo lưu.</small>
            </div>
        </div>
    </footer>
