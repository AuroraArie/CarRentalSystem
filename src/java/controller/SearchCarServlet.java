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
import java.util.List;
import model.Car;

/**
 *
 * @author ADMIN
 */
public class SearchCarServlet extends HttpServlet {

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
            out.println("<title>Servlet SearchCarServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SearchCarServlet at " + request.getContextPath() + "</h1>");
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
        request.setCharacterEncoding("UTF-8");

        // Lấy thông tin từ form search trên index.jsp
        String carType = request.getParameter("carType"); // "ALL", "4", "5", "7"
        String pickupDate = request.getParameter("pickupDate");
        String returnDate = request.getParameter("returnDate");

        CarDAO dao = new CarDAO();
        // Tìm xe trống theo loại xe
        List<Car> carList = dao.searchAvailableCars(carType);

        // Đẩy danh sách xe vào request để jsp hiển thị
        request.setAttribute("carList", carList);

        // Lưu tạm ngày mượn/trả vào session hoặc request để khách hàng biết họ đang xem cho khung giờ nào
        // (Sẽ cần dùng 2 biến này khi họ bấm "Thêm vào hợp đồng")
        HttpSession session = request.getSession();
        session.setAttribute("searchPickupDate", pickupDate);
        session.setAttribute("searchReturnDate", returnDate);
        session.setAttribute("searchCarType", carType);

        // Điều hướng sang trang hiển thị kết quả
        request.getRequestDispatcher("customer/search_car.jsp").forward(request, response);
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
