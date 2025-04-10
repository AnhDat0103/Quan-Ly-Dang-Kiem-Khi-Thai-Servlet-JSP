/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.vehicleController;

import dao.InspectionRecordDao;
import dao.LogSystemDao;
import dao.VehicleDao;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.LogSystem;
import model.User;
import validation.Validate;

/**
 *
 * @author Lenovo
 */
public class xoaPT extends HttpServlet {

    VehicleDao vehicleDao = new VehicleDao();
    InspectionRecordDao inspectionRecordDao = new InspectionRecordDao();
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
            out.println("<title>Servlet xoaPT</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet xoaPT at " + request.getContextPath() + "</h1>");
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
        RequestDispatcher dispatcher = request.getRequestDispatcher("resources/vehicle/xoaPT.jsp");
        dispatcher.forward(request, response);
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
        String plateNumber = request.getParameter("plateNumber");
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("currentUser");

        String message = "";
        boolean isDeleted = false;

        if (plateNumber == null || plateNumber.isEmpty() || !Validate.checkPlateNumber(plateNumber)) {
            message = "Vui lòng nhập đúng thông tin!";
        } else {
            inspectionRecordDao.removeInspectionRecordsWithPlateNumber(plateNumber);
            isDeleted = vehicleDao.deleteByPlateNumber(plateNumber);
            if (!isDeleted) {
                message = "Không tìm thấy phương tiện có biển số: " + plateNumber;
            } else {
                LogSystem log = new LogSystem();
                String ms = "Tài khoản với id = " + currentUser.getUserId() + " vừa xóa phương tiện mang biển số: " + plateNumber;
                log.setUser(currentUser);
                log.setAction(ms);
                ld.save(log);
                message = "Xóa phương tiện thành công!";
            }
        }

        // Đặt thông báo vào session để chuyển lại trang hiện tại
        session.setAttribute("deleteMessage", message);
        session.setAttribute("deleteSuccess", isDeleted);

        // Chuyển hướng về trang chính
        response.sendRedirect("quan-ly-phuong-tien");
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
