/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import DAO.UserDAO;
import Model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author ASUS
 */
public class RegisterController extends HttpServlet {

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
            out.println("<title>Servlet RegisterController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RegisterController at " + request.getContextPath() + "</h1>");
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
        // Lấy dữ liệu từ trang đăng ký
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirm_password");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone"); // 
        String address = request.getParameter("address");

        // Kiểm tra xác thực dữ liệu và xử lý đăng ký
        if (validateUserData(username, password, confirmPassword)) {
            // Dữ liệu hợp lệ, thực hiện đăng ký và thêm người dùng mới vào cơ sở dữ liệu
            if (registerUser(username, password, email, phone, address)) {
                // Đăng ký thành công, chuyển hướng người dùng đến trang đăng nhập với thông báo thành công
                request.setAttribute("registrationSuccess", true);
                request.getRequestDispatcher("Login.jsp").forward(request, response);
            } else {
                // Đăng ký không thành công, hiển thị thông báo lỗi trên trang đăng ký
                request.setAttribute("registrationError", "Đã xảy ra lỗi khi đăng ký.");
                request.getRequestDispatcher("register.jsp").forward(request, response);
            }
        } else {
            // Dữ liệu không hợp lệ, hiển thị thông báo lỗi trên trang đăng ký
            request.setAttribute("registrationError", "Thông tin đăng ký không hợp lệ.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
    }

    // Hàm kiểm tra xác thực dữ liệu đăng ký
    private boolean validateUserData(String username, String password, String confirmPassword) {
        // Kiểm tra xem tên người dùng đã tồn tại trong cơ sở dữ liệu chưa
        UserDAO userDAO = new UserDAO();
        if (userDAO.isUserExists(username)) {
            return false; // Trả về false nếu tên người dùng đã tồn tại
        }
        // Kiểm tra xem mật khẩu và mật khẩu xác nhận có khớp nhau không
        if (!password.equals(confirmPassword)) {
            return false; // Trả về false nếu mật khẩu và mật khẩu xác nhận không khớp nhau
        }

        return true; // Trả về true nếu dữ liệu hợp lệ
    }

    // Hàm xử lý đăng ký và thêm người dùng mới vào cơ sở dữ liệu
    private boolean registerUser(String username, String password, String email, String phone, String address) {
        
        UserDAO userDAO = new UserDAO();
        User user = new User();
        user.setUsername(username);
        user.setPwd(password);
        user.setEmail(email); 
        user.setPhone(phone); 
        user.setAddress(address); 

        // Thêm người dùng mới vào cơ sở dữ liệu
        boolean registrationSuccess = userDAO.addUser(user);

        return registrationSuccess;
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
