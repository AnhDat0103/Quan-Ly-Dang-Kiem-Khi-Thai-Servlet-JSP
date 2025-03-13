/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.vehicleController;

import dao.InspectionRecordDao;
import dao.StationDao;
import dao.VehicleDao;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import model.InspectionRecords;
import model.User;
import model.Vehicles;
import model.enums.RoleEnums;

/**
 *
 * @author Lenovo
 */
public class lichSuKD extends HttpServlet {

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
            out.println("<title>Servlet lichSuKD</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet lichSuKD at " + request.getContextPath() + "</h1>");
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
        response.setContentType("text/html;charset=UTF-8");

        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("currentUser");

        // Kiểm tra nếu user không đăng nhập hoặc không phải chủ phương tiện
        if (currentUser == null || !currentUser.getRole().equals(RoleEnums.Owner)) {
            response.sendRedirect("dang-nhap");
            return;
        }

        VehicleDao vehicleDao = new VehicleDao();
        InspectionRecordDao inspectionRecordDao = new InspectionRecordDao();
        
        // Lấy danh sách biển số xe theo User ID
        List<String> plateNumbers = vehicleDao.getPlateNumberByOwnerID(currentUser.getUserId());

        // Nếu người dùng không có phương tiện, hiển thị thông báo
        if (plateNumbers.isEmpty()) {
            request.setAttribute("message", "Vui lòng đăng ký phương tiện trước khi xem lịch sử kiểm định.");
            request.getRequestDispatcher("resources/vehicle/lichSuKD.jsp").forward(request, response);
            return;
        }

        String action = request.getParameter("action");
        List<InspectionRecords> historyList = new ArrayList<>();

        // Duyệt qua danh sách biển số và lấy lịch sử kiểm định
        for (String plateNumber : plateNumbers) {
            int vehicleID = vehicleDao.getVehicleIDByPlateNumber(plateNumber);
            if (vehicleID != -1) {
                if ("history-full".equals(action)) {
                    historyList.addAll(inspectionRecordDao.getFullHistoryVehicleInspected(vehicleID));
                } else {
                    historyList.addAll(inspectionRecordDao.getInspectedVehilce(vehicleID));
                }
            }
        }

        // Đặt danh sách lịch sử kiểm định vào request attribute
        request.setAttribute("historyList", historyList);
        request.getRequestDispatcher("resources/vehicle/lichSuKD.jsp").forward(request, response);
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
        doGet(request, response);
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
