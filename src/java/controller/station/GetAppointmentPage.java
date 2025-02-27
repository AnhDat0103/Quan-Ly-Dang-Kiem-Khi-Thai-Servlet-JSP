/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.station;

import config.Configuration;
import dao.InspectionRecordDao;
import dao.UserDao;
import dao.VehicleDao;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import model.InspectionRecords;
import model.User;
import model.Vehicles;

/**
 *
 * @author DAT
 */
public class GetAppointmentPage extends HttpServlet {

    InspectionRecordDao ird = new InspectionRecordDao();
    UserDao ud = new UserDao();
    VehicleDao vd = new VehicleDao();

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
            out.println("<title>Servlet GetAppointmentPage</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet GetAppointmentPage at " + request.getContextPath() + "</h1>");
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
        List<InspectionRecords> inspectionRecordses = new ArrayList<>();
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("currentUser");
        int stationId = currentUser.getInspectionStation().getStationId();
        request.setAttribute("stationID", stationId);
        String keyWord = request.getParameter("tu-khoa-tim-kiem") != null ? request.getParameter("tu-khoa-tim-kiem") : "";
        String startDate = request.getParameter("start-date") != null ? request.getParameter("start-date") : "";
        String endDate = request.getParameter("end-date") != null ? request.getParameter("end-date") : "";
        String statusReq = request.getParameter("trang-thai") != null ? request.getParameter("trang-thai") : "";
        request.setAttribute("statusFiltered", statusReq);
        int page = 1;
        int recordPerPage = 4;
        try {
            if (request.getParameter("trang-so") != null) {
                page = Integer.parseInt(request.getParameter("trang-so"));
            }
        } catch (NumberFormatException e) {
            page = 1;
        }
        int noOfRecords = 0;
        int startRecord = (page - 1) * recordPerPage;

        String action = request.getParameter("action") == null ? "" : request.getParameter("action");
        request.setAttribute("action", action);
        if (action.equals("loc-theo-thoi-gian")) {
            String status = getStatus(statusReq);
            inspectionRecordses = ird.getListInspectionRecordsWithTime(status, startDate, endDate, stationId, startRecord, recordPerPage);
            request.setAttribute("startDateKey", startDate);
            request.setAttribute("endDateKey", endDate);
            noOfRecords = ird.getNoOfRecordsWithTime(status, startDate, endDate, stationId);
        } else if (action.equals("tim-kiem")) {
            inspectionRecordses = ird.getListInspectionRecordsPendingBySearching(keyWord, stationId, startRecord, recordPerPage);
            request.setAttribute("searchKeyWord", keyWord);
            noOfRecords = ird.getNoOfRecordPendingByResearch(stationId, keyWord);
        } else {
            inspectionRecordses = ird.getListInspectionRecordsPending(stationId, startRecord, recordPerPage);
            noOfRecords = ird.getNoOfRecordsPending(stationId);
        }
        if (inspectionRecordses.isEmpty()) {
            request.setAttribute("listEmpty", "Không tìm thấy đăng kiểm nào.");
        }
        int noOfPage = (int) Math.ceil(noOfRecords * 1.0 / recordPerPage);
        request.setAttribute("inspectionPedding", inspectionRecordses);
        request.setAttribute("currentPage", page);
        request.setAttribute("noOfPage", noOfPage);
        request.getRequestDispatcher("resources/station/appointment.jsp").forward(request, response);
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
        String plateNumber = request.getParameter("plateNumber");
        String ownerName = request.getParameter("ownerName");
        String tel = request.getParameter("tel");
        String inspectionDate = request.getParameter("inspectionDate");
        String stationIdReq = request.getParameter("stationID");
        int stationId;
        try {
            stationId = Integer.parseInt(stationIdReq);
        } catch (NumberFormatException e) {
            stationId = 0;
        }

        String errorMsg = "";

        User owner = ud.getUserbyTelAndName(tel, ownerName);
        Vehicles v = vd.checkPlateNumAndOwner(plateNumber, ownerName, tel);
        if (owner != null && v != null) {
            if (!Configuration.checkInspectionDate(inspectionDate)) {
                errorMsg = "Ngày kiểm định không phù hợp. Hãy chọn ngày khác!";
            } else {
                java.sql.Date date = new java.sql.Date(Configuration.convertStringToDate(inspectionDate).getTime());
                if (!ird.isInspectionDateExists(v.getVehicleId(), date)) {
                    errorMsg = "Phương tiện đã đăng ký.";
                } else {
                    InspectionRecords t = new InspectionRecords();
                    t.setVehicle(v);
                    t.setStationID(stationId);
                    t.setInspectionDate(date);
                    t.setNextInspectionDate(Configuration.getNextInspectionDate(inspectionDate));
                    t.setResult("Pending");
                    ird.save(t);
                    request.setAttribute("successMsg", "Xe mang biển số: " + v.getPlateNumber() + "đã đăng ký thành công.");
                }
            }
        } else {
            errorMsg = "Người dùng hoặc phương tiện không chính xác. Vui lòng thử lại!";
        }
        request.setAttribute("errorMsg", errorMsg);
        doGet(request, response);
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

    private String getStatus(String statusReq) {
        if (statusReq == null) {
            return " ";
        }
        return switch (statusReq) {
            case "pending" ->
                "AND Result = 'Pending'";
            case "pass" ->
                "AND Result = 'Pass'";
            case "not-pass" ->
                "AND Result = 'Fail'";
            default ->
                " ";
        };
    }

}
