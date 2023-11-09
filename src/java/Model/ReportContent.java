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
    private String reportcontent;
    private boolean deleteFlag;

    public ReportContent() {
    }

    public ReportContent(int reportContentId, String reportcontent, boolean deleteFlag) {
        this.reportContentId = reportContentId;
        this.reportcontent = reportcontent;
        this.deleteFlag = deleteFlag;
    }

    public int getReportContentId() {
        return reportContentId;
    }

    public void setReportContentId(int reportContentId) {
        this.reportContentId = reportContentId;
    }

    public String getReportcontent() {
        return reportcontent;
    }

    public void setReportcontent(String reportcontent) {
        this.reportcontent = reportcontent;
    }

    public boolean isDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }
    
    
    
}
