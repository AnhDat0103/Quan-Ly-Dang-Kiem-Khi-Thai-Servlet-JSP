<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Danh sách trung tâm đăng kiểm</title>
        <link href="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/style.min.css" rel="stylesheet" />
        <link href="./resources/css/styles.css" rel="stylesheet" />
        <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
    </head>
    <body class="sb-nav-fixed">
        <%@include file="./layout/header.jsp" %>
        <div id="layoutSidenav">
            <%@include file="./layout/navigation.jsp" %>
            <div id="layoutSidenav_content">
                <main>
                    <div class="container-fluid px-4">
                        <h1 class="mt-4">Danh sách trung tâm đăng kiểm</h1>
                        <ol class="breadcrumb mb-4">
                            <li class="breadcrumb-item"><a href="#">Bảng điều khiển</a></li>
                            <li class="breadcrumb-item active">Danh sách trung tâm đăng kiểm</li>
                        </ol>
                        <div class="d-flex justify-content-between">
                           <div class="mb-3">
                            <a class="btn btn-secondary" href="quan-tri-vien">
                                <i class="fas fa-arrow-left me-1"></i>Quay lại
                            </a>
                        </div>
                        <div class="mb-3">
                            <a  class="btn btn-primary" href="tao-moi-trung-tam-kiem-dinh">Tạo mới</a>

                        </div> 
                        </div>
                        
                        <div class="card mb-4">
                            <div class="card-header">
                                <i class="fas fa-table me-1"></i>
                                Danh sách trung tâm đăng kiểm
                            </div>
                            <div class="card-body">
                                <table id="datatablesSimple">
                                    <thead>
                                        <tr>
                                            <th>Tên trung tâm</th>
                                            <th>Địa chỉ</th>
                                            <th>Số điện thoại</th>
                                            <th>Email</th>
                                            <th>Hành động</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="station" items="${stations}">
                                            <tr>
                                                <td>${station.name}</td>
                                                <td>${station.address}</td>
                                                <td>${station.phone}</td>
                                                <td>${station.email}</td>
                                                <td>
                                                    <a class="btn btn-warning btn-sm" href="danh-sach-nhan-vien-kiem-dinh?stationId=${station.stationId}">Danh sách nhân viên</a>
                                                    <a class="btn btn-primary btn-sm" href="StationServlet?action=update&stationId=${station.stationId}">Cập nhật</a>

                                                   <form action="StationServlet" method="POST" style="display:inline;" onsubmit="return confirm('Bạn có chắc chắn muốn xóa trung tâm này?');">
                                                        <input type="hidden" name="action" value="delete">
                                                        <input type="hidden" name="stationId" value="${station.stationId}">
                                                        <button type="submit" class="btn btn-danger btn-sm">Xóa</button>
                                                    </form>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </main>
                <%@include file="./layout/footer.jsp" %>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="./resources/js/scripts.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/umd/simple-datatables.min.js" crossorigin="anonymous"></script>
        <script src="./resources/js/datatables-simple-demo.js"></script>
    </body>
</html>