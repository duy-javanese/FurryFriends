/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dell
 */
public class LikePostDAO extends DBUtils.DBContext {

    public int InsertLike(int userId, int postId) {
        try {
            String sql = "SELECT *\n"
                    + "  FROM [LikePost]\n"
                    + "  Where UserId = ? and PostId = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, userId);
            stm.setInt(2, postId);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                sql = "DELETE FROM [dbo].[LikePost]\n"
                        + "      WHERE UserId = ?\n"
                        + "	  and PostId = ?";
                stm = connection.prepareStatement(sql);
                stm.setInt(1, userId);
                stm.setInt(2, postId);
                stm.executeUpdate();
                return -1;
            } else {
                sql = "INSERT INTO [dbo].[LikePost]\n"
                        + "           ([UserId]\n"
                        + "           ,[PostId])\n"
                        + "     VALUES\n"
                        + "           (?\n"
                        + "           ,?)";
                stm = connection.prepareStatement(sql);
                stm.setInt(1, userId);
                stm.setInt(2, postId);
                stm.executeUpdate();
                return 1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(LikePostDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public int GetTotalLikePost(int postId) {
        try {
            String sql = "SELECT Count(*)\n"
                    + "  FROM [LikePost]\n"
                    + "  Where PostId = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, postId);

            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LikePostDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

}
