/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.station;

import config.Configuration;
import dao.InspectionRecordDao;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import model.User;
import model.dto.Report;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 *
 * @author DAT
 */
public class GetReportPage extends HttpServlet {

    InspectionRecordDao ird = new InspectionRecordDao();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet GetReportPage</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet GetReportPage at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("currentUser");
        String startDate = request.getParameter("fromDate") != null ? request.getParameter("fromDate") : Configuration.getCurrentTimeByFormat(new Date());
        String endDate = request.getParameter("toDate") != null ? request.getParameter("toDate") : Configuration.getCurrentTimeByFormat(new Date());
        int stationId = currentUser.getInspectionStation().getStationId();
        request.setAttribute("startDate", startDate);
        request.setAttribute("endDate", endDate);
        HashMap<String, Integer> totalRecordByDateForEachDay = ird.getNoRecordsWithThoughtPendingByADay(startDate, endDate, stationId);
        HashMap<String, Integer> totalRecordPassByDateForEachDay = ird.getNoRecordsPassByADay(startDate, endDate, stationId);
        HashMap<String, Integer> totalRecordFailByDateForEachDay = ird.getNoRecordsFailByADay(startDate, endDate, stationId);
        int totalRecord = ird.getNoOfRecordsWithTime(startDate, endDate, stationId);
        List<Report> reports = getReportListWithTotalRecordByDate(totalRecordByDateForEachDay);
        setReportListWithTotalRecordPassByDate(reports, totalRecordPassByDateForEachDay);
        setReportListWithTotalRecordFailByDate(reports, totalRecordFailByDateForEachDay);
        session.setAttribute("records", reports);
        request.setAttribute("totalRecord", totalRecord);
        int sumVehicle = getSumVehicle(reports);
        int sumVehiclePass = getSumVehiclePass(reports);
        int sumVehicleFail = getSumVehicleFail(reports);
        double percentPass = getPercentPass(sumVehiclePass, sumVehicle);
        request.setAttribute("sumVehicle", sumVehicle);
        request.setAttribute("sumVehiclePass", sumVehiclePass);
        request.setAttribute("sumVehicleFail", sumVehicleFail);
        request.setAttribute("percentPass", percentPass);
        request.getRequestDispatcher("resources/station/report.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        List<Report> roReports = (List<Report>) session.getAttribute("records");
        if (action.equals("exportExcel")) {
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment;filename=reportsList" + new Date().getTime() + ".xls");
            Workbook wb = new HSSFWorkbook();
            Sheet sheet = wb.createSheet("Reports");
            // header
            int rowNo = 0;
            Row row = sheet.createRow(rowNo++);
            int cellNum = 0;
            Cell cell = row.createCell(cellNum++);
            cell.setCellValue("Ngày");

            cell = row.createCell(cellNum++);
            cell.setCellValue("Số lượng xe");

            cell = row.createCell(cellNum++);
            cell.setCellValue("Đạt");

            cell = row.createCell(cellNum++);
            cell.setCellValue("	Không đạt");

            cell = row.createCell(cellNum++);
            cell.setCellValue("	Tỷ lệ đạt(%)");

            cell = row.createCell(cellNum++);
            cell.setCellValue("Doanh thu(đ)");

            // detail
            for (Report r : roReports) {
                cellNum = 0;
                row = sheet.createRow(rowNo++);
                cell = row.createCell(cellNum++);
                cell.setCellValue(r.getInspectionDate());

                cell = row.createCell(cellNum++);
                cell.setCellValue(r.getSumNumOfVehicle());

                cell = row.createCell(cellNum++);
                cell.setCellValue(r.getSumNumOfPass());

                cell = row.createCell(cellNum++);
                cell.setCellValue(r.getSumNumOfFail());

                cell = row.createCell(cellNum++);
                cell.setCellValue(getPercentPass(r.getSumNumOfPass(), r.getSumNumOfVehicle()));

                cell = row.createCell(cellNum++);
                cell.setCellValue(getTotalAmount(r.getSumNumOfVehicle()));

            }

            wb.write(response.getOutputStream());
            wb.close();
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private List<Report> getReportListWithTotalRecordByDate(HashMap<String, Integer> totalRecordByDateForEachDay) {
        List<Report> reports = new ArrayList<>();
        totalRecordByDateForEachDay.forEach((t, u) -> {
            reports.add(new Report(u, 0, 0, t));
        });
        return reports;
    }

    private void setReportListWithTotalRecordPassByDate(List<Report> reports, HashMap<String, Integer> totalRecordPassByDateForEachDay) {
        totalRecordPassByDateForEachDay.forEach((t, u) -> {
            reports.forEach((x) -> {
                if (t.equals(x.getInspectionDate())) {
                    x.setSumNumOfPass(u);
                }
            });
        });
    }

    private void setReportListWithTotalRecordFailByDate(List<Report> reports, HashMap<String, Integer> totalRecordFailByDateForEachDay) {
        totalRecordFailByDateForEachDay.forEach((t, u) -> {
            reports.forEach((x) -> {
                if (t.equals(x.getInspectionDate())) {
                    x.setSumNumOfFail(u);
                }
            });
        });
    }

    private int getSumVehicle(List<Report> reports) {
        int sum = 0;
        for (Report r : reports) {
            sum += r.getSumNumOfVehicle();
        }
        return sum;
    }

    private int getSumVehiclePass(List<Report> reports) {
        int sum = 0;
        for (Report r : reports) {
            sum += r.getSumNumOfPass();
        }
        return sum;
    }

    private int getSumVehicleFail(List<Report> reports) {
        int sum = 0;
//        reports.stream().mapToInt((value) -> value.getSumNumOfFail()).sum();
        for (Report r : reports) {
            sum += r.getSumNumOfFail();
        }
        return sum;
    }

    private double getPercentPass(int noOfPass, int total) {
        if (total == 0) {
            return 0;
        }
        return (double) (noOfPass * 100) / total;
    }

    private long getTotalAmount(int sumVehicle) {
        return sumVehicle * 90000;
    }
}
