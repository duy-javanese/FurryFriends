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
import Model.PostStatus;
import Model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;

/**
 *
 * @author Admin
 */
public class ExchangeDAO extends DBContext {

    public void insert(Exchange exchange) {
        try {
            String sql = "INSERT INTO [dbo].[exchange] ([post_id], [is_free], [price], [address], [isFinish]) "
                    + "VALUES (?, ?, ?, ?, ?)";

            PreparedStatement stm = connection.prepareStatement(sql);

            stm.setInt(1, exchange.getPost().getPostId()); // Assuming you have a method to get post_id from the Post object.
            stm.setBoolean(2, exchange.isIsFree());
            stm.setDouble(3, exchange.getPrice());
            stm.setString(4, exchange.getAddress());
            stm.setBoolean(5, exchange.isIsFinish());

            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ExchangeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private List<Exchange> pendingExchange;

    public List<Exchange> getPendingExchange() {
        return pendingExchange;
    }

    public void getPendingExchangeList() throws SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBContext.getConnection();
            if (conn != null) {
                String sql = "Select e.exchange_id, p.post_id, e.is_free, e.price, e.address, e.isFinish "
                        + "From post as p, exchange as e "
                        + "Where e.post_id = p.post_id and p.post_type=4 and p.status = 1 and p.deleteFlag=0";
                ptm = conn.prepareStatement(sql);
                rs = ptm.executeQuery();
                while (rs.next()) {

                    int exchangeID = rs.getInt("exchange_id");

                    PostDAO pDao = new PostDAO();
                    Post p = pDao.GetPostById(rs.getInt("post_id"));

                    boolean isFree = rs.getBoolean("is_free");

                    double price = rs.getDouble("price");

                    String address = rs.getString("address");

                    boolean isFinish = rs.getBoolean("isFinish");
                    Exchange dto = new Exchange(exchangeID, p, isFree, price, address, isFinish);

                    if (this.pendingExchange == null) {
                        this.pendingExchange = new ArrayList<>();
                    }//end account list had not initialize
                    this.pendingExchange.add(dto);
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

    public Exchange GetExchangeByPostId(int postId) {
        try {
            String sql = "SELECT *\n"
                    + "  FROM [exchange]\n"
                    + "  Where post_id = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, postId);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return new Exchange(rs.getInt("exchange_id"),
                        null,
                        rs.getBoolean("is_free"),
                        rs.getDouble("price"),
                        rs.getString("address"),
                        rs.getBoolean("isFinish"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ExchangeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void UpdateExchange(Exchange exchange) {
        try {
            String sql = "UPDATE [dbo].[exchange]\n"
                    + "   SET [is_free] = ?\n"
                    + "      ,[price] = ?\n"
                    + "      ,[address] = ?\n"
                    + " WHERE exchange_id = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setBoolean(1, exchange.isIsFree());
            stm.setDouble(2, exchange.getPrice());
            stm.setString(3, exchange.getAddress());
            
            stm.setInt(4, exchange.getExchangeId());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ExchangeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Exchange GetExchangeById(int id) {
        try {
            String sql = "SELECT *\n"
                    + "  FROM [exchange]\n"
                    + "  Where exchange_id = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                
                PostDAO pDao = new PostDAO();
                Post post = pDao.GetPostById(rs.getInt("post_id"));
                
                boolean isFree = rs.getBoolean("is_free");
                
                double price = rs.getDouble("price");
                
                String address = rs.getString("address");
                
                boolean isFinish = rs.getBoolean("isFinish");
                return new Exchange(id, post, isFree, price, address, isFinish);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    private List<Exchange> ExchangeSearchResult;

    public List<Exchange> getExchangeSearchResult() {
        return ExchangeSearchResult;
    }
    
    public void searchExchangeByTitle(String searchValue)
            throws ClassNotFoundException, SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            
            con = DBContext.getConnection();

            if (con != null) {
                
                String sql = "Select e.exchange_id, e.post_id, e.is_free, e.price, e.address, e.isFinish "
                        + "From post as p, exchange as e "
                        + "where p.post_id = e.post_id and p.status = 1 and (p.title like ?);";

                
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchValue + "%");

                
                rs = stm.executeQuery();

                
                while (rs.next()) {
                    
                    int exchangeId = rs.getInt("exchange_id");
                    
                    PostDAO pDao = new PostDAO();
                    Post post = pDao.GetPostById(rs.getInt("post_id"));
                    
                    boolean isFree = rs.getBoolean("is_free");
                
                    double price = rs.getDouble("price");
                
                    String address = rs.getString("address");
                
                    boolean isFinish = rs.getBoolean("isFinish");
                    Exchange dto = new Exchange(exchangeId, post, isFree, price, address, isFinish);
                    
                    if (this.ExchangeSearchResult == null) {
                        this.ExchangeSearchResult = new ArrayList<>();
                    }
                    this.ExchangeSearchResult.add(dto);

                }
            }

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }
    
    public void searchExchangeByTitleAndCategory(String searchValue, int categoryId)
            throws ClassNotFoundException, SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            
            con = DBContext.getConnection();

            if (con != null) {
                
                String sql = "Select e.exchange_id, e.post_id, e.is_free, e.price, e.address, e.isFinish "
                        + "From post as p, exchange as e "
                        + "where p.post_id = e.post_id and p.category_id = ? and p.status = 1 and (p.title like ?);";

                
                stm = con.prepareStatement(sql);
                stm.setInt(1, categoryId);
                stm.setString(2, "%" + searchValue + "%");

                
                rs = stm.executeQuery();

                
                while (rs.next()) {
                    
                    int exchangeId = rs.getInt("exchange_id");
                    
                    PostDAO pDao = new PostDAO();
                    Post post = pDao.GetPostById(rs.getInt("post_id"));
                    
                    boolean isFree = rs.getBoolean("is_free");
                
                    double price = rs.getDouble("price");
                
                    String address = rs.getString("address");
                
                    boolean isFinish = rs.getBoolean("isFinish");
                    Exchange dto = new Exchange(exchangeId, post, isFree, price, address, isFinish);
                    
                    if (this.ExchangeSearchResult == null) {
                        this.ExchangeSearchResult = new ArrayList<>();
                    }
                    this.ExchangeSearchResult.add(dto);

                }
            }

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }
    
    public void searchExchangeByCategoryId(int categoryId)
            throws ClassNotFoundException, SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            
            con = DBContext.getConnection();

            if (con != null) {
                
                String sql = "Select e.exchange_id, e.post_id, e.is_free, e.price, e.address, e.isFinish "
                        + "From post as p, exchange as e "
                        + "where p.post_id = e.post_id and p.category_id = ? and p.status = 1";

                
                stm = con.prepareStatement(sql);
                stm.setInt(1, categoryId);

                
                rs = stm.executeQuery();

                
                while (rs.next()) {
                    
                    int exchangeId = rs.getInt("exchange_id");
                    
                    PostDAO pDao = new PostDAO();
                    Post post = pDao.GetPostById(rs.getInt("post_id"));
                    
                    boolean isFree = rs.getBoolean("is_free");
                
                    double price = rs.getDouble("price");
                
                    String address = rs.getString("address");
                
                    boolean isFinish = rs.getBoolean("isFinish");
                    Exchange dto = new Exchange(exchangeId, post, isFree, price, address, isFinish);
                    
                    if (this.ExchangeSearchResult == null) {
                        this.ExchangeSearchResult = new ArrayList<>();
                    }
                    this.ExchangeSearchResult.add(dto);

                }
            }

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }
    
    public List<Exchange> getExchangeByUserID(int userId) {
        List<Exchange> list = new ArrayList<>();
        try {
            String sql = "select * from post as p, exchange as e where p.post_id = e.post_id and p.userID=? and p.isPublic=1 and p.status=2 and p.deleteFlag=0;";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, userId);
            ResultSet rs = stm.executeQuery();

            ExchangeDAO eDao = new ExchangeDAO();
            while (rs.next()) {
                Exchange e = eDao.GetExchangeById(rs.getInt("exchange_id"));
                list.add(e);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
}
