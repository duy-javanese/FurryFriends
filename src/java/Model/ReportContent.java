/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author dell
 */
public class ReportContent {
    private int reportContentId;
    private String reportContent;
    private boolean deleteFlag;

    public ReportContent() {
    }

    public ReportContent(int reportContentId, String reportContent, boolean deleteFlag) {
        this.reportContentId = reportContentId;
        this.reportContent = reportContent;
        this.deleteFlag = deleteFlag;
    }

    public int getReportContentId() {
        return reportContentId;
    }

    public void setReportContentId(int reportContentId) {
        this.reportContentId = reportContentId;
    }

    public String getReportContent() {
        return reportContent;
    }

    public void setReportContent(String reportContent) {
        this.reportContent = reportContent;
    }

    public boolean isDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    
    
    
    
}
