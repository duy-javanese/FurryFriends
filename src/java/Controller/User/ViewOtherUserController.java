/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller.User;

import DAO.ExchangeDAO;
import DAO.PostDAO;
import DAO.UserDAO;
import Model.Exchange;
import Model.Post;
import Model.User;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author Admin
 */
public class ViewOtherUserController extends HttpServlet {
   private final String ERROR = "errorPage.jsp";
   private final String OTHER_USER_PROFILE="ViewOtherUserProfile.jsp";
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
        int userId = Integer.parseInt(request.getParameter("userId"));
        String action = request.getParameter("action");
        String url = ERROR;
        try  {
            UserDAO uDao = new UserDAO();
            PostDAO pDao = new PostDAO();
            ExchangeDAO eDao = new ExchangeDAO();
            User user = uDao.GetUserById(userId);
            if(action.equals("viewUserListPost")){
                List<Post> content = pDao.getTop3PostByUserID(userId);
                request.setAttribute("OTHER_USER_POST", content);
            }
            if(action.equals("viewUserListExchange")){
                List<Exchange> content = eDao.getExchangeByUserID(userId);
                request.setAttribute("OTHER_USER_EXCHANGE", content);
            }
            request.setAttribute("TOTAL_POST", pDao.getTotalShowedPostOfUser(userId));
            request.setAttribute("TOTAL_EXCHANGE", pDao.getTotalShowedExchangeOfUser(userId));
            request.setAttribute("OTHER_USER_PROFILE", user);
            url=OTHER_USER_PROFILE;
        }finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    public static void main(String[] args) {
        PostDAO pDao = new PostDAO();
        ExchangeDAO eDao = new ExchangeDAO();
//        List<Post> posts = pDao.getPostByUserID(18);
//        for (Post p : posts){
//            System.out.println(p);
//        }
        List<Exchange> exchanges = eDao.getExchangeByUserID(18);
        for (Exchange e : exchanges){
            System.out.println(e);
        }
    }
}
