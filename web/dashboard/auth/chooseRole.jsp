<%-- 
    Document   : chooseRole
    Created on : Mar 7, 2025, 8:48:56 PM
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
                <div class="col-lg-4 col-md-7 col-sm-9 mt-5">
                    <div class="card">
                        <div class="card-body">
                            <h4 class="text-center mb-4">Hãy chọn vai trò của bạn</h4>
                            <form action="dang-nhap/oauth2/google" method="POST">
                                <div class="mb-4">
                                    <label for="email" class="form-label">Email</label>
                                    <input type="email" class="form-control" id="email" value="${sessionScope.googleAccount.email}" readonly="true">
                                             
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

