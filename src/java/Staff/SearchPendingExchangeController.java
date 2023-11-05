/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Staff;

import DAO.CategoryDAO;
import DAO.ExchangeDAO;
import Model.Category;
import Model.Exchange;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;

/**
 *
 * @author Admin
 */
public class SearchPendingExchangeController extends HttpServlet {
   private final String ERROR_PAGE="errorPage.jsp";
   private final String RESULT_PAGE="SearchPendingExchangePage.jsp";
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
        String searchValue = request.getParameter("txtSearchValue");
        int categoryId = Integer.parseInt(request.getParameter("categoryId"));
        HttpSession session = request.getSession();
        CategoryDAO cDao = new CategoryDAO();
        ExchangeDAO eDao = new ExchangeDAO();
        String url = ERROR_PAGE;
         ArrayList<Category> categories = cDao.GetAllCategories();
        try  {
            if (!searchValue.trim().isEmpty()){
                if(categoryId == 0){
                    eDao.searchExchangeByTitle(searchValue);
                }
                else {
                    eDao.searchExchangeByTitleAndCategory(searchValue, categoryId);
                }
                List<Exchange> result = eDao.getExchangeSearchResult();
                session.setAttribute("categories", categories);
                request.setAttribute("SEARCH_EXCHANGE_RESULT", result);
                url = RESULT_PAGE;
            }
            if (searchValue.isEmpty()){
                if(categoryId == 0){
                    url="GetPendingExchange";
                }
                else {
                    eDao.searchExchangeByCategoryId(categoryId);
                    List<Exchange> result = eDao.getExchangeSearchResult();
                    session.setAttribute("categories", categories);
                    request.setAttribute("SEARCH_EXCHANGE_RESULT", result);
                    url = RESULT_PAGE;
                }
            }
                
            
        } catch (ClassNotFoundException ex) {
           Logger.getLogger(SearchPendingExchangeController.class.getName()).log(Level.SEVERE, null, ex);
       } catch (SQLException ex) {
           Logger.getLogger(SearchPendingExchangeController.class.getName()).log(Level.SEVERE, null, ex);
       } catch (NamingException ex) {
           Logger.getLogger(SearchPendingExchangeController.class.getName()).log(Level.SEVERE, null, ex);
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

}
