package controller;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
import config.Configuration;
import dao.InspectionRecordDao;
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
import model.enums.RoleEnums;

/**
 *
 * @author DAT
 */
public class LoginServlet extends HttpServlet {

    UserDao ud = new UserDao();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet LoginServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginServlet at " + request.getContextPath() + "</h1>");
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
         User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser != null) {
            if (currentUser.getRole().equals(RoleEnums.Station)) {
                response.sendRedirect("trung-tam-dang-kiem");
            }
            if (currentUser.getRole().equals(RoleEnums.Owner)) {
                VehicleDao vehicleDao = new VehicleDao();
                boolean checkVehicle = vehicleDao.checkVehicleExistByOwnerId(currentUser.getUserId());
                if (checkVehicle) {
                    response.sendRedirect("chu-phuong-tien");
                } else {
                    response.sendRedirect("kiem-tra-tai-khoan");
                }
            }
            if (currentUser.getRole().equals(RoleEnums.Inspector)) {
                response.sendRedirect("nguoi-kiem-dinh");
            }
            if (currentUser.getRole().equals(RoleEnums.Admin)) {
                response.sendRedirect("quan-tri-vien");
            }
            if (currentUser.getRole().equals(RoleEnums.Police)) {
                response.sendRedirect("trung-tam-canh-sat");
            }
        } else {
            request.getRequestDispatcher("dashboard/auth/login.jsp").forward(request, response);
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
        Configuration.killExpiredInspectionRecord();
        String email = request.getParameter("email") != null ? request.getParameter("email") : "";
        String password = request.getParameter("password") != null ? request.getParameter("password") : "";
        String role = request.getParameter("role") != null ? request.getParameter("role") : "";
        HttpSession session = request.getSession();
        if (!email.isEmpty() && !password.isEmpty() && !role.isEmpty()) {
            User currentUser = ud.findUserByEmailAndPasswordAndRole(email, password, role);
            if (currentUser != null) {
                session.setAttribute("currentUser", currentUser);
                if (currentUser.getRole().equals(RoleEnums.Station)) {
                    response.sendRedirect("trung-tam-dang-kiem");
                }
                if (currentUser.getRole().equals(RoleEnums.Owner)) {
                    VehicleDao vehicleDao = new VehicleDao();
                    boolean checkVehicle = vehicleDao.checkVehicleExistByOwnerId(currentUser.getUserId());
                    if (checkVehicle) {
                        response.sendRedirect("chu-phuong-tien");
                    } else {
                        response.sendRedirect("kiem-tra-tai-khoan");
                    }
                }
                if (currentUser.getRole().equals(RoleEnums.Inspector)) {
                    response.sendRedirect("nguoi-kiem-dinh");
                }
                if (currentUser.getRole().equals(RoleEnums.Admin)) {
                    response.sendRedirect("quan-tri-vien");
                }
                if (currentUser.getRole().equals(RoleEnums.Police)) {
                    response.sendRedirect("trung-tam-canh-sat");
                }
            } else {
                request.setAttribute("notFound", "Tài khoản hoặc mật khẩu không chính xác.");
                request.getRequestDispatcher("dashboard/auth/login.jsp").forward(request, response);
            }
        } else {
            if (email.isEmpty()) {
                request.setAttribute("emptyEmail", "Email không thể để trống.");
            }
            if (password.isEmpty()) {
                request.setAttribute("emptyPassword", "Mật khẩu không thể để trống.");
            }
            request.getRequestDispatcher("dashboard/auth/login.jsp").forward(request, response);
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
