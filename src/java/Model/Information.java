/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author DUY
 */
public class Information {

    private String logoPath;
    private String aboutUs;
    private String contact;
    private String notification;

    public Information() {
    }

    public Information(String logoPath, String aboutUs, String contact, String notification) {
        this.logoPath = logoPath;
        this.aboutUs = aboutUs;
        this.contact = contact;
        this.notification = notification;
    }

    public String getLogoPath() {
        return logoPath;
    }

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }

    public String getAboutUs() {
        return aboutUs;
    }

    public void setAboutUs(String aboutUs) {
        this.aboutUs = aboutUs;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getNotification() {
        return notification;
    }

    public void setNotification(String notification) {
        this.notification = notification;
    }

}
