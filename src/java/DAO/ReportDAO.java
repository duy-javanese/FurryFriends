/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DBUtils.DBContext;
import Model.Category;
import Model.Exchange;
import Model.Post;
import Model.Report;
import Model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class ReportDAO {
    
    private List<Report> ReportedPostList;

    public List<Report> getReportedPostList() {
        return ReportedPostList;
    }

    
    
    
    
    public void getReportedPost() throws SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBContext.getConnection();
            if (conn != null) {
                String sql = "Select * "
                        + "From report "
                        + "Where report_status = 0 AND report_type='Post' ";
                ptm = conn.prepareStatement(sql);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    int reportId = rs.getInt("report_id");
                    
                    UserDAO uDao = new UserDAO();
                    User reporter = uDao.GetUserById(rs.getInt("reporter_id"));
                    
                    PostDAO pDao = new PostDAO();
                    Post post = pDao.GetPostById(rs.getInt("post_id"));
                    
                    String reportType = rs.getString("report_type");
                    
                    String reason = rs.getString("reason");
                    
                    //Date
                    
                    boolean reportStatus = rs.getBoolean("report_status");
                    
                    Report dto = new Report(reportId, reporter, reportType, post, reason, rs.getDate("report_date"), reportStatus);
                    
                    
                    if (this.ReportedPostList == null) {
                        this.ReportedPostList = new ArrayList<>();
                    }//end account list had not initialize
                    this.ReportedPostList.add(dto);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }
    
    private List<Report> ReportedExchangeList;

    public List<Report> getReportedExchangeList() {
        return ReportedExchangeList;
    }
    
    
    
    public void getReportedExchange() throws SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBContext.getConnection();
            if (conn != null) {
                String sql = "Select * "
                        + "From report "
                        + "Where report_status = 0 AND report_type='Exchange' ";
                ptm = conn.prepareStatement(sql);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    int reportId = rs.getInt("report_id");
                    
                    UserDAO uDao = new UserDAO();
                    User reporter = uDao.GetUserById(rs.getInt("reporter_id"));
                    
                    ExchangeDAO eDao = new ExchangeDAO();
                    Exchange exchangeId = eDao.GetExchangeById(rs.getShort("exchange_id"));
                    
                    String reason = rs.getString("reason");
                    
                    String reportType = rs.getString("report_type");
                    //Date
                    
                    boolean reportStatus = rs.getBoolean("report_status");
                    
                    Report dto = new Report(reportId, reporter, reportType, exchangeId, reason, rs.getDate("report_date"), reportStatus);
                    
                    
                    if (this.ReportedExchangeList == null) {
                        this.ReportedExchangeList = new ArrayList<>();
                    }//end account list had not initialize
                    this.ReportedExchangeList.add(dto);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }
}
