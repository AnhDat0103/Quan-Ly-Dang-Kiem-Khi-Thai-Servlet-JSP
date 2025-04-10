/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.inspectController;

import dao.InspectionRecordDao;
import dao.NotificationDao;
import dao.StationDao;
import dao.VehicleDao;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.InspectionRecords;
import model.Notification;
import model.User;
import validation.Validate;

/**
 *
 * @author DUYEN
 */
public class GetInspectorHomePage extends HttpServlet {

    VehicleDao vhd = new VehicleDao();
    InspectionRecordDao vd = new InspectionRecordDao();
    NotificationDao ntd = new NotificationDao();

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
        //  processRequest(request, response);
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("currentUser");
        StationDao sd = new StationDao();
        String status = request.getParameter("status") != null? request.getParameter("status"):"";
        if( status.equals("error") ){
            request.setAttribute("errorMessage", "HC và CO2 phải là số, không được để trống, không chứa ký tự đặc biệt, khoảng trống, hoặc chữ cái.");
        }
        int stationId = 0;
        if (user.getInspectionStation() != null) {
            stationId = user.getInspectionStation().getStationId();
        }
        request.setAttribute("stations", sd.findAll());

        List<InspectionRecords> is = vd.getListInspectionRecordsByPendingAndInspectId(user.getUserId(), stationId);
        request.setAttribute("inspecrecord", is);

        // Tính "Còn lại" từ danh sách pendingRecords
        int remainingCount = is.size();
        request.setAttribute("remainingCount", remainingCount);

        // Giả sử "Đã kiểm định" là một giá trị tạm thời (hoặc bạn có cách lấy riêng)
        int checkedCount = vd.getInspectedRecord(user.getUserId(), stationId);
        request.setAttribute("checkedCount", checkedCount);

        request.getRequestDispatcher("resources/inspector/homepage.jsp").forward(request, response);
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

        String hcr = (request.getParameter("hc")) == null ? "" : (request.getParameter("hc"));
        String co2r = (request.getParameter("co2")) == null ? "" : (request.getParameter("co2"));
        String comment = request.getParameter("comment") == null ? "" : (request.getParameter("comment"));
        String recordId = (request.getParameter("recordId")) == null ? "" : (request.getParameter("recordId"));
        String plateNumber = request.getParameter("plateNum") == null ? "" : request.getParameter("plateNum");
        
         // Validate HC và CO2
    if (!Validate.checkHCAndCO2(hcr) || !Validate.checkHCAndCO2(co2r)) {
        request.setAttribute("errorMessage", "HC và CO2 phải là số, không được để trống, không chứa ký tự đặc biệt, khoảng trống, hoặc chữ cái.");
        response.sendRedirect("nguoi-kiem-dinh?status=error");
        return;
    }

        User onwer = vhd.getOwnerByPlateNumber(plateNumber);

        double hc, co2;
        int recordIdr = Integer.parseInt(recordId);

        try {
            hc = Double.parseDouble(hcr);
        } catch (Exception e) {
            hc = 0;
        }

        try {
            co2 = Double.parseDouble(co2r);
        } catch (Exception e) {
            co2 = 0;
        }

        try {
            String result = "";
            if (hc > 40 || co2 > 40) {
                result = "Fail";
            } else {
                result = "Pass";
            }
            
            vhd.update(result , plateNumber);
            
            
            vd.updateEmissions(recordIdr, co2, hc, comment, result);
            if (onwer != null) {
                String msg = "Xe mang biển số" + plateNumber + "đã được đăng kểm khí thải. Kết quả đăng kiểm: " + result;
                Notification notification = new Notification();
                notification.setIsRead(true);
                notification.setUser(onwer);
                notification.setMessage(msg);
                ntd.save(notification);
            } else {
                response.sendRedirect("500error");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect("nguoi-kiem-dinh");
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
