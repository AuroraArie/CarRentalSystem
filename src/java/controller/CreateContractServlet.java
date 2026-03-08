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
import model.Contract;
import model.ContractDetail;
import model.User;

/**
 *
 * @author ADMIN
 */
public class CreateContractServlet extends HttpServlet {
   
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
            out.println("<title>Servlet CreateContractServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CreateContractServlet at " + request.getContextPath () + "</h1>");
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
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        
        // 1. Kiểm tra đăng nhập
        User currentUser = (User) session.getAttribute("user");
        if (currentUser == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        // 2. Lấy thông tin giỏ hàng từ Session
        // Giả sử bạn lưu 1 object tên là 'cart' chứa danh sách chi tiết và ngày mượn/trả
        model.Cart cart = (model.Cart) session.getAttribute("cart");
        
        if (cart == null || cart.getItems().isEmpty()) {
            response.sendRedirect("index.jsp"); // Giỏ hàng trống thì quay về trang chủ
            return;
        }

        // 3. Khởi tạo đối tượng Contract
        Contract contract = new Contract();
        contract.setCustomerId(currentUser.getId());
        contract.setPickupDate(cart.getPickupDate());
        contract.setReturnDate(cart.getReturnDate());
        contract.setTotalPrice(cart.getTotalPrice());
        
        // ---> BẮT ĐẦU ĐOẠN CODE THÊM MỚI ĐỂ FIX LỖI <---
        // Tạo một danh sách ContractDetail để truyền vào DAO
        java.util.List<ContractDetail> contractDetails = new java.util.ArrayList<>();
        
        // Duyệt qua từng sản phẩm trong giỏ hàng (CartItem) để chuyển thành ContractDetail
        for (model.CartItem item : cart.getItems()) {
            ContractDetail detail = new ContractDetail();
            detail.setCarId(item.getCar().getId());
            detail.setNeedDriver(item.isNeedDriver());
            detail.setPrice(item.getCar().getPricePerDay()); // Lưu lại giá tại thời điểm thuê
            
            // Thêm vào danh sách chi tiết hợp đồng
            contractDetails.add(detail);
        }
        // ---> KẾT THÚC ĐOẠN CODE THÊM MỚI <---
        
        // 4. Gọi DAO để lưu vào Database (sử dụng Transaction)
        ContractDAO dao = new ContractDAO();
        boolean isSuccess = dao.createContract(contract, contractDetails);

        if (isSuccess) {
            // Tạo thành công -> Xóa giỏ hàng -> Chuyển hướng sang trang Lịch sử hợp đồng
            session.removeAttribute("cart");
            response.sendRedirect("customer/my_contracts.jsp");
        } else {
            // Thất bại -> Báo lỗi
            request.setAttribute("error", "Có lỗi xảy ra khi tạo hợp đồng. Vui lòng thử lại!");
            request.getRequestDispatcher("customer/cart.jsp").forward(request, response);
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
