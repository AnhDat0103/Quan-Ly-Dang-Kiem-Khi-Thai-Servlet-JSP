<%-- 
    Document   : report
    Created on : Feb 4, 2025, 11:07:53 PM
    Author     : DAT
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Báo cáo Thống kê - Trung tâm đăng kiểm</title>

        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">

        <!-- Font Awesome -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet">

        <!-- Custom CSS -->
        <link href="resources/css/sb-admin-2.min.css" rel="stylesheet">
    </head>
    <body id="page-top">
        <%@include file="../layout/navbar.jsp"%>
        <div class="container mt-4">
            <div id="wrapper">
                <div id="content-wrapper" class="d-flex flex-column">
                    <div id="content">

                        <!-- Begin Page Content -->
                        <div class="container-fluid">
                            <!-- Page Heading -->
                            <h1 class="h3 mb-2 text-gray-800">Báo cáo Thống kê</h1>

                            <!-- Bộ lọc -->
                            <div class="card shadow mb-4">
                                <div class="card-header py-3">
                                    <h6 class="m-0 font-weight-bold text-primary">Bộ lọc báo cáo</h6>
                                </div>
                                <form action="bao-cao-kiem-dinh" method="GET">
                                    <div class="card-body">
                                        <div class="row">
                                            <div class="col-md-3 mb-3">
                                                <label>Từ ngày:</label>
                                                <input type="date" class="form-control form-control-lg" name="fromDate" value="${requestScope.startDate}" required>
                                            </div>
                                            <div class="col-md-3 mb-3">
                                                <label>Đến ngày:</label>
                                                <input type="date" class="form-control form-control-lg" name="toDate" value="${requestScope.endDate}" required>
                                            </div>
                                            <div class="col-md-3 mb-3">
                                                <label>Loại báo cáo:</label>
                                                <select class="form-control form-control-lg">
                                                    <option value="daily">Báo cáo theo ngày</option>
                                                </select>
                                            </div>
                                            <div class="col-md-3 mb-3">
                                                <label>&nbsp;</label>
                                                <button style="margin-top:24px;" class="btn btn-primary btn-lg btn-block" type="submit">Tạo báo cáo</button>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                            </div>

                            <!-- Thống kê tổng quan -->
                            <div class="row">
                                <div class="col-xl-3 col-md-6 mb-4">
                                    <div class="card border-left-primary shadow h-100 py-2">
                                        <div class="card-body">
                                            <div class="row no-gutters align-items-center">
                                                <div class="col mr-2">
                                                    <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">
                                                        Tổng số xe đăng kiểm</div>
                                                    <div class="h5 mb-0 font-weight-bold text-gray-800">${requestScope.sumVehicle}</div>
                                                </div>
                                                <div class="col-auto">
                                                    <i class="fas fa-car fa-2x text-gray-300"></i>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="col-xl-3 col-md-6 mb-4">
                                    <div class="card border-left-success shadow h-100 py-2">
                                        <div class="card-body">
                                            <div class="row no-gutters align-items-center">
                                                <div class="col mr-2">
                                                    <div class="text-xs font-weight-bold text-success text-uppercase mb-1">
                                                        Đạt tiêu chuẩn</div>
                                                    <div class="h5 mb-0 font-weight-bold text-gray-800">${requestScope.sumVehiclePass}</div>
                                                </div>
                                                <div class="col-auto">
                                                    <i class="fas fa-check fa-2x text-gray-300"></i>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="col-xl-3 col-md-6 mb-4">
                                    <div class="card border-left-warning shadow h-100 py-2">
                                        <div class="card-body">
                                            <div class="row no-gutters align-items-center">
                                                <div class="col mr-2">
                                                    <div class="text-xs font-weight-bold text-warning text-uppercase mb-1">
                                                        Không đạt</div>
                                                    <div class="h5 mb-0 font-weight-bold text-gray-800">${requestScope.sumVehicleFail}</div>
                                                </div>
                                                <div class="col-auto">
                                                    <i class="fas fa-exclamation-triangle fa-2x text-gray-300"></i>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="col-xl-3 col-md-6 mb-4">
                                    <div class="card border-left-info shadow h-100 py-2">
                                        <div class="card-body">
                                            <div class="row no-gutters align-items-center">
                                                <div class="col mr-2">
                                                    <div class="text-xs font-weight-bold text-info text-uppercase mb-1">
                                                        Tỷ lệ đạt</div>
                                                    <div class="row no-gutters align-items-center">
                                                        <div class="col-auto">
                                                            <div class="h5 mb-0 mr-3 font-weight-bold text-gray-800"><fmt:formatNumber value="${percentPass}" type="number" maxFractionDigits="2" />%</div>
                                                        </div>
                                                        <div class="col">
                                                            <div class="progress progress-sm mr-2">
                                                                <div class="progress-bar bg-info" role="progressbar"
                                                                     style="width: ${requestScope.percentPass}px;"></div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-auto">
                                                    <i class="fas fa-clipboard-list fa-2x text-gray-300"></i>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <c:if test="${sessionScope.records.size() > 0}">
                                <!-- Bảng chi tiết -->
                                <div class="card shadow mb-4">
                                    <form action="bao-cao-kiem-dinh" method="POST">
                                        <input type="hidden" name="action" value="exportExcel">
                                        <div class="card-header py-3 d-flex justify-content-between align-items-center">
                                        <h6 class="m-0 font-weight-bold text-primary">Chi tiết báo cáo</h6>
                                        <button class="btn btn-success" type="submit">
                                            <i class="fas fa-download fa-sm text-white-50"></i> Xuất Excel
                                        </button>
                                    </div>
                                    </form>
                                    <div class="card-body">
                                        <div class="table-responsive">
                                            <table class="table table-bordered" width="100%" cellspacing="0">
                                                <thead>
                                                    <tr>
                                                        <th>Ngày</th>
                                                        <th>Số lượng xe</th>
                                                        <th>Đạt</th>
                                                        <th>Không đạt</th>
                                                        <th>Tỷ lệ đạt</th>
                                                        <th>Doanh thu</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:forEach items="${sessionScope.records}" var="r">
                                                        <tr>
                                                            <td>${r.inspectionDate}</td>
                                                            <td>${r.sumNumOfVehicle}</td>
                                                            <td>${r.sumNumOfPass}</td>
                                                            <td>${r.sumNumOfFail}</td>
                                                            <td><fmt:formatNumber value="${(r.sumNumOfPass  * 100) / r.sumNumOfVehicle}" type="number" maxFractionDigits="2" />%</td>
                                                            <td><fmt:formatNumber value="${r.sumNumOfVehicle * 90000}" />đ</td>
                                                        </tr>
                                                    </c:forEach>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </c:if>
                            <c:if test="${sessionScope.records.size() == 0}">
                                <div class="alert alert-dark text-center" role="alert">
                                    Không có bản đăng kiểm nào được tìm thấy.
                                </div>
                            </c:if>


                        </div>
                    </div>

                    <!-- Footer -->
                    <%@include file="../layout/footer.jsp" %>
                </div>
            </div>
        </div>


        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js"></script>
    </body>
</html> 