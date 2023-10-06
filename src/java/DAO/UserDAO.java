/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DBUtils.DBContext;
import Model.User;
import Model.UserRole;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dell
 */
public class UserDAO extends DBContext {

    public User doLogin(String username, String pwd) {
        try {
            String sql = "SELECT *\n"
                    + "  FROM [users]\n"
                    + "  Where username = ? and pwd = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            stm.setString(2, pwd);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                UserRoleDAO rDao = new UserRoleDAO();
                UserRole role = rDao.getRoleById(rs.getInt("role_id"));

                return new User(rs.getInt("userID"),
                        username,
                        pwd,
                        rs.getString("email"),
                        rs.getString("phone_num"),
                        rs.getBoolean("user_status"),
                        role,
                        rs.getString("user_address"),
                        rs.getInt("point"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
