<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Xử lý bản đăng kiểm</title>
        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
        <!-- Font Awesome -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet">
        <!-- Custom CSS -->
        <link href="resources/css/sb-admin-2.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.2/font/bootstrap-icons.css">
        <style>
            .custom-card {
                border-radius: 15px;
                box-shadow: 0 0 20px rgba(0,0,0,0.1);
                border: none;
            }
            .card-header {
                background: linear-gradient(45deg, #1cc88a, #36b9cc);
                color: white;
                border-radius: 15px 15px 0 0 !important;
                padding: 1.5rem;
            }
            .form-control {
                border-radius: 10px;
                padding: 0.75rem;
                border: 1px solid #e3e6f0;
            }
            .form-control:focus {
                border-color: #1cc88a;
                box-shadow: 0 0 0 0.2rem rgba(28, 200, 138, 0.25);
            }
            .btn {
                padding: 0.75rem 1.5rem;
                border-radius: 10px;
                font-weight: 600;
                transition: all 0.3s;
            }
            .btn-primary {
                background: #1cc88a;
                border: none;
            }
            .btn-primary:hover {
                background: #169b6b;
                transform: translateY(-2px);
            }
            .btn-secondary:hover {
                transform: translateY(-2px);
            }
            .vehicle-info {
                background: #f8f9fc;
                padding: 1.5rem;
                border-radius: 10px;
                margin-bottom: 1.5rem;
            }
            .vehicle-info p {
                margin-bottom: 0.5rem;
                color: #5a5c69;
                font-size: 1rem;
            }
            .vehicle-info strong {
                color: #2e59d9;
            }
            .alert {
                border-radius: 10px;
                padding: 1rem 1.5rem;
            }
        </style>
    </head>
    <body class="bg-light">
        <%@include file="../layout/navbar.jsp" %>

        <div class="container py-5">
            <div class="row justify-content-center">
                <div class="col-lg-8">
                    <div class="card custom-card">
                        <div class="card-header">
                            <h2 class="m-0 font-weight-bold">
                                <i class="fas fa-clipboard-check me-2"></i>
                                Xử lý bản đăng kiểm
                            </h2>
                        </div>
                        <div class="card-body p-4">
                            <c:if test="${not empty successMsg}">
                                <div class="alert alert-success alert-dismissible fade show" role="alert">
                                    <i class="fas fa-check-circle me-2"></i>
                                    ${successMsg}
                                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                                </div>
                            </c:if>
                            
                            <c:if test="${not empty errorMsg}">
                                <div class="alert alert-danger alert-dismissible fade show" role="alert">
                                    <i class="fas fa-exclamation-circle me-2"></i>
                                    ${errorMsg}
                                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                                </div>
                            </c:if>

                            <form action="xac-thuc-don-kiem-dinh" method="POST">
                                <input type="hidden" name="recordId" value="${record.recordId}">

                                <div class="vehicle-info mb-4">
                                    <h5 class="text-primary mb-3">
                                        <i class="fas fa-car me-2"></i>
                                        Thông tin phương tiện
                                    </h5>
                                    <p><strong>Biển số:</strong> <span class="ms-2">${record.vehicle.plateNumber}</span></p>
                                    <p><strong>Chủ xe:</strong> <span class="ms-2">${record.vehicle.owner.fullName}</span></p>
                                    <p><strong>Ngày kiểm định:</strong> <span class="ms-2">${record.inspectionDate}</span></p>
                                </div>

                                <div class="form-group mb-4">
                                    <label for="inspectorId" class="form-label fw-bold">
                                        <i class="fas fa-user-tie me-2"></i>
                                        Chọn người đăng kiểm:
                                    </label>
                                    <select name="inspectorId" id="inspectorId" required class="form-control form-select">
                                        <option value="">-- Chọn người đăng kiểm --</option>
                                        <c:forEach items="${sessionScope.inspectors}" var="inspector">
                                            <option value="${inspector.userId}"  ${record.inspectorID == inspector.userId ? 'selected' : ''}>${inspector.fullName} </option>
                                        </c:forEach>
                                    </select>
                                </div>

                                <div class="d-flex gap-3 mt-4">
                                    <button type="submit" class="btn btn-primary">
                                        <i class="fas fa-check me-2"></i>
                                        Xác nhận
                                    </button>
                                    <button type="button" class="btn btn-secondary" onclick="window.history.back();">
                                        <i class="fas fa-arrow-left me-2"></i>
                                        Quay lại
                                    </button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Footer -->
        <%@include file="../layout/footer.jsp" %>
        
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.4.1/jquery.easing.min.js"></script>
        <script src="resources/js/sb-admin-2.min.js"></script>
        <script>
            document.addEventListener("DOMContentLoaded", function () {
                // Animate alert fadeout
                let alertBox = document.querySelector(".alert");
                if (alertBox) {
                    setTimeout(function () {
                        alertBox.classList.add('fade');
                        setTimeout(function() {
                            alertBox.remove();
                        }, 300);
                    }, 5000);
                }
                
                // Add animation to buttons
                const buttons = document.querySelectorAll('.btn');
                buttons.forEach(button => {
                    button.addEventListener('mouseover', function() {
                        this.style.transform = 'translateY(-2px)';
                    });
                    button.addEventListener('mouseout', function() {
                        this.style.transform = 'translateY(0)';
                    });
                });
            });
        </script>
    </body>
</html>