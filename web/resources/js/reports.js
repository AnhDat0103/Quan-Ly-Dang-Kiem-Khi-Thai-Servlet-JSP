document.addEventListener('DOMContentLoaded', function() {
    // Khởi tạo biến và hằng số
    const fromDateInput = document.querySelector('input[type="date"]:first-of-type');
    const toDateInput = document.querySelector('input[type="date"]:last-of-type');
    const reportTypeSelect = document.querySelector('select');
    const generateReportBtn = document.querySelector('.btn-primary');
    const exportExcelBtn = document.querySelector('.btn-success');

    // Khởi tạo biểu đồ
    let revenueChart = null;
    let inspectionChart = null;

    // Set giá trị mặc định cho date inputs
    const today = new Date();
    const thirtyDaysAgo = new Date(today);
    thirtyDaysAgo.setDate(today.getDate() - 30);
    
    fromDateInput.value = formatDate(thirtyDaysAgo);
    toDateInput.value = formatDate(today);

    // Xử lý sự kiện tạo báo cáo
    generateReportBtn.addEventListener('click', function() {
        generateReport();
    });

    // Xử lý sự kiện xuất Excel
    exportExcelBtn.addEventListener('click', function() {
        exportToExcel();
    });

    // Hàm hỗ trợ format date
    function formatDate(date) {
        const year = date.getFullYear();
        const month = String(date.getMonth() + 1).padStart(2, '0');
        const day = String(date.getDate()).padStart(2, '0');
        return `${year}-${month}-${day}`;
    }

    // Hàm format tiền tệ VND
    function formatCurrency(amount) {
        return new Intl.NumberFormat('vi-VN', {
            style: 'currency',
            currency: 'VND'
        }).format(amount);
    }

    // Hàm tạo báo cáo
    function generateReport() {
        const fromDate = fromDateInput.value;
        const toDate = toDateInput.value;
        const reportType = reportTypeSelect.value;

        // TODO: Gọi API để lấy dữ liệu báo cáo
        // Giả lập dữ liệu cho demo
        const reportData = {
            totalVehicles: 215,
            passedVehicles: 180,
            failedVehicles: 35,
            passRate: 83.7,
            revenue: 107500000,
            details: [
                {
                    date: '2024-03-20',
                    totalVehicles: 25,
                    passed: 20,
                    failed: 5,
                    passRate: 80,
                    revenue: 12500000
                },
                {
                    date: '2024-03-19',
                    totalVehicles: 30,
                    passed: 28,
                    failed: 2,
                    passRate: 93.3,
                    revenue: 15000000
                }
            ]
        };

        updateDashboardCards(reportData);
        updateReportTable(reportData.details);
        updateCharts(reportData.details);
    }

    // Hàm xuất Excel
    function exportToExcel() {
        const table = document.querySelector('.table');
        const wb = XLSX.utils.table_to_book(table, {sheet: "Báo cáo"});
        XLSX.writeFile(wb, `bao-cao-${formatDate(new Date())}.xlsx`);
    }

    // Hàm cập nhật các card thống kê
    function updateDashboardCards(data) {
        document.querySelector('.text-primary + .h5').textContent = data.totalVehicles;
        document.querySelector('.text-success + .h5').textContent = data.passedVehicles;
        document.querySelector('.text-warning + .h5').textContent = data.failedVehicles;
        
        const passRateElements = document.querySelectorAll('.text-info + div .h5, .progress-bar');
        passRateElements.forEach(element => {
            if (element.classList.contains('progress-bar')) {
                element.style.width = `${data.passRate}%`;
            } else {
                element.textContent = `${data.passRate}%`;
            }
        });
    }

    // Hàm cập nhật bảng báo cáo
    function updateReportTable(details) {
        const tbody = document.querySelector('tbody');
        tbody.innerHTML = '';

        details.forEach(detail => {
            const row = document.createElement('tr');
            row.innerHTML = `
                <td>${formatDateForDisplay(detail.date)}</td>
                <td>${detail.totalVehicles}</td>
                <td>${detail.passed}</td>
                <td>${detail.failed}</td>
                <td>${detail.passRate}%</td>
                <td>${formatCurrency(detail.revenue)}</td>
            `;
            tbody.appendChild(row);
        });
    }

    // Hàm format date cho hiển thị
    function formatDateForDisplay(dateString) {
        const date = new Date(dateString);
        return date.toLocaleDateString('vi-VN');
    }

    // Hàm cập nhật biểu đồ
    function updateCharts(details) {
        const dates = details.map(detail => formatDateForDisplay(detail.date));
        const revenues = details.map(detail => detail.revenue);
        const passRates = details.map(detail => detail.passRate);

        // Biểu đồ doanh thu
        if (revenueChart) {
            revenueChart.destroy();
        }
        const revenueCtx = document.getElementById('revenueChart').getContext('2d');
        revenueChart = new Chart(revenueCtx, {
            type: 'bar',
            data: {
                labels: dates,
                datasets: [{
                    label: 'Doanh thu',
                    data: revenues,
                    backgroundColor: 'rgba(78, 115, 223, 0.5)',
                    borderColor: 'rgba(78, 115, 223, 1)',
                    borderWidth: 1
                }]
            },
            options: {
                responsive: true,
                scales: {
                    y: {
                        beginAtZero: true,
                        ticks: {
                            callback: function(value) {
                                return formatCurrency(value);
                            }
                        }
                    }
                }
            }
        });

        // Biểu đồ tỷ lệ đạt
        if (inspectionChart) {
            inspectionChart.destroy();
        }
        const inspectionCtx = document.getElementById('inspectionChart').getContext('2d');
        inspectionChart = new Chart(inspectionCtx, {
            type: 'line',
            data: {
                labels: dates,
                datasets: [{
                    label: 'Tỷ lệ đạt',
                    data: passRates,
                    borderColor: 'rgba(28, 200, 138, 1)',
                    tension: 0.1,
                    fill: false
                }]
            },
            options: {
                responsive: true,
                scales: {
                    y: {
                        beginAtZero: true,
                        max: 100,
                        ticks: {
                            callback: function(value) {
                                return value + '%';
                            }
                        }
                    }
                }
            }
        });
    }

    // Khởi tạo báo cáo khi trang load
    generateReport();

    // Khởi tạo DataTable
    $(document).ready(function() {
        $('.table').DataTable({
            language: {
                url: '//cdn.datatables.net/plug-ins/1.11.5/i18n/vi.json'
            },
            pageLength: 10,
            order: [[0, 'desc']]
        });
    });
}); 