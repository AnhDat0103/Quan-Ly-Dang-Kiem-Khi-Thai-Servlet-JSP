<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Tìm kiếm phương tiện</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f5f5f5;
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100vh;
                flex-direction: column;
            }

            .container {
                background: white;
                padding: 20px;
                border-radius: 8px;
                box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.1);
                width: 400px;
                text-align: center;
            }

            h2 {
                font-size: 24px;
                color: #333;
                margin-bottom: 15px;
            }

            .search-container {
                display: flex;
                gap: 10px;
                justify-content: center;
                align-items: center;
            }

            input[type="text"] {
                flex: 1;
                padding: 12px;
                font-size: 16px;
                border: 2px solid #ccc;
                border-radius: 5px;
                outline: none;
                transition: border-color 0.3s ease-in-out;
            }

            input[type="text"]:focus {
                border-color: #007bff;
            }

            button {
                background-color: #007bff;
                color: white;
                padding: 12px 20px;
                font-size: 16px;
                border: none;
                border-radius: 5px;
                cursor: pointer;
                transition: background-color 0.3s ease-in-out;
            }

            button:hover {
                background-color: #0056b3;
            }

            .message {
                margin-top: 10px;
                color: red;
                font-size: 14px;
            }

            #vehicleTable {
                width: 100%;
                border-collapse: collapse;
                margin-top: 20px;
            }

            #vehicleTable th, #vehicleTable td {
                border: 1px solid #ccc;
                padding: 10px;
                text-align: left;
            }

            #vehicleTable th {
                background-color: #007bff;
                color: white;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <form action="/timKiemPT1" method="GET">
                <h2>Tìm kiếm phương tiện</h2>
                <div class="search-container">
                    <input type="text" name="plateNumber" placeholder="Nhập biển số xe" value="<%= request.getParameter("plateNumber") != null ? request.getParameter("plateNumber") : "" %>" required>
                    <button type="submit">Tìm kiếm</button>
                </div>


                <!-- Hiển thị thông báo lỗi nếu có -->
                <div class="message">
                    <% 
                        String errorMessage = (String) request.getAttribute("errorMessage"); 
                        if (errorMessage != null) { 
                    %>
                    <%= errorMessage %>
                    <% } %>
                </div>

                <!-- Bảng hiển thị kết quả -->
                <table id="vehicleTable">
                    <thead>
                        <tr>
                            <th>ID Phương Tiện</th>
                            <th>ID Chủ Sở Hữu</th>
                            <th>Biển Số Xe</th>
                            <th>Hãng Xe</th>
                            <th>Mẫu Xe</th>
                            <th>Năm Sản Xuất</th>
                            <th>Số Động Cơ</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% 
                            List<Vehicle> vehicles = (List<Vehicle>) request.getAttribute("vehicleList");
                            if (vehicles != null && !vehicles.isEmpty()) {
                                for (Vehicle vehicle : vehicles) {
                        %>
                        <tr>
                            <td><%= vehicle.getId() %></td>
                            <td><%= vehicle.getOwnerId() %></td>
                            <td><%= vehicle.getPlate() %></td>
                            <td><%= vehicle.getBrand() %></td>
                            <td><%= vehicle.getModel() %></td>
                            <td><%= vehicle.getYear() %></td>
                            <td><%= vehicle.getEngineNumber() %></td>
                        </tr>
                        <% 
                                }
                            } else if (vehicles != null) { // Nếu danh sách rỗng, hiển thị thông báo không tìm thấy
                        %>
                        <tr><td colspan="7" class="message">Không tìm thấy phương tiện với biển số xe bạn nhập.</td></tr>
                        <% 
                            }
                        %>
                    </tbody>
                </table>
            </form>
        </div>
    </body>
</html>
