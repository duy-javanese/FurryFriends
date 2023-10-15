/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Post;
import Model.User;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dell
 */
public class PostDAO extends DBUtils.DBContext {

    public void InsertPost(Post post) {
        try {
            String sql = "INSERT INTO [dbo].[post]\n"
                    + "           ([userID]\n"
                    + "           ,[category_id]\n"
                    + "           ,[title]\n"
                    + "           ,[post_content]\n"
                    + "           ,[post_img]\n"
                    + "           ,[isPublic]\n"
                    + "           ,[datePosted]\n"
                    + "           ,[reason]\n"
                    + "           ,[sta])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, post.getUser().getUserId());
            stm.setInt(2, post.getCategory().getCategoryId());
            stm.setString(3, post.getTitle());
            stm.setString(4, post.getContent());
            stm.setString(5, post.getImg());
            stm.setBoolean(6, post.isIsPublic());
            stm.setDate(7, post.getDatePost());
            stm.setString(8, post.getReason());
            stm.setString(9, post.getSta());
            stm.executeUpdate();
        } catch (SQLException ex) {
            
        }
    }
    
}
