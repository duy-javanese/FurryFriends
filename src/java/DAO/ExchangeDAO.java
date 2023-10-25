/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Exchange;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dell
 */
public class ExchangeDAO extends DBUtils.DBContext {

    public void insert(Exchange exchange) {
        try {
            String sql = "INSERT INTO [dbo].[exchange] ([post_id], [is_free], [price], [address], [isFinish]) "
                    + "VALUES (?, ?, ?, ?, ?)";

            PreparedStatement stm = connection.prepareStatement(sql);

            stm.setInt(1, exchange.getPost().getPostId()); // Assuming you have a method to get post_id from the Post object.
            stm.setBoolean(2, exchange.isIsFree());
            stm.setDouble(3, exchange.getPrice());
            stm.setString(4, exchange.getAddress());
            stm.setBoolean(5, exchange.isIsFinish());

            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ExchangeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
