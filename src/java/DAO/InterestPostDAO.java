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

}