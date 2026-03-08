/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dal.CarDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Car;
import model.CarType;
import model.User;

/**
 *
 * @author ADMIN
 */
public class ManagerCarServlet extends HttpServlet {
   
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
            out.println("<title>Servlet ManagerCarServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ManagerCarServlet at " + request.getContextPath () + "</h1>");
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
        User manager = (User) session.getAttribute("user");
        if (manager == null || manager.getRoleId() != 2) {
            response.sendRedirect("login.jsp");
            return;
        }

        String action = request.getParameter("action");
        String carIdStr = request.getParameter("id");

        if ("delete".equals(action) && carIdStr != null) {
            int carId = Integer.parseInt(carIdStr);
            CarDAO dao = new CarDAO();
            // Hàm deleteCar cần được định nghĩa thêm trong CarDAO
            dao.deleteCar(carId); 
        }
        
        response.sendRedirect("manager/manage_cars.jsp");
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
        request.setCharacterEncoding("UTF-8");
        
        String action = request.getParameter("action");
        
        String name = request.getParameter("name");
        String licensePlate = request.getParameter("licensePlate");
        double price = Double.parseDouble(request.getParameter("price"));
        int typeId = Integer.parseInt(request.getParameter("typeId")); // ID loại xe (4, 5, 7 chỗ)
        String status = request.getParameter("status");

        Car car = new Car();
        car.setName(name);
        car.setLicensePlate(licensePlate);
        car.setPricePerDay(price);
        car.setStatus(status);
        
        CarType type = new CarType();
        type.setId(typeId);
        car.setCarType(type);

        CarDAO dao = new CarDAO();

        if ("add".equals(action)) {
            // Hàm insertCar cần được định nghĩa trong CarDAO
            dao.insertCar(car);
        } else if ("update".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            car.setId(id);
            // Hàm updateCar cần được định nghĩa trong CarDAO
            dao.updateCar(car);
        }

        response.sendRedirect("manager/manage_cars.jsp");
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
