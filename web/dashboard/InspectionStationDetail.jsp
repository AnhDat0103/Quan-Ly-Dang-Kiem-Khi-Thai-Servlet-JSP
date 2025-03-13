<%-- 
    Document   : InspectionStationDetail
    Created on : Mar 13, 2025, 10:06:46 PM
    Author     : DAT
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Cập Nhật Thông Tin Trung Tâm Đăng Kiểm</title>
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
        <link href="./resources/css/styles.css" rel="stylesheet" />
        <style>
            * {
                margin: 0;
                padding: 0;
                box-sizing: border-box;
            }

            body {
                font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
                background: #f4f6f9;
                color: #333;
            }

            .container {
                max-width: 800px;
                margin: 2rem auto;
                padding: 2rem;
                background: white;
                border-radius: 10px;
                box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
            }

            .form-header {
                text-align: center;
                margin-bottom: 2rem;
                padding-bottom: 1rem;
                border-bottom: 2px solid #eee;
            }

            .form-header h2 {
                color: #2c3e50;
                font-size: 1.8rem;
                margin-bottom: 0.5rem;
            }

            .form-header p {
                color: #7f8c8d;
                font-size: 0.9rem;
            }

            .form-group {
                margin-bottom: 1.5rem;
            }

            .form-group label {
                display: block;
                margin-bottom: 0.5rem;
                color: #34495e;
                font-weight: 500;
            }

            .form-control {
                width: 100%;
                padding: 0.8rem;
                border: 1px solid #ddd;
                border-radius: 5px;
                font-size: 1rem;
                transition: border-color 0.3s ease;
            }

            .form-control:focus {
                border-color: #3498db;
                outline: none;
                box-shadow: 0 0 5px rgba(52, 152, 219, 0.3);
            }

            .form-control:disabled {
                background-color: #f8f9fa;
                cursor: not-allowed;
            }

            .button-group {
                display: flex;
                gap: 1rem;
                margin-top: 2rem;
            }

            .btn {
                padding: 0.8rem 1.5rem;
                border: none;
                border-radius: 5px;
                font-size: 1rem;
                cursor: pointer;
                transition: all 0.3s ease;
                display: flex;
                align-items: center;
                gap: 0.5rem;
            }

            .btn-primary {
                background: #3498db;
                color: white;
                flex: 1;
            }

            .btn-primary:hover {
                background: #2980b9;
            }

            .btn-secondary {
                background: #95a5a6;
                color: white;
            }

            .btn-secondary:hover {
                background: #7f8c8d;
            }

            .validation-message {
                color: #e74c3c;
                font-size: 0.8rem;
                margin-top: 0.3rem;
                display: none;
            }

            .form-control.error {
                border-color: #e74c3c;
            }

            .form-control.error + .validation-message {
                display: block;
            }

            .info-box {
                background: #f8f9fa;
                padding: 1rem;
                border-radius: 5px;
                margin-bottom: 1.5rem;
            }

            .info-box p {
                margin: 0;
                color: #666;
                font-size: 0.9rem;
            }
            .form-update-station{
                width: 800px;
                height: auto;
            }
        </style>
    </head>
    <body class="sb-nav-fixed">
        <%@include file="./layout/header.jsp" %>
        <div id="layoutSidenav">
            <%@include file="./layout/navigation.jsp" %>
            <div id="layoutSidenav_content">
                <div class="container-fluid form-update-station mt-4">
                    <c:if test="${not empty error}">
                        <div class="alert alert-danger">
                            ${error}
                        </div>
                    </c:if>
                    <div class="form-header">
                        <h2>Cập Nhật Thông Tin Trung Tâm Đăng Kiểm</h2>
                        <p>Vui lòng điền đầy đủ thông tin bên dưới</p>
                    </div>

                    <form action="StationServlet" method="POST" id="updateForm" onsubmit="return validateForm()">
                        <input type="hidden" name="action" value="update">
                        <input type="hidden" name="stationId" value="${station.stationId}">

                        <div class="info-box">
                            <p><i class="fas fa-info-circle"></i> Mã trung tâm: ${station.stationId}</p>
                        </div>

                        <div class="form-group">
                            <label for="email">Email</label>
                            <input type="email" class="form-control" id="email" 
                                   value="${station.email}" disabled>
                            <div class="validation-message">Email không thể thay đổi</div>
                        </div>

                        <div class="form-group">
                            <label for="name">Tên Trung Tâm</label>
                            <input type="text" class="form-control" id="name" name="name" 
                                   value="${station.name}" required>
                            <div class="validation-message">Vui lòng nhập tên trung tâm</div>
                        </div>

                        <div class="form-group">
                            <label for="address">Địa Chỉ</label>
                            <input type="text" class="form-control" id="address" name="address" 
                                   value="${station.address}" required>
                            <div class="validation-message">Vui lòng nhập địa chỉ</div>
                        </div>

                        <div class="form-group">
                            <label for="phone">Số Điện Thoại</label>
                            <input type="tel" class="form-control" id="phone" name="phone" 
                                   value="${station.phone}" required pattern="[0-9]{10}">
                            <div class="validation-message">Vui lòng nhập số điện thoại hợp lệ (10 số)</div>
                        </div>

                        <div class="button-group">
                            <button type="submit" class="btn btn-primary">
                                <i class="fas fa-save"></i>
                                Lưu Thay Đổi
                            </button>
                            <a href="chi-tiet-trung-tam-dang-kiem" class="btn btn-secondary">
                                <i class="fas fa-times"></i>
                                Hủy
                            </a>
                        </div>
                    </form>
                </div>
                <%@include file="./layout/footer.jsp" %>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="./resources/js/scripts.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/umd/simple-datatables.min.js" crossorigin="anonymous"></script>
        <script>
                        function validateForm() {
                            let isValid = true;
                            const name = document.getElementById('name');
                            const address = document.getElementById('address');
                            const phone = document.getElementById('phone');

                            // Validate name
                            if (!name.value.trim()) {
                                name.classList.add('error');
                                isValid = false;
                            } else {
                                name.classList.remove('error');
                            }

                            // Validate address
                            if (!address.value.trim()) {
                                address.classList.add('error');
                                isValid = false;
                            } else {
                                address.classList.remove('error');
                            }

                            // Validate phone
                            const phonePattern = /^[0-9]{10}$/;
                            if (!phonePattern.test(phone.value)) {
                                phone.classList.add('error');
                                isValid = false;
                            } else {
                                phone.classList.remove('error');
                            }

                            return isValid;
                        }

                        // Real-time validation
                        document.querySelectorAll('.form-control').forEach(input => {
                            input.addEventListener('input', function () {
                                if (this.value.trim()) {
                                    this.classList.remove('error');
                                }
                            });
                        });
                        document.addEventListener("DOMContentLoaded", function () {
                            // Animate alert fadeout
                            let alertBox = document.querySelector(".alert");
                            if (alertBox) {
                                setTimeout(function () {
                                    alertBox.classList.add('fade');
                                    setTimeout(function () {
                                        alertBox.remove();
                                    }, 300);
                                }, 5000);
                            }

                        });
        </script>
    </body>
</html>