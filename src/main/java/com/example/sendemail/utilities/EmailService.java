package com.example.sendemail.utilities;

public interface EmailService {

    public void sendEmail(String to , String text , String subject) ;

    public void sendEmailWithAttachement(String
                                           to , String text , String subject , String pathAttachement ) ;
}
