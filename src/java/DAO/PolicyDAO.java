/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DBUtils.DBContext;
import Model.Category;
import Model.Policy;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;

/**
 *
 * @author Admin
 */
public class PolicyDAO extends DBUtils.DBContext {
    private List<Policy> policyList;

    public List<Policy> getPolicyList() {
        return policyList;
    }
    
    public void getAllPolicy() throws SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBContext.getConnection();
            if (conn != null) {
                String sql = "Select * "
                        + "From policies ";
                ptm = conn.prepareStatement(sql);
                rs = ptm.executeQuery();
                while (rs.next()) {

                    int policyId = rs.getInt("policy_id");
                    String policyTitle = rs.getString("policy_title");
                    String policyDetail=rs.getString("policy_detail");
                    Policy dto = new Policy(policyId, policyTitle, policyDetail);

                    if (this.policyList == null) {
                        this.policyList = new ArrayList<>();
                    }//end account list had not initialize
                    this.policyList.add(dto);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }
    
    
    
    public boolean updatePolciy(String policyTitle, String policyDetail, int policyId) 
            throws SQLException, ClassNotFoundException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;

        try {
            //1. Make connection
            con = DBContext.getConnection();

            if (con != null) {
                //2. create SQL String
                String sql = "UPDATE policies "
                        + "SET policy_title = ? "
                        + " ,policy_detail = ? "
                        + " WHERE policy_id = ?";
                //3. Create statement
                stm = con.prepareStatement(sql);
                stm.setString(1, policyTitle);
                stm.setString(2, policyDetail);
                stm.setInt(3, policyId);
                //4. Excute querry to get Result set
                int effectRow = stm.executeUpdate();
                //5. Process Result set
                if (effectRow > 0) {
                    result = true;
                }

            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result ;
    }
    
    public Policy GetPolicyById(int id) {
        try {
            String sql = "SELECT *\n"
                    + "  FROM [policies]\n"
                    + "  Where policy_id = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                String policyTitle = rs.getString("policy_title");
                String policyDetail = rs.getString("policy_detail");

                return new Policy(id, policyTitle, policyDetail);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
