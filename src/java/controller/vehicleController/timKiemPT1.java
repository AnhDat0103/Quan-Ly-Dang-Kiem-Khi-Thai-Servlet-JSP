/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.vehicleController;

import model.Vehicles;
import dao.VehicleDao;
import validation.Validate;
import jakarta.servlet.RequestDispatcher;
import java.sql.SQLException;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author Lenovo
 */
@WebServlet("/timKiemPT1")
public class timKiemPT1 extends HttpServlet {

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
            out.println("<title>Servlet timKiemPT1</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet timKiemPT1 at " + request.getContextPath() + "</h1>");
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
        String plateNumber = request.getParameter("plateNumber");
       
        String bug = "";
        
        if(plateNumber == null || plateNumber.isEmpty() || !Validate.checkPlateNumber(plateNumber)){
            bug = "Vui lòng nhập đúng thông tin.";
        }
        
        if (!bug.isEmpty()) {
            request.setAttribute("errorMessage", bug);
            RequestDispatcher rd = request.getRequestDispatcher("/thongbao.jsp");
            rd.forward(request, response);
            return;
        }
        
        VehicleDao vehicleDao = new VehicleDao();
        vehicleDao.selectByPlateNumber(plateNumber);
        if(vehicleDao == null){
            bug = "Không tìm thấy xe với biển số: " + plateNumber; 
            request.setAttribute("errorMessage", bug);
            
            RequestDispatcher rd = request.getRequestDispatcher("/error.jsp");
            rd.forward(request, response);
            return;
        } else{
             request.setAttribute("vehicleDao", vehicleDao);
        }
        RequestDispatcher rd = request.getRequestDispatcher("/ketqua.jsp");
        rd.forward(request, response);        
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
