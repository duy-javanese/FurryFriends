/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DBUtils.DBContext;
import Model.Category;
import Model.Constant;
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

    public ArrayList<Post> GetPostByUserInsterested(int offset, int recordsPerPage,
            int userId, String textSearch, int categoryId, int typeId, int status, int isPublic) {
        ArrayList<Post> list = new ArrayList<>();
        try {
            int count = 0;
            String sql = "SELECT DISTINCT ipost.PostId\n"
                    + "  FROM [InterestPost] ipost\n"
                    + "  left join post p\n"
                    + "  on p.post_id = ipost.PostId\n"
                    + "  Where p.userID = ? and ipost.UserId != ?";
            HashMap<Integer, Object> setter = new HashMap<>();
            //find by user id
            setter.put(++count, userId);
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

            sql += " order by ipost.PostId\n";
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
                p = GetPostById(rs.getInt("PostId"));
                list.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public int GetNoOfRecordsPostByUserInsterested(int userId, String textSearch, int categoryId, int typeId, int status, int isPublic) {
        try {
            int count = 0;
            String sql = "SELECT Count(DISTINCT ipost.PostId)\n"
                    + "  FROM [InterestPost] ipost\n"
                    + "  left join post p\n"
                    + "  on p.post_id = ipost.PostId\n"
                    + "  Where p.userID = ? and ipost.UserId != ?\n";
            HashMap<Integer, Object> setter = new HashMap<>();
            //find by user id
            setter.put(++count, userId);
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
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public static void main(String[] args) {
        PostDAO pDao = new PostDAO();
        Post p = pDao.GetPostById(7);
        ArrayList<Post> list = pDao.GetPostPagnition(0, 3, -1, -1);
        System.out.println(list.size());
        System.out.println(p.getContent());
        pDao.InsertPost(p);
    }

    public ArrayList<Post> GetPostPagnition(int offset, int recordsPerPage, int postType, int category) {
        ArrayList<Post> list = new ArrayList<>();
        try {
            int count = 0;
            String sql = "SELECT *\n"
                    + "  FROM [post]\n"
                    + "  Where isPublic = ? and status = ?\n";
            HashMap<Integer, Object> setter = new HashMap<>();
            setter.put(++count, Constant.PostPublic);
            setter.put(++count, Constant.StatusPostAccept);

            if (postType != -1) {
                sql += " and post_type = ?\n";
                setter.put(++count, postType);
            }
            if (category != -1) {
                sql += " and category_id = ?\n";
                setter.put(++count, category);
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

    public int GetNoOfRecordsPost(int postType, int category) {
        try {
            int count = 0;
            String sql = "SELECT count(*) as total\n"
                    + "  FROM [post]\n"
                    + "  Where isPublic = ? and status = ?\n";
            HashMap<Integer, Object> setter = new HashMap<>();
            setter.put(++count, Constant.PostPublic);
            setter.put(++count, Constant.StatusPostAccept);

            if (postType != -1) {
                sql += " and post_type = ?\n";
                setter.put(++count, postType);
            }
            if (category != -1) {
                sql += " and category_id = ?\n";
                setter.put(++count, category);
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

    public int GetNoOfRecordsPostByUser(int userId) {
        int total = -1; // Initialize with a default value (e.g., -1) in case no records exist.
        try {
            String sql = "SELECT count(*)\n"
                    + "  FROM [post]\n"
                    + "  Where userID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, userId);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return total;
    }

    public boolean HideReportedPost(int postId)
            throws SQLException, ClassNotFoundException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;

        try {
            //1. Make connection
            con = DBContext.getConnection();

            if (con != null) {
                //2. create SQL String
                String sql = "UPDATE post SET status = 4 WHERE post_id = ?";
                //3. Create statement
                stm = con.prepareStatement(sql);
                stm.setInt(1, postId);
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
    
    private List<Post> PostSearchResult;

    public List<Post> getPostSearchResult() {
        return PostSearchResult;
    }
    
    
    
    public void searchPostByTitle(String searchValue)
            throws ClassNotFoundException, SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            
            con = DBContext.getConnection();

            if (con != null) {
                
                String sql = "Select * "
                        + "From post "
                        + "where title like ? and status =1 and (post_type = 1 or post_type = 2 or post_type = 3)";

                
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchValue + "%");

                
                rs = stm.executeQuery();

                
                while (rs.next()) {
                    int postId = rs.getInt("post_id");
                    
                    UserDAO uDao = new UserDAO();
                    User user = uDao.GetUserById(rs.getInt("userID"));

                    CategoryDAO cDao = new CategoryDAO();
                    Category category = cDao.GetCategoryById(rs.getInt("category_id"));

                    PostTypeDAO ptDao = new PostTypeDAO();
                    PostType postType = ptDao.GetTypeById(rs.getInt("post_type"));

                    PostStatusDAO psDao = new PostStatusDAO();
                    PostStatus status = psDao.GetStatusById(rs.getInt("status"));
                    
                    String title = rs.getString("title");
                    String content = rs.getString("post_content");
                    String img = rs.getString("post_img");
                    String reason = rs.getString("reason");
                    
                    Post dto = new Post(postId, user, category, title, content, img, rs.getDate("datePosted"), reason, status, postType);
                    
                    if (this.PostSearchResult == null) {
                        this.PostSearchResult = new ArrayList<>();
                    }
                    this.PostSearchResult.add(dto);

                }
            }

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }
    
    public void searchPostByTitleAndType(String searchValue, int postTypeId)
            throws ClassNotFoundException, SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            
            con = DBContext.getConnection();

            if (con != null) {
                
                String sql = "Select * "
                        + "From post "
                        + "where title like ? and post_type = ? and status = 1";

                
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchValue + "%");
                stm.setInt(2, postTypeId);
                
                rs = stm.executeQuery();

                
                while (rs.next()) {
                    int postId = rs.getInt("post_id");
                    
                    UserDAO uDao = new UserDAO();
                    User user = uDao.GetUserById(rs.getInt("userID"));

                    CategoryDAO cDao = new CategoryDAO();
                    Category category = cDao.GetCategoryById(rs.getInt("category_id"));

                    PostTypeDAO ptDao = new PostTypeDAO();
                    PostType postType = ptDao.GetTypeById(postTypeId);

                    PostStatusDAO psDao = new PostStatusDAO();
                    PostStatus status = psDao.GetStatusById(rs.getInt("status"));
                    
                    String title = rs.getString("title");
                    String content = rs.getString("post_content");
                    String img = rs.getString("post_img");
                    String reason = rs.getString("reason");
                    
                    Post dto = new Post(postId, user, category, title, content, img, rs.getDate("datePosted"), reason, status, postType);
                    
                    if (this.PostSearchResult == null) {
                        this.PostSearchResult = new ArrayList<>();
                    }
                    this.PostSearchResult.add(dto);

                }
            }

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }
    
    public void searchPostByType(int postTypeId)
            throws ClassNotFoundException, SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            
            con = DBContext.getConnection();

            if (con != null) {
                
                String sql = "Select * "
                        + "From post "
                        + "where status = 1 and post_type = ?";

                
                stm = con.prepareStatement(sql);
                stm.setInt(1, postTypeId);
                
                rs = stm.executeQuery();

                
                while (rs.next()) {
                    int postId = rs.getInt("post_id");
                    
                    UserDAO uDao = new UserDAO();
                    User user = uDao.GetUserById(rs.getInt("userID"));

                    CategoryDAO cDao = new CategoryDAO();
                    Category category = cDao.GetCategoryById(rs.getInt("category_id"));

                    PostTypeDAO ptDao = new PostTypeDAO();
                    PostType postType = ptDao.GetTypeById(postTypeId);

                    PostStatusDAO psDao = new PostStatusDAO();
                    PostStatus status = psDao.GetStatusById(rs.getInt("status"));
                    
                    String title = rs.getString("title");
                    String content = rs.getString("post_content");
                    String img = rs.getString("post_img");
                    String reason = rs.getString("reason");
                    
                    Post dto = new Post(postId, user, category, title, content, img, rs.getDate("datePosted"), reason, status, postType);
                    
                    if (this.PostSearchResult == null) {
                        this.PostSearchResult = new ArrayList<>();
                    }
                    this.PostSearchResult.add(dto);

                }
            }

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }
    public ArrayList<User> GetUserInterested(int postId) {
        ArrayList<User> list = new ArrayList<>();
        try {
            String sql = "SELECT *\n"
                    + "  FROM [InterestPost]\n"
                    + "  Where PostId = ?";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, postId);
            ResultSet rs = stm.executeQuery();

            UserDAO uDao = new UserDAO();
            while (rs.next()) {
                User u = uDao.GetUserById(rs.getInt("UserId"));
                list.add(u);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public ArrayList<Post> GetPostInterestedByUser(int offset, int recordsPerPage, int userId, String textSearch, int categoryId, int typeId, int status, int isPublic) {
        ArrayList<Post> list = new ArrayList<>();
        try {
            int count = 0;
            String sql = "SELECT *\n"
                    + "  FROM [InterestPost]\n"
                    + "  Where UserId = ?\n";
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

            sql += " order by PostId\n";
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
                p = GetPostById(rs.getInt("PostId"));
                list.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public int GetNoOfRecordsPostInterestedByUser(int userId, String textSearch, int categoryId, int typeId, int status, int isPublic) {
        try {
            int count = 0;
            String sql = "SELECT count(*)\n"
                    + "  FROM [InterestPost]\n"
                    + "  Where UserId = ?\n";
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

            PreparedStatement stm = connection.prepareStatement(sql);
            for (Map.Entry<Integer, Object> entry : setter.entrySet()) {
                stm.setObject(entry.getKey(), entry.getValue());
            }
            ResultSet rs = stm.executeQuery();

            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public ArrayList<Post> GetTop3Post() {
        ArrayList<Post> list = new ArrayList<>();
        try {
            String sql = "SELECT Top 3 PostId, count(*)\n"
                    + "  FROM [InterestPost]\n"
                    + "  Group by PostId";

            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();

            Post p;
            while (rs.next()) {
                p = new Post();
                p = GetPostById(rs.getInt("PostId"));
                list.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public ArrayList<User> GetUserLike(int postId) {
        ArrayList<User> list = new ArrayList<>();
        try {
            String sql = "SELECT *\n"
                    + "  FROM [LikePost]\n"
                    + "  Where PostId = ?";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, postId);
            ResultSet rs = stm.executeQuery();

            UserDAO uDao = new UserDAO();
            while (rs.next()) {
                User u = uDao.GetUserById(rs.getInt("UserId"));
                list.add(u);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public int getYearTotalPost(int i) {
        int numofPost = 0; // Initialize with a default value (e.g., -1) in case no records exist.

        try {
            String sql = "select YEAR(datePosted) as year, count(post_id) as 'NumOfPost' from post where YEAR(datePosted) = ? GROUP BY YEAR(datePosted)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, i);
            ResultSet rs = stm.executeQuery();

            if (rs.next()) {
                numofPost = rs.getInt("NumOfPost");
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return numofPost;
    }
    
    public int getTotalPostByStatus(int statusId) {
        int numofPost = 0; // Initialize with a default value (e.g., -1) in case no records exist.

        try {
            String sql = "select status, count(post_id) as 'NumOfPost' from post where status = ? group by status";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, statusId);
            ResultSet rs = stm.executeQuery();

            if (rs.next()) {
                numofPost = rs.getInt("NumOfPost");
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return numofPost;
    }
    
    public Post GetMostLikedPost() {
        try {
            String sql = "SELECT TOP 1 PostId, COUNT(LikePostId) AS NumberOfLikes\n" +
                            "FROM LikePost\n" +
                            "GROUP BY PostId\n" +
                            "ORDER BY NumberOfLikes DESC ;";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                
               PostDAO pDao = new PostDAO();
               Post post = pDao.GetPostById(rs.getInt("PostId"));

                return post;
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
