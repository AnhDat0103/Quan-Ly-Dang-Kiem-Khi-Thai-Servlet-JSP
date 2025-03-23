<%-- 
    Document   : vehicleOwners
    Created on : Mar 4, 2025
    Author     : DAT
--%>

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
        <title>Danh Sách Cảnh Sát Giao Thông</title>
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
                        <h1 class="mt-4">Danh sách cảnh sát giao thông</h1>
                        <ol class="breadcrumb mb-4">
                            <li class="breadcrumb-item"><a href="#">Bảng điều kiển</a></li>
                            <li class="breadcrumb-item active">Danh sách cảnh sát giao thông</li>
                        </ol>
                        <div class="mb-3">
                            <button class="btn btn-secondary" onclick="history.back()">
                                <i class="fas fa-arrow-left me-1"></i>Quay lại
                            </button>
                        </div>
                        <div class="card mb-4">
                            <div class="card-header">
                                <i class="fas fa-table me-1"></i>
                                Danh sách cảnh sát giao thông
                            </div>
                            <div class="card-body">
                                <table id="datatablesSimple">
                                    <thead>
                                        <tr>
                                            <th>Tên</th>
                                            <th>Địa chỉ</th>
                                            <th>Số điện thoại</th>
                                            <th>Email</th>
                                            <th>Hành động</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="police" items="${polices}">
                                            <tr>
                                                <td>${police.fullName}</td>
                                                <td>${police.address}</td>
                                                <td>${police.phone}</td>
                                                <td>${police.email}</td>
                                                <td>
                                                    <a class="btn btn-danger btn-sm" data-police-id="${police.userId}" onclick="MyFunction(this)">Xóa</a>
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
        <script>
                                                        function MyFunction(element) {
                                                            if (confirm("Bạn có chắc chắn muốn xóa tài khoản này?")) {
                                                                let policeId = element.dataset.policeId;
                                                                if (policeId) {
                                                                    window.location.href = 'xoa-canh-sat?policeId=' + policeId;
                                                                } else {
                                                                    alert("Lỗi: Không lấy được ownerId.");
                                                                }

                                                            }
                                                        }
        </script>
    </body>
</html>