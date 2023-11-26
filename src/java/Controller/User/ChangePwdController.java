/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class ChangePwdController extends HttpServlet {
   private final String ERROR_PAGE = "/Views/User/ChangePwd.jsp";
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException, NoSuchAlgorithmException {
        response.setContentType("text/html;charset=UTF-8");
        UserDAO uDao = new UserDAO();
        String username = request.getParameter("userName");
        String oldPwd = request.getParameter("oldPwd");
        String hashedPwd = hashMD5(oldPwd);
        System.out.println(oldPwd);
        System.out.println(username);
        User testPwd = uDao.doLogin(username.toLowerCase(), hashedPwd);
        
        String newPwd = request.getParameter("newPwd");
        String RnewPwd = request.getParameter("RnewPwd");
        int userId = Integer.parseInt(request.getParameter("userId"));
        boolean validate = true;
        String url = ERROR_PAGE;
        
        if (testPwd == null){
            request.setAttribute("OLD_PASSWORD", "Mật khẩu cũ không đúng!");
            validate = false;
        }
        if (newPwd.length()<6 || newPwd.length()>30 ){
            request.setAttribute("NEW_PASSWORD", "Mật khẩu phải từ 6 - 30 kí tự!");
            validate = false;
        }
        if (!newPwd.equals(RnewPwd)){
            request.setAttribute("RNEW_PASSWORD", "Mật khẩu không khớp!");
            validate = false;
        }
        if (validate){
            String hashNewPwd = hashMD5(RnewPwd);
            uDao.changePwd(hashNewPwd, userId);
            response.sendRedirect("userProfile");
        }else{
            request.getRequestDispatcher("Views/User/ChangePwd.jsp").forward(request, response);
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
       try {
           processRequest(request, response);
       } catch (NoSuchAlgorithmException ex) {
           Logger.getLogger(ChangePwdController.class.getName()).log(Level.SEVERE, null, ex);
       }
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
       try {
           processRequest(request, response);
       } catch (NoSuchAlgorithmException ex) {
           Logger.getLogger(ChangePwdController.class.getName()).log(Level.SEVERE, null, ex);
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
    
    public static void main(String[] args) {
        UserDAO uDao = new UserDAO();
        boolean validate = false;
        
    }
}
