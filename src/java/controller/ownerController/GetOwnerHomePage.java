/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.ownerController;

import dao.InspectionRecordDao;
import dao.VehicleDao;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta .servlet.ServletException;
import jakarta .servlet.http.HttpServlet;
import jakarta .servlet.http.HttpServletRequest;
import jakarta .servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.InspectionRecords;
import model.User;
import model.Vehicles;

/**
 *
 * @author Lenovo
 */
public class GetOwnerHomePage extends HttpServlet {
    VehicleDao vehicleDao = new VehicleDao();
    InspectionRecordDao inspectionRecordDao= new InspectionRecordDao();
        
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
            out.println("<title>Servlet GetOwnerHomePage</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet GetOwnerHomePage at " + request.getContextPath() + "</h1>");
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
        User currentUser = (User) session.getAttribute("currentUser"); // Lấy user từ session

        if (currentUser != null) {
            int ownerID = currentUser.getUserId(); // Lấy ownerID từ user object
            List<Vehicles> vehicleList = vehicleDao.getAllVehiclesByUserID(ownerID); // Lấy danh sách xe
            request.setAttribute("vehicleList", vehicleList); // Gửi danh sách xe lên JSP

            List<InspectionRecords> historyList = inspectionRecordDao.getInspectionHistoryByOwnerID(ownerID); // Lấy lịch sử đăng kiểm
            request.setAttribute("historyList", historyList); // Gửi lịch sử lên JSP
        } else {
            response.sendRedirect("login.jsp"); // Chuyển hướng về trang đăng nhập nếu chưa đăng nhập
            return;
        }

        request.getRequestDispatcher("resources/owner/ownerHomePage.jsp").forward(request, response);
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
        processRequest(request, response);
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
