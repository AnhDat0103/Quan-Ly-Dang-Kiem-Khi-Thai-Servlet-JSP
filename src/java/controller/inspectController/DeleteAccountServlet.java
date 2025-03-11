/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.inspectController;

import dao.DBContext;
import dao.LogSystemDao;
import dao.UserDao;
import java.io.IOException;
import java.sql.Connection;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.PrintWriter;
import model.LogSystem;
import model.User;

/**
 *
 * @author DUYEN
 */
public class DeleteAccountServlet extends HttpServlet {

    Connection connect = DBContext.getInstance().getConnection();
    LogSystemDao ld = new LogSystemDao();

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
            out.println("<title>Servlet DeleteAccountServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DeleteAccountServlet at " + request.getContextPath() + "</h1>");
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
        try {
            int userId = Integer.parseInt(request.getParameter("userId"));
            HttpSession session = request.getSession();
            User currentUser = (User) session.getAttribute("currentUser");

            // Lấy kết nối CSDL
            // Tạo UserDao và gọi phương thức delete
            UserDao userDao = new UserDao();
            int rowsAffected = userDao.delete(userId);

            // Đóng kết nối
            connect.close();

            if (rowsAffected > 0) {
                LogSystem log = new LogSystem();
                String ms = "Tài khoản với id = " + userId + " vừa được xóa khỏi hệ thống.";
                log.setUser(currentUser);
                log.setAction(ms);
                ld.save(log);
                session.invalidate(); // Xóa session sau khi xóa tài khoản
                response.getWriter().write("Success");
            } else {
                response.getWriter().write("Failed");
            }
        } catch (NumberFormatException e) {
            response.getWriter().write("Invalid UserID");
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("Error");
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
