/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.PostType;
import Model.ReportContent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dell
 */
public class ReportContentDAO extends DBUtils.DBContext {

    public ArrayList<ReportContent> GetListReportContent() {
        ArrayList<ReportContent> list = new ArrayList<>();
        PostTypeDAO ptDao = new PostTypeDAO();
        PostType pt = new PostType();
        try {
            String sql = "SELECT *\n"
                    + "  FROM [reportContent]\n"
                    + "  Where deleteFlag = 0";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                int reportContentId = rs.getInt("reportContent_id");
                String reportContent = rs.getString("reportContent");
                pt = ptDao.GetTypeById(rs.getInt("post_Type"));
                boolean deleteFlag = rs.getBoolean("deleteFlag");
                list.add(new ReportContent(reportContentId, reportContent, pt, deleteFlag));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReportContentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public ReportContent GetReportContentById(int id) {
        try {
            String sql = "SELECT *\n"
                    + "  FROM [reportContent]\n"
                    + "  Where ReportContent_id = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                
                String reportContent = rs.getString("reportContent");
                
                PostTypeDAO ptDao = new PostTypeDAO();
                PostType pt = ptDao.GetTypeById(rs.getInt("post_Type"));
                
                boolean deleteFlag = rs.getBoolean("deleteFlag");

                return new ReportContent(id, reportContent, pt, deleteFlag);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
