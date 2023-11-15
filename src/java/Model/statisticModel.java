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
public class statisticModel {
    private int object;
    private int quantity;
    private PostType postType;
    private Post post;
    
    public statisticModel() {
    }

    public statisticModel(int object, int quantity) {
        this.object = object;
        this.quantity = quantity;
    }

    //constructor for posttype statistic
    public statisticModel(int quantity, PostType postType) {
        this.quantity = quantity;
        this.postType = postType;
    }

    public statisticModel(int quantity, Post post) {
        this.quantity = quantity;
        this.post = post;
    }
    
    public int getObject() {
        return object;
    }

    public void setObject(int object) {
        this.object = object;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public PostType getPostType() {
        return postType;
    }

    public void setPostType(PostType postType) {
        this.postType = postType;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    @Override
    public String toString() {
        return "statisticModel{" + "object=" + object + ", quantity=" + quantity + ", postType=" + postType + ", post=" + post + '}';
    }

    
    

    
    
    
            
}
