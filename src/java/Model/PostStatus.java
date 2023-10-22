/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author dell
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
