/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Date;

/**
 *
 * @author Admin
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
    private String address;

    public Post() {
    }

    
    //contrucstor to get post list for staff
    public Post(int postId, User user, Category category, String title, String content, String img, Date datePost, String reason, PostStatus status) {
        this.postId = postId;
        this.user = user;
        this.category = category;
        this.title = title;
        this.content = content;
        this.img = img;
        this.datePost = datePost;
        this.reason = reason;
        this.status = status;
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

    public PostType getPostType() {
        return postType;
    }

    public void setPostType(PostType postType) {
        this.postType = postType;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Post{" + "postId=" + postId + ", user=" + user + ", category=" + category + ", postType=" + postType + ", title=" + title + ", content=" + content + ", img=" + img + ", isPublic=" + isPublic + ", datePost=" + datePost + ", reason=" + reason + ", status=" + status + ", address=" + address + '}';
    }
    
    
}
