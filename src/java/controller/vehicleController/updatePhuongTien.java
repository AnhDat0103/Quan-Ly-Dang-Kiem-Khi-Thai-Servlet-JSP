/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.vehicleController;

import dao.VehicleDao;
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
import model.Vehicles;
import validation.Validate;

/**
 *
 * @author Lenovo
 */
public class updatePhuongTien extends HttpServlet {

    VehicleDao vehicleDao = new VehicleDao();

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
            out.println("<title>Servlet updatePhuongTien</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet updatePhuongTien at " + request.getContextPath() + "</h1>");
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
        String plateNumber = request.getParameter("plateNumber");
        Vehicles vehicle = vehicleDao.findByPlateNumber(plateNumber);

        if (vehicle == null) {
            request.setAttribute("bug", "Không tìm thấy phương tiện.");
            request.getRequestDispatcher("resources/vehicle/vehicleManagement.jsp").forward(request, response);
        } else {
            request.setAttribute("vehicle", vehicle);
            request.getRequestDispatcher("resources/vehicle/updateVehicle.jsp").forward(request, response);
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
        request.setCharacterEncoding("UTF-8");

        String plateNumber = request.getParameter("plateNumber");
        String brand = request.getParameter("brand");
        String model = request.getParameter("model");
        String manufactureYeard = request.getParameter("manufactureYear");
        String engineNumber = request.getParameter("engineNumber");
        
        String bug = "";

        if (plateNumber == null || brand == null || model == null || manufactureYeard == null || engineNumber == null
            || plateNumber.isEmpty() || brand.isEmpty() || model.isEmpty() || manufactureYeard.isEmpty() || engineNumber.isEmpty()) {
            bug += "Vui lòng nhập đầy đủ thông tin.\n";
        }
        
        try {
            if (vehicleDao.kiemtrakhungphuongtien(engineNumber)){
                bug += "Khung xe đã tồn tại, vui lòng kiểm tra lại khung phương tiện!\n";
            }  
        } catch (SQLException ex) {
            Logger.getLogger(dangKyPT.class.getName()).log(Level.SEVERE, null, ex);
            bug += "Lỗi hệ thống khi kiểm tra phương tiện.\n";
        }
        
        // Kiểm tra năm sản xuất
        int manufactureYear = 0;
        try {
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
            request.getRequestDispatcher("resources/submit/Update_Failed.jsp").forward(request, response);
            return;
        }

        Vehicles vehicle = new Vehicles();
        vehicle.setPlateNumber(plateNumber);
        vehicle.setBrand(brand);
        vehicle.setModel(model);
        vehicle.setManufactureYear(manufactureYear);
        vehicle.setEngineNumber(engineNumber);

        boolean updated = vehicleDao.updateVehicle(vehicle);

        if (updated) {
            request.getRequestDispatcher("resources/submit/Update_Successfully.jsp").forward(request, response);
        } 
    }

}
