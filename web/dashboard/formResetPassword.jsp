<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Reset Password</title>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: linear-gradient(120deg, #2980b9, #8e44ad);
            height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
        }

        .container {
            background: white;
            padding: 2rem;
            border-radius: 10px;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
            width: 400px;
        }

        .form-header {
            text-align: center;
            margin-bottom: 2rem;
        }

        .form-header i {
            font-size: 3rem;
            color: #2980b9;
            margin-bottom: 1rem;
        }

        .form-header h2 {
            color: #333;
            font-size: 1.8rem;
            margin-bottom: 0.5rem;
        }

        .form-header p {
            color: #666;
            font-size: 0.9rem;
        }

        .form-group {
            margin-bottom: 1.5rem;
            position: relative;
        }

        .form-group label {
            display: block;
            margin-bottom: 0.5rem;
            color: #555;
            font-size: 0.9rem;
        }

        .form-group input {
            width: 100%;
            padding: 0.8rem;
            border: 1px solid #ddd;
            border-radius: 5px;
            font-size: 1rem;
            transition: border-color 0.3s ease;
        }

        .form-group input:focus {
            border-color: #2980b9;
            outline: none;
        }

        .password-toggle {
            position: absolute;
            right: 10px;
            top: 35px;
            cursor: pointer;
            color: #666;
        }

        .submit-btn {
            width: 100%;
            padding: 1rem;
            background: #2980b9;
            color: white;
            border: none;
            border-radius: 5px;
            font-size: 1rem;
            cursor: pointer;
            transition: background 0.3s ease;
        }

        .submit-btn:hover {
            background: #2471a3;
        }

        .password-requirements {
            margin-top: 1rem;
            padding: 1rem;
            background: #f8f9fa;
            border-radius: 5px;
        }

        .password-requirements h3 {
            font-size: 0.9rem;
            color: #555;
            margin-bottom: 0.5rem;
        }

        .requirement-list {
            list-style: none;
            font-size: 0.8rem;
            color: #666;
        }

        .requirement-list li {
            margin-bottom: 0.3rem;
        }

        .requirement-list li i {
            margin-right: 0.5rem;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="form-header">
            <i class="fas fa-lock"></i>
            <h2>Reset Password</h2>
            <p>Please enter your new password</p>
        </div>
        
        <form action="nhap-mat-khau-moi-nao" method="POST" id="resetForm">
            <input type="hidden" name="email" value="${email}">
            <input type="hidden" name="token" value="${token}">
            <div class="form-group">
                <label for="password">Mật khẩu mới</label>
                <input type="password" id="password" name="password" required>
                <i class="fas fa-eye password-toggle" onclick="togglePassword('password')"></i>
            </div>
            
            <div class="form-group">
                <label for="confirmPassword">Xác nhận lại mật khẩu</label>
                <input type="password" id="confirmPassword" name="confirmPassword" required>
                <i class="fas fa-eye password-toggle" onclick="togglePassword('confirmPassword')"></i>
            </div>
            <button type="submit" class="submit-btn">Thay đổi mật khẩu</button>
            
            <div class="password-requirements">
                <h3>Yêu cầu mật khẩu:</h3>
                <ul class="requirement-list">
                    <li><i class="fas fa-check"></i>Dễ nhớ thôi, không lại quên :((((</li>

                </ul>
            </div>
        </form>
    </div>

    <script>
        function togglePassword(inputId) {
            const input = document.getElementById(inputId);
            const icon = input.nextElementSibling;
            
            if (input.type === 'password') {
                input.type = 'text';
                icon.classList.remove('fa-eye');
                icon.classList.add('fa-eye-slash');
            } else {
                input.type = 'password';
                icon.classList.remove('fa-eye-slash');
                icon.classList.add('fa-eye');
            }
        }

        document.getElementById('resetForm').addEventListener('submit', function(e) {
            const password = document.getElementById('password').value;
            const confirmPassword = document.getElementById('confirmPassword').value;
            
            if (password !== confirmPassword) {
                e.preventDefault();
                alert('Mật khẩu không khớp. Hãy thử lại!!!');
                return;
            }
        });
    </script>
</body>
</html> 