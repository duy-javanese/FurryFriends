/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.User;

import DAO.UserDAO;
import Model.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author dell
 */
public class EditProfileController extends HttpServlet {

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
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet EditProfileController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditProfileController at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher("/Views/User/EditProfile.jsp").forward(request, response);
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
        HttpSession session = request.getSession();
        boolean validate = true;
        User account = (User) session.getAttribute("account");
        
        int userId = Integer.parseInt(request.getParameter("userId"));
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String avatar = request.getParameter("avatar");
        
        if (!checkEmail(email)) {
                request.setAttribute("EDIT_EMAIL_ERROR", "Email không hợp lệ!");
                validate = false;
            }
        if (!checkPhone(phone)) {
                request.setAttribute("EDIT_PHONE_ERROR", "Số điện thoại không hợp lệ!");
                validate = false;
            }
        if (!checkAddress(address)) {
                request.setAttribute("EDIT_ADDRESS_ERROR", "Địa chỉ không hợp lệ!");
                validate = false;
            }
        
        if (validate){
            User user = new User();
            user.setUserId(account.getUserId());
            user.setEmail(email);
            user.setPhone(phone);
            user.setAddress(address);
            user.setImg(avatar);
        
        UserDAO uDao = new UserDAO();
        uDao.UpdateUser(user);
        
        session.setAttribute("account", user);
        response.sendRedirect("userProfile");
        }
        else{
            request.getRequestDispatcher("Views/User/EditProfile.jsp").forward(request, response);
        }
        
    }
    
    public static void main(String[] args) {
        User user = new User();
        user.setUserId(1);
        user.setEmail("@@@@@@@");
        user.setUsername("@@@@@@@@@@@@@@@@");
        user.setPwd("@@@@@@@@@@@@@");
        user.setPhone("09809890");
        user.setAddress("@@@@@@@@@@");

        UserDAO uDao = new UserDAO();
        uDao.UpdateUser(user);
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
    
    private static boolean checkAddress(String address) {
        String regex = ".*[a-zA-Z].{9,}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(address);
        return matcher.matches();
    }
}
