<%-- 
    Document   : loginAdmin
    Created on : Feb 26, 2025, 3:14:39 PM
    Author     : DAT
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                <div class="col-lg-4 col-md-7 col-sm-9" style="margin-top: 70px">
                    <div class="card">
                        <div class="card-body">
                            <h4 class="text-center mb-4">Đăng nhập cho quản trị viên</h4>
                            <div style="color:green; text-align: center"; >${requestScope.logoutSuccessMessage}</div>
                            <form action="21.04.2000" method="POST">
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
                                <div style="color:red"; >${requestScope.notFound}</div>
                                <button type="submit" class="btn btn-primary w-100 mb-3">Đăng nhập</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html> 