<%-- 
    Document   : homepage
    Created on : Feb 9, 2025, 11:32:30 PM
    Author     : DUYEN
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Công nhân Kiểm định</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
    <!-- Navbar -->
    <%@include file="../layout/narbar_inspector.jsp" %>

    <!-- Main content -->
    <div class="container mt-4">
        <div class="row">
            <!-- Thống kê công việc -->
            <div class="col-md-4">
                <div class="card mb-4">
                    <div class="card-body">
                        <h5 class="card-title">Thống kê hôm nay</h5>
                        <div class="row">
                            <div class="col">
                                <div class="text-muted">Đã kiểm định</div>
                                <h3>5</h3>
                            </div>
                            <div class="col">
                                <div class="text-muted">Còn lại</div>
                                <h3>3</h3>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Danh sách xe đang chờ -->
            <div class="col-md-8">
                <div class="card">
                    <div class="card-header d-flex justify-content-between align-items-center">
                        <h5 class="card-title mb-0">Xe đang chờ kiểm định</h5>
                        <button class="btn btn-primary btn-sm">Bắt đầu kiểm định mới</button>
                    </div>
                    <div class="card-body">
                        <table class="table">
                            <thead>
                                <tr>
                                    <th>STT</th>
                                    <th>Biển số</th>
                                    <th>Loại xe</th>
                                    <th>Thời gian chờ</th>
                                    <th>Thao tác</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>1</td>
                                    <td>AB-12345</td>
                                    <td>Honda Wave</td>
                                    <td>15 phút</td>
                                    <td>
                                        <button class="btn btn-sm btn-success">Kiểm định</button>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>

        <!-- Form kiểm định -->
        <div class="row mt-4">
            <div class="col-12">
                <div class="card">
                    <div class="card-header">
                        <h5 class="card-title mb-0">Biên bản kiểm định</h5>
                    </div>
                    <div class="card-body">
                        <form>
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="mb-3">
                                        <label class="form-label">Chỉ số CO (%)</label>
                                        <input type="number" class="form-control" step="0.01">
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="mb-3">
                                        <label class="form-label">Chỉ số HC (ppm)</label>
                                        <input type="number" class="form-control">
                                    </div>
                                </div>
                            </div>
                            <div class="mb-3">
                                <label class="form-label">Ghi chú</label>
                                <textarea class="form-control" rows="3"></textarea>
                            </div>
                            <div class="mb-3">
                                <label class="form-label">Kết luận</label>
                                <select class="form-select">
                                    <option value="pass">Đạt</option>
                                    <option value="fail">Không đạt</option>
                                </select>
                            </div>
                            <button type="submit" class="btn btn-primary">Lưu biên bản</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html> 