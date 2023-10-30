/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dell
 */
public class InterestPostDAO extends DBUtils.DBContext {

    public void Insert(int userId, int postId) {
        try {
            String sql = "INSERT INTO [dbo].[InterestPost]\n"
                    + "           ([UserId]\n"
                    + "           ,[PostId])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, userId);
            stm.setInt(2, postId);

            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(LikePostDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
