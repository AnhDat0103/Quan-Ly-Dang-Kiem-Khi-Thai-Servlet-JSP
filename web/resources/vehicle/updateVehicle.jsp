<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <title>Cập Nhật Phương Tiện</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <style>
            body {
                min-height: 100vh;
                display: flex;
                flex-direction: column;
                margin: 0;
            }

            .navbar {
                position: fixed;
                top: 0;
                left: 0;
                width: 100%;
                z-index: 1030;
            }

            .content-wrapper {
                flex: 1;
                padding-top: 0px; /* Đảm bảo nội dung không bị navbar che */
                padding-bottom: 0px; /* Tránh bị footer che */
            }

            /* Footer nhỏ gọn hơn */
            .footer {
                position: fixed;
                bottom: 0;
                width: 100%;
                background-color: #f8f9fa;
                padding: 0.5rem 0; /* Giảm padding để footer nhỏ hơn */
                box-shadow: 0 -2px 5px rgba(0, 0, 0, 0.1);
                font-size: 0.9rem; /* Chữ nhỏ hơn */
            }
        </style>

    </head>
    <body>
        <%@include file="../layout/owner_navbar.jsp" %>
        <div class="container my-5 d-flex justify-content-center">
            <div class="card p-4 w-100" style="max-width: 600px;">
                <h2 class="text-center text-primary mb-4">Cập Nhật Phương Tiện</h2>
                <form action="update-phuong-tien" method="post">
                    <div class="mb-3">
                        <label class="form-label">Biển số xe:</label>
                        <input type="text" name="plateNumber" pattern="[0-9]{2}[A-Z]-[0-9]{5}" 
                               title="Biển số xe phải có định dạng: 30A-12345" 
                               class="form-control" required 
                               value="${vehicle.plateNumber}" readonly>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Hãng Xe:</label>
                        <input type="text" name="brand" class="form-control" required 
                               placeholder="${vehicle.brand}">
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Mẫu Xe:</label>
                        <input type="text" name="model" class="form-control" required 
                               placeholder="${vehicle.model}">
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Năm Sản Xuất:</label>
                        <input type="number" name="manufactureYear" class="form-control" required 
                               placeholder="${vehicle.manufactureYear}">
                    </div>
                    <div class="mb-4">
                        <label class="form-label">Số Động Cơ:</label>
                        <input type="text" name="engineNumber" class="form-control" required 
                               placeholder="${vehicle.engineNumber}">
                    </div>
                    <button type="submit" class="btn btn-success w-100" 
                            onclick="return confirm('Xác nhận cập nhật phương tiện?');">
                        Cập Nhật
                    </button>
                </form>
            </div>
        </div>
        <%@include file="../layout/owner_footer.jsp" %>
    </body>
</html>