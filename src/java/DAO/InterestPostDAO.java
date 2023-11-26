/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dell
 */
public class InterestPostDAO extends DBUtils.DBContext {

    public int Insert(int userId, int postId) {
        try {
            String sql = "SELECT *\n"
                    + "  FROM [InterestPost]\n"
                    + "  Where UserId = ? and PostId = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, userId);
            stm.setInt(2, postId);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                sql = "DELETE FROM [dbo].[InterestPost]\n"
                        + "      WHERE UserId = ? and PostId = ?";
                stm = connection.prepareStatement(sql);
                stm.setInt(1, userId);
                stm.setInt(2, postId);
                stm.executeUpdate();
                return -1;
            } else {
                sql = "INSERT INTO [dbo].[InterestPost]\n"
                        + "           ([UserId]\n"
                        + "           ,[PostId]\n"
                        + "           ,[DateTime]\n"
                        + "           ,[ExchangeStatus]\n"
                        + "           ,[isRated])\n"
                        + "     VALUES\n"
                        + "           (?\n"
                        + "           ,?\n"
                        + "           ,?\n"
                        + "           ,1\n"
                        + "           ,0)";
                stm = connection.prepareStatement(sql);
                stm.setInt(1, userId);
                stm.setInt(2, postId);
                stm.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
                stm.executeUpdate();
                return 1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(LikePostDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public void updateExchangeStatus(int postId, int userId, int exchangeStatus) {
        try {
            String sql = "UPDATE [dbo].[InterestPost]\n"
                    + "   SET [ExchangeStatus] = ?\n"
                    + " WHERE UserId = ? and PostId = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, exchangeStatus);
            stm.setInt(2, userId);
            stm.setInt(3, postId);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ExchangeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void UpdateRateStatus(boolean isRated, int interestedPostId) {
        try {
            String sql = "UPDATE [dbo].[InterestPost]\n"
                    + "   SET [isRated] = ?\n"
                    + " WHERE IntersestPost = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setBoolean(1, isRated);
            stm.setInt(2, interestedPostId);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ExchangeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
