/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

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
public class UserRoleDao extends DBContext {

    public UserRole getRoleById(int roleId) {
        try {
            String sql = "SELECT *\n"
                    + "  FROM [user_role]\n"
                    + "  Where role_id =?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, roleId);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return new UserRole(rs.getInt("role_id"), rs.getString("role_name"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserRoleDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
