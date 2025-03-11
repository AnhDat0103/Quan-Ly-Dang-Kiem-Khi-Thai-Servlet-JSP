/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.station;

import dao.InspectionRecordDao;
import dao.UserDao;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.InspectionRecords;
import model.User;

/**
 *
 * @author DAT
 */
public class ProcessInspectionRecord extends HttpServlet {

    UserDao ud = new UserDao();
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
            out.println("<title>Servlet ProcessInspectionRecord</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProcessInspectionRecord at " + request.getContextPath() + "</h1>");
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
        response.setContentType("text/html;charset=UTF-8");
        String recordIdRequest = request.getParameter("ban-dang-kiem");
        int recordId;
        try {
            recordId = Integer.parseInt(recordIdRequest);
        } catch (NumberFormatException e) {
            recordId = 0;
        }
        if (recordId > 0) {
            HttpSession session = request.getSession();
            User currentUser = (User) session.getAttribute("currentUser");
            int stationId = currentUser.getInspectionStation().getStationId();
            List<User> inspectors = ud.getAllInspectors(stationId);
            InspectionRecords inspectionRecord = ird.getInspectionRecordByID(recordId);
            request.setAttribute("record", inspectionRecord);
            session.setAttribute("inspectors", inspectors);
            request.getRequestDispatcher("resources/station/processInspectionRecord.jsp").forward(request, response);
        } else {
            response.sendRedirect("500error");
        }

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
        response.setContentType("text/html;charset=UTF-8");
        try {
            int recordId = Integer.parseInt(request.getParameter("recordId"));
            int inspectorId = Integer.parseInt(request.getParameter("inspectorId"));
            InspectionRecords record = ird.getInspectionRecordByID(recordId);
            record.setInspectorID(inspectorId);
            request.setAttribute("record", record);
            boolean success = ird.updateInspectionRecord(record);

            if (success) {
                request.setAttribute("successMsg", "Bản kiểm định đã được duyệt.");
                request.getRequestDispatcher("resources/station/processInspectionRecord.jsp").forward(request, response);
            } else {
                request.setAttribute("errorMsg", "Lỗi xử lý. Hãy thử lại!");
                request.getRequestDispatcher("resources/station/processInspectionRecord.jsp").forward(request, response);
            }
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Dữ liệu không hợp lệ");
            doGet(request, response);
        } catch (Exception e) {
            request.setAttribute("error", "Đã xảy ra lỗi: " + e.getMessage());
            doGet(request, response);
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

}
