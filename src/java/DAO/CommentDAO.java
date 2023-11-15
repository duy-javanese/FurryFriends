/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Comment;
import Model.Post;
import Model.User;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dell
 */
public class CommentDAO extends DBUtils.DBContext {

    public void InsertComment(int postId, int userId, String cmt, Date createdDate) {
        try {
            String sql = "INSERT INTO [dbo].[comment]\n"
                    + "           ([post_id]\n"
                    + "           ,[userID]\n"
                    + "           ,[commentValue]\n"
                    + "           ,[createdDate]\n"
                    + "           ,[sta]\n"
                    + "           ,[isParent])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, postId);
            stm.setInt(2, userId);
            stm.setString(3, cmt);
            stm.setDate(4, createdDate);
            stm.setBoolean(5, true);
            stm.setBoolean(6, true);

            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CommentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void InsertCommentReply(int postId, int userId, String cmt, Date createdDate, int cmntId) {
        try {
            String sql = "INSERT INTO [dbo].[comment]\n"
                    + "           ([post_id]\n"
                    + "           ,[userID]\n"
                    + "           ,[commentValue]\n"
                    + "           ,[createdDate]\n"
                    + "           ,[sta]\n"
                    + "           ,[isParent]\n"
                    + "           ,[parentId])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, postId);
            stm.setInt(2, userId);
            stm.setString(3, cmt);
            stm.setDate(4, createdDate);
            stm.setBoolean(5, true);
            stm.setBoolean(6, false);
            stm.setInt(7, cmntId);

            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CommentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Comment GetCommentById(int cmntId) {
        try {
            String sql = "SELECT *\n"
                    + "  FROM [comment]\n"
                    + "  Where comment_id = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, cmntId);

            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                PostDAO pDao = new PostDAO();
                Post post = pDao.GetPostById(rs.getInt("post_id"));

                UserDAO uDao = new UserDAO();
                User user = uDao.GetUserById(rs.getInt("userID"));

                return new Comment(cmntId, post, user, rs.getString("commentValue"), rs.getDate("createdDate"), rs.getBoolean("sta"), rs.getBoolean("isParent"), rs.getInt("parentId"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CommentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<Comment> GetCommentByPostId(int postId) {
        ArrayList<Comment> list = new ArrayList<>();
        CommentDAO cmDao = new CommentDAO();
        try {
            String sql = "SELECT *\n"
                    + "  FROM [comment]\n"
                    + "  Where post_id = ? and isParent = 1";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, postId);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Comment cmt = cmDao.GetCommentById(rs.getInt("comment_id"));
                list.add(cmt);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CommentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public ArrayList<Comment> getCommentChildById(int commentId) {
        ArrayList<Comment> list = new ArrayList<>();
        CommentDAO cmDao = new CommentDAO();
        try {
            String sql = "SELECT *\n"
                    + "  FROM[comment]\n"
                    + "  Where isParent = 0 and parentId = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, commentId);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Comment cmt = cmDao.GetCommentById(rs.getInt("comment_id"));
                list.add(cmt);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CommentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public static void main(String[] args) {
        CommentDAO cDao = new CommentDAO();
        ArrayList<Comment> list = cDao.getCommentChildById(2);
        System.out.println(list.size());
    }
}
