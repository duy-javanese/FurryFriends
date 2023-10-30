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
                        + "Where e.post_id = p.post_id and p.post_type=4 and p.status = 1";
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
                PostDAO pDao = new PostDAO();
                Post post = pDao.GetPostById(rs.getInt("post_id"));
                return new Exchange(rs.getInt("exchange_id"),
                        post,
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
}
