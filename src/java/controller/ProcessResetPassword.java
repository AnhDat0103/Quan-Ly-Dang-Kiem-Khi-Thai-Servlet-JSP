/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import config.Configuration;
import dao.ResetPasswordDao;
import dao.UserDao;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;

/**
 *
 * @author DAT
 */
public class ProcessResetPassword extends HttpServlet {

    ResetPasswordDao pd = new ResetPasswordDao();
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
            out.println("<title>Servlet ProcessResetPassword</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProcessResetPassword at " + request.getContextPath() + "</h1>");
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
        String token = request.getParameter("token");
        String email = request.getParameter("email");
        User user = ud.findUserByEmail(email);
        if (user == null) {
            response.sendRedirect("500error");
            return;
        }
        if (pd.checkIsResetToken(token)) {
            request.setAttribute("email", email);
            request.setAttribute("token", token);
            request.getRequestDispatcher("dashboard/formResetPassword.jsp").forward(request, response);
        } else {
            response.sendRedirect("loi-cap-nhat-mat-khau");
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
        String token = request.getParameter("token");
        String email = request.getParameter("email");
        String newPassword = request.getParameter("password");
          User user = ud.findUserByEmail(email);
        if (user == null) {
            response.sendRedirect("500error");
            return;
        } else {
            user.setPassword(newPassword);
            String passHash = Configuration.hashPasswordByMD5(newPassword);
            int result = ud.updatePassword(passHash, user.getUserId());
            if(result > 0) {
                pd.delete(token);
                            request.getRequestDispatcher("dashboard/resetPasswordSuccess.jsp").forward(request, response);
            }else {
                response.sendRedirect("loi-cap-nhat-mat-khau");
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
