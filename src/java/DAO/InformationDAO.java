/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DBUtils.DBContext;
import Model.Information;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author DUY
 */
public class InformationDAO extends DBUtils.DBContext {

    public static Information getInfor() throws SQLException {
        Information information = new Information();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBContext.getConnection();
            if (conn != null) {
                String sql = "SELECT logoPath, aboutUs, contact FROM configuration WHERE id = 1";
                ptm = conn.prepareStatement(sql);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String logoPath = rs.getString("logoPath");
                    String aboutUs = rs.getString("aboutUs");
                    String contact = rs.getString("contact");
                    information = new Information(logoPath, aboutUs, contact);
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
        return information;
    }

    public boolean updateLogo(Information information) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBContext.getConnection();
            if (conn != null) {
                String sql = "UPDATE configuration SET logoPath = ? WHERE id = 1";
                ptm = conn.prepareStatement(sql);
                ptm.setString(1, information.getLogoPath());
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

    public boolean updateAboutUs(Information information) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBContext.getConnection();
            if (conn != null) {
                String sql = "UPDATE configuration SET aboutUs = ? WHERE id = 1";
                ptm = conn.prepareStatement(sql);
                ptm.setString(1, information.getAboutUs());
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

    public boolean updateContact(Information information) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBContext.getConnection();
            if (conn != null) {
                String sql = "UPDATE configuration SET contact = ? WHERE id = 1";
                ptm = conn.prepareStatement(sql);
                ptm.setString(1, information.getContact());
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

    public int countUsers() throws SQLException {
        int userCount = 0;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBContext.getConnection();
            if (conn != null) {
                String sql = "SELECT COUNT(*) AS user_count FROM dbo.users WHERE role_id = 3";
                ptm = conn.prepareStatement(sql);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    userCount = rs.getInt("user_count");
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
        return userCount;
    }

    public int countActiveUsers() throws SQLException {
        int userCount = 0;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBContext.getConnection();
            if (conn != null) {
                String sql = "SELECT COUNT(*) AS user_count FROM dbo.users WHERE role_id = 3 AND user_status = 1";
                ptm = conn.prepareStatement(sql);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    userCount = rs.getInt("user_count");
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
        return userCount;
    }

    public int countBannedUsers() throws SQLException {
        int userCount = 0;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBContext.getConnection();
            if (conn != null) {
                String sql = "SELECT COUNT(*) AS user_count FROM dbo.users WHERE role_id = 3 AND user_status = 0";
                ptm = conn.prepareStatement(sql);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    userCount = rs.getInt("user_count");
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
        return userCount;
    }

    public int countStaffs() throws SQLException {
        int staffCount = 0;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBContext.getConnection();
            if (conn != null) {
                String sql = "SELECT COUNT(*) AS staff_count FROM dbo.users WHERE role_id = 2";
                ptm = conn.prepareStatement(sql);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    staffCount = rs.getInt("staff_count");
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
        return staffCount;
    }

    public int countActiveStaffs() throws SQLException {
        int staffCount = 0;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBContext.getConnection();
            if (conn != null) {
                String sql = "SELECT COUNT(*) AS staff_count FROM dbo.users WHERE role_id = 2 AND user_status = 1";
                ptm = conn.prepareStatement(sql);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    staffCount = rs.getInt("staff_count");
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
        return staffCount;
    }

    public int countInactiveStaffs() throws SQLException {
        int staffCount = 0;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBContext.getConnection();
            if (conn != null) {
                String sql = "SELECT COUNT(*) AS staff_count FROM dbo.users WHERE role_id = 2 AND user_status = 0";
                ptm = conn.prepareStatement(sql);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    staffCount = rs.getInt("staff_count");
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
        return staffCount;
    }

    public int countPosts() throws SQLException {
        int postCount = 0;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBContext.getConnection();
            if (conn != null) {
                String sql = "SELECT COUNT(*) AS post_count FROM dbo.post";
                ptm = conn.prepareStatement(sql);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    postCount = rs.getInt("post_count");
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
        return postCount;
    }

    public int countBlogs() throws SQLException {
        int postCount = 0;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBContext.getConnection();
            if (conn != null) {
                String sql = "SELECT COUNT(*) AS post_count FROM dbo.post WHERE post_type IN (1, 2, 3)";
                ptm = conn.prepareStatement(sql);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    postCount = rs.getInt("post_count");
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
        return postCount;
    }

    public int countExchangePosts() throws SQLException {
        int postCount = 0;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBContext.getConnection();
            if (conn != null) {
                String sql = "SELECT COUNT(*) AS post_count FROM dbo.post WHERE post_type IN (4)";
                ptm = conn.prepareStatement(sql);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    postCount = rs.getInt("post_count");
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
        return postCount;
    }
}
