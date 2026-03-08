/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dal.ContractDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;

/**
 *
 * @author ADMIN
 */
public class StaffOrderServlet extends HttpServlet {
   
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
            out.println("<title>Servlet StaffOrderServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet StaffOrderServlet at " + request.getContextPath () + "</h1>");
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
        processRequest(request, response);
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
        HttpSession session = request.getSession();
        User staff = (User) session.getAttribute("user");
        
        // Kiểm tra quyền hạn (chỉ Staff hoặc Admin mới được thao tác)
        if (staff == null || (staff.getRoleId() != 3 && staff.getRoleId() != 1)) {
            response.sendRedirect("../login.jsp");
            return;
        }

        // Lấy ID hợp đồng và hành động nhân viên muốn thực hiện
        String contractIdStr = request.getParameter("contractId");
        String action = request.getParameter("action");
        
        if (contractIdStr != null && action != null) {
            int contractId = Integer.parseInt(contractIdStr);
            String newStatus = "";

            // Ánh xạ action từ giao diện thành Status trong Database
            switch (action) {
                case "APPROVE":
                    newStatus = "APPROVED"; // Đã duyệt, chờ cọc
                    break;
                case "REJECT":
                    newStatus = "REJECTED"; // Từ chối
                    break;
                case "DEPOSIT_RECEIVED":
                    newStatus = "DEPOSITED"; // Đã nhận cọc
                    break;
                case "CAR_PICKED_UP":
                    newStatus = "ACTIVE"; // Đang mượn xe
                    break;
                case "COMPLETED":
                    newStatus = "COMPLETED"; // Đã trả xe và thanh toán
                    break;
            }

            if (!newStatus.isEmpty()) {
                ContractDAO dao = new ContractDAO();
                dao.updateContractStatus(contractId, newStatus);
            }
        }
        
        // Cập nhật xong thì load lại trang quản lý đơn hàng
        response.sendRedirect("staff/manage_orders.jsp");
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
