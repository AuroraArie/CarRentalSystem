/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Helper {

    /**
     * Mã hóa mật khẩu sử dụng thuật toán SHA-256 (Khuyên dùng thay vì MD5)
     * @param password Mật khẩu thô do người dùng nhập
     * @return Chuỗi Hex đã được mã hóa
     */
    public static String hashPassword(String password) {
        if (password == null) return null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Lỗi mã hóa SHA-256: " + e.getMessage());
            return null;
        }
    }

    /**
     * Chuyển đổi định dạng ngày tháng từ HTML form (<input type="datetime-local">) 
     * sang định dạng để hiển thị (dd/MM/yyyy HH:mm)
     * @param htmlDateTime Chuỗi dạng "yyyy-MM-dd'T'HH:mm"
     * @return Chuỗi định dạng "dd/MM/yyyy HH:mm"
     */
    public static String formatDateTimeForDisplay(String htmlDateTime) {
        if (htmlDateTime == null || htmlDateTime.isEmpty()) return "";
        try {
            // Định dạng nhận từ datetime-local của HTML5
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
            // Định dạng muốn hiển thị ra giao diện cho người Việt
            SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            
            Date date = inputFormat.parse(htmlDateTime);
            return outputFormat.format(date);
        } catch (ParseException e) {
            System.out.println("Lỗi format ngày tháng: " + e.getMessage());
            return htmlDateTime; // Trả về chuỗi gốc nếu lỗi
        }
    }

    /**
     * Chuyển đổi ngày tháng để lưu vào SQL Server (Tùy thuộc định dạng DB của bạn)
     * @param htmlDateTime
     * @return 
     */
    public static String formatDateTimeForSQL(String htmlDateTime) {
        if (htmlDateTime == null || htmlDateTime.isEmpty()) return null;
        try {
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
            SimpleDateFormat sqlFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            
            Date date = inputFormat.parse(htmlDateTime);
            return sqlFormat.format(date);
        } catch (ParseException e) {
            System.out.println("Lỗi format ngày tháng SQL: " + e.getMessage());
            return htmlDateTime;
        }
    }
}
