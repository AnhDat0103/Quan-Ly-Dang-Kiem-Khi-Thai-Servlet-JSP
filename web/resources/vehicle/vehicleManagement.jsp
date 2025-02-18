<%-- 
    Document   : vehicleManagement
    Created on : Feb 5, 2025, 9:20:20 AM
    Author     : Lenovo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Trang Quản Lý Phương Tiện</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
        <link rel="stylesheet" href="../css/css.vehicle/quanlyPT.css"> <!-- Đảm bảo liên kết với file CSS -->
    </head>
    <body>
        <header>
            <div class="header-container">
                <!-- Điều hướng -->
                <nav class="navbar">
                    <ul>
                        <li><a href="../owner/ownerHomePage.jsp">Trang Chủ</a></li>
                        <li class="dropdown">
                            <a href="#" class="dropbtn">Quản Lý Phương Tiện</a>
                            <div class="dropdown-content">
                                <a href="dangKyPT.jsp">Đăng Ký Phương Tiện</a>
                                <a href="timKiemPT.jsp">Tìm Phương Tiện</a>
                                <a href="xoaPT.jsp">Xoá Phương Tiện</a>
                            </div>
                        </li>
                    </ul>
                </nav>

                <!-- Ô tìm kiếm -->
                <div class="search-box">
                    <input type="text" placeholder="Tìm kiếm...">
                    <button type="button">Tìm</button>
                </div>
            </div>
        </header>

        <!-- Nội dung chính -->
        <main class="main-content">
            <div class="content-introduction">
                <h3>Chào mừng bạn đến với trang quản lý phương tiện</h3>
                <p>Chúng tôi cung cấp các công cụ giúp bạn quản lý phương tiện dễ dàng và hiệu quả.</p>
            </div>

            <!-- Các chức năng -->
            <div class="functionality">
                <div class="function-item">
                    <h4>Quản lý phương tiện</h4>
                    <p>Quản lý thông tin phương tiện của bạn</p>
                    <a href="phuongTien.jsp" class="btn">Xem chi tiết</a>
                </div>
                <div class="function-item">
                    <h4>Đăng ký kiểm tra</h4>
                    <p>Đăng ký lịch kiểm tra phương tiện</p>
                    <a href="dangKy.jsp" class="btn">Đăng ký ngay</a>
                </div>
                <div class="function-item">
                    <h4>Theo dõi trạng thái</h4>
                    <p>Kiểm tra trạng thái kiểm tra phương tiện</p>
                    <a href="theoDoi.jsp" class="btn">Theo dõi</a>
                </div>
            </div>
        </main>

        <!-- Footer -->
        <footer>
            <p>&copy; 2025 Quản Lý Phương Tiện. Mọi quyền được bảo lưu.</p>
        </footer>
    </body>
</html>
