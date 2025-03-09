document.addEventListener('DOMContentLoaded', function () {
    // Khởi tạo các elements
    const profileForm = document.getElementById('profileForm');
    const editProfileBtn = document.getElementById('editProfileBtn');
    const cancelBtn = document.getElementById('cancelBtn');
    const saveButtons = document.getElementById('saveButtons');
    const changeAvatarBtn = document.getElementById('changeAvatarBtn');
    const avatarInput = document.getElementById('avatarInput');
    const formInputs = profileForm.querySelectorAll('input, textarea');
    const saveAvatarBtn = document.getElementById('saveAvatarBtn');
    let selectedAvatarFile = null;

    // Load thông tin người dùng
//    loadUserProfile();


    // Xử lý sự kiện chỉnh sửa profile
    editProfileBtn.addEventListener('click', function () {
        toggleEditMode(true);
    });

    // Xử lý sự kiện hủy chỉnh sửa
    cancelBtn.addEventListener('click', function () {
        toggleEditMode(false);
        loadUserProfile(); // Reset lại form
    });

    // Xử lý sự kiện submit form
    profileForm.addEventListener('submit', function (e) {
        saveProfile();
    });

    // Xử lý sự kiện thay đổi ảnh đại diện
    changeAvatarBtn.addEventListener('click', function () {
        avatarInput.click();
    });

    avatarInput.addEventListener('change', function (e) {
        if (e.target.files && e.target.files[0]) {
            selectedAvatarFile = e.target.files[0];
            // Hiển thị preview ảnh
            const reader = new FileReader();
            reader.onload = function (e) {
                document.querySelector('.img-profile').src = e.target.result;
                document.querySelector('input[name="newAvatar"]').value = e.target.result;
            };
            reader.readAsDataURL(selectedAvatarFile);
            // Hiển thị nút lưu
            saveAvatarBtn.style.display = 'inline-block';   
        }
    });

    // Thêm xử lý sự kiện cho nút lưu ảnh
    saveAvatarBtn.addEventListener('click', function () {
        if (selectedAvatarFile) {
            uploadAvatar(selectedAvatarFile);
        }
    });

    // Hàm bật/tắt chế độ chỉnh sửa
    function toggleEditMode(editable) {
        formInputs.forEach(input => {
            input.disabled = !editable;
        });
        saveButtons.style.display = editable ? 'block' : 'none';
        editProfileBtn.style.display = editable ? 'none' : 'block';
    }

    //Hàm load thông tin người dùng
//    function loadUserProfile() {
//        // TODO: Gọi API để lấy thông tin người dùng
//        // Demo data
//        const userData = {
//            fullName: 'Nguyễn Văn A',
//            email: 'nguyenvana@example.com',
//            phone: '0901234567',
//            address: '123 Đường ABC, Quận XYZ, TP.HCM'
//        };
//
//        // Điền thông tin vào form
//        Object.keys(userData).forEach(key => {
//            const input = document.getElementById(key);
//            if (input) {
//                input.value = userData[key];
//            }
//        });
//    }

    // Hàm lưu thông tin profile
    function saveProfile() {
        const formData = new FormData(profileForm);
        const profileData = Object.fromEntries(formData);

        // TODO: Gọi API để cập nhật thông tin
        fetch('cap-nhat-thong-tin', {
            method: 'POST',
            body: formData
        })
                .then(response => response.json()) // Đọc phản hồi từ Servlet
                .then(data => {
                    if (data.success) {
                        toggleEditMode(false);
                        showAlert('success', 'Cập nhật thông tin thành công!');
                    } else {
                        showAlert('danger', 'Cập nhật thất bại! Vui lòng thử lại.');
                    }
                })
                .catch(error => {
                    console.error('Lỗi khi cập nhật thông tin:', error);
                    showAlert('danger', 'Lỗi kết nối máy chủ!');
                });
    }

    // Hàm upload avatar
    function uploadAvatar(file) {
        // Tạo FormData để gửi file
        const formData = new FormData();
        formData.append('avatar', file);

       fetch('cap-nhat-thong-tin', {
            method: 'POST',
            body: formData
        })
                .then(response => response.json()) // Đọc phản hồi từ Servlet
                .then(data => {
                    if (data.success) {
                        toggleEditMode(false);
                        showAlert('success', 'Cập nhật thông tin thành công!');
                    } else {
                        showAlert('danger', 'Cập nhật thất bại! Vui lòng thử lại.');
                    }
                })
                .catch(error => {
                    console.error('Lỗi khi cập nhật thông tin:', error);
                    showAlert('danger', 'Lỗi kết nối máy chủ!');
                });
    }

    // Hàm hiển thị thông báo
    function showAlert(type, message) {
        const alertDiv = document.createElement('div');
        alertDiv.className = `alert alert-${type} alert-dismissible fade show`;
        alertDiv.innerHTML = `
            ${message}
            <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
        `;

        document.querySelector('.container-fluid').insertBefore(
                alertDiv,
                document.querySelector('.container-fluid').firstChild
                );

        setTimeout(() => {
            alertDiv.remove();
        }, 3000);
    }

    // Xử lý đổi mật khẩu
    document.getElementById('changePasswordForm')?.addEventListener('submit', function (e) {
        console.log('Changing password...');
    });

    // Xử lý cài đặt thông báo
    const notificationToggles = document.querySelectorAll('.form-check-input');
    notificationToggles.forEach(toggle => {
        toggle.addEventListener('change', function (e) {
            // TODO: Lưu cài đặt thông báo
            console.log('Notification setting changed:', e.target.id, e.target.checked);
        });
    });
}); 