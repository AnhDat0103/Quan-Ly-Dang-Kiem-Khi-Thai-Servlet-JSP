/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.station;

import dao.InspectionRecordDao;
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

/**
 *
 * @author DAT
 */
public class GetAppointmentPage extends HttpServlet {

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
        String keyWord = request.getParameter("tu-khoa-tim-kiem");
        int page = 1;
        int recordPerPage = 4;
        try {
            if (request.getParameter("trang-so") != null) {
                page = Integer.parseInt(request.getParameter("trang-so"));
            }
        } catch (NumberFormatException e) {
            page = 1;
        }
        if (keyWord == null || keyWord.isEmpty()) {
            keyWord = "";
        }
        int noOfRecords = 0;
        int startRecord = (page - 1) * recordPerPage;

        String action = request.getParameter("action") == null ? "" : request.getParameter("action");
        request.setAttribute("action", action);
        if (action.equals("")) {
            inspectionRecordses = ird.getListInspectionRecordsPending(stationId, startRecord, recordPerPage);
            noOfRecords = ird.getNoOfRecordsPending(stationId);
        } else if (action.equals("tim-kiem")) {
            inspectionRecordses = ird.getListInspectionRecordsPendingBySearching(keyWord, stationId, startRecord, recordPerPage);
            request.setAttribute("searchKeyWord", keyWord);
            noOfRecords = ird.getNoOfRecordPendingByResearch(stationId, keyWord);
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
