/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Post;

import DAO.CategoryDAO;
import DAO.ExchangeDAO;
import DAO.PostDAO;
import DAO.PostTypeDAO;
import Model.Category;
import Model.Constant;
import Model.Exchange;
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
public class CreateExchangeController extends HttpServlet {

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
            out.println("<title>Servlet CreateExchangeController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CreateExchangeController at " + request.getContextPath() + "</h1>");
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
            request.getSession().setAttribute("msg", "Bạn cần đăng nhập để sử dụng tính năng này!");
            response.sendRedirect("home");
        } else {
            CategoryDAO cDao = new CategoryDAO();
            PostTypeDAO ptDao = new PostTypeDAO();

            //get all category post
            ArrayList<Category> categories = cDao.GetAllCategories();

            //get all post type
            ArrayList<PostType> types = ptDao.GetAllPostType();

            request.setAttribute("PostExchange", Constant.PostExchange);
            request.setAttribute("categories", categories);
            request.getRequestDispatcher("/Views/Post/CreateExchange.jsp").forward(request, response);
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

            Category category = new Category();
            category.setCategoryId(Integer.parseInt(request.getParameter("category")));

            PostType type = new PostType();
            type.setPostTypeId(Constant.PostExchange);

            String title = request.getParameter("title");
            String content = request.getParameter("content");

            Date createDate = Date.valueOf(LocalDate.now());

            PostStatus status = new PostStatus();
            status.setPostStatusId(Constant.StatusPostPending);

            boolean isFree = false;
            double price = Double.parseDouble(request.getParameter("price"));
            if (price == 0) {
                isFree = true;
            }
            String address = request.getParameter("address");

            Exchange exchange = new Exchange();
            exchange.setIsFree(isFree);
            exchange.setAddress(address);
            exchange.setPrice(price);
            exchange.setIsFinish(false);

            //tao post
            Post post = new Post();
            post.setUser(account);
            post.setCategory(category);
            post.setPostType(type);
            post.setTitle(title);
            post.setContent(content);
            post.setIsPublic(true);
            post.setDatePost(createDate);
            post.setStatus(status);

            response.getWriter().print("oke");
            //lay file anh client gui len server
            List<Part> fileParts = request.getParts().stream().filter(part -> "file".equals(part.getName())).collect(Collectors.toList());

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
            PostDAO pDao = new PostDAO();
            pDao.InsertPostExchange(post);
            int lastPost = pDao.GetLastPost();
            post.setPostId(lastPost);
            exchange.setPost(post);
            ExchangeDAO eDao = new ExchangeDAO();
            eDao.insert(exchange);

            response.sendRedirect("listPost");
        } catch (Exception e) {
            Logger.getLogger(CreateExchangeController.class.getName()).log(Level.SEVERE, null, e);
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

    public static void main(String[] args) {
        //tao post
        User account = new User();
        account.setUserId(3);

        Category category = new Category();
        category.setCategoryId(1);

        PostType type = new PostType();
        type.setPostTypeId(1);

        Date createDate = Date.valueOf(LocalDate.now());

        PostStatus status = new PostStatus();
        status.setPostStatusId(Constant.StatusPostPending);

        Post post = new Post();
        post.setUser(account);
        post.setCategory(category);
        post.setPostType(type);
        post.setTitle("Test");
        post.setContent("Test");
        post.setIsPublic(true);
        post.setDatePost(createDate);
        post.setStatus(status);
        post.setImg("oke");

        PostDAO pDao = new PostDAO();
        pDao.InsertPostExchange(post);
    }

}
