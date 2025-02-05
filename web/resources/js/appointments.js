document.addEventListener('DOMContentLoaded', function() {
    // Khởi tạo biến
    const searchInput = document.getElementById('searchInput');
    const appointmentTable = document.querySelector('.table');
    
    // Xử lý tìm kiếm
    function handleSearch() {
        const searchTerm = searchInput.value.toLowerCase();
        const rows = appointmentTable.querySelectorAll('tbody tr');
        
        rows.forEach(row => {
            const text = row.textContent.toLowerCase();
            row.style.display = text.includes(searchTerm) ? '' : 'none';
        });
    }

    // Xử lý thêm lịch hẹn mới
    function handleAddAppointment(event) {
        event.preventDefault();
        const form = event.target;
        const formData = new FormData(form);
        
        // TODO: Gửi dữ liệu đến server
        console.log('Adding new appointment...', Object.fromEntries(formData));
        
        // Đóng modal sau khi thêm
        const modal = bootstrap.Modal.getInstance(document.getElementById('addAppointmentModal'));
        modal.hide();
        
        // Reset form
        form.reset();
    }

    // Xử lý xóa lịch hẹn
    function handleDeleteAppointment(appointmentId) {
        if (confirm('Bạn có chắc chắn muốn xóa lịch hẹn này?')) {
            // TODO: Gửi yêu cầu xóa đến server
            console.log('Deleting appointment...', appointmentId);
        }
    }

    // Xử lý cập nhật trạng thái
    function handleStatusUpdate(appointmentId, newStatus) {
        // TODO: Gửi yêu cầu cập nhật đến server
        console.log('Updating status...', {appointmentId, newStatus});
    }

    // Đăng ký các event listeners
    searchInput?.addEventListener('input', handleSearch);

    document.querySelector('#addAppointmentModal form')?.addEventListener('submit', handleAddAppointment);

    // Khởi tạo các tooltips và popovers
    const tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'));
    tooltipTriggerList.map(function (tooltipTriggerEl) {
        return new bootstrap.Tooltip(tooltipTriggerEl);
    });
}); 