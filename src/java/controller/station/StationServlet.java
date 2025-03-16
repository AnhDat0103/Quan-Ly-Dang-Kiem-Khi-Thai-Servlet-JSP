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

    private boolean deleteStation(int stationId) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private StationDao stationDao = new StationDao();
    private InspectionRecordDao inspectionRecordDao = new InspectionRecordDao();

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
            out.println("<title>Servlet StationServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet StationServlet at " + request.getContextPath() + "</h1>");
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
        String action = request.getParameter("action") == null ? "" : request.getParameter("action");
        if (action.equals("update")) {
            int stationId = Integer.parseInt(request.getParameter("stationId"));
            InspectionStation station = stationDao.findStationById(stationId);
            request.setAttribute("station", station);
            request.getRequestDispatcher("dashboard/InspectionStationDetail.jsp").forward(request, response);
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
        String action = request.getParameter("action");
        String message = "";
        if (action.equals("update")) {
            int stationId = Integer.parseInt(request.getParameter("stationId"));
            String name = request.getParameter("name") != null ? request.getParameter("name") : "";
            String address = request.getParameter("address") != null ? request.getParameter("address") : "";
            String phone = request.getParameter("phone") != null ? request.getParameter("phone") : "";
            InspectionStation inspectionStation = stationDao.findStationById(stationId);
            if (inspectionStation != null) {
                inspectionStation.setPhone(phone);
                inspectionStation.setAddress(address);
                inspectionStation.setName(name);
                boolean updated = stationDao.updateStation(inspectionStation);
                if (updated == false) {
                     request.setAttribute("error", "Cập nhật trung tâm thất bại!");
                    request.getRequestDispatcher("dashboard/InspectionStationDetail.jsp").forward(request, response);
                    return;
                }
            } else {
                request.setAttribute("error", "Trung tâm kiểm định không tồn tại");
                request.getRequestDispatcher("dashboard/InspectionStationDetail.jsp").forward(request, response);
                    return;
            }
        } else if (action.equals("delete")) {

            // Xóa trung tâm đăng kiểm
            int stationId = Integer.parseInt(request.getParameter("stationId"));

            // Bước 1: Cập nhật StationID thành NULL trong InspectionRecords
            inspectionRecordDao.setStationIdToNull(stationId);

            // Bước 2: Xóa trung tâm (vì StationDao chưa có delete, thêm tạm phương thức này)
            boolean deleted = deleteStation(stationId);
            int rowsDeleted = stationDao.delete(stationId);

            if (deleted) {
                message = "Xóa trung tâm thành công!";
                request.setAttribute("message", "Xóa trung tâm thành công!");
            } else {
                message = "Xóa trung tâm thất bại!";
            }
        }
        // Quay lại danh sách
        List<InspectionStation> stations = stationDao.getAllStations();
        request.setAttribute("stations", stations);
        request.getRequestDispatcher("dashboard/inspectionStations.jsp").forward(request, response);
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
