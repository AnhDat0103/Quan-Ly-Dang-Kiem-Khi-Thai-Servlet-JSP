/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.station;

import dao.InspectionRecordDao;
import dao.StationDao;
import dao.VehicleDao;
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
public class GetStationHomePage extends HttpServlet {
        InspectionRecordDao ird = new InspectionRecordDao();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet GetStationHomePage</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet GetStationHomePage at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        int page = 1;
        int recordsPerPage = 4;
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("currentUser");
        if(request.getParameter("trang-so") != null){
            page = Integer.parseInt(request.getParameter("trang-so"));
        }
        int stationId = currentUser.getInspectionStation().getStationId();
        int startRecord =  (page - 1) * recordsPerPage;
        int noOfRecords = ird.getNoOfRecord(stationId);
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
        List<InspectionRecords> inspectionRecordses = ird.getInspecedtationRecords(stationId,startRecord, recordsPerPage);
        request.setAttribute("currentPage", page);
        request.setAttribute("noOfPage", noOfPages);
        request.setAttribute("InspecedtionRecords", inspectionRecordses);
        request.setAttribute("InspecRecordsSum", ird.getNumberOfInspectionRecordsInCurrentDay(stationId));
        request.setAttribute("InspecedRecordsSum", ird.getNumberOfInspectionRecordsIsInspected(stationId));
        request.getRequestDispatcher("resources/station/home.jsp").forward(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
