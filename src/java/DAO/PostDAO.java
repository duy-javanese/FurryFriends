/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Category;
import Model.Constant;
import Model.Post;
import Model.PostStatus;
import Model.PostType;
import Model.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

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
                        status,
                        sql);
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

    public ArrayList<Post> GetPostByUser(int offset, int recordsPerPage,
            int userId, String textSearch, int categoryId, int typeId, int status, int isPublic) {
        ArrayList<Post> list = new ArrayList<>();
        try {
            int count = 0;
            String sql = "SELECT *\n"
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
        ArrayList<Post> list = pDao.GetPostByUser(0,6,3, "", -1, -1, -1, -1);
        System.out.println(list.size());
        System.out.println(p.getContent());
    }
}
