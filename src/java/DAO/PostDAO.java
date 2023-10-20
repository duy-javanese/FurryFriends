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
                        + "From users "
                        + "Where status = 2";
                ptm = conn.prepareStatement(sql);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    UserDAO uDao = new UserDAO();
                    User user = uDao.GetUserById(rs.getInt("userID"));

                    CategoryDAO cDao = new CategoryDAO();
                    Category category = cDao.GetCategoryById(rs.getInt("category_id"));

                    PostStatusDAO psDao = new PostStatusDAO();
                    PostStatus poststatus = psDao.GetStatusById(2);
                    
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
}
