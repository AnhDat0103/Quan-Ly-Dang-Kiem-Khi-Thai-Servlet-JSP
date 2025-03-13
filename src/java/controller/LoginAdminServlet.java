/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import config.Configuration;
import dao.UserDao;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;
import model.enums.RoleEnums;

/**
 *
 * @author DAT
 */
public class LoginAdminServlet extends HttpServlet {

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
            out.println("<title>Servlet LoginAdminServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginAdminServlet at " + request.getContextPath() + "</h1>");
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
       Configuration.killExpiredInspectionRecord();
        request.getRequestDispatcher("dashboard/auth/loginAdmin.jsp").forward(request, response);
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
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        UserDao ud = new UserDao();
        User currentUser = ud.findUserByEmail(email);
        HttpSession session = request.getSession();
        if (!email.isEmpty() && !password.isEmpty()) {
            if (currentUser != null) { 
                if (currentUser.getRole().equals(RoleEnums.Admin)) {
                    session.setAttribute("currentUser", currentUser);
                    response.sendRedirect("quan-tri-vien");
                } else {
                    request.setAttribute("notFound", "Tài khoản hoặc mật khẩu không chính xác.");
                    request.getRequestDispatcher("dashboard/auth/loginAdmin.jsp").forward(request, response);
                }
            }
        } else {
            if (email.isEmpty()) {
                request.setAttribute("emptyEmail", "Email không thể để trống.");
            }
            if (password.isEmpty()) {
                request.setAttribute("emptyPassword", "Mật khẩu không thể để trống.");
            }
            request.getRequestDispatcher("dashboard/auth/loginAdmin.jsp").forward(request, response);
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
