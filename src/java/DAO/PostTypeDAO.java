/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DBUtils.DBContext;
import Model.PostType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dell
 */
public class PostTypeDAO extends DBContext {

    public ArrayList<PostType> GetAllPostType() {
        ArrayList<PostType> list = new ArrayList<>();
        try {
            String sql = "SELECT *\n"
                    + "  FROM [postType]";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                list.add(new PostType(rs.getInt("postTypeId"),
                        rs.getString("postTypeName")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostTypeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    PostType GetTypeById(int id) {
        try {
            String sql = "SELECT *\n"
                    + "  FROM [postType]\n"
                    + "  Where postTypeId = ?\n";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                return new PostType(id,
                        rs.getString("postTypeName"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostTypeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<PostType> GetPostTypeForSearchPost() {
        ArrayList<PostType> list = new ArrayList<>();
        try {
            String sql = "SELECT *\n"
                    + "  FROM [postType]"
                    + "  Where postTypeId = 1 or postTypeId = 2 or postTypeId = 3 ";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                list.add(new PostType(rs.getInt("postTypeId"),
                        rs.getString("postTypeName")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostTypeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public boolean addPostType(PostType postType) {
        try {
            String sql = "INSERT INTO postType (postTypeID, postTypeName) "
                    + "VALUES (?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, generateRandomId());
            statement.setString(2, postType.getPostTypeName());
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            // Xử lý ngoại lệ nếu cần
            return false;
        }
    }

    public boolean isPostTypeExists(String s) {
        try {
            String sql = "SELECT *\n"
                    + "FROM postType\n"
                    + "WHERE postTypeName = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, s);
            ResultSet resultSet = statement.executeQuery();

            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            // Xử lý ngoại lệ nếu cần
            return false;
        }
    }

    public boolean updatePostType(PostType postType) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBContext.getConnection();
            if (conn != null) {
                String sql = "UPDATE postType SET postTypeName = ? WHERE postTypeID = ?";
                ptm = conn.prepareStatement(sql);
                ptm.setString(1, postType.getPostTypeName());
                ptm.setInt(2, postType.getPostTypeId());
                check = ptm.executeUpdate() > 0 ? true : false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    public boolean deletePostType(PostType postType) throws SQLException, ClassNotFoundException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBContext.getConnection();
            if (conn != null) {
                String sql = "DELETE FROM postType WHERE postTypeID = ?";
                ptm = conn.prepareStatement(sql);
                ptm.setInt(1, postType.getPostTypeId());
                check = ptm.executeUpdate() > 0 ? true : false;
            }
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }

    private static int generateRandomId() {
        Random random = new Random();
        return random.nextInt(901) + 100;
    }
}
