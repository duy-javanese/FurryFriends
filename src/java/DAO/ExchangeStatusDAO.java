/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.ExchangeStatus;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dell
 */
public class ExchangeStatusDAO extends DBUtils.DBContext {

    public ExchangeStatus GetExchangeStatusById(int id) {
        try {
            String sql = "SELECT *\n"
                    + "  FROM [ExnchangeSatus]\n"
                    + "  where ExchangeStatusId = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return new ExchangeStatus(id, rs.getString("ExchangeStatusValue"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ExchangeStatusDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
