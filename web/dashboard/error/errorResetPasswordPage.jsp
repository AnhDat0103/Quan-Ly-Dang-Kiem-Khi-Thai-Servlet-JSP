<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Reset Password Error</title>
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
        <style>
            * {
                margin: 0;
                padding: 0;
                box-sizing: border-box;
            }

            body {
                font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
                background: linear-gradient(120deg, #e74c3c, #c0392b);
                height: 100vh;
                display: flex;
                align-items: center;
                justify-content: center;
            }

            .error-container {
                background: white;
                padding: 3rem;
                border-radius: 10px;
                box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
                text-align: center;
                max-width: 500px;
                width: 90%;
            }

            .error-icon {
                font-size: 5rem;
                color: #e74c3c;
                margin-bottom: 1.5rem;
                animation: shake 0.5s ease-in-out;
            }

            @keyframes shake {
                0%, 100% {
                    transform: translateX(0);
                }
                25% {
                    transform: translateX(-10px);
                }
                75% {
                    transform: translateX(10px);
                }
            }

            .error-title {
                color: #333;
                font-size: 2rem;
                margin-bottom: 1rem;
            }

            .error-message {
                color: #666;
                font-size: 1.1rem;
                line-height: 1.6;
                margin-bottom: 2rem;
            }

            .action-buttons {
                display: flex;
                gap: 1rem;
                justify-content: center;
            }

            .btn {
                padding: 0.8rem 1.5rem;
                border-radius: 5px;
                font-size: 1rem;
                cursor: pointer;
                transition: all 0.3s ease;
                text-decoration: none;
                display: inline-flex;
                align-items: center;
                gap: 0.5rem;
            }

            .btn-primary {
                background: #3498db;
                color: white;
                border: none;
            }

            .btn-primary:hover {
                background: #2980b9;
            }

            .btn-secondary {
                background: #95a5a6;
                color: white;
                border: none;
            }

            .btn-secondary:hover {
                background: #7f8c8d;
            }

            .error-details {
                margin-top: 2rem;
                padding-top: 1.5rem;
                border-top: 1px solid #eee;
                font-size: 0.9rem;
                color: #777;
            }

            .error-details p {
                margin-bottom: 0.5rem;
            }

            .error-code {
                font-family: monospace;
                background: #f8f9fa;
                padding: 0.2rem 0.5rem;
                border-radius: 3px;
            }
        </style>
    </head>
    <body>
        <div class="error-container">
            <i class="fas fa-exclamation-circle error-icon"></i>
            <h1 class="error-title">Đặt lại mật khẩu không thành công</h1>
            <p class="error-message">
                Liên kết đặt lại mật khẩu đã hết hạn hoặc không hợp lệ. 
                Vì lý do bảo mật, liên kết đặt lại mật khẩu chỉ có hiệu lực trong thời gian giới hạn là 5 phút.
            </p>

            <div class="action-buttons">
                <a href="cap-lai-mat-khau" class="btn btn-primary">
                    <i class="fas fa-redo"></i>
                    Yêu cầu liên kết đặt lại mới
                </a>
                <a href="dang-nhap" class="btn btn-secondary">
                    <i class="fas fa-sign-in-alt"></i>
                    Quay lại đăng nhập
                </a>
            </div>

            <div class="error-details">
                <p>Nếu bạn vẫn gặp sự cố, vui lòng liên hệ với nhóm hỗ trợ của chúng tôi.</p>
            </div>
        </div>
    </body>
</html> 