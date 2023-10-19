/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Post;

import DAO.CategoryDAO;
import DAO.PostDAO;
import DAO.PostTypeDAO;
import Model.Category;
import Model.Post;
import Model.PostStatus;
import Model.PostType;
import Model.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.time.LocalDate;
import jakarta.servlet.http.Part;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.apache.commons.io.FilenameUtils;

/**
 *
 * @author dell
 */
@MultipartConfig
public class CreatePostController extends HttpServlet {

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
            out.println("<title>Servlet CreatePostController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CreatePostController at " + request.getContextPath() + "</h1>");
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
        if (request.getSession().getAttribute("account") == null) {
            request.getSession().setAttribute("msg","Bạn cần đăng nhập để sử dụng tính năng này!");
            response.sendRedirect("home");
        } else {
            CategoryDAO cDao = new CategoryDAO();
            PostTypeDAO ptDao = new PostTypeDAO();

            //get all category post
            ArrayList<Category> categories = cDao.GetAllCategories();

            //get all post type
            ArrayList<PostType> types = ptDao.GetAllPostType();

            request.setAttribute("types", types);
            request.setAttribute("categories", categories);
            request.getRequestDispatcher("/Views/Post/CreatePost.jsp").forward(request, response);
        }
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
        try {
            //lay thong tin tai khoan dang dang nhap
            User account = (User) request.getSession().getAttribute("account");

            //lay thong tin tu client gui ve
            int categoryId = Integer.parseInt(request.getParameter("category"));
            Category category = new Category();
            category.setCategoryId(categoryId);
            
            int typeId = Integer.parseInt(request.getParameter("type"));
            PostType type = new PostType();
            type.setPostTypeId(typeId);

            String title = request.getParameter("title");
            String content = request.getParameter("content");
            boolean isPublic = Boolean.parseBoolean(request.getParameter("isPublic"));
            String addess = request.getParameter("addess");
            
            Date createDate = Date.valueOf(LocalDate.now());
            
            PostStatus status = new PostStatus();
            status.setPostStatusId(1);

            //tao post
            Post post = new Post();
            post.setUser(account);
            post.setCategory(category);
            post.setPostType(type);
            post.setTitle(title);
            post.setContent(content);
            post.setIsPublic(isPublic);
            post.setDatePost(createDate);
            post.setStatus(status);
            post.setAddress(addess);

            //lay file anh client gui len server
            List<Part> fileParts = request.getParts().stream().filter(part -> "file".equals(part.getName())).collect(Collectors.toList());

            //lay ra duong dan luu folder anh
            String realPath = getServletContext().getRealPath("") + File.separator + "img";

            for (Part part : fileParts) {
                //random ten cho image
                UUID uuid = UUID.randomUUID();
                String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
                String fileExtension = FilenameUtils.getExtension(filename);

                if (!Files.exists(Paths.get(realPath))) {
                    Files.createDirectory(Paths.get(realPath));
                }

                filename = uuid + "." + fileExtension;
                part.write(realPath + File.separator + filename);

                String pathImage = "/" + "img" + "/" + filename;

                //them anh vao database
                post.setImg(pathImage);
            }

            PostDAO pDao = new PostDAO();
            pDao.InsertPost(post);
            response.sendRedirect("listPost");
        } catch (Exception e) {
            Logger.getLogger(CreatePostController.class.getName()).log(Level.SEVERE, null, e);
        }
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
