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
import javax.naming.NamingException;

/**
 *
 * @author dell
 */
public class UserDAO extends DBContext {

    public User getUserGoogle(String EmailID) {
        try {
            String sql = "SELECT *\n"
                    + "  FROM [users] where emailId like ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, EmailID);
            ResultSet rs = stm.executeQuery();

            if (rs.next()) {
                UserDAO uDao = new UserDAO();
                User user = uDao.GetUserById(rs.getInt("userID"));
                return user;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

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

    public boolean isEmailExists(String email) {
        try {
            String sql = "SELECT *\n"
                    + "FROM users\n"
                    + "WHERE email = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();

            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            // Xử lý ngoại lệ nếu cần
            return false;
        }
    }

    public boolean isPhoneExists(String phone) {
        try {
            String sql = "SELECT *\n"
                    + "FROM users\n"
                    + "WHERE phone_num = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, phone);
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
            String sql = "INSERT INTO users (username, pwd, email, phone_num, user_status, role_id, user_address, point) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
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
    private List<User> userList;

    public List<User> getUserList() {
        return userList;
    }

    public void searchUser(String searchValue)
            throws ClassNotFoundException, SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            //1.Make connection
            con = DBContext.getConnection();

            if (con != null) {
                //2.Create SQL String
                String sql = "Select userID, username, email, phone_num, user_address, point,user_status "
                        + "From users "
                        + "Where username Like ? and role_id=3";

                //3.Create statement
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchValue + "%");

                //4.Execute Query to get Result Set
                rs = stm.executeQuery();

                //5.Process Result Set
                while (rs.next()) {
                    //5.1 get values/data from result set
                    int userID = Integer.parseInt(rs.getString("userID"));
                    String username = rs.getString("username");
                    String email = rs.getString("email");
                    String phone_num = rs.getString("phone_num");
                    String user_address = rs.getString("user_address");
                    int point = Integer.parseInt(rs.getString("point"));
                    boolean status = rs.getBoolean("user_status");

                    //5.2 set values to DTO
                    User dto = new User(userID, username, email, phone_num, user_address, point, status);

                    //5.3 update account List
                    if (this.userList == null) {
                        this.userList = new ArrayList<>();
                    }//end account list had not initialize
                    this.userList.add(dto);

                }// end result has at least one row
            } // end connection had existed

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public void getAllUser() throws SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBContext.getConnection();
            if (conn != null) {
                String sql = "Select userID, username, email, phone_num, user_address, point,user_status "
                        + "From users "
                        + "Where role_id=3";
                ptm = conn.prepareStatement(sql);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    int userID = Integer.parseInt(rs.getString("userID"));
                    String username = rs.getString("username");
                    String email = rs.getString("email");
                    String phone_num = rs.getString("phone_num");
                    String user_address = rs.getString("user_address");
                    int point = Integer.parseInt(rs.getString("point"));
                    boolean status = rs.getBoolean("user_status");
                    User dto = new User(userID, username, email, phone_num, user_address, point, status);

                    if (this.userList == null) {
                        this.userList = new ArrayList<>();
                    }//end account list had not initialize
                    this.userList.add(dto);
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

    public List<User> getAllStaff() throws SQLException {
        List<User> listStaff = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBContext.getConnection();
            if (conn != null) {
                String sql = "Select userID, username, email, phone_num, user_address, point,user_status "
                        + "From users "
                        + "Where role_id=2";
                ptm = conn.prepareStatement(sql);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    int userID = Integer.parseInt(rs.getString("userID"));
                    String username = rs.getString("username");
                    String email = rs.getString("email");
                    String phone_num = rs.getString("phone_num");
                    String user_address = rs.getString("user_address");
                    int point = Integer.parseInt(rs.getString("point"));
                    boolean status = rs.getBoolean("user_status");
                    User dto = new User(userID, username, email, phone_num, user_address, point, status);
                    listStaff.add(dto);
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
        return listStaff;
    }

    public boolean UpdateUserStatus(int userID, Boolean status)
            throws SQLException, ClassNotFoundException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;

        try {
            //1. Make connection
            con = DBContext.getConnection();

            if (con != null) {
                //2. create SQL String
                String sql = "UPDATE users SET user_status = ? WHERE userID = ?";
                //3. Create statement
                stm = con.prepareStatement(sql);
                stm.setBoolean(1, status);
                stm.setInt(2, userID);
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
        return result;
    }

    public boolean addStaff(User user) {
        try {
            String sql = "INSERT INTO users (username, pwd, email, phone_num, user_status, role_id, user_address, point) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPwd());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPhone());
            statement.setBoolean(5, true);  // user_status luôn là 1
            statement.setInt(6, 2);         // role_id staff là 2
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

    public User GetUserById(int id) {
        try {
            String sql = "SELECT *\n"
                    + "  FROM [users]\n"
                    + "  Where userID = ? ";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {

                UserRoleDAO rDao = new UserRoleDAO();
                UserRole role = rDao.getRoleById(rs.getInt("role_id"));

                return new User(id,
                        rs.getString("username"),
                        null,
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

    // test searchUser
    public static void main(String[] args) {
        UserDAO dao = new UserDAO();
        try {
            try {
                dao.searchUser("robert");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (NamingException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (User x : dao.getUserList()) {
            System.out.println(x);
        }
    }

    public boolean addUserGoogle(User user) {
        try {
            String sql = "INSERT INTO users (username, pwd, email, emailId, phone_num, user_status, role_id, user_address, point) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPwd());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getEmailId());
            statement.setString(5, user.getPhone());
            statement.setBoolean(6, true);  // user_status luôn là 1
            statement.setInt(7, 3);         // role_id luôn là 3
            statement.setString(8, user.getAddress());
            statement.setInt(9, 0);         // point luôn là 0
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            // Xử lý ngoại lệ nếu cần
            return false;
        }
    }

    public void UpdateUser(User user) {
        try {
            String sql = "UPDATE [dbo].[users]\n"
                    + "   SET [username] = ?\n"
                    + "      ,[pwd] = ?\n"
                    + "      ,[email] = ?\n"
                    + "      ,[phone_num] = ?\n"
                    + "      ,[user_address] = ?\n"
                    + " WHERE userID = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPwd());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPhone());
            statement.setString(5, user.getAddress());
            statement.setInt(6, user.getUserId());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
