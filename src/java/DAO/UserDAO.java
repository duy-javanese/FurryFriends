/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DBUtils.DBContext;
import Model.User;
import Model.UserRole;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
    public boolean isUserExists(String username) {
        try {
            String sql = "SELECT *\n"
                    + "FROM users\n"
                    + "WHERE username = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            // Xử lý ngoại lệ nếu cần
            return false;
        }
    }

    public boolean addUser(User user) {
    try {
        String sql = "INSERT INTO users (username, pwd, email, phone_num, user_status, role_id, user_address, point) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, user.getUsername());
        statement.setString(2, user.getPwd());
        statement.setString(3, user.getEmail());
        statement.setString(4, user.getPhone());
        statement.setBoolean(5, true);  // user_status luôn là 1
        statement.setInt(6, 3);         // role_id luôn là 3
        statement.setString(7, user.getAddress());
        statement.setInt(8, 0);         // point luôn là 0
        
        int rowsInserted = statement.executeUpdate();
        return rowsInserted > 0;
    } catch (SQLException e) {
        e.printStackTrace();
        // Xử lý ngoại lệ nếu cần
        return false;
    }
}
public void getAllUser() throws SQLException {
    
}
}
