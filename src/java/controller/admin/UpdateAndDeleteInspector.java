/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import dao.InspectionRecordDao;
import dao.LogSystemDao;
import dao.StationDao;
import dao.UserDao;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.InspectionStation;
import model.LogSystem;
import model.User;

/**
 *
 * @author DAT
 */
public class UpdateAndDeleteInspector extends HttpServlet {

    UserDao ud = new UserDao();
    StationDao sd = new StationDao();
    InspectionRecordDao ird = new InspectionRecordDao();
    LogSystemDao lsd = new LogSystemDao();

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
            out.println("<title>Servlet UpdateAndDeleteInspector</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateAndDeleteInspector at " + request.getContextPath() + "</h1>");
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
        String action = request.getParameter("action");
        String inspectorId = request.getParameter("inspectorId");
        User currentInspector = ud.findUserById(Integer.parseInt(inspectorId));
        List<InspectionStation> inspectionStations = sd.findAll();
        if (currentInspector == null) {
            response.sendRedirect("500error");
        } else {
            request.setAttribute("inspector", currentInspector);
            request.setAttribute("stations", inspectionStations);
            if (action.equals("update")) {
                request.getRequestDispatcher("dashboard/inspectorDetails.jsp").forward(request, response);
            } else if (action.equals("delete")) {
                request.getRequestDispatcher("dashboard/confirmDeleteInspector.jsp").forward(request, response);
            }
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
        String action = request.getParameter("action") != null ? request.getParameter("action") : "";
        String inspectorId = request.getParameter("inspectorId") != null ? request.getParameter("inspectorId") : "";
        String name = request.getParameter("name") != null ? request.getParameter("name") : "";
        String phone = request.getParameter("phone") != null ? request.getParameter("phone") : "";
        String address = request.getParameter("address") != null ? request.getParameter("address") : "";
        String stationId = request.getParameter("station") != null ? request.getParameter("station") : "";
        User currentInspector = ud.findUserById(Integer.parseInt(inspectorId));
        if (action.equals("update")) {
            currentInspector.setAddress(address);
            currentInspector.setFullName(name);
            currentInspector.setPhone(phone);
            currentInspector.setInspectionStation(sd.findStationById(Integer.parseInt(stationId)));
            int result = ud.update(currentInspector);
            if (result > 0) {
                response.sendRedirect("danh-sach-nhan-vien-kiem-dinh?stationId=" + stationId);
            } else {
                request.setAttribute("error", "Cập nhật thất bại. Hãy thử lại!");
            }
        } else if (action.equals("delete")) {
            List<LogSystem> logSystems = lsd.findAllByUserId(currentInspector.getUserId());
            ird.UpdateInspectionRecordBeforeDeleteInspector(currentInspector.getUserId());
            if(!logSystems.isEmpty()) {
                logSystems.stream().forEach(l -> lsd.delete(l.getLogId()));
            }
            int result = ud.delete(currentInspector.getUserId());
            if (result > 0) {
                response.sendRedirect("danh-sach-nhan-vien-kiem-dinh?stationId=" + currentInspector.getInspectionStation().getStationId());
            } else {
                response.sendRedirect("500error");
            }
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
