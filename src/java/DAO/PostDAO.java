/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DBUtils.DBContext;
import Model.Category;
import Model.Constant;
import Model.Exchange;
import Model.Post;
import Model.PostStatus;
import Model.PostType;
import Model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;

/**
 *
 * @author dell
 */
public class PostDAO extends DBUtils.DBContext {

    public Post GetPostById(int id) {
        try {
            String sql = "SELECT *\n"
                    + "  FROM [post]\n"
                    + "  Where post_id = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                UserDAO uDao = new UserDAO();
                User user = uDao.GetUserById(rs.getInt("userID"));

                CategoryDAO cDao = new CategoryDAO();
                Category category = cDao.GetCategoryById(rs.getInt("category_id"));

                PostTypeDAO ptDao = new PostTypeDAO();
                PostType postType = ptDao.GetTypeById(rs.getInt("post_type"));

                PostStatusDAO psDao = new PostStatusDAO();
                PostStatus status = psDao.GetStatusById(rs.getInt("status"));

                return new Post(id,
                        user,
                        category,
                        postType,
                        rs.getString("title"),
                        rs.getString("post_content"),
                        rs.getString("post_img"),
                        rs.getBoolean("isPublic"),
                        rs.getDate("datePosted"),
                        rs.getString("reason"),
                        status);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void InsertPost(Post post) {
        try {
            String sql = "INSERT INTO [dbo].[post]\n"
                    + "           ([userID]\n"
                    + "           ,[post_type]\n"
                    + "           ,[title]\n"
                    + "           ,[post_content]\n"
                    + "           ,[post_img]\n"
                    + "           ,[isPublic]\n"
                    + "           ,[datePosted]\n"
                    + "           ,[reason]\n"
                    + "           ,[status])\n"
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
            stm.setInt(2, post.getPostType().getPostTypeId());
            stm.setString(3, post.getTitle());
            stm.setString(4, post.getContent());
            stm.setString(5, post.getImg());
            stm.setBoolean(6, post.isIsPublic());
            stm.setDate(7, post.getDatePost());
            stm.setString(8, post.getReason());
            stm.setInt(9, post.getStatus().getPostStatusId());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void InsertPostExchange(Post post) {
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
                    + "           ,[status])\n"
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
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Post> GetPostByUser(int offset, int recordsPerPage,
            int userId, String textSearch, int categoryId, int typeId, int status, int isPublic) {
        ArrayList<Post> list = new ArrayList<>();
        try {
            int count = 0;
            String sql = "SELECT *\n"
                    + "  FROM [post]\n"
                    + "  Where userId = ?\n";
            HashMap<Integer, Object> setter = new HashMap<>();
            //find by user id
            setter.put(++count, userId);

            if (!textSearch.isEmpty() && !textSearch.equalsIgnoreCase("")) {
                textSearch = "%" + textSearch + "%";
                sql += " and title like ?\n";
                setter.put(++count, textSearch);
            }

            if (categoryId != -1) {
                sql += " and category_id = ?\n";
                setter.put(++count, categoryId);
            }

            if (typeId != -1) {
                sql += " and post_type = ?\n";
                setter.put(++count, typeId);
            }

            if (status != -1) {
                sql += " and status = ?\n";
                setter.put(++count, status);
            }

            if (isPublic != -1) {
                sql += " and isPublic = ?";
                setter.put(++count, isPublic);
            }

            sql += " order by post_id\n";
            sql += "  offset ? ROW\n"
                    + "  FETCH Next ? Rows only";
            setter.put(++count, offset);
            setter.put(++count, recordsPerPage);

            PreparedStatement stm = connection.prepareStatement(sql);
            for (Map.Entry<Integer, Object> entry : setter.entrySet()) {
                stm.setObject(entry.getKey(), entry.getValue());
            }
            ResultSet rs = stm.executeQuery();

            Post p;
            while (rs.next()) {
                p = new Post();
                p = GetPostById(rs.getInt("post_id"));
                list.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public int GetNoOfRecordsPostByUser(int userId, String textSearch, int categoryId, int typeId, int status, int isPublic) {
        try {
            int count = 0;
            String sql = "SELECT count(*) as total\n"
                    + "  FROM [post]\n"
                    + "  Where isPublic = ?\n"
                    + "  and userId = ?\n";
            HashMap<Integer, Object> setter = new HashMap<>();
            setter.put(++count, Constant.PostPublic);
            //find by user id
            setter.put(++count, userId);

            if (!textSearch.isEmpty() && !textSearch.equalsIgnoreCase("")) {
                textSearch = "%" + textSearch + "%";
                sql += " and title like ?\n";
                setter.put(++count, textSearch);
            }

            if (categoryId != -1) {
                sql += " and category_id = ?\n";
                setter.put(++count, categoryId);
            }

            if (typeId != -1) {
                sql += " and post_type = ?\n";
                setter.put(++count, typeId);
            }

            if (status != -1) {
                sql += " and status = ?\n";
                setter.put(++count, status);
            }

            if (isPublic != -1) {
                sql += " and isPublic = ?";
                setter.put(++count, isPublic);
            }

            PreparedStatement stm = connection.prepareStatement(sql);
            for (Map.Entry<Integer, Object> entry : setter.entrySet()) {
                stm.setObject(entry.getKey(), entry.getValue());
            }
            ResultSet rs = stm.executeQuery();

            if (rs.next()) {
                return rs.getInt("total");
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public static void main(String[] args) {
        PostDAO pDao = new PostDAO();
        Post p = pDao.GetPostById(7);
        ArrayList<Post> list = pDao.GetPostPagnition(0, 3);
        System.out.println(list.size());
        System.out.println(p.getContent());
        pDao.InsertPost(p);
    }

    public ArrayList<Post> GetPostPagnition(int offset, int recordsPerPage) {
        ArrayList<Post> list = new ArrayList<>();
        try {
            int count = 0;
            String sql = "SELECT *\n"
                    + "  FROM [post]\n"
                    + "  Where isPublic = ? and status = ?\n";
            HashMap<Integer, Object> setter = new HashMap<>();
            setter.put(++count, Constant.PostPublic);
            setter.put(++count, Constant.StatusPostAccept);

            sql += " order by post_id\n";
            sql += "  offset ? ROW\n"
                    + "  FETCH Next ? Rows only";
            setter.put(++count, offset);
            setter.put(++count, recordsPerPage);

            PreparedStatement stm = connection.prepareStatement(sql);
            for (Map.Entry<Integer, Object> entry : setter.entrySet()) {
                stm.setObject(entry.getKey(), entry.getValue());
            }
            ResultSet rs = stm.executeQuery();

            Post p;
            while (rs.next()) {
                p = new Post();
                p = GetPostById(rs.getInt("post_id"));
                list.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public int GetNoOfRecordsPost() {
        try {
            int count = 0;
            String sql = "SELECT count(*) as total\n"
                    + "  FROM [post]\n"
                    + "  Where isPublic = ? and status = ?\n";
            HashMap<Integer, Object> setter = new HashMap<>();
            setter.put(++count, Constant.PostPublic);
            setter.put(++count, Constant.StatusPostAccept);

            PreparedStatement stm = connection.prepareStatement(sql);
            for (Map.Entry<Integer, Object> entry : setter.entrySet()) {
                stm.setObject(entry.getKey(), entry.getValue());
            }
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt("total");
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

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
                        + "Where status = 1 AND (post_type = 1 or post_type = 2 or post_type =3 )";
                ptm = conn.prepareStatement(sql);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    UserDAO uDao = new UserDAO();
                    User user = uDao.GetUserById(rs.getInt("userID"));

                    PostTypeDAO ptDao = new PostTypeDAO();
                    PostType posttype = ptDao.GetTypeById(rs.getInt("post_type"));

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
                    Post dto = new Post(postId, user, category, title, content, img, rs.getDate("datePosted"), reason, poststatus, posttype);

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
        return result;
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
        return result;
    }

    public int GetLastPost() {
        int maxPostId = -1; // Initialize with a default value (e.g., -1) in case no records exist.

        try {
            String sql = "SELECT MAX(post_id) FROM post";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();

            if (rs.next()) {
                maxPostId = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return maxPostId;
    }

    public void UpdatePostExchange(Post post) {
        try {
            String sql = "UPDATE [dbo].[post]\n"
                    + "   SET [post_type] = ?\n"
                    + "      ,[title] = ?\n"
                    + "      ,[post_content] = ?\n"
                    + "      ,[post_img] = ?\n"
                    + " WHERE post_id = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, post.getPostType().getPostTypeId());
            stm.setString(2, post.getTitle());
            stm.setString(3, post.getContent());
            stm.setString(4, post.getImg());
            stm.setInt(5, post.getPostId());

            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void UpdatePostBlog(Post post) {
        try {
            String sql = "UPDATE [dbo].[post]\n"
                    + "   SET [post_type] = ?\n"
                    + "      ,[title] = ?\n"
                    + "      ,[post_content] = ?\n"
                    + "      ,[post_img] = ?\n"
                    + " WHERE post_id = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, post.getPostType().getPostTypeId());
            stm.setString(2, post.getTitle());
            stm.setString(3, post.getContent());
            stm.setString(4, post.getImg());
            stm.setInt(5, post.getPostId());

            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
