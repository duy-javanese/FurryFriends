/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author dell
 */
public class Post {
    private int postId;
    private User user;
    private Category category;
    private PostType postType;
    private String title;
    private String content;
    private String img;
    private boolean isPublic;
    private Date datePost;
    private String reason;
    private PostStatus status;
    private Exchange exchange;
    private ArrayList<Comment> comments;
    private ArrayList<User> userInterested;
    private ArrayList<User> userLike;

    public Post() {
    }

    public Post(int postId, User user, Category category, PostType postType, String title, String content, String img, boolean isPublic, Date datePost, String reason, PostStatus status) {
        this.postId = postId;
        this.user = user;
        this.category = category;
        this.postType = postType;
        this.title = title;
        this.content = content;
        this.img = img;
        this.isPublic = isPublic;
        this.datePost = datePost;
        this.reason = reason;
        this.status = status;
    }

    public Post(int postId, User user, Category category, PostType postType, String title, String content, String img, boolean isPublic, Date datePost, String reason, PostStatus status, Exchange exchange) {
        this.postId = postId;
        this.user = user;
        this.category = category;
        this.postType = postType;
        this.title = title;
        this.content = content;
        this.img = img;
        this.isPublic = isPublic;
        this.datePost = datePost;
        this.reason = reason;
        this.status = status;
        this.exchange = exchange;
    }
    
    public PostType getPostType() {
        return postType;
    }

    public void setPostType(PostType postType) {
        this.postType = postType;
    }
    
    //contrucstor to get post list for staff
    public Post(int postId, User user, Category category, String title, String content, String img, Date datePost, String reason, PostStatus status, PostType postType) {
        this.postId = postId;
        this.user = user;
        this.category = category;
        this.title = title;
        this.content = content;
        this.img = img;
        this.datePost = datePost;
        this.reason = reason;
        this.status = status;
        this.postType = postType;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public boolean isIsPublic() {
        return isPublic;
    }

    public void setIsPublic(boolean isPublic) {
        this.isPublic = isPublic;
    }

    public Date getDatePost() {
        return datePost;
    }

    public void setDatePost(Date datePost) {
        this.datePost = datePost;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public PostStatus getStatus() {
        return status;
    }

    public void setStatus(PostStatus status) {
        this.status = status;
    }

    public Exchange getExchange() {
        return exchange;
    }

    public void setExchange(Exchange exchange) {
        this.exchange = exchange;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }

    public ArrayList<User> getUserInterested() {
        return userInterested;
    }

    public void setUserInterested(ArrayList<User> userInterested) {
        this.userInterested = userInterested;
    }

    public ArrayList<User> getUserLike() {
        return userLike;
    }

    public void setUserLike(ArrayList<User> userLike) {
        this.userLike = userLike;
    }
    
    
    @Override
    public String toString() {
        return "Post{" + "postId=" + postId + ", user=" + user + ", category=" + category + ", postType=" + postType + ", title=" + title + ", content=" + content + ", img=" + img + ", isPublic=" + isPublic + ", datePost=" + datePost + ", reason=" + reason + ", status=" + status + '}';
    }
    
    
}
