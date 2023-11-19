/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller;

import DAO.PostDAO;
import Model.Post;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;

/**
 *
 * @author Admin
 */
public class SearchAjaxHomePage extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException, ClassNotFoundException {
        response.setContentType("text/html;charset=UTF-8");
        String searchValue = request.getParameter("txt");
        PostDAO pDao = new PostDAO();
        PrintWriter out = response.getWriter();
        try {
            List<Post> post = pDao.ajaxSearchPostByTitle(searchValue);
            for(Post o : post){
                String editedContent = "";
                if(o.getContent().length()>100){
                    editedContent = o.getContent().substring(0, Math.min(o.getContent().length(), 100));
                }else {
                    editedContent = o.getContent();
                }
                out.println("<div class=\"each-ajax-result\">\n" +
"                        <img src=\""+o.getImg()+"\" width=\"70px\" height=\"70px\" style=\"object-fit: cover; margin-left : 15px; border-radius : 5px ; border: 1px solid black;\">\n" +
"                            <div style=\"\">    \n" +
"                                <a style=\"font-weight: bolder ; margin: 0 ; color: black; font-style: normal\" href=\"viewPostDetails?postId="+o.getPostId()+"\">"+o.getTitle()+"</a>\n" +
"                                <p style=\"font-size: 12px\">"+editedContent+"...</p>\n" +
"                            </div>\n" +
"                    </div>");
            }
        } catch (SQLException ex) {
            Logger.getLogger(SearchAjaxHomePage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(SearchAjaxHomePage.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SearchAjaxHomePage.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SearchAjaxHomePage.class.getName()).log(Level.SEVERE, null, ex);
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
