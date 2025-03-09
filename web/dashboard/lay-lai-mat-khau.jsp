<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Lấy Lại mật khẩu</title>
        <link href="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/style.min.css" rel="stylesheet" />
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" />
        <link href="./resources/css/styles.css" rel="stylesheet" />
        <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
    </head>
    <body>

        <%@include file="./layout/header.jsp" %>
        <div id="layoutSidenav">
            <%@include file="./layout/navigation.jsp" %>
            <div id="layoutSidenav_content">
                <main class="container mt-4">
                    <h2 class="text-center mb-4">Lấy lại mật khẩu</h2>
                    <div class="row justify-content-center">
                        <div class="col-md-6">
                            <form action="lay-lai-mat-khau" method="post" class="needs-validation" novalidate>
                                <div class="mb-3">
                                    <label for="email" class="form-label">Gmail:</label>
                                    <input type="email" class="form-control" id="email" name="email" required>
                                    <div class="invalid-feedback">Vui lòng nhập email hợp lệ.</div>
                                </div>
                                <div class="mb-3">
                                    <label for="newPassword" class="form-label">Mật khẩu mới:</label>
                                    <input type="password" class="form-control" id="newPassword" name="newPassword" required>
                                    <div class="invalid-feedback">Vui lòng nhập mật khẩu mới.</div>
                                </div>
                                <div class="mb-3">
                                    <label for="confirmPassword" class="form-label">Xác nhận mật khẩu mới:</label>
                                    <input type="password" class="form-control" id="confirmPassword" name="confirmPassword" required>
                                    <div class="invalid-feedback">Vui lòng xác nhận mật khẩu mới.</div>
                                </div>
                                <%
                                String message = (String) request.getAttribute("message");
                                if (message != null) {
                            %>
                                <p style="color:red;"><%= message %></p>
                            <%
                                }
                            %>
                                <button type="submit" class="btn btn-primary w-100">Lấy lại mật khẩu</button>
                            </form>
                           
                        </div>
                    </div>
                </main>
                <%@include file="./layout/footer.jsp" %>
            </div>
        </div>
            
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="./resources/js/scripts.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/umd/simple-datatables.min.js" crossorigin="anonymous"></script>
        <script src="./resources/js/datatables-simple-demo.js"></script>
        <script>
            // Example starter JavaScript for disabling form submissions if there are invalid fields
            (function () {
                'use strict'
                var forms = document.querySelectorAll('.needs-validation')
                Array.prototype.slice.call(forms)
                    .forEach(function (form) {
                        form.addEventListener('submit', function (event) {
                            if (!form.checkValidity()) {
                                event.preventDefault()
                                event.stopPropagation()
                            }
                            form.classList.add('was-validated')
                        }, false)
                    })
            })()
        </script>
    </body>
</html>
