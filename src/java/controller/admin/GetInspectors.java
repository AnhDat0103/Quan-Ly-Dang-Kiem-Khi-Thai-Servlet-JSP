package controller.admin;

import dao.UserDao;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;

public class GetInspectors extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        int stationId = Integer.parseInt(request.getParameter("stationId"));
        UserDao inspectorDao = new UserDao();
        List<User> inspectors = inspectorDao.getAllInspectors(stationId);
        request.setAttribute("inspectors", inspectors);
        request.getRequestDispatcher("dashboard/inspector.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    public String getServletInfo() {
        return "GetInspectors Servlet";
    }
}
