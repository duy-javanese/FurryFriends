/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.UserDAO;
import Model.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author DUY
 */
public class AddStaffController extends HttpServlet {

    private static final String ERROR = "addStaff.jsp";
    private static final String SUCCESS = "addStaff.jsp";

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
        String url = ERROR;
        UserDAO dao = new UserDAO();
        User user = new User();
        boolean check = true;
        try {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String confirmPassword = request.getParameter("confirm_password");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            String address = request.getParameter("address");
            if (!checkUsername(username)) {
                request.setAttribute("USERNAME_ERROR", "Tên đăng nhập không được bắt đầu bằng số hoặc khoảng trống!");
                check = false;
            }
            if (username.length() < 6 || username.length() > 20) {
                request.setAttribute("USERNAME_ERROR", "Tên đăng nhập phải từ 6 - 20 kí tự!");
                check = false;
            }
            if (password.length() < 6 || password.length() > 30) {
                request.setAttribute("PASSWORD_ERROR", "Mật khẩu phải từ 6 - 30 kí tự!");
                check = false;
            }
            if (!confirmPassword.equals(password)) {
                request.setAttribute("CONFIRM_PASSWORD_ERROR", "Mật khẩu không khớp!");
                check = false;
            }
            if (!checkEmail(email)) {
                request.setAttribute("EMAIL_ERROR", "Email không hợp lệ!");
                check = false;
            }
            if (!checkPhone(phone)) {
                request.setAttribute("PHONE_ERROR", "Số điện thoại không hợp lệ!");
                check = false;
            }
            if (check) {
                user.setUsername(username);
                user.setPwd(hashMD5(password));
                user.setEmail(email);
                user.setPhone(phone);
                user.setAddress(address);
                boolean checkExists = true;
                boolean checkUsername = dao.isUserExists(user.getUsername());
                if (checkUsername) {
                    request.setAttribute("USERNAME_ERROR", "Tên đăng nhập đã tồn tại!");
                    checkExists = false;
                }
                boolean checkEmail = dao.isEmailExists(user.getEmail());
                if (checkEmail) {
                    request.setAttribute("EMAIL_ERROR", "Email đã tồn tại!");
                    checkExists = false;
                }
                boolean checkPhone = dao.isPhoneExists(user.getPhone());
                if (checkPhone) {
                    request.setAttribute("PHONE_ERROR", "Số điện thoại đã tồn tại!");
                    checkExists = false;
                }
                if (checkExists) {
                    boolean checkAdd = dao.addStaff(user);
                    if (checkAdd) {
                        url = SUCCESS;
                        request.setAttribute("SUCCESS", "Quản trị viên " + user.getUsername() + " đã được tạo thành công!");
                    }
                }
            }
        } catch (Exception e) {
            log("Error at AddStaffController: " + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
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
        processRequest(request, response);
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

    private static boolean checkUsername(String username) {
        char firstChar = username.charAt(0);
        if (Character.isDigit(firstChar) || firstChar == ' ') {
            return false;
        }
        return true;
    }

    private static boolean checkEmail(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@([A-Za-z0-9.-]+)\\.[A-Za-z]{2,4}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private static boolean checkPhone(String phone) {
        String regex = "0\\d{9}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phone);
        return matcher.matches();
    }

    private String hashMD5(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] byteData = md.digest();

        StringBuilder sb = new StringBuilder();
        for (byte b : byteData) {
            sb.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }
}
