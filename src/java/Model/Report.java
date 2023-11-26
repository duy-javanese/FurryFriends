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
    private Post post;
    private String reason;
    private Date reportDate;
    private int reportStatus;
    private ReportContent reportContent;
    private Date processDate;
    private User processStaff;
    private Exchange exchange;
    
    public Report() {
    }

    public Report(int reportId, User reporter, Post post, String reason, Date reportDate, int reportStatus, ReportContent reportContent, Date processDate, User processStaff) {
        this.reportId = reportId;
        this.reporter = reporter;
        this.post = post;
        this.reason = reason;
        this.reportDate = reportDate;
        this.reportStatus = reportStatus;
        this.reportContent = reportContent;
        this.processDate = processDate;
        this.processStaff = processStaff;
    }
    
    
    
    
    
    // Constructor for Reported post
    public Report(int reportId, User reporter, Post post, String reason, Date reportDate, int reportStatus, ReportContent reportContent) {   
        this.reportId = reportId;
        this.reporter = reporter;
        this.post = post;
        this.reason = reason;
        this.reportDate = reportDate;
        this.reportStatus = reportStatus;
        this.reportContent = reportContent;
    }

    // Constructor for Reported Exchange

    public Report(int reportId, User reporter, String reason, Date reportDate, int reportStatus, ReportContent reportContent, Exchange exchange) {
        this.reportId = reportId;
        this.reporter = reporter;
        this.reason = reason;
        this.reportDate = reportDate;
        this.reportStatus = reportStatus;
        this.reportContent = reportContent;
        this.exchange = exchange;
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

    public void setReporter(User reporter) {
        this.reporter = reporter;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
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

    public int getReportStatus() {
        return reportStatus;
    }

    public void setReportStatus(int reportStatus) {
        this.reportStatus = reportStatus;
    }

    public ReportContent getReportContent() {
        return reportContent;
    }

    public void setReportContent(ReportContent reportContent) {
        this.reportContent = reportContent;
    }

    public Date getProcessDate() {
        return processDate;
    }

    public void setProcessDate(Date processDate) {
        this.processDate = processDate;
    }

    public User getProcessStaff() {
        return processStaff;
    }

    public void setProcessStaff(User processStaff) {
        this.processStaff = processStaff;
    }

    public Exchange getExchange() {
        return exchange;
    }

    public void setExchange(Exchange exchange) {
        this.exchange = exchange;
    }

    @Override
    public String toString() {
        return "Report{" + "reportId=" + reportId + ", reporter=" + reporter + ", post=" + post + ", reason=" + reason + ", reportDate=" + reportDate + ", reportStatus=" + reportStatus + ", reportContent=" + reportContent + ", processDate=" + processDate + ", processStaff=" + processStaff + ", exchange=" + exchange + '}';
    }

}
