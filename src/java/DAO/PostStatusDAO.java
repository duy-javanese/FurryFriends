/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.PostStatus;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dell
 */
public class PostStatusDAO extends DBUtils.DBContext {

    public PostStatus GetStatusById(int id) {
        try {
            String sql = "SELECT *\n"
                    + "  FROM [postStatus]\n"
                    + "  Where postStatusId = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return new PostStatus(id, rs.getString("postStatusValue"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostStatusDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
