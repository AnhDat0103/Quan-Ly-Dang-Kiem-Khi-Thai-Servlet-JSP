<%-- 
    Document   : completed_inspections
    Created on : Mar 7, 2025, 2:06:59 PM
    Author     : DUYEN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Danh Sách Kiểm Định Đã Hoàn Thành</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
        tr:nth-child(even) {
            background-color: #f9f9f9;
        }
        .message {
            color: blue;
            font-weight: bold;
        }
        .error {
            color: red;
            font-weight: bold;
        }
    </style>
</head>
<body>
    <h1>Danh Sách Kiểm Định Đã Hoàn Thành</h1>

    <!-- Hiển thị thông báo nếu có -->
    <c:if test="${not empty message}">
        <p class="message">${message}</p>
    </c:if>
    <c:if test="${not empty error}">
        <p class="error">${error}</p>
    </c:if>

    <!-- Bảng hiển thị danh sách -->
    <table>
        <tr>
            <th>ID Bản Ghi</th>
            <th>Biển Số Xe</th>
            <th>Ngày Kiểm Định</th>
            <th>Trạm Kiểm Định</th>
            <th>Kết Quả</th>
            <th>CO2 Emission</th>
            <th>HC Emission</th>
            <th>Nhận Xét</th>
        </tr>
        <c:forEach var="record" items="${inspecrecord}">
            <tr>
                <td>${record.recordId}</td>
                <td>${record.vehicle.plateNumber}</td>
                <td>${record.inspectionDate}</td>
                <td>${record.stationID}</td>
                <td>${record.result}</td>
                <td>${record.co2Emission}</td>
                <td>${record.hcEmission}</td>
                <td>${record.comments}</td>
            </tr>
        </c:forEach>
    </table>

    <!-- Nút quay lại trang chính (tùy chọn) -->
    <p><a href="nguoi-kiem-dinh">Quay lại trang chính</a></p>
</body>
</html>