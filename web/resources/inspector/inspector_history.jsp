<%-- 
    Document   : history
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
        <title>Lịch sử Kiểm định</title>
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
                <div class="col-md-12">
                    <div class="card">
                        <div class="card-header">
                            <h5>Lịch sử Kiểm định</h5>
                        </div>
                        <div class="card-body">
                            <c:if test="${not empty historyRecords}" >
                                <table id="historyList" class="table">
                                    <thead>
                                        <tr>
                                            <th>Mã số xe</th>
                                            <th>Biển số xe</th>
                                            <th>Ngày kiểm định</th>
                                            <th>CO2</th> 
                                            <th>HC</th>  
                                            <th>Mô tả</th>
                                            <th>Kết quả</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${historyRecords}" var="h">
                                            <tr>
                                                <td>${h.vehicle.vehicleId}</td>
                                                <td>${h.vehicle.plateNumber}</td>
                                                <td>${h.inspectionDate}</td>
                                                <td>${h.co2Emission}</td>
                                                <td>${h.hcEmission}</td>
                                                <td>${h.comments}</td>
                                                <td><span class="badge resultStatus">${h.result}</span></td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </c:if>
                            
                            <c:if test="${empty historyRecords }">
                                ${message}
                            </c:if>

                        </div>
                    </div>
                </div>
            </div>
        </div>
        
        <script>
            document.addEventListener("DOMContentLoaded", function () {
                document.querySelectorAll(".resultStatus").forEach(span => {
                    setBadge1(span, span.innerText);
                });
            });

            function setBadge1(element, status) {
                element.innerText = status;
                if (status === 'Accepted') {
                    element.className = "badge bg-info text-dark modelResult";
                } else {
                    element.className = "badge " + (status === "Pass" ? "bg-success" : "bg-danger");
                }
            }
        </script>

    </body>
</html>
