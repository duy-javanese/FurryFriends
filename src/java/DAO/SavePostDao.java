/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Post;
import Model.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class SavePostDao extends DBUtils.DBContext {
    public int InsertSave(int userId, int postId) {
        try {
            String sql = "SELECT *\n"
                    + "  FROM [savePost]\n"
                    + "  Where UserId = ? and PostId = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, userId);
            stm.setInt(2, postId);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                sql = "DELETE FROM [dbo].[savePost]\n"
                        + "      WHERE UserId = ?\n"
                        + "	  and PostId = ?";
                stm = connection.prepareStatement(sql);
                stm.setInt(1, userId);
                stm.setInt(2, postId);
                stm.executeUpdate();
                return -1;
            } else {
                sql = "INSERT INTO [dbo].[savePost]\n"
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
    
    public ArrayList<Post> getSavePostByUser(int UserId) {
        ArrayList<Post> list = new ArrayList<>();
        try {
            String sql = "select s.savePostId, s.PostId, s.UserId \n" +
                         "from savePost as s, post as p \n" +
                            "where s.PostId=p.post_id and p.isPublic=1 and p.status=2 and p.deleteFlag=0 and s.UserId= ? ";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, UserId);
            ResultSet rs = stm.executeQuery();

            PostDAO pDao = new PostDAO();
            while (rs.next()) {
                Post p = pDao.GetPostById(rs.getInt("PostId"));
                list.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
}
