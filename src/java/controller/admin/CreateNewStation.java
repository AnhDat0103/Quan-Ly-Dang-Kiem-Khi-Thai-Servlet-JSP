/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import dao.StationDao;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.InspectionStation;
import validation.Validate;

/**
 *
 * @author DAT
 */
public class CreateNewStation extends HttpServlet {

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
            out.println("<title>Servlet CreateNewStation</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CreateNewStation at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher("dashboard/createNewStation.jsp").forward(request, response);
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
        String name = request.getParameter("name") != null ? request.getParameter("name").trim() : "";
        String phone = request.getParameter("phone") != null ? request.getParameter("phone").trim() : "";
        String email = request.getParameter("email") != null ? request.getParameter("email").trim() : "";
        String address = request.getParameter("address") != null ? request.getParameter("address").trim() : "";

        if (!name.isEmpty() && !address.isEmpty() && !phone.isEmpty() && !email.isEmpty()) {
            if (!sd.existedUserWithEmail(email) && Validate.checkEmail(email) && Validate.checkTele(phone)) {
                InspectionStation is = new InspectionStation();
                is.setName(name);
                is.setAddress(address);
                is.setEmail(email);
                is.setPhone(phone);
                int result = sd.save(is);
                if (result > 0) {
                    response.sendRedirect("chi-tiet-trung-tam-dang-kiem");
                } else {
                    System.out.println("Tạo thấ bại");
                    response.sendRedirect("500error");
                }
            } else {
                if (!Validate.checkEmail(email)) {
                    request.setAttribute("errorEmail", "Email không đúng định dạng.");
                }
                if (sd.existedUserWithEmail(email)) {
                    request.setAttribute("errorEmail", "Email đã tồn tại.");
                }
                if (!Validate.checkTele(phone)) {
                    request.setAttribute("errorPhone", "Số điện thoại phải có 10,11 ký tự số.");
                }
                request.getRequestDispatcher("dashboard/createNewStation.jsp").forward(request, response);
            }
        }else {
            if(name.isEmpty()) {
                request.setAttribute("errorName", "Tên không được để trống.");
            }
            if(address.isEmpty()) {
                request.setAttribute("errorAddress", "Không được để trống.");
            }
            if(phone.isEmpty()) {
                request.setAttribute("errorPhone", "Không được để trống.");
            }
            if(email.isEmpty()) {
                request.setAttribute("errorEmail", "Không được để trống.");
            }
            request.getRequestDispatcher("dashboard/createNewStation.jsp").forward(request, response);
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
