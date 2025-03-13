/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import config.Configuration;
import dao.UserDao;
import dao.VehicleDao;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;
import model.dto.GoogleAccount;
import model.enums.ProviderClass;
import model.enums.RoleEnums;

/**
 *
 * @author DAT
 */
public class LoginOauth2WithGoogle extends HttpServlet {

    UserDao ud = new UserDao();

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
            out.println("<title>Servlet LoginOauth2WithGoogle</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginOauth2WithGoogle at " + request.getContextPath() + "</h1>");
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
        String code = request.getParameter("code");
        if (code == null || code.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/500error");
        } else {
            String accessToken = Configuration.getAccessToken(code);
            if (accessToken != null && !accessToken.isEmpty()) {
                GoogleAccount account = Configuration.getUserInfo(accessToken);
                session.setAttribute("googleAccount", account);
                User user = ud.findUserByEmail(account.getEmail());
                if (user != null && user.getRole() != null) {
                    session.setAttribute("currentUser", user);
                    loginService(user, request, response);
                } else {
                    response.sendRedirect(request.getContextPath() + "/chon-vai-tro");
                }
            } else {
                response.sendRedirect(request.getContextPath() + "/500error");
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
        String role = request.getParameter("role") != null ? request.getParameter("role") : "";
        HttpSession session = request.getSession();
        GoogleAccount account = (GoogleAccount) session.getAttribute("googleAccount");
        User user = new User();
        user.setEmail(account.getEmail());
        user.setAddress(" ");
        user.setPassword("123");
        user.setPhone("0123456789");
        user.setAvatar("avatar.png");
        user.setProvider(ProviderClass.GOOGLE);
        if (account.getFirstName() == null && account.getLastName() == null) {
            user.setFullName(getFullNameFromEmail(account.getEmail()));
        } else {
            String fullName = account.getFirstName() + " " + account.getLastName();
            user.setFullName(fullName.trim());
        }
        user.setRole(RoleEnums.valueOf(role));
        ud.save(user);
        session.setAttribute("currentUser", user);
        loginService(user, request, response);
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

    private void loginService(User user, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
        if (user.getRole().equals(RoleEnums.Station)) {
            response.sendRedirect(request.getContextPath() + "/trung-tam-dang-kiem");
            return;
        }
        if (user.getRole().equals(RoleEnums.Owner)) {
            VehicleDao vehicleDao = new VehicleDao();
            boolean checkVehicle = vehicleDao.checkVehicleExistByOwnerId(user.getUserId());
            if (checkVehicle) {
                response.sendRedirect(request.getContextPath() + "/chu-phuong-tien");
            } else {
                response.sendRedirect(request.getContextPath() + "/kiem-tra-tai-khoan");
            }
            return;
        }
        if (user.getRole().equals(RoleEnums.Inspector)) {
            response.sendRedirect(request.getContextPath() + "/nguoi-kiem-dinh");
        }
    }

    private String getFullNameFromEmail(String email) {
        String[] emailString = email.split("@");
        return emailString[0];
    }

}
