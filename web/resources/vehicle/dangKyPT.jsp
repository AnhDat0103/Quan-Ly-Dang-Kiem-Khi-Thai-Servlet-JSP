<%-- 
    Document   : dangKyPT
    Created on : Jan 28, 2025, 4:26:32 PM
    Author     : Lenovo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Đăng Ký Phương Tiện</title>
    <link rel="stylesheet" href="../css/css.vehicle/dangKyPT.css"> 
</head>
<body>

    <div class="container">
        <h2>Đăng Ký Phương Tiện</h2>
        
        <%-- Hiển thị thông báo lỗi --%>
        <% 
            String bug = (String) request.getAttribute("bug");
            if (bug != null && !bug.isEmpty()) { 
        %>
            <p class="error-message"><%= bug %></p>
        <% } %>
        
        <form action="<%= request.getContextPath() %>/dangkyPT" method="POST">
            <div class="form-group">
                <label for="ownerID">ID Chủ Sở Hữu:</label>
                <input type="number" id="ownerID" name="ownerID" required>
            </div>

            <div class="form-group">
                <label for="plateNumber">Biển số xe:</label>
                <input type="text" id="plateNumber" name="plateNumber" 
                    pattern="[0-9]{2}[A-Z]-[0-9]{5}" 
                    title="Biển số xe phải có định dạng: 30A-12345" required>
            </div>
            
            <div class="form-group">
                <label for="brand">Hãng Xe:</label>
                <input type="text" id="brand" name="brand" required>
            </div>

            <div class="form-group">
                <label for="model">Mẫu Xe:</label>
                <input type="text" id="model" name="model" required>
            </div>

            <div class="form-group">
                <label for="manufactureYear">Năm Sản Xuất:</label>
                <input type="number" id="manufactureYear" name="manufactureYear" required>
            </div>

            <div class="form-group">
                <label for="engineNumber">Số Động Cơ:</label>
                <input type="text" id="engineNumber" name="engineNumber" required>
            </div>

            <button type="submit" class="submit-btn">Đăng Ký</button>
        </form>
    </div>

</body>
</html>
