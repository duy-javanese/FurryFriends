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
import javax.naming.NamingException;

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
    
    public boolean ReportIsFinish(int reportId)
            throws SQLException, ClassNotFoundException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;

        try {
            //1. Make connection
            con = DBContext.getConnection();

            if (con != null) {
                //2. create SQL String
                String sql = "UPDATE report SET report_status = 1 WHERE report_id = ?";
                //3. Create statement
                stm = con.prepareStatement(sql);
                stm.setInt(1, reportId);
                //4. Excute querry to get Result set
                int effectRow = stm.executeUpdate();
                //5. Process Result set
                if (effectRow > 0) {
                    result = true;
                }

            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;
    }
}
