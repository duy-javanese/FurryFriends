/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Timestamp;
import java.util.ArrayList;

/**
 *
 * @author dell
 */
public class InterestedPost {
    private int interestedPostId;
    private Post post;
    private User user;
    private ArrayList<User> userInterested;
    private Timestamp datetime;

    public InterestedPost() {
    }

    public InterestedPost(int interestedPostId, Post post, User user, ArrayList<User> userInterested) {
        this.interestedPostId = interestedPostId;
        this.post = post;
        this.user = user;
        this.userInterested = userInterested;
    }

    public InterestedPost(int interestedPostId, Post post, User user, ArrayList<User> userInterested, Timestamp datetime) {
        this.interestedPostId = interestedPostId;
        this.post = post;
        this.user = user;
        this.userInterested = userInterested;
        this.datetime = datetime;
    }

    public int getInterestedPostId() {
        return interestedPostId;
    }

    public void setInterestedPostId(int interestedPostId) {
        this.interestedPostId = interestedPostId;
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

    public ArrayList<User> getUserInterested() {
        return userInterested;
    }

    public void setUserInterested(ArrayList<User> userInterested) {
        this.userInterested = userInterested;
    }

    public Timestamp getDatetime() {
        return datetime;
    }

    public void setDatetime(Timestamp datetime) {
        this.datetime = datetime;
    }
    
}
