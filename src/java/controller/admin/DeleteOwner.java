/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.admin;

import dao.InspectionRecordDao;
import dao.LogSystemDao;
import dao.NotificationDao;
import dao.ResetPasswordDao;
import dao.UserDao;
import dao.VehicleDao;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.LogSystem;
import model.Notification;
import model.User;
import model.Vehicles;
import model.enums.RoleEnums;

/**
 *
 * @author DAT
 */
public class DeleteOwner extends HttpServlet {
   UserDao ud = new UserDao();
        LogSystemDao ld = new LogSystemDao();
        NotificationDao nd = new NotificationDao();
        ResetPasswordDao rd = new ResetPasswordDao();
        VehicleDao vd = new VehicleDao();
        InspectionRecordDao ird = new InspectionRecordDao();
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
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
            out.println("<title>Servlet DeleteOwner</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DeleteOwner at " + request.getContextPath () + "</h1>");
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
        String ownerId = request.getParameter("ownerId");
        User u = ud.findUserById(Integer.parseInt(ownerId));
        if(u != null) {
            List<Notification> notifications = nd.findAllByUserId(u.getUserId());
            List<model.ResetPassword> resetPasswords = rd.findAllByUserId(u.getUserId());
            if (u.getRole().compareTo(RoleEnums.Owner) == 0) {
                List<Vehicles> vehicleses = vd.getAllVehiclesByUserID(u.getUserId());
                vehicleses.stream().forEach(v -> ird.removeInspectionRecordsWithPlateNumber(v.getPlateNumber()));

                vehicleses.stream().forEach(v -> vd.deleteByPlateNumber(v.getPlateNumber()));
            }
            if (!resetPasswords.isEmpty()) {
                resetPasswords.stream().forEach(r -> rd.delete(r.getToken()));
            }
            if (!notifications.isEmpty()) {
                notifications.stream().forEach(n -> nd.delete(n.getNotificationId()));
            }
            ld.updateLogsBeforeDelete(u.getUserId());
            LogSystem log = new LogSystem();
            String ms = "Tài khoản với id = " + u.getUserId() + " vừa được xóa khỏi hệ thống.";
            log.setUser(u);
            log.setAction(ms);
            ld.save(log);
            ud.delete(u.getUserId());
            response.sendRedirect("chi-tiet-chu-phuong-tien");
        }else {
            response.sendRedirect("500error");
        }
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
