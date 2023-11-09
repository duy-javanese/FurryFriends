/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.InformationDAO;
import Model.Information;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.apache.commons.io.FilenameUtils;

/**
 *
 * @author DUY
 */
@WebServlet(name = "UpdateLogoController", urlPatterns = {"/UpdateLogoController"})
@MultipartConfig
public class UpdateLogoController extends HttpServlet {

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
        InformationDAO dao = new InformationDAO();
        Information information = new Information();
        try {
            List<Part> fileParts = request.getParts().stream().filter(part -> "logo".equals(part.getName())).collect(Collectors.toList());
            String realPath = getServletContext().getRealPath("") + "img";
            for (Part part : fileParts) {
                UUID uuid = UUID.randomUUID();
                String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
                String fileExtension = FilenameUtils.getExtension(filename);
                if (checkImage(fileExtension)) {
                    if (!Files.exists(Paths.get(realPath))) {
                        Files.createDirectory(Paths.get(realPath));
                    }
                    filename = uuid + "." + fileExtension;
                    part.write(realPath + File.separator + filename);
                    String pathImage = "img" + "/" + filename;
                    information.setLogoPath(pathImage);
                    boolean check = dao.updateLogo(information);
                    if (check) {
                        url = SUCCESS;
                    }
                } else {
                    request.setAttribute("LOGO_ERROR", "Định dạng logo không hợp lệ!");
                    url = ERROR;
                }
            }
        } catch (Exception e) {
            log("Error at UpdateLogoController: " + e.toString());
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

    private boolean checkImage(String extension) {
        String[] imageExtensions = {"jpg", "jpeg", "png", "gif", "bmp", "tiff", "webp"};
        return Arrays.asList(imageExtensions).contains(extension.toLowerCase());
    }
}
