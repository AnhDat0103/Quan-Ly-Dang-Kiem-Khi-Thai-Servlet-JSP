/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.vehicleController;

import dao.LogSystemDao;
import dao.UserDao;
import model.Vehicles;
import dao.VehicleDao;
import jakarta.servlet.RequestDispatcher;
import validation.Validate;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.LogSystem;
import model.User;
import model.enums.vehicleEnums.vehicleEnums;

/**
 *
 * @author Lenovo
 */
public class dangKyPT extends HttpServlet {

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
            out.println("<title>Servlet dangKyPT</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet dangKyPT at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    public dangKyPT() {
        super();
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
        User user = (User) session.getAttribute("currentUser");
        System.out.println(user.getUserId());
        RequestDispatcher dispatcher = request.getRequestDispatcher("resources/vehicle/dangKyPT.jsp");
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
        String ownerIDr = request.getParameter("ownerID");
        String plateNumber = request.getParameter("plateNumber");
        String brand = request.getParameter("brand");
        String model = request.getParameter("model");
        String manufactureYeard = request.getParameter("manufactureYear");
        String engineNumber = request.getParameter("engineNumber");
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("currentUser");

        String bug = "";

        if (ownerIDr == null || plateNumber == null
                || brand == null || model == null || manufactureYeard == null || engineNumber == null
                || ownerIDr.isEmpty() || plateNumber.isEmpty()
                || brand.isEmpty() || model.isEmpty() || manufactureYeard.isEmpty() || engineNumber.isEmpty()) {
            bug += "Vui lòng nhập đầy đủ thông tin.\n";
        }

        if (bug.isEmpty() && !Validate.checkPlateNumber(plateNumber)) {
            bug += "Biển số xe không hợp lệ, vui lòng nhập lại.\n";
        }

        // Kiểm tra trùng biển số
        VehicleDao vehicleDao = new VehicleDao();
        try {
            if (vehicleDao.kiemtraphuongtien(plateNumber)) {
                bug += "Biển số xe đã tồn tại, vui lòng nhập biển số khác!\n";
            }
        } catch (SQLException ex) {
            Logger.getLogger(dangKyPT.class.getName()).log(Level.SEVERE, null, ex);
            bug += "Lỗi hệ thống khi kiểm tra phương tiện.\n";
        }

        // Kiểm tra năm sản xuất
        int ownerID = 0, manufactureYear = 0;
        try {
            ownerID = Integer.parseInt(ownerIDr);
            manufactureYear = Integer.parseInt(manufactureYeard);

            int currentYear = java.time.Year.now().getValue();
            if (manufactureYear < 1900 || manufactureYear > currentYear) {
                bug += "Năm sản xuất không hợp lệ! Vui lòng nhập trong khoảng 1900 - " + currentYear + ".\n";
            }
        } catch (NumberFormatException e) {
            bug += "Dữ liệu nhập vào không hợp lệ! Vui lòng kiểm tra lại.\n";
        }

        if (!bug.isEmpty()) {
            request.setAttribute("bug", bug);
            request.getRequestDispatcher("resources/submit/Failed.jsp").forward(request, response);
            return;
        }

        request.setAttribute("ownerID", ownerIDr);
        request.setAttribute("plateNumber", plateNumber);
        request.setAttribute("brand", brand);
        request.setAttribute("model", model);
        request.setAttribute("manufactureYear", manufactureYeard);
        request.setAttribute("engineNumber", engineNumber);
        Vehicles vh = new Vehicles();
        vh.setOwner(ud.findUserById(ownerID));
        vh.setBrand(brand);
        vh.setEngineNumber(engineNumber);
        vh.setManufactureYear(manufactureYear);
        vh.setModel(model);
        vh.setPlateNumber(plateNumber);
        vh.setStatus(vehicleEnums.Fail);
        vehicleDao.save(vh);
        LogSystem log = new LogSystem();
        String ms = "Tài khoản với id = " + currentUser.getUserId() + " vừa cập nhật xe với biển số: " + plateNumber;
        log.setUser(currentUser);
        log.setAction(ms);
        ld.save(log);
        request.getRequestDispatcher("resources/submit/Successfully.jsp").forward(request, response);

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
