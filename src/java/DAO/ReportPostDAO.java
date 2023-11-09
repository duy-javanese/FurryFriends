/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dell
 */
public class ReportPostDAO extends DBUtils.DBContext {

    public void InsertReportPost(int userId, int postId, int reportId, String reason, Date date) {
        try {
            String sql = "INSERT INTO [dbo].[report]\n"
                    + "           ([reporter_id]\n"
                    + "           ,[post_id]\n"
                    + "           ,[reason]\n"
                    + "           ,[report_date]\n"
                    + "           ,[report_status]\n"
                    + "           ,[report_content_id])\n"
                    + "           ([reporter_type]\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,'Post')";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, userId);
            stm.setInt(2, postId);
            stm.setString(3, reason);
            stm.setDate(4, date);
            stm.setBoolean(5, true);
            stm.setInt(6, reportId);
            
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ReportPostDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
