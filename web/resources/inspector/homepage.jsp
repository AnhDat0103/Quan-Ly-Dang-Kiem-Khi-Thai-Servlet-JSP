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
                <div class="card mb-4">
                    <div class="card-body">
                        <h5 class="card-title">Thống kê </h5>
                        <div class="row">
                            <div class="col">
                                <div class="text-muted">Đã kiểm định</div>
                                <h3 id="checked-count">0</h3>
                            </div>
                            <div class="col">
                                <div class="text-muted">Còn lại</div>
                                <h3 id="remaining-count">${lv.size()}</h3>
                            </div>
                        </div>
                    </div>
                </div>

            </div><div class="row">
                <!-- Danh sách xe đang chờ -->
                <div class="col-md-12">
                    <div class="card">
                        <div class="card-header d-flex justify-content-between align-items-center">
                        </div>
                        <div class="card-body">

                            <table id="waitingList" class="table">
                                <thead>
                                    <tr>
                                        <th>Mã số xe</th>
                                        <th>Biển số xe</th>
                                        <th></th>
                                        <th>CO2</th> 
                                        <th>HC</th>  
                                        <th>Mô tả</th>
                                        <th>Thao tác</th>
                                        <th>Kết quả</th>
                                    </tr>
                                </thead>

                                <tbody>
                                    <c:forEach items="${inspecrecord}" var="v">
                                    <form action="nguoi-kiem-dinh" method="POST">
                                        <tr>
                                            <td>${v.vehicle.vehicleId}</td>
                                            <td>${v.vehicle.plateNumber}</td>
                                            <td>
                                        <input type="hidden" value="${v.recordId}" name="recordId" >
                                            </td>
                                           
                                            <td>
                                                <input type="text" name="co2" value="${v.co2Emission}">
                                            </td>
                                            <td>
                                                <input type="text" name="hc" value="${v.hcEmission}">
                                            </td>
                                            
                                            <td>
                                                 <input type="text" name="comment" value="${v.comments}">
                                            </td>
                                            <td>
                                                <button class="btn btn-sm btn-success" type="submit">
                                                    Kiểm định
                                                </button>
                                            </td>
                                            <td>
                                               ${v.result}
                                            </td>
                                        </tr>
                                    </form>
                                    </c:forEach>
                                </tbody>

                            </table>

                        </div>
                    </div>
                </div>
            </div>
                            
   




            




    </body>
</html> 