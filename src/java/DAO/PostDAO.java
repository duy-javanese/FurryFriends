/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DBUtils.DBContext;
import Model.Category;
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
public class PostDAO extends DBUtils.DBContext {
    private List<Post> pendingPost;

    public List<Post> getPendingPost() {
        return pendingPost;
    }
    
    public void getPendingPostList() throws SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBContext.getConnection();
            if (conn != null) {
                String sql = "Select * "
                        + "From post "
                        + "Where status = 1";
                ptm = conn.prepareStatement(sql);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    UserDAO uDao = new UserDAO();
                    User user = uDao.GetUserById(rs.getInt("userID"));

                    CategoryDAO cDao = new CategoryDAO();
                    Category category = cDao.GetCategoryById(rs.getInt("category_id"));

                    PostStatusDAO psDao = new PostStatusDAO();
                    PostStatus poststatus = psDao.GetStatusById(rs.getInt("status"));
                    
                    int postId = rs.getInt("post_id");
                    //user
                    //category
                    String title = rs.getString("title");
                    String content = rs.getString("post_content");
                    String img = rs.getString("post_img");
                    String reason = rs.getString("reason");
                    Post dto = new Post(postId, user, category, title, content, img, rs.getDate("datePosted"), reason, poststatus);
                    
                    if (this.pendingPost == null) {
                        this.pendingPost = new ArrayList<>();
                    }//end account list had not initialize
                    this.pendingPost.add(dto);
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
    
    public boolean ApprovePost(int postId, int status) 
            throws SQLException, ClassNotFoundException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;

        try {
            //1. Make connection
            con = DBContext.getConnection();

            if (con != null) {
                    //2. create SQL String
                    String sql = "UPDATE post SET status = ? WHERE post_id = ?";
                    //3. Create statement
                    stm = con.prepareStatement(sql);
                    stm.setInt(1, status);
                    stm.setInt(2, postId);
                    //4. Excute querry to get Result set
                    int effectRow = stm.executeUpdate();
                    //5. Process Result set
                    if (effectRow > 0) {
                        result = true;
                    }
                
                

            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result ;
    }
    
    public boolean DeclinePost(int postId, int status, String declineReason) 
            throws SQLException, ClassNotFoundException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;

        try {
            //1. Make connection
            con = DBContext.getConnection();

            if (con != null) {
                    //2. create SQL String
                    String sql = "UPDATE post SET status = ?, reason = ? WHERE post_id = ?";
                    //3. Create statement
                    stm = con.prepareStatement(sql);
                    stm.setInt(1, status);
                    stm.setString(2, declineReason);
                    stm.setInt(3, postId);
                    //4. Excute querry to get Result set
                    int effectRow = stm.executeUpdate();
                    //5. Process Result set
                    if (effectRow > 0) {
                        result = true;
                    }
                
                

            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result ;
    }
    
}
