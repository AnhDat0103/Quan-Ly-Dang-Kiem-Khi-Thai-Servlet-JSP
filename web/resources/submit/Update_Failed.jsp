<%-- 
    Document   : Update_Failed
    Created on : Mar 23, 2025, 1:48:42 PM
    Author     : Lenovo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="that-bai" method="GET">
            <div style="text-align: center; margin-top: 50px;">
                <h1 style="color: #dc3545;">Cập Nhập thất bại!</h1>
                <h1 style="color: #dc3545;">${bug}</h1>
                <p style="font-size: 18px; margin: 20px 0;">Thông tin của bạn chưa được lưu vào hệ thống</p>
                <div style="margin: 30px 0;">
                    <a href="quan-ly-phuong-tien" style="text-decoration: none; background-color: #007bff; color: white; padding: 10px 20px; border-radius: 5px; display: inline-block; font-weight: bold; transition: background-color 0.3s ease; box-shadow: 0 2px 4px rgba(0,0,0,0.2);" onmouseover="this.style.backgroundColor='#0056b3'" onmouseout="this.style.backgroundColor='#007bff'">Quay về trang chủ</a>
                </div>
        </form>
    </div>
</body>
</html>
