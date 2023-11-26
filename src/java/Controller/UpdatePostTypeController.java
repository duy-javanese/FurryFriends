/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.PostTypeDAO;
import Model.PostType;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author DUY
 */
@WebServlet(name = "UpdatePostTypeController", urlPatterns = {"/UpdatePostTypeController"})
public class UpdatePostTypeController extends HttpServlet {

    private static final String ERROR = "configPage.jsp";
    private static final String SUCCESS = "configPage.jsp";

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
        PostTypeDAO dao = new PostTypeDAO();
        PostType postType = new PostType();
        try {
            String postType_name = request.getParameter("postType_name_update");
            int postType_id = Integer.parseInt(request.getParameter("postType_id"));
            if (postType_name != null && !postType_name.isEmpty()) {
                boolean checkExists = dao.isPostTypeExists(postType_name);
                if (checkExists) {
                    request.setAttribute("POSTTYPE_ERROR", "Thể loại bài viết đã tồn tại!");
                } else {
                    postType.setPostTypeId(postType_id);
                    postType.setPostTypeName(postType_name);
                    boolean check = dao.updatePostType(postType);
                    if (check) {
                        url = SUCCESS;
                    }
                }
            }
        } catch (Exception e) {
            log("Error at UpdatePostTypeController: " + e.toString());
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

}
