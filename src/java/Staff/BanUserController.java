/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Staff;

import DAO.PostDAO;
import DAO.ReportDAO;
import DAO.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;

/**
 *
 * @author Admin
 */
public class BanUserController extends HttpServlet {
   private final String ERROR_PAGE = "errorPage.jsp";
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
        String urlRewriting = ERROR_PAGE;
        int reportId = Integer.parseInt(request.getParameter("reportId"));
        int userId = Integer.parseInt(request.getParameter("userId"));
        int postType = Integer.parseInt(request.getParameter("postType"));
        int staffId = Integer.parseInt(request.getParameter("staffId"));
        String action = request.getParameter("action");
        try{
            UserDAO uDao = new UserDAO();
            PostDAO pDao = new PostDAO();
            ReportDAO rDdao = new ReportDAO();
            Date processDate = Date.valueOf(LocalDate.now());
            if(postType != 4){
                boolean result = uDao.BanUser(userId);
                boolean hideAllUserPost = pDao.UpdateUserPostIsPublic(userId, false);
                boolean updateReportStatus = rDdao.UpdateReportResult(reportId, 4, staffId, processDate);
                if (result && hideAllUserPost && updateReportStatus){
                    urlRewriting = "GetReportedPost";
                }
            }
            if (postType == 4){
                boolean result = uDao.BanUser(userId);
                boolean hideAllUserPost = pDao.UpdateUserPostIsPublic(userId, false);
                boolean updateReportStatus = rDdao.UpdateReportResult(reportId, 4, staffId, processDate);
                if (result && hideAllUserPost && updateReportStatus){
                    urlRewriting = "GetReportedExchange";
                }
            }
        } catch (SQLException ex) {
           Logger.getLogger(BanUserController.class.getName()).log(Level.SEVERE, null, ex);
       } catch (ClassNotFoundException ex) {
           Logger.getLogger(BanUserController.class.getName()).log(Level.SEVERE, null, ex);
       } catch (NamingException ex) {
           Logger.getLogger(BanUserController.class.getName()).log(Level.SEVERE, null, ex);
       }finally {
            //2.2 transfer Dispatcher
            response.sendRedirect(urlRewriting);
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

}
