package Controller;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
import DAO.CategoryDAO;
import DAO.PostDAO;
import DAO.PostTypeDAO;
import Model.Category;
import Model.Constant;
import Model.Post;
import Model.PostType;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 *
 * @author dell
 */
public class HomePage extends HttpServlet {

    private int recordsPerPage = 4;

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
        PostTypeDAO ptDao = new PostTypeDAO();
        PostDAO pDao = new PostDAO();
        CategoryDAO cDao = new CategoryDAO();

        
        //get session
        HttpSession session = request.getSession();
        
        //pagenition 
        int page = 1;

        if (request.getParameter("page") != null) {
            page = Integer.parseInt(
                    request.getParameter("page"));
        }

        //get list type post
        ArrayList<PostType> listType = ptDao.GetAllPostType();

        //get all post
        ArrayList<Post> posts = pDao.GetPostPagnition((page - 1) * recordsPerPage,
                recordsPerPage);
        ArrayList<Category> categories = cDao.GetAllCategories();

        int noOfRecords = pDao.GetNoOfRecordsPost();

        int noOfPages = (int) Math.ceil((double) noOfRecords
                / recordsPerPage);

        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", page);
        request.setAttribute("noOfRecords", noOfRecords);

        request.setAttribute("posts", posts);
        session.setAttribute("categories", categories);

        String msg = null;

        if (session.getAttribute("msg") != null) {
            msg = (String) session.getAttribute("msg");
            session.removeAttribute("msg");
        }

        session.setAttribute("types", listType);
        request.setAttribute("msg", msg);
        session.setAttribute("PostExchange", Constant.PostExchange);
        request.getRequestDispatcher("HomePage.jsp").forward(request, response);
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
