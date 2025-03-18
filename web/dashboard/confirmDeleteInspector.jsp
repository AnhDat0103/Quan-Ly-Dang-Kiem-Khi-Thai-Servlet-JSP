<%-- 
    Document   : confirmDeleteInspector
    Created on : Mar 14, 2025, 10:35:47 PM
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
        <title>Xóa công nhân kiểm định</title>
        <link href="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/style.min.css" rel="stylesheet" />
        <link href="./resources/css/styles.css" rel="stylesheet" />
        <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
    </head>
    <body class="sb-nav-fixed">
        <%@include file="./layout/header.jsp" %>
        <div id="layoutSidenav">
            <%@include file="./layout/navigation.jsp" %>
            <div id="layoutSidenav_content">
                <main class="container mt-4">
                    <h3 class="text-center">Xóa công nhân kiểm định</h3>
                    <form action="cap-nhat-inspector" method="POST">
                        <input type="hidden" name="action" value="delete">
                        <input type="hidden" name="inspectorId" value="${inspector.userId}">
                        <div class="alert alert-danger" role="alert">
                            Bạn có chắn chắn muốn xóa vĩnh viễn người dùng này?
                        </div>
                        <div class="d-flex justify-content-between">
                            <button type="submit" class="btn btn-danger">Xóa</button>
                            <a type="button" class="btn btn-secondary" href="danh-sach-nhan-vien-kiem-dinh?stationId=${inspector.inspectionStation.stationId}">Quay lại</a>
                        </div>
                    </form>
                </main>
                <%@include file="./layout/footer.jsp" %>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="./resources/js/scripts.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/umd/simple-datatables.min.js" crossorigin="anonymous"></script>
    </body>
</html>