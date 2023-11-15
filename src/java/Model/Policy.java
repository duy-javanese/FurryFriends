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
public class Policy {
    private int policyId;
    private String policyTitle;
    private String policyDetail;

    public Policy() {
    }

    public Policy(int policyId, String policyTitle, String policyDetail) {
        this.policyId = policyId;
        this.policyTitle = policyTitle;
        this.policyDetail = policyDetail;
    }

    public int getPolicyId() {
        return policyId;
    }

    public void setPolicyId(int policyId) {
        this.policyId = policyId;
    }

    public String getPolicyTitle() {
        return policyTitle;
    }

    public void setPolicyTitle(String policyTitle) {
        this.policyTitle = policyTitle;
    }

    public String getPolicyDetail() {
        return policyDetail;
    }

    public void setPolicyDetail(String policyDetail) {
        this.policyDetail = policyDetail;
    }

    @Override
    public String toString() {
        return "Policy{" + "policyId=" + policyId + ", policyTitle=" + policyTitle + ", policyDetail=" + policyDetail + '}';
    }

    
}
