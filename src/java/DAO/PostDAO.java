/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Post;
import Model.User;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
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
                    + "           ,[post_type]\n"
                    + "           ,[title]\n"
                    + "           ,[post_content]\n"
                    + "           ,[post_img]\n"
                    + "           ,[isPublic]\n"
                    + "           ,[datePosted]\n"
                    + "           ,[reason]\n"
                    + "           ,[status]\n"
                    + "           ,[address])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,?\n"
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
            stm.setInt(3, post.getPostType().getPostTypeId());
            stm.setString(4, post.getTitle());
            stm.setString(5, post.getContent());
            stm.setString(6, post.getImg());
            stm.setBoolean(7, post.isIsPublic());
            stm.setDate(8, post.getDatePost());
            stm.setString(9, post.getReason());
            stm.setInt(10, post.getStatus().getPostStatusId());
            stm.setString(11, post.getAddress());
            stm.executeUpdate();
        } catch (SQLException ex) {

        }
    }

    public ArrayList<Post> GetPostByUser(int userId, String textSearch, int categoryId, int typeId, int status, int aPublic) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public int GetNoOfRecordsPostByUser(int userId, String textSearch, int categoryId, int typeId, int status, int aPublic) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
