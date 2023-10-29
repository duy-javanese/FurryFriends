/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Post;

import DAO.PostDAO;
import Model.Constant;
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
import jakarta.servlet.http.Part;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Date;
import java.time.LocalDate;
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
public class SaveEditBlogController extends HttpServlet {

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
        try {

            int postId = Integer.parseInt(request.getParameter("postId"));

            int typeId = Integer.parseInt(request.getParameter("type"));
            PostType type = new PostType();
            type.setPostTypeId(typeId);

            String title = request.getParameter("title");
            String content = request.getParameter("content");

            Date createDate = Date.valueOf(LocalDate.now());

            PostStatus status = new PostStatus();
            status.setPostStatusId(Constant.StatusPostPending);

            String img = request.getParameter("existImg");

            //tao post
            Post post = new Post();
            post.setPostId(postId);
            post.setPostType(type);
            post.setTitle(title);
            post.setContent(content);
            post.setIsPublic(true);
            post.setDatePost(createDate);
            post.setStatus(status);
            post.setImg(img);

            //lay file anh client gui len server
            List<Part> fileParts = request.getParts().stream()
                    .filter(part -> "file".equals(part.getName()) && part.getSize() > 0)
                    .collect(Collectors.toList());

            if (!fileParts.isEmpty()) {
                //lay ra duong dan luu folder anh
                String realPath = getServletContext().getRealPath("") + "img";

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
                    String pathImage = "img" + "/" + filename;

                    //them anh vao database
                    post.setImg(pathImage);
                }
            }
            //insert post to DB
            PostDAO pDao = new PostDAO();
            pDao.UpdatePostBlog(post);

            response.sendRedirect("listPost");
        } catch (Exception e) {
            Logger.getLogger(CreatePostController.class.getName()).log(Level.SEVERE, null, e);
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
