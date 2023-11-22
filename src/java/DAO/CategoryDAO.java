/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DBUtils.DBContext;
import Model.Category;
import java.sql.Connection;
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
public class CategoryDAO extends DBUtils.DBContext {

    public ArrayList<Category> GetAllCategories() {
        ArrayList<Category> list = new ArrayList<>();
        try {
            String sql = "SELECT *\n"
                    + "  FROM [category]";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                list.add(new Category(rs.getInt("category_id"),
                        rs.getString("category_name")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public Category GetCategoryById(int id) {
        try {
            String sql = "SELECT *\n"
                    + "  FROM [category]\n"
                    + "  Where category_id = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return new Category(id, rs.getString("category_name"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean addCategory(Category category) {
        try {
            String sql = "INSERT INTO category (category_name) "
                    + "VALUES (?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, category.getCategoryName());
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            // Xử lý ngoại lệ nếu cần
            return false;
        }
    }

    public boolean isCategoryExists(String category) {
        try {
            String sql = "SELECT *\n"
                    + "FROM category\n"
                    + "WHERE category_name = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, category);
            ResultSet resultSet = statement.executeQuery();

            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            // Xử lý ngoại lệ nếu cần
            return false;
        }
    }

    public boolean updateCategory(Category category) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBContext.getConnection();
            if (conn != null) {
                String sql = "UPDATE category SET category_name = ? WHERE category_id = ?";
                ptm = conn.prepareStatement(sql);
                ptm.setString(1, category.getCategoryName());
                ptm.setInt(2, category.getCategoryId());
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

    public boolean deleteCategory(Category category) throws SQLException, ClassNotFoundException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBContext.getConnection();
            if (conn != null) {
                String sql = "DELETE FROM category WHERE category_id = ?";
                ptm = conn.prepareStatement(sql);
                ptm.setInt(1, category.getCategoryId());
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
}
