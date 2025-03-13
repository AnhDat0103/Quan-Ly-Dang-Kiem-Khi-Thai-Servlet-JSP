/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.station;

import dao.InspectionRecordDao;
import dao.StationDao;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.InspectionStation;

/**
 *
 * @author DUYEN
 */
public class StationServlet extends HttpServlet {
    private StationDao stationDao = new StationDao();
    private InspectionRecordDao inspectionRecordDao = new InspectionRecordDao();
    
   
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
            out.println("<title>Servlet StationServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet StationServlet at " + request.getContextPath () + "</h1>");
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
       // processRequest(request, response);
       
       String action = request.getParameter("action");
        if (action == null) {
            // Hiển thị danh sách trung tâm đăng kiểm
            List<InspectionStation> stations = stationDao.getAllStations();
            request.setAttribute("stations", stations);
            request.getRequestDispatcher("dashbord/inspecStation.jsp").forward(request, response);
        } else if (action.equals("update")) {
            // Lấy thông tin trung tâm để cập nhật
            int stationId = Integer.parseInt(request.getParameter("stationId"));
            InspectionStation station = stationDao.findStationById(stationId);
            request.setAttribute("station", station);
            request.getRequestDispatcher("dashbord/inspecStation.jsp").forward(request, response);
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
     //   processRequest(request, response);
       String action = request.getParameter("action");

        if (action.equals("update")) {
            // Cập nhật trung tâm đăng kiểm
            int stationId = Integer.parseInt(request.getParameter("stationId"));
            String name = request.getParameter("name");
            String address = request.getParameter("address");
            String phone = request.getParameter("phone");
            String email = request.getParameter("email");

            // Gọi phương thức updateStation từ StationDao
            boolean updated = stationDao.updateStation(stationId, name, address, phone, email);

            if (updated) {
                request.setAttribute("message", "Cập nhật trung tâm thành công!");
            } else {
                request.setAttribute("error", "Cập nhật trung tâm thất bại!");
            }
            // Quay lại danh sách
            List<InspectionStation> stations = stationDao.getAllStations();
            request.setAttribute("stations", stations);
            request.getRequestDispatcher("resources/admin/station_list.jsp").forward(request, response);

        } else if (action.equals("delete")) {
            // Xóa trung tâm đăng kiểm
            int stationId = Integer.parseInt(request.getParameter("stationId"));

            // Bước 1: Cập nhật StationID thành NULL trong InspectionRecords
            inspectionRecordDao.setStationIdToNull(stationId);

            // Bước 2: Xóa trung tâm (vì StationDao chưa có delete, thêm tạm phương thức này)
            boolean deleted = deleteStation(stationId);

            if (deleted) {
                request.setAttribute("message", "Xóa trung tâm thành công!");
            } else {
                request.setAttribute("error", "Xóa trung tâm thất bại!");
            }
            // Quay lại danh sách
            List<InspectionStation> stations = stationDao.getAllStations();
            request.setAttribute("stations", stations);
            request.getRequestDispatcher("resources/admin/station_list.jsp").forward(request, response);
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

    private boolean deleteStation(int stationId) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
