<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Tạo mới trung tâm kiểm định</title>
        <link href="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/style.min.css" rel="stylesheet" />
        <link href="./resources/css/styles.css" rel="stylesheet" />
        <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
    </head>
    <body class="sb-nav-fixed">
        <%@include file="./layout/header.jsp" %>
        <div id="layoutSidenav">
            <%@include file="./layout/navigation.jsp" %>
            <div id="layoutSidenav_content">
                <main>
                    <div class="container-fluid px-4">
                        <h1 class="mt-4">Tạo mới trung tâm kiểm định</h1>
                        <ol class="breadcrumb mb-4">
                            <li class="breadcrumb-item"><a href="#">Bảng điều khiển</a></li>
                            <li class="breadcrumb-item active">Tạo mới trung tâm kiểm định</li>
                        </ol>
                        <div class="d-flex justify-content-between">
                            <div class="mb-3">
                                <a class="btn btn-secondary" href="quan-tri-vien">
                                    <i class="fas fa-arrow-left me-1"></i>Quay lại
                                </a>
                            </div>
                        </div>

                        <div class="container">
                            <div class="card mb-4">
                                <div class="card-body">
                                    <form action="tao-moi-trung-tam-kiem-dinh" method="POST">
                                        <div class="row mb-3">
                                            <div class="col-md-6">
                                                <div class="form-group mb-3">
                                                    <label for="name" class="form-label">Tên trung tâm <span class="text-danger">*</span></label>
                                                    <input type="text" class="form-control ${not empty errorName ? 'is-invalid' : ''}" name="name"
                                                           placeholder="Nhập tên trung tâm kiểm định">
                                                    <c:if test="${not empty errorName}">
                                                        <div class="invalid-feedback">
                                                            ${errorName}
                                                        </div>
                                                    </c:if>

                                                </div>

                                                <div class="form-group mb-3">
                                                    <label for="email" class="form-label">Email <span class="text-danger">*</span></label>
                                                    <input type="email" class="form-control ${not empty errorEmail ? 'is-invalid' : ''}"  name="email" 
                                                           placeholder="example@email.com">

                                                    <c:if test="${not empty errorEmail}">
                                                        <div class="invalid-feedback">
                                                            ${errorEmail}
                                                        </div>
                                                    </c:if>
      
                                                </div>
                                            </div>

                                            <div class="col-md-6">
                                                <div class="form-group mb-3">
                                                    <label for="phone" class="form-label">Số điện thoại <span class="text-danger">*</span></label>
                                                    <input type="tel" class="form-control ${not empty errorPhone ? 'is-invalid' : ''}"  name="phone"
                                                            placeholder="0123456789">
                                                    <c:if test="${not empty errorPhone}">
                                                        <div class="invalid-feedback">
                                                            <c:out value="${errorPhone}" />

                                                        </div>
                                                    </c:if>

                                                </div>

                                                <div class="form-group mb-3">
                                                    <label for="address" class="form-label">Địa chỉ <span class="text-danger">*</span></label>
                                                    <textarea class="form-control ${not empty errorAddress ? 'is-invalid' : ''}" name="address" 
                                                              rows="3" placeholder="Nhập địa chỉ trung tâm"></textarea>
                                                     <c:if test="${not empty errorAddress}">
                                                        <div class="invalid-feedback">
                                                            <c:out value="${errorAddress}" />

                                                        </div>
                                                    </c:if>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="row">
                                            <div class="col-12">
                                                <div class="alert alert-info" role="alert">
                                                    <i class="fas fa-info-circle me-2"></i>
                                                    Vui lòng điền đầy đủ thông tin. Các trường có dấu <span class="text-danger">*</span> là bắt buộc.
                                                </div>
                                            </div>
                                        </div>

                                        <div class="row mt-3">
                                            <div class="col-12">
                                                <button type="submit" class="btn btn-primary me-2">
                                                    <i class="fas fa-save me-2"></i>Tạo mới
                                                </button>
                                                <a href="quan-tri-vien" class="btn btn-secondary">
                                                    <i class="fas fa-times me-2"></i>Hủy
                                                </a>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
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
    </body>
</html>