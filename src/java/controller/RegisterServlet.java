/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.LogSystemDao;
import dao.UserDao;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.LogSystem;
import model.User;
import model.dto.RegisterForm;
import model.enums.ProviderClass;
import model.enums.RoleEnums;
import validation.Validate;

/**
 *
 * @author DAT
 */
public class RegisterServlet extends HttpServlet {

    UserDao ud = new UserDao();
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
            out.println("<title>Servlet RegisterServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RegisterServlet at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher("dashboard/auth/register.jsp").forward(request, response);
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
        String fullName = request.getParameter("fullName") != null ? request.getParameter("fullName") : "";
        String phone = request.getParameter("phone") != null ? request.getParameter("phone") : "";
        String email = request.getParameter("email") != null ? request.getParameter("email") : "";
        String password = request.getParameter("password") != null ? request.getParameter("password") : "";
        String confirmPassword = request.getParameter("confirmPassword") != null ? request.getParameter("confirmPassword") : "";
        String role = request.getParameter("role") != null ? request.getParameter("role") : "";
        String address = request.getParameter("address") != null ? request.getParameter("address") : "";

        if (!fullName.isEmpty() && !phone.isEmpty() && !email.isEmpty()
                && !password.isEmpty() && !confirmPassword.isEmpty()
                && !role.isEmpty() && !address.isEmpty()) {
            RegisterForm rf = new RegisterForm(fullName, email, password, confirmPassword, RoleEnums.valueOf(role), phone, address);
            request.setAttribute("rf", rf);
            if (Validate.checkEmail(email) && Validate.checkConfirmPassword(password, confirmPassword)
                    && Validate.checkTele(phone) && !Validate.emailIsExist(email)) {
                User newUser = new User();
                newUser.setEmail(email);
                newUser.setFullName(fullName);
                newUser.setPassword(password);
                newUser.setRole(RoleEnums.valueOf(role));
                newUser.setAddress(address);
                newUser.setAvatar("avatar.png");
                newUser.setPhone(phone);
                newUser.setProvider(ProviderClass.LOCAL);
                int result = ud.save(newUser);
                if (result == 1) {
                    String msg = "Tài khoản với email: " + newUser.getEmail() + " vừa được thêm mới.";
                    User userLog = ud.findUserByEmail(newUser.getEmail());
                    LogSystem logSystem = new LogSystem();
                    logSystem.setUser(userLog);
                    logSystem.setAction(msg);
                    ld.save(logSystem);
                    response.sendRedirect("dang-nhap");
                }
            } else {
                if (!Validate.checkEmail(email)) {
                    request.setAttribute("emailFormatError", "Email không đúng định dạng");
                }
                if (!Validate.checkTele(phone)) {
                    request.setAttribute("phoneFormatError", "Số điện thoại không hợp lệ.");
                }
                if (!Validate.checkConfirmPassword(password, confirmPassword)) {
                    request.setAttribute("confirmPassError", "Mật khẩu không chính xác.");
                }
                if (Validate.emailIsExist(email)) {
                    request.setAttribute("existedEmail", "Email đã tồn tại");
                }
                request.getRequestDispatcher("dashboard/auth/register.jsp").forward(request, response);
            }
        } else {
            if (email.isEmpty()) {
                request.setAttribute("emptyEmail", "Email không thể để trống.");
            }
            if (phone.isEmpty()) {
                request.setAttribute("emptyPhone", "Số điện không thể để trống.");
            }
            if (password.isEmpty()) {
                request.setAttribute("emptyPass", "Mật khẩu không thể để trống.");
            }
            if (confirmPassword.isEmpty()) {
                request.setAttribute("emptyConfirPass", "Không thể để trống.");
            }
            if (fullName.isEmpty()) {
                request.setAttribute("emptyName", "Họ tên không thể để trống.");
            }
            if (address.isEmpty()) {
                request.setAttribute("emptyAddress", "Địa chỉ không thể để trống.");
            }
            request.getRequestDispatcher("dashboard/auth/register.jsp").forward(request, response);
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
