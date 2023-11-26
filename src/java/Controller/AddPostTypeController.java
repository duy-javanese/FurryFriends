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
@WebServlet(name = "AddPostTypeController", urlPatterns = {"/AddPostTypeController"})
public class AddPostTypeController extends HttpServlet {

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
        boolean checkExists;
        boolean checkAdd;
        try {
            String postType_name = request.getParameter("postType_name");
            if (postType_name != null && !postType_name.isEmpty()) {
                checkExists = dao.isPostTypeExists(postType_name);
                if (checkExists) {
                    request.setAttribute("POSTTYPE_ERROR", "Thể loại bài viết đã tồn tại!");
                } else {
                    postType.setPostTypeName(postType_name);
                    do {
                        checkAdd = dao.addPostType(postType);
                    } while (checkAdd == false);
                    if (checkAdd) {
                        url = SUCCESS;
                    }
                }
            }
        } catch (Exception e) {
            log("Error at AddPostTypeController: " + e.toString());
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
