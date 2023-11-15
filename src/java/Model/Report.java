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
    private Post post;
    private Comment comment;
    private Exchange exchange;
    private String reason;
    private Date reportDate;
    private boolean reportStatus;
    private ReportContent reportContent;

    public Report() {
    }

    public Report(int reportId, User reporter, String reportType, Post post, Comment comment, Exchange exchange, String reason, Date reportDate, boolean reportStatus, ReportContent reportContent) {
        this.reportId = reportId;
        this.reporter = reporter;
        this.reportType = reportType;
        this.post = post;
        this.comment = comment;
        this.exchange = exchange;
        this.reason = reason;
        this.reportDate = reportDate;
        this.reportStatus = reportStatus;
        this.reportContent = reportContent;
    }

    

    
    
    // Constructor for Reported post

    public Report(int reportId, User reporter, String reportType, Post post, String reason, Date reportDate, boolean reportStatus, ReportContent reportContent) {
        this.reportId = reportId;
        this.reporter = reporter;
        this.reportType = reportType;
        this.post = post;
        this.reason = reason;
        this.reportDate = reportDate;
        this.reportStatus = reportStatus;
        this.reportContent = reportContent;
    }

    // Constructor for Reported Exchange

    public Report(int reportId, User reporter, String reportType, Exchange exchange, String reason, Date reportDate, boolean reportStatus, ReportContent reportContent) {
        this.reportId = reportId;
        this.reporter = reporter;
        this.reportType = reportType;
        this.exchange = exchange;
        this.reason = reason;
        this.reportDate = reportDate;
        this.reportStatus = reportStatus;
        this.reportContent = reportContent;
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

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    
    public Post getPost() {
        return post;
    }

    public void setPostId(Post post) {
        this.post = post;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment= comment;
    }

    public Exchange getExchange() {
        return exchange;
    }

    public void setExchangeId(Exchange exchange) {
        this.exchange = exchange;
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

    public void setReportContent(ReportContent reportContent) {
        this.reportContent = reportContent;
    }
    
    public ReportContent getReportContent() {
        return reportContent;
    }
    
    @Override
    public String toString() {
        return "Report{" + "reportId=" + reportId + ", reporter=" + reporter + ", reportType=" + reportType + ", post=" + post + ", comment=" + comment + ", exchange=" + exchange + ", reason=" + reason + ", reportDate=" + reportDate + ", reportStatus=" + reportStatus + '}';
    }



    
}
