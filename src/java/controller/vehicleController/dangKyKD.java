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
import java.text.SimpleDateFormat;
import java.util.List;
import model.InspectionRecords;
import model.User;
import model.enums.RoleEnums;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Lenovo
 */
public class dangKyKD extends HttpServlet {
   
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
            out.println("<title>Servlet dangKyKD</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet dangKyKD at " + request.getContextPath () + "</h1>");
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
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("currentUser");
        
        if(currentUser != null && currentUser.getRole().equals(RoleEnums.Owner)){
            VehicleDao vehicleDao = new VehicleDao();
            StationDao stationDao = new StationDao();
            
            List<String> plateNumbers = vehicleDao.getPlateNumberByOwnerID(currentUser.getUserId());
            List<String> stationNames = stationDao.getAllStationNames();
            
            request.setAttribute("plateNumbers", plateNumbers);
            request.setAttribute("stationNames", stationNames);
            
            RequestDispatcher dispatcher = request.getRequestDispatcher("resources/vehicle/dangKyKD.jsp");
            dispatcher.forward(request, response);
            
        } else {
            // Nếu không phải chủ phương tiện, chuyển hướng về trang đăng nhập
            response.sendRedirect("login.jsp");
        }
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
        try {
            String plateNumber = request.getParameter("plateNumber");
            String stationName = request.getParameter("stationName");
            String inspectionDate = request.getParameter("inspectionDate");

            // Validate dữ liệu đầu vào
            if (plateNumber == null || stationName == null || inspectionDate == null 
                || plateNumber.trim().isEmpty() || stationName.trim().isEmpty() || inspectionDate.trim().isEmpty()) {
                request.setAttribute("message", "Vui lòng chọn đầy đủ thông tin!");
                doGet(request, response);
                return;
            }

            // Chuyển từ PlateNumber và StationName thành ID
            VehicleDao vehicleDao = new VehicleDao();
            StationDao stationDao = new StationDao();
            InspectionRecordDao inspectionRecordDao = new InspectionRecordDao();

            int vehicleID = vehicleDao.getVehicleIDByPlateNumber(plateNumber);
            int stationID = stationDao.getStationIDByName(stationName);

            // Kiểm tra ID có hợp lệ
            if (vehicleID == 0 || stationID == 0) {
                request.setAttribute("message", "Không tìm thấy thông tin phương tiện hoặc trạm đăng kiểm!");
                doGet(request, response);
                return;
            }

            // Kiểm tra ngày đăng kiểm
            SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
            String todayStr = sd.format(new java.util.Date());
            Date today = sd.parse(todayStr);
            Date date = sd.parse(inspectionDate);
            if (today.compareTo(date) > 0) {
                request.setAttribute("message", "Ngày đăng kiểm không thể là ngày trong quá khứ!");
                doGet(request, response);
                return;
            }
         

            // Tạo bản ghi kiểm định mới
            InspectionRecords record = new InspectionRecords();
            record.setVehicle(vehicleDao.getVehiclesById(vehicleID));
            record.setStationID(stationID);
            record.setInspectionDate(sd.parse(inspectionDate));
            
            // Tính nextInspectionDate (1 năm sau)
            Calendar cal = Calendar.getInstance();
            cal.setTime(sd.parse(inspectionDate));
            cal.add(Calendar.YEAR, 1);
            java.sql.Date nextInspectionDate = new java.sql.Date(cal.getTimeInMillis());
            record.setNextInspectionDate(nextInspectionDate);

            // Thêm vào database
            int result = inspectionRecordDao.save(record);

            if (result > 0) {
                request.setAttribute("message", "Đăng ký kiểm định thành công!");
            } else {
                request.setAttribute("message", "Đăng ký kiểm định thất bại. Vui lòng thử lại.");
            }

            // Refresh lại trang đăng ký kiểm định
            doGet(request, response);
            
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "Có lỗi xảy ra: " + e.getMessage());
            doGet(request, response);
        }
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
