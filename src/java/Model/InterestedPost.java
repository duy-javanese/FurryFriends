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
    private Timestamp datetime;
    private ExchangeStatus exchangeStatus;
    private boolean isRated;
    

    public InterestedPost() {
    }

    public InterestedPost(int interestedPostId, Post post, User user) {
        this.interestedPostId = interestedPostId;
        this.post = post;
        this.user = user;
    }

    public InterestedPost(int interestedPostId, Post post, User user, Timestamp datetime) {
        this.interestedPostId = interestedPostId;
        this.post = post;
        this.user = user;
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

    public Timestamp getDatetime() {
        return datetime;
    }

    public void setDatetime(Timestamp datetime) {
        this.datetime = datetime;
    }

    public ExchangeStatus getExchangeStatus() {
        return exchangeStatus;
    }

    public void setExchangeStatus(ExchangeStatus exchangeStatus) {
        this.exchangeStatus = exchangeStatus;
    }

    public boolean isIsRated() {
        return isRated;
    }

    public void setIsRated(boolean isRated) {
        this.isRated = isRated;
    }
    
}
