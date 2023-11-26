/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DBUtils.DBContext;
import Model.Category;
import Model.Comment;
import Model.Exchange;
import Model.Post;
import Model.Report;
import Model.ReportContent;
import Model.User;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class ReportDAO extends DBUtils.DBContext {
    
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
                String sql = "select *\n" +
                            "from report as r, post as p\n" +
                            "where r.post_id = p.post_id and p.post_type!=4 and p.isPublic=1 and p.status=2 and p.deleteFlag=0 and r.report_status=1;";
                ptm = conn.prepareStatement(sql);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    int reportId = rs.getInt("report_id");
                    
                    UserDAO uDao = new UserDAO();
                    User reporter = uDao.GetUserById(rs.getInt("reporter_id"));
                    
                    PostDAO pDao = new PostDAO();
                    Post post = pDao.GetPostById(rs.getInt("post_id"));
                    
                    String reason = rs.getString("reason");
                    
                    //Date
                    
                    int reportStatus = rs.getInt("report_status");
                    
                    ReportContentDAO rcDao = new ReportContentDAO();
                    ReportContent reportContent = rcDao.GetReportContentById(rs.getInt("reportContent_id"));
                    
                    Report dto = new Report(reportId, reporter, post, reason, rs.getDate("report_date"), reportStatus, reportContent);
                    
                    
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
                String sql = "select *\n" +
                            "from report as r, post as p, exchange as e\n" +
                            "where r.post_id = p.post_id and e.post_id = p.post_id and p.isPublic=1 and p.status=2 and p.deleteFlag=0 and r.report_status=1;";
                ptm = conn.prepareStatement(sql);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    int reportId = rs.getInt("report_id");
                    
                    UserDAO uDao = new UserDAO();
                    User reporter = uDao.GetUserById(rs.getInt("reporter_id"));
                    
                    ExchangeDAO eDao = new ExchangeDAO();
                    Exchange exchangeId = eDao.GetExchangeById(rs.getShort("exchange_id"));
                    
                    String reason = rs.getString("reason");
                    
                    //Date
                    
                    int reportStatus = rs.getInt("report_status");
                    
                    ReportContentDAO rcDao = new ReportContentDAO();
                    ReportContent reportContent = rcDao.GetReportContentById(rs.getInt("reportContent_id"));
                    
                    Report dto = new Report(reportId, reporter, reason, rs.getDate("report_date"), reportStatus, reportContent, exchangeId);
                    
                    
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
                String sql = "UPDATE report SET report_status = 0 WHERE report_id = ?";
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
    
    public Report GetReportPostById(int id) {
        try {
            String sql = "SELECT *\n"
                    + "  FROM [report]\n"
                    + "  Where report_id = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                UserDAO uDao = new UserDAO();
                User user = uDao.GetUserById(rs.getInt("reporter_id"));

                PostDAO pDao = new PostDAO();
                Post post = pDao.GetPostById(rs.getInt("post_id"));
                
                String reason = rs.getString("reason");
                
                int reportStatus = rs.getInt("report_status");
                
                ReportContentDAO rcDao = new ReportContentDAO();
                ReportContent reportcontent = rcDao.GetReportContentById(rs.getInt("reportContent_id"));
                
                return new Report(id, user, post, reason, rs.getDate("report_date"), reportStatus, reportcontent);
               
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public void InsertReportPost(int userId, int postId, int reportContentId, String reason, Date date) {
        try {
            String sql = "INSERT INTO [dbo].[report]\n"
                    + "           ([reporter_id]\n"
                    + "           ,[post_id]\n"
                    + "           ,[reason]\n"
                    + "           ,[report_date]\n"
                    + "           ,[report_status]\n"
                    + "           ,[reportContent_id])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, userId);
            stm.setInt(2, postId);
            stm.setString(3, reason);
            stm.setDate(4, date);
            stm.setInt(5, 1);
            stm.setInt(6, reportContentId);
            
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ReportPostDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int getTotalReportedExchange() {
        int total = 0; // Initialize with a default value (e.g., -1) in case no records exist.

        try {
            String sql = "select count(r.report_id) as total\n" +
                        "from report as r, post as p, exchange as e\n" +
                        "where r.post_id = p.post_id and e.post_id = p.post_id and p.isPublic=1 and p.status=2 and p.deleteFlag=0 and r.report_status=1;";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();

            if (rs.next()) {
                total = rs.getInt("total");
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return total;
    }
    
    public int getTotalReportedPost() {
        int total = 0; // Initialize with a default value (e.g., -1) in case no records exist.

        try {
            String sql = "select count(r.report_id) as total\n" +
                        "from report as r, post as p\n" +
                        "where r.post_id = p.post_id and p.post_type!=4 and p.isPublic=1 and p.status=2 and p.deleteFlag=0 and r.report_status=1;";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();

            if (rs.next()) {
                total = rs.getInt("total");
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return total;
    }
    
    public Report GetReportExchangeById(int id) {
        try {
            String sql = "SELECT *\n"
                    + "  FROM [report]\n"
                    + "  Where report_id = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                UserDAO uDao = new UserDAO();
                User user = uDao.GetUserById(rs.getInt("reporter_id"));

                ExchangeDAO eDao = new ExchangeDAO();
                Exchange exchange = eDao.usePostIdToGetExchange(rs.getInt("post_id"));
                
                String reason = rs.getString("reason");
                
                int reportStatus = rs.getInt("report_status");
                
                ReportContentDAO rcDao = new ReportContentDAO();
                ReportContent reportcontent = rcDao.GetReportContentById(rs.getInt("reportContent_id"));
                
                return new Report(id, user, reason, rs.getDate("report_date"), reportStatus, reportcontent, exchange);
               
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public boolean UpdateReportResult(int reportId, int reportStatus, int staffId, Date processDate)
            throws SQLException, ClassNotFoundException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;

        try {
            //1. Make connection
            con = DBContext.getConnection();

            if (con != null) {
                //2. create SQL String
                String sql = "UPDATE report SET report_status = ?, process_staff_id = ?, process_date = ? WHERE report_id = ?";
                //3. Create statement
                stm = con.prepareStatement(sql);
                stm.setInt(1, reportStatus);
                stm.setInt(2, staffId);
                stm.setDate(3, processDate);
                stm.setInt(4, reportId);
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
    
    public List<Report> getProcessReportHistory() {
        List<Report> list = new ArrayList<>();
        try {
            String sql = "select * from report where report_status != 1";

            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();

            PostDAO pDao = new PostDAO();
            UserDAO uDao = new UserDAO();
            ReportContentDAO rcDao = new ReportContentDAO();
            while (rs.next()) {
                int report_id = rs.getInt("report_id");
                
                User reporter = uDao.GetUserById(rs.getInt("reporter_id"));
                
                Post post = pDao.GetPostById(rs.getInt("post_id"));
                
                String reason = rs.getString("reason");
                
                //reprortDate
                
                int reportStatus = rs.getInt("report_status");
                
                ReportContent reportContent = rcDao.GetReportContentById(rs.getInt("reportContent_id"));
                
                User staff = uDao.GetUserById(rs.getInt("process_staff_id"));
                
                //processDate
                
                list.add(new Report(report_id, reporter, post, reason, rs.getDate("report_date"), reportStatus, reportContent, rs.getDate("process_date"), staff));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
}
