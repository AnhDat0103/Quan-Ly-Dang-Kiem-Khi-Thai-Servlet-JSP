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
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
        <style>
            html, body {
                height: 100%;
                margin: 0;
                display: flex;
                flex-direction: column;
            }

            body > .container {
                flex: 1 0 auto;
            }

            footer {
                flex-shrink: 0;
            }

            .hover-opacity:hover {
                opacity: 0.8;
                transition: opacity 0.2s ease-in-out;
            }

            footer h5 {
                border-bottom: 2px solid rgba(255,255,255,0.1);
                padding-bottom: 0.5rem;
                margin-bottom: 1rem;
            }
        </style>
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
                                <h3 id="checked-count">${checkedCount}</h3>
                            </div>
                            <div class="col">
                                <div class="text-muted">Còn lại</div>
                                <h3 id="remaining-count">${remainingCount}</h3>
                            </div>
                        </div>
                    </div>
                </div>


                <!-- Danh sách xe đang chờ -->
                <div class="card mb-4 col-md-12">
                    <div class="card-body">
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
                                
                                <c:if test="${not empty inspecrecord }">
                                    <tbody>
                                    <c:forEach items="${inspecrecord}" var="v">
                                    <form action="nguoi-kiem-dinh" method="POST">
                                        <tr>
                                            <td>${v.vehicle.vehicleId}</td>
                                            <td>${v.vehicle.plateNumber}</td>
                                            <input type="hidden" name="plateNum" value="${v.vehicle.plateNumber}">
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
                                                <span class="badge ${v.result eq 'Pass' ? 'bg-success' : 'bg-info text-dark'}">${v.result}
                                            </td>
                                        </tr>
                                    </form>
                                </c:forEach>
                                </tbody>
                                </c:if>
                                
                                
                                

                            </table>
                            <c:if test="${ empty inspecrecord }">
                                <div class="text-center">  Chưa có bản kiểm định </div>
                            </c:if>
                        </div>
                    </div>
                </div>

                <!-- Modal nhập thông tin khi stationId null -->

                <div class="modal fade" id="stationModal" tabindex="-1" aria-labelledby="stationModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="stationModalLabel">Cập nhật nơi làm việc</h5>
                            </div>
                            <form action="cap-nhat-thong-tin" method="POST">
                                <div class="modal-body">
                                    <input type="hidden" name="action" value="change-location">
                                    <div class="mb-3">
                                        <label class="form-label">Cơ sở làm việc</label>
                                        <select class="form-control" name="inspecStation" required>
                                            <option value="">-- Chọn cơ sở --</option>
                                            <c:forEach var="s" items="${requestScope.stations}">
                                                <option value="${s.stationId}">${s.name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class="modal-footer">
                                    <button type="submit" class="btn btn-primary">Lưu</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Footer -->
        <%@include file="../layout/footer.jsp" %>
        <script>
            
            document.addEventListener("DOMContentLoaded", function () {
                    let stationId = "${currentUser.inspectionStation.stationId}";
            console.log("Station ID:", stationId);
            if (!stationId || stationId === "0") {
            let stationModal = new bootstrap.Modal(document.getElementById("stationModal"));
            stationModal.show();
            }
                        });
        
        // Hàm cập nhật màu sắc cho badge
                        function setBadge(elementId, status) {
                    const element = document.getElementById(elementId);
            element.innerText = status;
            element.className = "badge " + (status === "Pass" ? "bg-success" : "bg-danger");
                }
        </script>


    </body>
</html> 