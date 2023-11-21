/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DBUtils.DBContext;
import Model.Category;
import Model.Post;
import Model.PostType;
import Model.statisticModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class statisticModelDAO extends DBUtils.DBContext {
    public List<statisticModel> PostStatisticDetail(int year) throws SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        List<statisticModel> statistic = new ArrayList<statisticModel>();
        try {
            conn = DBContext.getConnection();
            if (conn != null) {
                String sql = " select MONTH(datePosted) as Month, count(post_id) as 'NumOfPost' from post where YEAR(datePosted) = ? GROUP BY MONTH(datePosted) ";
                ptm = conn.prepareStatement(sql);
                ptm.setInt(1, year);
                rs = ptm.executeQuery();
                
                while (rs.next()) {
                    int Month = rs.getInt("Month");
                    int quantity = rs.getInt("NumOfPost");
                    
                    statistic.add(new statisticModel(Month, quantity));
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
        return statistic;
    }
    
    public int getPostTypeQuantity(int i) {
        int numofPost = 0; // Initialize with a default value (e.g., -1) in case no records exist.

        try {
            String sql = "select post_type, count(post_type) as 'NumOfPost' from post where post_type= ? group by post_type";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, i);
            ResultSet rs = stm.executeQuery();

            if (rs.next()) {
                numofPost = rs.getInt("NumOfPost");
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return numofPost;
    }
    
    public int getCategoryQuantity(int i) {
        int numofPost = 0; // Initialize with a default value (e.g., -1) in case no records exist.

        try {
            String sql = "select category_id, count(category_id) as 'NumOfPost' from post where category_id = ? group by category_id";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, i);
            ResultSet rs = stm.executeQuery();

            if (rs.next()) {
                numofPost = rs.getInt("NumOfPost");
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return numofPost;
    }
    
    public int getFinishedExchangeQuantity() {
        int numofFinishedExchange = 0; // Initialize with a default value (e.g., -1) in case no records exist.

        try {
            String sql = "select isFinish,count(isFinish) as 'NumOfFinishedExchange' from exchange where isFinish = 1 group by isFinish";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();

            if (rs.next()) {
                numofFinishedExchange = rs.getInt("NumOfFinishedExchange");
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return numofFinishedExchange;
    }
    
    
    public List<statisticModel> PostTypeStatistic() throws SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        List<statisticModel> statistic = new ArrayList<statisticModel>();
        try {
            conn = DBContext.getConnection();
            if (conn != null) {
                String sql = " select post_type, count(post_type) as 'NumOfPost' from post group by post_type ";
                ptm = conn.prepareStatement(sql);
                
                rs = ptm.executeQuery();
                
                while (rs.next()) {
                    PostTypeDAO ptDao = new PostTypeDAO();
                    PostType pt = ptDao.GetTypeById(rs.getInt("post_type"));
                    int quantity = rs.getInt("NumOfPost");
                    
                    statistic.add(new statisticModel(quantity, pt));
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
        return statistic;
    }
    
    public statisticModel GetMostCommentedPost() {
        try {
            String sql = "SELECT TOP 1 post_id, COUNT(comment_id) AS NumberOfComments\n" +
                            "FROM comment\n" +
                            "GROUP BY post_id\n" +
                            "ORDER BY NumberOfComments DESC ;";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                
               PostDAO pDao = new PostDAO();
               Post post = pDao.GetPostById(rs.getInt("post_id"));

               int quantity = rs.getInt("NumberOfComments");
                return new statisticModel(quantity, post);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public statisticModel GetMostInteractedPost() {
        try {
            String sql = "SELECT TOP 1 PostId, SUM(TotalInteractions) AS TotalInteractions\n" +
                            "FROM (\n" +
                            "    SELECT PostId, COUNT(LikePostId) AS TotalInteractions\n" +
                            "    FROM LikePost\n" +
                            "    GROUP BY PostId\n" +
                            "\n" +
                            "    UNION ALL\n" +
                            "\n" +
                            "    SELECT post_id AS PostId, COUNT(comment_id) AS TotalInteractions\n" +
                            "    FROM comment\n" +
                            "    GROUP BY post_id\n" +
                            ") AS CombinedData\n" +
                            "GROUP BY PostId\n" +
                            "ORDER BY TotalInteractions DESC;";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                
               PostDAO pDao = new PostDAO();
               Post post = pDao.GetPostById(rs.getInt("PostId"));

               int quantity = rs.getInt("TotalInteractions");
                return new statisticModel(quantity, post);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public List<statisticModel> PostTypeTotalPerType() throws SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        List<statisticModel> postType = new ArrayList<statisticModel>();
        try {
            conn = DBContext.getConnection();
            if (conn != null) {
                String sql = " select post_type, count(post_type) as 'NumOfPost' from post group by post_type ";
                ptm = conn.prepareStatement(sql);
                
                rs = ptm.executeQuery();
                
                while (rs.next()) {
                    PostTypeDAO ptDao = new PostTypeDAO();
                    PostType pt = ptDao.GetTypeById(rs.getInt("post_type"));
                    int quantity = rs.getInt("NumOfPost");
                    
                    postType.add(new statisticModel(quantity, pt));
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
        return postType;
    }
    
    public List<statisticModel> CategoryTotalPerCate() throws SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        List<statisticModel> categorylist = new ArrayList<statisticModel>();
        try {
            conn = DBContext.getConnection();
            if (conn != null) {
                String sql = " select category_id, count(category_id) as 'NumOfPost' from post where post_type = 4 group by category_id ";
                ptm = conn.prepareStatement(sql);
                
                rs = ptm.executeQuery();
                
                while (rs.next()) {
                    CategoryDAO cDao = new CategoryDAO();
                    Category cate = cDao.GetCategoryById(rs.getInt("category_id"));
                    int quantity = rs.getInt("NumOfPost");
                    
                    categorylist.add(new statisticModel(quantity, cate));
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
        return categorylist;
    }
}
