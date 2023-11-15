/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author dell
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
