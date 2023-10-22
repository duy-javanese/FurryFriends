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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author DUY
 */
public class RegisterController extends HttpServlet {
    
    private static final String ERROR = "registerPage.jsp";
    private static final String SUCCESS = "loginPage.jsp";

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
            if (username.length() < 6 || username.length() > 30) {
                request.setAttribute("USERNAME_ERROR", "Tên người dùng phải từ 6 - 30 kí tự!");
                check = false;
            }
            if (!confirmPassword.equals(password)) {
                request.setAttribute("PASSWORD_ERROR", "Mật khẩu không khớp!");
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
                user.setPwd(password);
                user.setEmail(email);
                user.setPhone(phone);
                user.setAddress(address);
                boolean checkUsername = dao.isUserExists(user.getUsername());
                if (!checkUsername) {
                    boolean checkAdd = dao.addUser(user);
                    if (checkAdd) {
                        url = SUCCESS;
                    }
                } else {
                    request.setAttribute("USERNAME_ERROR", "Username đã tồn tại!");
                }
            }
        } catch (Exception e) {
            log("Error at RegisterController: " + e.toString());
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
}
