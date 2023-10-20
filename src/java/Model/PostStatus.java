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
public class PostStatus {
    private int postStatusId;
    private String postStatusValue;

    public PostStatus(int postStatusId, String postStatusValue) {
        this.postStatusId = postStatusId;
        this.postStatusValue = postStatusValue;
    }

    public PostStatus() {
    }

    public int getPostStatusId() {
        return postStatusId;
    }

    public void setPostStatusId(int postStatusId) {
        this.postStatusId = postStatusId;
    }

    public String getPostStatusValue() {
        return postStatusValue;
    }

    public void setPostStatusValue(String postStatusValue) {
        this.postStatusValue = postStatusValue;
    }

    @Override
    public String toString() {
        return "PostStatus{" + "postStatusId=" + postStatusId + ", postStatusValue=" + postStatusValue + '}';
    }
    
    
}
