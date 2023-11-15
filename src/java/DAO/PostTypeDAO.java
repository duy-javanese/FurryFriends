/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DBUtils.DBContext;
import Model.PostType;
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
    
    
}
