<%-- 
    Document   : login
    Created on : Jan 27, 2025, 9:11:06 PM
    Author     : DAT
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="config.Constant" %>

<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Đăng nhập - Hệ thống Đăng kiểm Khí thải</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
        <link rel="stylesheet" href="resources/css/login.css">
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
                            <h4 class="text-center mb-4">Đăng nhập hệ thống</h4>
                            <div style="color:green; text-align: center"; >${requestScope.logoutSuccessMessage}</div>
                            <form action="dang-nhap" method="POST">
                                <div class="mb-4">
                                    <label for="email" class="form-label">Email</label>
                                    <input type="email" class="form-control ${not empty emptyEmail ? 'is-invalid' : ''}" id="email" name="email">
                                    <div class="invalid-feedback">
                                        <c:out value="${emptyEmail}" />
                                    </div>                      
                                </div>
                                <div class="mb-4">
                                    <label for="password" class="form-label">Mật khẩu</label>
                                    <input type="password" class="form-control ${not empty emptyPassword ? 'is-invalid' : ''}" id="password" name="password">
                                    <div class="invalid-feedback">
                                        <c:out value="${emptyPassword}" />
                                    </div>
                                </div>
                                <div class="mb-4">
                                    <label for="role" class="form-label">Vai trò</label>
                                    <select class="form-select" id="role" name="role" required>
                                        <option value="">Chọn vai trò</option>
                                        <option value="Owner">Chủ phương tiện</option>
                                        <option value="Inspector">Công nhân kiểm định</option>
                                        <option value="Station">Trung tâm đăng kiểm</option>
                                        <option value="Police">Cảnh sát giao thông</option>
                                    </select>
                                </div>
                                <div class="mb-4 form-check">
                                    <input type="checkbox" class="form-check-input" id="remember">
                                    <label class="form-check-label" for="remember">Ghi nhớ đăng nhập</label>
                                </div>
                                <div style="color:red"; >${requestScope.notFound}</div>
                                <button type="submit" class="btn btn-primary w-100 mb-3">Đăng nhập</button>

                                <div class="social-login text-center">
                                    <p class="mb-4">_____Hoặc_____</p>
                                    <a href="https://accounts.google.com/o/oauth2/auth?scope=email profile openid&redirect_uri=http://localhost:8080/dang-kiem-khi-thai/dang-nhap/oauth2/google&response_type=code&client_id=<%= Constant.CLIENT_ID %>&approval_prompt=force" 
                                       class="btn btn-outline-danger w-100">
                                        <i class="fab fa-google me-2"></i>Google
                                    </a>
                                </div>
                            </form>
                            <div class="mt-3 text-center">
                                <p>Chưa có tài khoản? <a href="dang-ky">Đăng ký ngay</a></p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html> 
