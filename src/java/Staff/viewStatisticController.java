/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Staff;

import DAO.InformationDAO;
import DAO.LikePostDAO;
import DAO.PostDAO;
import DAO.statisticModelDAO;
import Model.statisticModel;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class viewStatisticController extends HttpServlet {
   private static final String ERROR = "errorPage.jsp";
   private static final String VIEW_STATISTIC="StaffPage.jsp";
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
        String url = ERROR;
        try{
            PostDAO pDao = new PostDAO();
            InformationDAO iDao = new InformationDAO();
            statisticModelDAO dao = new statisticModelDAO();
            LikePostDAO lDao = new LikePostDAO();
            statisticModel dto = new statisticModel();
            int currentYear = LocalDate.now().getYear();
            // Num of posts created this year and last year
            List<statisticModel> list= dao.PostStatisticDetail(currentYear);
            request.setAttribute("LIST_POST_OF_THIS_YEAR", this.generateDataMonth(list));
            request.setAttribute("THIS_YEAR_TOTAL_POST", pDao.getYearTotalPost(currentYear));
            list= dao.PostStatisticDetail(currentYear-1);
            request.setAttribute("LIST_POST_OF_LAST_YEAR", this.generateDataMonth(list));
            request.setAttribute("LAST_YEAR_TOTAL_POST", pDao.getYearTotalPost(currentYear-1));
            
            request.setAttribute("TOTAL_PENDING_POST", pDao.getTotalPostByStatus(1));
            request.setAttribute("TOTAL_APPROVED_POST", pDao.getTotalPostByStatus(2));
            request.setAttribute("TOTAL_REJECTED_POST", pDao.getTotalPostByStatus(3));
            
            request.setAttribute("FIRST_POST_TYPE_DETAIL", dao.getPostTypeQuantity(1));
            request.setAttribute("SECOND_POST_TYPE_DETAIL", dao.getPostTypeQuantity(2));
            request.setAttribute("THIRD_POST_TYPE_DETAIL", dao.getPostTypeQuantity(3));
            request.setAttribute("FORTH_POST_TYPE_DETAIL", dao.getPostTypeQuantity(4));
            
            request.setAttribute("ACTIVE_USER", iDao.countUsers());
            request.setAttribute("BANNED_USER", iDao.countBannedUsers());
            request.setAttribute("ACTIVE_STAFF", iDao.countStaffs());
            request.setAttribute("BANNED_STAFF", iDao.countInactiveStaffs());
            
            request.setAttribute("FIRST_CATEGORY_DETAIL", dao.getCategoryQuantity(1));
            request.setAttribute("SECOND_CATEGORY_DETAIL", dao.getCategoryQuantity(2));
            request.setAttribute("THIRD_CATEGORY_DETAIL", dao.getCategoryQuantity(3));
            
            request.setAttribute("MOST_LIKED_POST", pDao.GetMostLikedPost());
            request.setAttribute("MOST_LIKED_POST_TOTAL", lDao.GetTotalLikePost(pDao.GetMostLikedPost().getPostId()));
            request.setAttribute("MOST_COMMENTED_POST", dao.GetMostCommentedPost());
            request.setAttribute("MOST_INTERACTION_POST", dao.GetMostInteractedPost());
            
            request.setAttribute("FINISHED_EXCHANGE_QUANTITY", dao.getFinishedExchangeQuantity());
            url = VIEW_STATISTIC;
        } catch (SQLException ex) {
           Logger.getLogger(viewStatisticController.class.getName()).log(Level.SEVERE, null, ex);
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

    public List<Integer> generateDataMonth(List<statisticModel> list ){
        List<Integer> quantityOfPost = new ArrayList<>();
        for(int i = 1; i <= 12; i++) {
            boolean check = false;
            for(int j = 0; j < list.size(); j++) {
                if(list.get(j).getObject() == i) {
                    quantityOfPost.add(list.get(j).getQuantity());
                    check = true;
                    break;
                }
            }
            if(!check) quantityOfPost.add(0);
        }
        return quantityOfPost;
    }
    
    //test
    public static void main(String[] args) {
         List<Integer> quantityOfPost = new ArrayList<>();
         List<statisticModel> ab = new ArrayList<>();
        try{
            PostDAO pDao = new PostDAO();
            InformationDAO iDao = new InformationDAO();
            statisticModelDAO dao = new statisticModelDAO();
            LikePostDAO lDao = new LikePostDAO();
            int currentYear = LocalDate.now().getYear();
            List<statisticModel> list= dao.PostStatisticDetail(currentYear);
            for(int i = 1; i <= 12; i++) {
            boolean check = false;
            for(int j = 0; j < list.size(); j++) {
                if(list.get(j).getObject() == i) {
                    quantityOfPost.add(list.get(j).getQuantity());
                    check = true;
                    break;
                }
            }
            if(!check) quantityOfPost.add(0);
        }
            System.out.println(lDao.GetTotalLikePost(pDao.GetMostLikedPost().getPostId()));
            System.out.println(dao.GetMostInteractedPost());
        } catch (SQLException ex) {
           Logger.getLogger(viewStatisticController.class.getName()).log(Level.SEVERE, null, ex);
       }
        for (statisticModel x : ab){
            System.out.println(x);
        }
    }
}
