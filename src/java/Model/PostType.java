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
public class PostType {
    private int postTypeId;
    private String postTypeName;
    
    public PostType() {
    }

    public PostType(int postTypeId, String postTypeName) {
        this.postTypeId = postTypeId;
        this.postTypeName = postTypeName;
    }

    public void setPostTypeId(int postTypeId) {
        this.postTypeId = postTypeId;
    }

    public void setPostTypeName(String postTypeName) {
        this.postTypeName = postTypeName;
    }

    public int getPostTypeId() {
        return postTypeId;
    }

    public String getPostTypeName() {
        return postTypeName;
    }
}
