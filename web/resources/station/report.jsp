<%-- 
    Document   : report
    Created on : Feb 4, 2025, 11:07:53 PM
    Author     : DAT
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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

        <!-- DataTables -->
        <link href="https://cdn.datatables.net/1.11.5/css/dataTables.bootstrap5.min.css" rel="stylesheet">

        <!-- Custom CSS -->
        <link href="resources/css/sb-admin-2.min.css" rel="stylesheet">
    </head>
    <body id="page-top">
        <%@include file="../layout/navbar.jsp"%>
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
                            <div class="card-body">
                                <div class="row">
                                    <div class="col-md-3 mb-3">
                                        <label>Từ ngày:</label>
                                        <input type="date" class="form-control form-control-lg">
                                    </div>
                                    <div class="col-md-3 mb-3">
                                        <label>Đến ngày:</label>
                                        <input type="date" class="form-control form-control-lg">
                                    </div>
                                    <div class="col-md-3 mb-3">
                                        <label>Loại báo cáo:</label>
                                        <select class="form-control form-control-lg">
                                            <option value="daily">Báo cáo theo ngày</option>
                                            <option value="monthly">Báo cáo theo tháng</option>
                                            <option value="yearly">Báo cáo theo năm</option>
                                        </select>
                                    </div>
                                    <div class="col-md-3 mb-3">
                                        <label>&nbsp;</label>
                                        <button style="margin-top:24px;" class="btn btn-primary btn-lg btn-block">Tạo báo cáo</button>
                                    </div>
                                </div>
                            </div>
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
                                                <div class="h5 mb-0 font-weight-bold text-gray-800">215</div>
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
                                                <div class="h5 mb-0 font-weight-bold text-gray-800">180</div>
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
                                                <div class="h5 mb-0 font-weight-bold text-gray-800">35</div>
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
                                                        <div class="h5 mb-0 mr-3 font-weight-bold text-gray-800">83.7%</div>
                                                    </div>
                                                    <div class="col">
                                                        <div class="progress progress-sm mr-2">
                                                            <div class="progress-bar bg-info" role="progressbar"
                                                                 style="width: 83.7%"></div>
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

                        <!-- Bảng chi tiết -->
                        <div class="card shadow mb-4">
                            <div class="card-header py-3 d-flex justify-content-between align-items-center">
                                <h6 class="m-0 font-weight-bold text-primary">Chi tiết báo cáo</h6>
                                <button class="btn btn-success">
                                    <i class="fas fa-download fa-sm text-white-50"></i> Xuất Excel
                                </button>
                            </div>
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
                                            <tr>
                                                <td>20/03/2024</td>
                                                <td>25</td>
                                                <td>20</td>
                                                <td>5</td>
                                                <td>80%</td>
                                                <td>12,500,000 đ</td>
                                            </tr>
                                            <tr>
                                                <td>19/03/2024</td>
                                                <td>30</td>
                                                <td>28</td>
                                                <td>2</td>
                                                <td>93.3%</td>
                                                <td>15,000,000 đ</td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>

                <!-- Footer -->
                  <%@include file="../layout/footer.jsp" %>
            </div>
        </div>

        <!-- Scroll to Top Button-->
        <a class="scroll-to-top rounded" href="#page-top">
            <i class="fas fa-angle-up"></i>
        </a>

        <!-- Logout Modal-->
        <div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
             aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Bạn muốn đăng xuất?</h5>
                        <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">×</span>
                        </button>
                    </div>
                    <div class="modal-body">Chọn "Đăng xuất" bên dưới nếu bạn thực sự muốn kết thúc phiên làm việc.</div>
                    <div class="modal-footer">
                        <button class="btn btn-secondary" type="button" data-dismiss="modal">Hủy</button>
                        <a class="btn btn-primary" href="login.html">Đăng xuất</a>
                    </div>
                </div>
            </div>
        </div>

        <!-- jQuery -->
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

        <!-- Bootstrap Bundle JS -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>

        <!-- jQuery Easing -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.4.1/jquery.easing.min.js"></script>

        <!-- DataTables -->
        <script src="https://cdn.datatables.net/1.11.5/js/jquery.dataTables.min.js"></script>
        <script src="https://cdn.datatables.net/1.11.5/js/dataTables.bootstrap5.min.js"></script>

        <!-- Chart.js -->
        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

        <!-- Custom scripts -->
        <script src="resources/js/sb-admin-2.min.js"></script>
        <script src="resources/js/reports.js"></script>
    </body>
</html> 