<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Đặt lại mật khẩu</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <div class="row justify-content-center mt-5">
                <div class="col-md-6">
                    <div class="card">
                        <div class="card-header">
                            <h3 class="text-center">Đặt lại mật khẩu</h3>
                        </div>
                        <div class="card-body">
                            <% if(request.getAttribute("error") != null) { %>
                                <div class="alert alert-danger">
                                    <%= request.getAttribute("error") %>
                                </div>
                            <% } %>
                            <% if(request.getAttribute("message") != null) { %>
                                <div class="alert alert-success">
                                    <%= request.getAttribute("message") %>
                                </div>
                            <% } %>
                            <form action="cap-lai-mat-khau" method="POST">
                                <div class="mb-3">
                                    <label for="email" class="form-label">Email</label>
                                    <input type="email" class="form-control" id="email" name="email" required>
                                    <div class="form-text">
                                        <p>Nhập email đã đăng ký của bạn để nhận link đặt lại mật khẩu.</p>
                                       
                                        <p>Link lấy lại mật khẩu sẽ có hiệu lực trong vòng 5 phút.</p>
                                      
                                        <p>Nếu không nhận được mail, vui lòng liên hệ quản trị viên để biết thêm chi tiết.</p>
                                    </div>
                                </div>
                                <div class="d-grid">
                                    <button type="submit" class="btn btn-primary">Gửi yêu cầu</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html> 