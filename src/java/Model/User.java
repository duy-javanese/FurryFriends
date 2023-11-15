    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author dell
 */
public class User {
    private int userId;
    private String username;
    private String pwd;
    private String email;
    private String emailId;
    private String phone;
    private boolean status;
    private UserRole role;
    private String address;
    private int point;

    public User() {
    }

    //Construction to search members
    public User(int userId, String username, String email, String phone, String address, int point, boolean status) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.point = point;
        this.status = status;
    }

    public User(int userId, String username, String pwd, String email, String emailId, String phone, boolean status, UserRole role, String address, int point) {
        this.userId = userId;
        this.username = username;
        this.pwd = pwd;
        this.email = email;
        this.emailId = emailId;
        this.phone = phone;
        this.status = status;
        this.role = role;
        this.address = address;
        this.point = point;
    }

    
    
    public User(int userId, String username, String pwd, String email, String phone, boolean status, UserRole role, String address, int point) {
        this.userId = userId;
        this.username = username;
        this.pwd = pwd;
        this.email = email;
        this.phone = phone;
        this.status = status;
        this.role = role;
        this.address = address;
        this.point = point;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    
    @Override
    public String toString() {
        return "User{" + "userId=" + userId + ", username=" + username + ", pwd=" + pwd + ", email=" + email + ", phone=" + phone + ", status=" + status + ", role=" + role + ", address=" + address + ", point=" + point + '}';
    }
    
    
}
