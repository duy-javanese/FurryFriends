/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Admin
 */
public class SavePost {
    private int SavePostId;
    private Post post;
    private User user;

    public SavePost() {
    }

    public SavePost(int SavePostId, Post post, User user) {
        this.SavePostId = SavePostId;
        this.post = post;
        this.user = user;
    }

    public int getSavePostId() {
        return SavePostId;
    }

    public void setSavePostId(int SavePostId) {
        this.SavePostId = SavePostId;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    
}
