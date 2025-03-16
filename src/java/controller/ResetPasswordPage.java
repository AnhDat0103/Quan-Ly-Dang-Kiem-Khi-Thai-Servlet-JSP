/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import config.EmailService;
import dao.ResetPasswordDao;
import dao.UserDao;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.UUID;
import java.time.LocalDateTime;
import model.User;

/**
 *
 * @author DAT
 */
public class ResetPasswordPage extends HttpServlet {
    UserDao userDAO = new UserDao();
    ResetPasswordDao pd = new ResetPasswordDao();
   
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
            out.println("<title>Servlet ResetPasswordPage</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ResetPasswordPage at " + request.getContextPath () + "</h1>");
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
        request.getRequestDispatcher("dashboard/resetPassPage.jsp").forward(request, response);
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
        String email = request.getParameter("email");
        
        User user = userDAO.findUserByEmail(email);
        
        if (user != null) {
            
            String token = UUID.randomUUID().toString();
            
            String resetLink = "http://localhost:8080/dang-kiem-khi-thai/nhap-mat-khau-moi-nao?token=" + token +"&email=" + email;
            pd.saveResetToken(user.getUserId(), token, LocalDateTime.now().plusMinutes(5));
            
            EmailService emailService = new EmailService();
            String subject = "Yêu cầu đặt lại mật khẩu";
            String content = "Xin chào,"+ user.getFullName() +"<br>"
                    + "Bạn đã yêu cầu đặt lại mật khẩu. Vui lòng click vào đường link sau để đặt lại mật khẩu:" +"<br>"
                    + resetLink + "<br>"
                    + "Link này sẽ hết hạn sau 5 phút." +"<br>"
                    + "Nếu bạn không yêu cầu đặt lại mật khẩu, vui lòng bỏ qua email này." + "<br>"
                    + "Trân trọng," + "<br>"
                    + "Đăng kiểm khí thải";
            
            emailService.sendEmail(email, subject, content);
            
            request.setAttribute("message", "Chúng tôi đã gửi hướng dẫn đặt lại mật khẩu đến email của bạn.");
        } else {
            request.setAttribute("error", "Email không tồn tại trong hệ thống.");
        }       
        request.getRequestDispatcher("dashboard/resetPassPage.jsp").forward(request, response);
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
