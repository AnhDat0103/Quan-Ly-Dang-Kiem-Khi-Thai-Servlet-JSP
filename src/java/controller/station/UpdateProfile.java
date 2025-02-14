/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.station;

import config.Configuration;
import dao.StationDao;
import dao.UserDao;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;
import validation.Validate;

/**
 *
 * @author DAT
 */
public class UpdateProfile extends HttpServlet {

    UserDao ud = new UserDao();
    StationDao sd = new StationDao();

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
            out.println("<title>Servlet UpdateProfile</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateProfile at " + request.getContextPath() + "</h1>");
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
        processRequest(request, response);
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
        String newFullName = request.getParameter("fullName") != null ? request.getParameter("fullName") : "";
        String newPhone = request.getParameter("phone") != null ? request.getParameter("phone") : "";
        String newAddress = request.getParameter("address") != null ? request.getParameter("address") : "";
        String oldPass = request.getParameter("oldPass") != null ? request.getParameter("oldPass") : "";
        String newPass = request.getParameter("newPass") != null ? request.getParameter("newPass") : "";
        String confirm = request.getParameter("confirmNewPass") != null ? request.getParameter("confirmNewPass") : "";
        String inspecStaion = request.getParameter("inspecStation") != null ? request.getParameter("inspecStation") : "";
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("currentUser");
        int rs = 0;
        if (action == null) {
            if (!newFullName.isEmpty() && !newPhone.isEmpty() && !newAddress.isEmpty() && Validate.checkTele(newPhone)) {
                currentUser.setFullName(newFullName);
                currentUser.setAddress(newAddress);
                currentUser.setPhone(newPhone);
                rs = ud.update(currentUser);
                if (rs == 1) {
                    response.sendRedirect("thong-tin-ca-nhan?status=success");
                }
            } else {
                response.sendRedirect("thong-tin-ca-nhan?status=error");
            }
        } else if (action.equals("change-pass")) {
            if (!oldPass.isEmpty() && !newPass.isEmpty() && !confirm.isEmpty()) {
                if (checkNewPassAndConfirmNewPass(newPass, confirm) && Configuration.verifyPasswordAfterHashed(oldPass, currentUser.getPassword())) {
                    String hashNewPass = Configuration.hashPasswordByMD5(newPass);
                    rs = ud.updatePassword(hashNewPass, currentUser.getUserId());
                    if (rs == 1) {
                        response.sendRedirect("thong-tin-ca-nhan?status=success");
                    }
                } else {
                    response.sendRedirect("thong-tin-ca-nhan?status=error");
                }
            } else {
                response.sendRedirect("thong-tin-ca-nhan?status=error");
            }
        } else if (action.equals("delete-account")) {
            ud.delete(currentUser.getUserId());
            session.removeAttribute("currentUser");
            response.sendRedirect("dang-nhap");
        } else if (action.equals("change-location")) {
            currentUser.setInspectionStation(sd.findStationById(Integer.parseInt(inspecStaion)));
            rs = ud.updateInspecStationId(Integer.parseInt(inspecStaion), currentUser.getUserId());
            if (rs == 1) {
                response.sendRedirect("thong-tin-ca-nhan?status=success");
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

    public boolean checkNewPassAndConfirmNewPass(String newPass, String confirNewPass) {
        return newPass.equals(confirNewPass);
    }
}
