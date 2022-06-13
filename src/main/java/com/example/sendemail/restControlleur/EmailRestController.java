package com.example.sendemail.restControlleur;

import com.example.sendemail.utilities.EmailServiceImpl;
import com.example.sendemail.utilities.Message;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping( path = "app/")
@RestController
public class EmailRestController {

    private EmailServiceImpl emailService ;

    public EmailRestController(EmailServiceImpl emailService) {
        this.emailService = emailService;
    }

    @RequestMapping( path = "send" , consumes = "application/json" ,method = RequestMethod.POST)
    public ResponseEntity<String> sendEmail(@RequestBody(required = true) Message message) {
         emailService.sendEmail(  message.getTo() , message.getText() , message.getSubject()  );
        return new ResponseEntity<String> ( "Email sent with success..." , HttpStatus.CREATED ) ;
    }

    @RequestMapping( path = "/send-with-attachement")
    public  ResponseEntity<String> sendEmailWithAttachement(@RequestBody Message message) {
        this.emailService.sendEmailWithAttachement(message.getTo() , message.getText(), message.getSubject(),  "C:\\Users\\HACKER\\Videos\\Android\\FR_6173501_apprenez-a-programmer-en-java_2.0_Downloadable+Summary.pdf"   );
        return  new ResponseEntity<String> ( "email sent with success.." , HttpStatus.CREATED ) ;
    }
}
