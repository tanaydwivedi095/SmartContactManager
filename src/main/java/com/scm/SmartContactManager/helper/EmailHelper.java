package com.scm.SmartContactManager.helper;

public class EmailHelper {
    public static String getLinkForEmailVerification(String emailToken, String email){
        String link = "http://localhost:8081/auth/verify-email?token="+emailToken+"&email="+email;
        return link;
    }
}
