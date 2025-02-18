<%-- 
    Document   : dangKyKD
    Created on : Feb 13, 2025, 11:09:08 PM
    Author     : Lenovo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Đăng ký lịch kiểm định</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
        <style>
            body {
                min-height: 100vh;
                display: flex;
                flex-direction: column;
            }

            .content-wrapper {
                flex: 1 0 auto;
                margin-bottom: 2rem;
            }

            .footer {
                flex-shrink: 0;
                padding: 1rem 0;
            }
        </style>
    </head>
    <body>
        <%@include file="../layout/owner_navbar.jsp" %>
        <form action="dang-ky-kiem-dinh" method="post">
            <div class="container mt-4">
                <div class="row justify-content-center">
                    <div class="col-md-8">
                        <div class="card">
                            <div class="card-header">
                                <h4 class="card-title mb-0">Đăng ký lịch kiểm định</h4>
                            </div>
                            <div class="card-body">
                                <!-- Thông báo -->
                                <c:if test="${not empty message}">
                                    <div class="alert ${message.contains('thành công') ? 'alert-success' : 'alert-danger'} alert-dismissible fade show" role="alert">
                                        ${message}
                                        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                                    </div>
                                </c:if>

                                <!-- Biển Số Xe -->
                                <div class="mb-3">
                                    <label for="plateNumber" class="form-label">Biển Số Xe Phương Tiện</label>
                                    <c:choose>
                                        <c:when test="${empty plateNumbers}">
                                            <div class="alert alert-warning">
                                                Bạn chưa có phương tiện nào. Vui lòng đăng ký phương tiện trước.
                                            </div>
                                            <a href="dang-ky-phuong-tien" class="btn btn-primary">Đăng ký phương tiện mới</a>
                                        </c:when>
                                        <c:otherwise>
                                            <select class="form-select" id="plateNumber" name="plateNumber" required>
                                                <option value="">Chọn Phương Tiện</option>
                                                <c:forEach var="plate" items="${plateNumbers}">
                                                    <option value="${plate}" ${plate eq param.plateNumber ? 'selected' : ''}>${plate}</option>
                                                </c:forEach>
                                            </select>
                                        </c:otherwise>
                                    </c:choose>
                                </div>

                                <!-- Khu Vực Đăng Kiểm -->
                                <div class="mb-3">
                                    <label for="stationName" class="form-label">Khu Vực Đăng Kiểm</label>
                                    <select class="form-select" id="stationName" name="stationName" required>
                                        <option value="">Chọn trung tâm đăng kiểm</option>
                                        <c:forEach var="station" items="${stationNames}">
                                            <option value="${station}" ${station eq param.stationName ? 'selected' : ''}>${station}</option>
                                        </c:forEach>
                                    </select>
                                </div>

                                <!-- Thời Gian Đăng Ký -->
                                <div class="mb-3">
                                    <label for="inspectionDate" class="form-label">Thời Gian Đăng Ký</label>
                                    <input type="date" class="form-control" id="inspectionDate" name="inspectionDate" value="${param.inspectionDate}" required>
                                </div>

                                <!-- Buttons -->
                                <div class="d-grid gap-2">
                                    <button type="submit" class="btn btn-primary">Đăng Ký Lịch Kiểm Định</button>
                                    <button type="reset" class="btn btn-secondary">Làm Mới</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </form>

        <script>
            // Set min và max date cho input ngày
            const today = new Date().toISOString().split('T')[0];
            const maxDate = new Date();
            maxDate.setMonth(maxDate.getMonth() + 3);
            
            const dateInput = document.getElementById('inspectionDate');
            dateInput.setAttribute('min', today);
            dateInput.setAttribute('max', maxDate.toISOString().split('T')[0]);
        </script>

        <!-- Footer từ owner-dashboard -->
        <%@include file="../layout/owner_footer.jsp"%>
    </body>
</html> 