package com.alkemy.somosmas.services;

import com.alkemy.somosmas.exceptions.EmailErrorException;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import com.sendgrid.helpers.mail.objects.Personalization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.IOException;


@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private Environment env;

    /* Falta completar*/
    @Value("${alkemy.somosmas.email.sender}")
    private String emailSender;
    @Value("${alkemy.somosmas.email.enabled}")
    private boolean enabled;

    @Override
    public void sendWelcomeEmailTo(String to, String message, String subjectEmail) {

        if (!enabled){
            return;
        }

        /*Falta completar */
        String apiKey= env.getProperty("EMAIL_API_KEY");

        Email fromEmail= new Email(emailSender);
        Email toEmail= new Email(to);
        Content content = new Content(
                "text/plain",
                message);

        String subject =subjectEmail;

        Mail mail = new Mail(fromEmail,subject,toEmail,content);
        SendGrid sg= new SendGrid(apiKey);
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response= sg.api(request);

            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());

        }catch (IOException ex){
            System.out.println("Error trying to send the email:" + ex.getMessage());
        }
    }

    @Override
    public void sendWelcomeEmailTemplateTo(String to, String message, String title, String subjectEmail)
            throws EmailErrorException {

        if (!enabled){
            return;
        }

        /*Falta completar */
        String apiKey= env.getProperty("EMAIL_API_KEY");

        Email fromEmail= new Email(emailSender);
        Email toEmail= new Email(to);
        Content content = new Content(
                "text/html",
                message);

        String subject =subjectEmail;

        Mail mail = new Mail();
        mail.setFrom(fromEmail);

        mail.setTemplateId("d-d378baffd29947de9b24567af3a1d100");

        Personalization personalization = new Personalization();
        personalization.addDynamicTemplateData("subject", subjectEmail);
        personalization.addDynamicTemplateData("content", message);
        personalization.addDynamicTemplateData("title", title);
        personalization.addTo(toEmail);
        personalization.setSubject(subjectEmail);


        mail.addPersonalization(personalization);

        SendGrid sg= new SendGrid(apiKey);
        Request request = new Request();

        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response= sg.api(request);

            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());

        }catch (IOException ex){
            System.out.println("Error trying to send the email:" + ex.getMessage());
            throw new EmailErrorException("No se pudo enviar el mail");

        }
    }


}

