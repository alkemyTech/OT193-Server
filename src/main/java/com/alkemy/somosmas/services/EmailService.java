package com.alkemy.somosmas.services;

public interface EmailService {


    void sendWelcomeEmailTo (String to, String message, String subjectEmail);

    void  sendWelcomeEmailTemplateTo(String to, String message, String title, String subjectEmail);
}
