/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author dell
 */
public class Constant {
    
    public final static String GOOGLE_CLIENT_ID = "583964261490-qa8kt76ed9evd3l597jn9hmsnrm6s97q.apps.googleusercontent.com";

    public final static String GOOGLE_CLIENT_SECRET = "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";

    public final static String GOOGLE_REDIRECT_URI = "http://localhost:9999/loginEmail";

    public final static String GOOGLE_LINK_GET_TOKEN = "https://accounts.google.com/o/oauth2/token";

    public final static String GOOGLE_LINK_GET_USER_INFO = "https://www.googleapis.com/oauth2/v1/userinfo?access_token=";

    public final static String GOOGLE_GRANT_TYPE = "authorization_code";
    
    public static int CustomerRole = 3;
    
    public static int StatusPostPending = 1;
    public static int StatusPostAccept = 2;
    public static int StatusPostDenined = 3;
    
    public static int PostPublic = 1;
    public static int PostPrivate = 0;
    
    public static int PostHealth = 1;
    public static int PostInformation = 2;
    public static int PostTip = 3;
    public static int PostExchange = 4;
}
