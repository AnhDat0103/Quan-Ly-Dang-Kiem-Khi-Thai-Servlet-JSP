<%-- 
    Document   : resetPasswordSuccess
    Created on : Mar 12, 2025, 10:20:34 AM
    Author     : DAT
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Đổi Mật Khẩu Thành Công</title>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: linear-gradient(120deg, #2ecc71, #27ae60);
            height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
        }

        .success-container {
            background: white;
            padding: 3rem;
            border-radius: 15px;
            box-shadow: 0 10px 30px rgba(0, 0, 0, 0.1);
            text-align: center;
            max-width: 500px;
            width: 90%;
        }

        .success-icon {
            width: 100px;
            height: 100px;
            background: #2ecc71;
            border-radius: 50%;
            display: flex;
            align-items: center;
            justify-content: center;
            margin: 0 auto 2rem;
            animation: scaleIn 0.5s ease-out;
        }

        .success-icon i {
            font-size: 50px;
            color: white;
        }

        @keyframes scaleIn {
            0% {
                transform: scale(0);
            }
            80% {
                transform: scale(1.1);
            }
            100% {
                transform: scale(1);
            }
        }

        .success-title {
            color: #2c3e50;
            font-size: 2rem;
            margin-bottom: 1rem;
            animation: fadeIn 0.5s ease-out 0.5s both;
        }

        .success-message {
            color: #7f8c8d;
            font-size: 1.1rem;
            line-height: 1.6;
            margin-bottom: 2rem;
            animation: fadeIn 0.5s ease-out 0.7s both;
        }

        @keyframes fadeIn {
            from {
                opacity: 0;
                transform: translateY(20px);
            }
            to {
                opacity: 1;
                transform: translateY(0);
            }
        }

        .login-button {
            display: inline-block;
            padding: 1rem 2rem;
            background: #2ecc71;
            color: white;
            text-decoration: none;
            border-radius: 50px;
            font-size: 1.1rem;
            transition: all 0.3s ease;
            animation: fadeIn 0.5s ease-out 0.9s both;
        }

        .login-button:hover {
            background: #27ae60;
            transform: translateY(-2px);
            box-shadow: 0 5px 15px rgba(46, 204, 113, 0.3);
        }

        .login-button i {
            margin-right: 10px;
        }

        .additional-info {
            margin-top: 2rem;
            padding-top: 2rem;
            border-top: 1px solid #eee;
            color: #95a5a6;
            font-size: 0.9rem;
            animation: fadeIn 0.5s ease-out 1.1s both;
        }
    </style>
</head>
<body>
    <div class="success-container">
        <div class="success-icon">
            <i class="fas fa-check"></i>
        </div>
        <h1 class="success-title">Đổi Mật Khẩu Thành Công!</h1>
        <p class="success-message">
            Mật khẩu của bạn đã được cập nhật thành công. 
            Bạn có thể đăng nhập bằng mật khẩu mới ngay bây giờ.
        </p>
        <a href="dang-nhap" class="login-button">
            <i class="fas fa-sign-in-alt"></i>
            Đăng Nhập Ngay
        </a>
        <div class="additional-info">
            <p>Nếu bạn cần hỗ trợ thêm, vui lòng liên hệ với chúng tôi</p>
        </div>
    </div>
</body>
</html>