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
public class Report {
    private int reportId;
    private User reporter; // who report
    private String reportType;
    private Post postId;
    private Comment commentId;
    private Exchange exchangeId;
    private String reason;
    private Date reportDate;
    private boolean reportStatus;

    public Report() {
    }

    // Constructor for Reported post
    public Report(int reportId, User reporter, String reportType, Post postId, String reason, Date reportDate, boolean reportStatus) {
        this.reportId = reportId;
        this.reporter = reporter;
        this.reportType = reportType;
        this.postId = postId;
        this.reason = reason;
        this.reportDate = reportDate;
        this.reportStatus = reportStatus;
    }
    
    // Constructor for Reported Exchange

    public Report(int reportId, User reporter, String reportType, Exchange exchangeId, String reason, Date reportDate, boolean reportStatus) {
        this.reportId = reportId;
        this.reporter = reporter;
        this.reportType = reportType;
        this.exchangeId = exchangeId;
        this.reason = reason;
        this.reportDate = reportDate;
        this.reportStatus = reportStatus;
    }
     

    public int getReportId() {
        return reportId;
    }

    public void setReportId(int reportId) {
        this.reportId = reportId;
    }

    public User getReporter() {
        return reporter;
    }

    public void setReporterId(User reporter) {
        this.reporter = reporter;
    }

    public Post getPostId() {
        return postId;
    }

    public void setPostId(Post postId) {
        this.postId = postId;
    }

    public Comment getCommentId() {
        return commentId;
    }

    public void setCommentId(Comment commentId) {
        this.commentId = commentId;
    }

    public Exchange getExchangeId() {
        return exchangeId;
    }

    public void setExchangeId(Exchange exchangeId) {
        this.exchangeId = exchangeId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }

    public boolean isReportStatus() {
        return reportStatus;
    }

    public void setReportStatus(boolean reportStatus) {
        this.reportStatus = reportStatus;
    }

    @Override
    public String toString() {
        return "Report{" + "reportId=" + reportId + ", reporter=" + reporter + ", postId=" + postId + ", commentId=" + commentId + ", exchangeId=" + exchangeId + ", reason=" + reason + ", reportDate=" + reportDate + ", reportStatus=" + reportStatus + '}';
    }


    
}
