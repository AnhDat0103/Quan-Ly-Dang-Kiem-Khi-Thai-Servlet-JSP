<%-- 
    Document   : register
    Created on : Jan 27, 2025, 9:11:47 PM
    Author     : DAT
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Đăng ký - Hệ thống Đăng kiểm Khí thải</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
    <link rel="stylesheet" href="resources/css/login.css">
    <style>
        .card {
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }
        
        .card-body {
            padding: 1.5rem;
        }

        .form-label {
            margin-bottom: 0.3rem;
            font-size: 0.9rem;
        }

        .form-control, .form-select {
            padding: 0.4rem 0.75rem;
            font-size: 0.9rem;
        }

        .mb-3 {
            margin-bottom: 0.8rem !important;
        }

        .mb-4 {
            margin-bottom: 1rem !important;
        }

        textarea.form-control {
            min-height: 60px;
        }

        .social-login p {
            margin-bottom: 0.5rem;  
            font-size: 0.9rem;
        }

        .mt-3 {
            margin-top: 0.8rem !important;
        }

        h4 {
            font-size: 1.4rem;
            margin-bottom: 1rem !important;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="row vh-100 align-items-center justify-content-center">
            <div class="col-lg-5 d-none d-lg-block">
                <%@include file="../layout/banner.jsp" %>
            </div>
            <div class="col-lg-4 col-md-7 col-sm-9">
                <div class="card">
                    <div class="card-body">
                        <h4 class="text-center mb-4">Đăng ký tài khoản</h4>
                        <form action="dang-ky" method="POST">
                            <div class="row mb-3">
                                <div class="col-md-6">
                                    <label for="fullName" class="form-label">Họ và tên</label>
                                    <input type="text" class="form-control ${not empty emptyName ? 'is-invalid' : ''}" id="fullName" name="fullName" value="${requestScope.rf.fullName}">
                                    <div class="invalid-feedback">
                                        <c:out value="${emptyName}" />
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <label for="phone" class="form-label">Số điện thoại</label>
                                    <input type="tel" class="form-control ${not empty emptyPhone ? 'is-invalid' : ''} ${not empty phoneFormatError ? 'is-invalid' : ''}" id="phone" name="phone" value="${requestScope.rf.phone}">
                                    <div class="invalid-feedback">
                                        <c:out value="${emptyPhone}" />
                                        <c:out value="${phoneFormatError}" />
                                    </div>
                                </div>
                            </div>

                            <div class="mb-3">
                                <label for="email" class="form-label">Email</label>
                                <input type="email" class="form-control ${not empty emptyEmail ? 'is-invalid' : ''} ${not empty emailFormatError ? 'is-invalid' : ''} ${not empty existedEmail ? 'is-invalid' : ''}" id="email" name="email" value="${requestScope.rf.email}">
                                <div class="invalid-feedback">
                                        <c:out value="${existedEmail}" />
                                        <c:out value="${emptyEmail}" />
                                        <c:out value="${emailFormatError}" />
                                    </div>
                            </div>

                            <div class="row mb-3">
                                <div class="col-md-6">
                                    <label for="password" class="form-label">Mật khẩu</label>
                                    <input type="password" class="form-control ${not empty emptyPass ? 'is-invalid' : ''}" id="password" name="password">
                                    <div class="invalid-feedback">
                                        <c:out value="${emptyPass}" />
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <label for="confirmPassword" class="form-label">Xác nhận mật khẩu</label>
                                    <input type="password" class="form-control ${not empty emptyConfirPass ? 'is-invalid' : ''} ${not empty confirmPassError ? 'is-invalid' : ''}" id="confirmPassword" name="confirmPassword">
                                    <div class="invalid-feedback">
                                        <c:out value="${emptyConfirPass}" />
                                        <c:out value="${confirmPassError}" />
                                    </div>
                                </div>
                            </div>

                            <div class="mb-3">
                                <label for="role" class="form-label">Đăng ký với vai trò</label>
                                <select class="form-select" id="role" name="role" required>
                                    <option value="">Chọn vai trò</option>
                                    <option value="Owner">Chủ phương tiện</option>
                                    <option value="Inspector">Công nhân kiểm định</option>
                                    <option value="Station">Trung tâm đăng kiểm</option>
                                    <option value="Police">Cảnh sát giao thông</option>
                                </select>
                            </div>

                            <div class="mb-4">
                                <label for="address" class="form-label">Địa chỉ</label>
                                <textarea class="form-control ${not empty emptyAddress ? 'is-invalid' : ''}" id="address" name="address" rows="2">${requestScope.rf.address}</textarea>
                                <div class="invalid-feedback">
                                    <c:out value="${emptyAddress}" />
                                </div>
                            </div>

                            <button type="submit" class="btn btn-primary w-100 mb-3">Đăng ký</button>
                            
<!--                            <div class="social-login text-center">
                                <p class="mb-4">_____Hoặc_____</p>
                                <a href="/oauth2/authorization/google" class="btn btn-outline-danger w-100">
                                    <i class="fab fa-google me-2"></i>Google
                                </a>
                            </div>-->
                        </form>
                        <div class="mt-3 text-center">
                            <p>Đã có tài khoản? <a href="dang-nhap">Đăng nhập</a></p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html> 