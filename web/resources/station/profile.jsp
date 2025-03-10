<%-- 
    Document   : profile
    Created on : Feb 7, 2025, 11:21:35 PM
    Author     : DAT
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Thông tin cá nhân - Trung tâm đăng kiểm</title>

        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">

        <!-- Font Awesome -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet">

        <style>
            .alert {
                padding: 15px;
                margin: 10px 0;
                border-radius: 5px;
                font-weight: bold;
                text-align: center;
            }
            .alert-success {
                background-color: #d4edda;
                color: #155724;
                border: 1px solid #c3e6cb;
            }
            .alert-error {
                background-color: #f8d7da;
                color: #721c24;
                border: 1px solid #f5c6cb;
            }
        </style>

        <!-- Custom CSS -->
        <link href="resources/css/sb-admin-2.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.2/font/bootstrap-icons.css">
    </head>
    <body id="page-top">
        <%@include file="../layout/navbar.jsp"%>
        <div class="container mt-4">
<!--            <div class="row">-->
                <div id="wrapper">


                    <div id="content-wrapper" class="d-flex flex-column">
                        <% 
               String status = request.getParameter("status");
               if ("success".equals(status)) { 
                        %>
                        <div class="alert alert-success">Cập nhật thành công!</div>
                        <% } else if ("error".equals(status)) { %>
                        <div class="alert alert-error">Cập nhật thất bại! Vui lòng kiểm tra lại.</div>
                        <% } %>
                        <div id="content">

                            <!-- Begin Page Content -->
                            <div class="container-fluid">

                                <!-- Page Heading -->
                                <h1 class="h3 mb-4 text-gray-800">Thông tin cá nhân</h1>

                                <div class="row">
                                    <div class="col-lg-4">
                                        <!-- Profile Picture Card --> 
                                        <div class="card shadow mb-4">
                                            <div class="card-header py-3">
                                                <h6 class="m-0 font-weight-bold text-primary">Ảnh đại diện</h6>
                                            </div>
                                            <div class="card-body text-center">
                                                <img class="img-profile rounded-circle mb-3" src="resources/images/${currentUser.avatar}" 
                                                     style="width: 150px; height: 150px;">
                                                <div class="mb-3 d-lg-flex justify-content-center">
                                                    <button class="btn btn-primary btn-sm" id="changeAvatarBtn">
                                                        <i class="fas fa-camera mr-2"></i>Chọn ảnh
                                                    </button>
                                                    <div>
                                                        <form action="cap-nhat-thong-tin" method="POST" enctype="multipart/form-data">
                                                            <input type="hidden" name="action" value="change-avatar">
                                                            <input type="file" id="avatarInput" accept="image/*" style="display: none;" name="newAvatar">
                                                            <button class="btn btn-success btn-sm" id="saveAvatarBtn" style="display: none;">
                                                                <i class="fas fa-save mr-2"></i>Lưu ảnh
                                                            </button>
                                                        </form> 
                                                    </div>
                                                </div>

                                            </div>
                                        </div>



                                        <!-- Account Settings Card -->
                                        <div class="card shadow mb-4">
                                            <div class="card-header py-3">
                                                <h6 class="m-0 font-weight-bold text-primary">Cài đặt tài khoản</h6>
                                            </div>
                                            <div class="card-body">
                                                <div class="mb-3">
                                                    <button class="btn btn-warning btn-block" data-bs-toggle="modal" 
                                                            data-bs-target="#changePasswordModal">
                                                        <i class="fas fa-key mr-2"></i>Đổi mật khẩu
                                                    </button>
                                                </div>
                                                <!--                                <div class="mb-3">
                                                                                        <button class="btn btn-info btn-block" data-bs-toggle="modal" 
                                                                                                data-bs-target="#notificationSettingsModal">
                                                                                            <i class="fas fa-bell mr-2"></i>Cài đặt thông báo
                                                                                        </button>
                                                                                    </div>-->
                                                <div class="mb-3">
                                                    <button class="btn btn-danger btn-block" data-bs-toggle="modal" 
                                                            data-bs-target="#notificationSettingsModal">
                                                        <i class="fas fa-trash"></i>Xóa tài khoản
                                                    </button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="col-lg-8">
                                        <!-- Personal Information Card -->
                                        <div class="card shadow mb-4">
                                            <div class="card-header py-3 d-flex justify-content-between align-items-center">
                                                <h6 class="m-0 font-weight-bold text-primary">Thông tin cá nhân</h6>
                                                <button class="btn btn-primary btn-sm" id="editProfileBtn">
                                                    <i class="fas fa-edit mr-2"></i>Chỉnh sửa
                                                </button>
                                            </div>
                                            <div class="card-body">
                                                <form id="profileForm" action="cap-nhat-thong-tin" method="POST">
                                                    <div class="row mb-3">
                                                        <div class="col-md-6" style="display: none">
                                                            <input type="text" value="${requestScope.currentUser.userId}">
                                                        </div>
                                                        <div class="col-md-6">
                                                            <label class="form-label">Họ và tên</label>
                                                            <input type="text" class="form-control" name="fullName" value="${sessionScope.currentUser.fullName}" disabled>
                                                        </div>
                                                        <div class="col-md-6">
                                                            <label class="form-label">Email</label>
                                                            <input type="email" class="form-control" value="${sessionScope.currentUser.email}" readonly>
                                                        </div>
                                                    </div>
                                                    <div class="row mb-3">
                                                        <div class="col-md-6">
                                                            <label class="form-label">Số điện thoại</label>
                                                            <input type="tel" class="form-control" name="phone" value="${sessionScope.currentUser.phone}" disabled>
                                                        </div>
                                                        <div class="col-md-6">
                                                            <label class="form-label">Chức vụ</label>
                                                            <input type="text" class="form-control" disabled readonly value="Quản lý hành chính">
                                                        </div>
                                                    </div>

                                                    <div class="mb-3">
                                                        <label class="form-label">Địa chỉ</label>
                                                        <textarea class="form-control" name="address" rows="2" disabled>${sessionScope.currentUser.address}</textarea>
                                                    </div>
                                                    <div class="text-end" style="display: none;" id="saveButtons">
                                                        <button type="button" class="btn btn-secondary me-2" id="cancelBtn">Hủy</button>
                                                        <input type="submit" class="btn btn-primary" value="Lưu thay đổi">
                                                    </div>
                                                </form>
                                            </div>
                                        </div>

                                        <!-- Activity Log Card -->
                                        <!--                            <div class="card shadow mb-4">
                                                                        <div class="card-header py-3">
                                                                            <h6 class="m-0 font-weight-bold text-primary">Lịch sử hoạt động</h6>
                                                                        </div>
                                                                        <div class="card-body">
                                                                            <div class="timeline">
                                                                                <div class="timeline-item">
                                                                                    <div class="timeline-date">20/03/2024</div>
                                                                                    <div class="timeline-content">
                                                                                        <div class="text-primary">Đăng nhập hệ thống</div>
                                                                                        <small class="text-muted">08:30 AM</small>
                                                                                    </div>
                                                                                </div>
                                                                                <div class="timeline-item">
                                                                                    <div class="timeline-date">19/03/2024</div>
                                                                                    <div class="timeline-content">
                                                                                        <div class="text-success">Cập nhật thông tin cá nhân</div>
                                                                                        <small class="text-muted">15:45 PM</small>
                                                                                    </div>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                    </div>-->
                                    </div>
                                </div>
                            </div>
                        </div>

                        <!-- Footer -->
                        <%@include file="../layout/footer.jsp" %>
                    </div>
                </div>

                <!-- Change Password Modal -->
                <div class="modal fade" id="changePasswordModal" tabindex="-1">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title">Đổi mật khẩu</h5>
                                <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                            </div>
                            <form id="changePasswordForm" action="cap-nhat-thong-tin" method="POST">
                                <div class="modal-body">
                                    <input type="hidden" name="action" value="change-pass">
                                    <div class="mb-3">
                                        <label class="form-label">Mật khẩu hiện tại</label>
                                        <input type="password" class="form-control" name="oldPass">
                                    </div>
                                    <div class="mb-3">
                                        <label class="form-label">Mật khẩu mới</label>
                                        <input type="password" class="form-control" name="newPass">
                                    </div>
                                    <div class="mb-3">
                                        <label class="form-label">Xác nhận mật khẩu mới</label>
                                        <input type="password" class="form-control" name="confirmNewPass">
                                    </div>

                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
                                    <button type="submit" class="btn btn-primary">Lưu thay đổi</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>


                <!-- Notification Settings Modal -->
                <!--            <div class="modal fade" id="notificationSettingsModal" tabindex="-1">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title">Cài đặt thông báo</h5>
                                            <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                                        </div>
                                        <div class="modal-body">
                                            <div class="form-check form-switch mb-3">
                                                <input class="form-check-input" type="checkbox" id="emailNotif">
                                                <label class="form-check-label" for="emailNotif">
                                                    Thông báo qua email
                                                </label>
                                            </div>
                                            <div class="form-check form-switch mb-3">
                                                <input class="form-check-input" type="checkbox" id="smsNotif">
                                                <label class="form-check-label" for="smsNotif">
                                                    Thông báo qua SMS
                                                </label>
                                            </div>
                                            <div class="form-check form-switch mb-3">
                                                <input class="form-check-input" type="checkbox" id="browserNotif">
                                                <label class="form-check-label" for="browserNotif">
                                                    Thông báo trên trình duyệt
                                                </label>
                                            </div>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
                                            <button type="button" class="btn btn-primary">Lưu thay đổi</button>
                                        </div>
                                    </div>
                                </div>
                            </div>-->
                <!--Xóa tài khoản-->
                <div class="modal fade" id="notificationSettingsModal" tabindex="-1">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title">Xóa tài khoản</h5>

                            </div>
                            <form action="cap-nhat-thong-tin" method="POST">
                                <input type="hidden" name="action" value="delete-account">
                                <div class="modal-body">
                                    <div class="form-check mb-3" style="padding-left: 0px;">
                                        <input type="text" name="userId" value="${sessionScope.currentUser.userId}" style="display: none">
                                        <div class="remove-acc" role="alert">
                                            Bạn có chắc chắn muốn xóa vĩnh viễn tài khoản này?
                                        </div>
                                    </div>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
                                    <input type="submit" class="btn btn-primary" value="Xóa">
                                </div>
                            </form>

                        </div>
                    </div>
                </div>
            <!--</div>-->
        </div>


        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
        <script src="resources/js/sb-admin-2.min.js"></script>
        <script src="resources/js/profile.js"></script>
        <script>
            document.addEventListener("DOMContentLoaded", function () {
                let alertBox = document.querySelector(".alert");

                if (alertBox) {
                    setTimeout(function () {
                        alertBox.style.display = "none";
                    }, 3000);
                }
            });

        </script>

    </body>
</html> 