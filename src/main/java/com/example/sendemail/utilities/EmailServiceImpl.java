package com.example.sendemail.utilities;

import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Date;

@Component
public class EmailServiceImpl implements  EmailService {

    private JavaMailSender javaMailSender ;

    public EmailServiceImpl(JavaMailSender mailSender) {
        this.javaMailSender = mailSender ;
    }


    @Override
    public void sendEmail(String to, String text, String subject)  {

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage() ;

        simpleMailMessage.setFrom( "kervensorima0@gmail.com");
        simpleMailMessage.setTo( to );
        simpleMailMessage.setText( text );
        simpleMailMessage.setSentDate(new Date( ) );
        simpleMailMessage.setSubject( subject );

        javaMailSender.send( simpleMailMessage );
        System.out.println( "Email sent with success");
    }

    @Override
    public void sendEmailWithAttachement(String to, String text, String subject, String pathAttachement) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage() ;

        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper( mimeMessage , true ) ;

            mimeMessageHelper.setFrom( "kervensorima0@gmail.com");
            mimeMessageHelper.setSubject( subject );
            mimeMessageHelper.setText( text );
            mimeMessageHelper.setTo( to );


            System.out.println( System.getProperty("user.dir") + File.separator +
                    "src" + File.separator + "" + File.separator + "main" + File.separator +
                    "resources" + File.separator + "templates" + File.separator + "test.png" );

            FileSystemResource fileSystemResource = new FileSystemResource( new File(pathAttachement) ) ;

            mimeMessageHelper.addAttachment( fileSystemResource.getFilename(), fileSystemResource );

            javaMailSender.send( mimeMessage );

            System.out.println( "email with attachement sent with success...");

        } catch (MessagingException e) {
            e.printStackTrace();
        }


    }
}
