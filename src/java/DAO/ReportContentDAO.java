/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

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
        try {
            String sql = "SELECT *\n"
                    + "  FROM [ReportContent]\n"
                    + "  Where deleteFlag = 1";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                list.add(new ReportContent(rs.getInt("ReportContentId"), rs.getString("ReportContent"), true));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReportContentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
}
