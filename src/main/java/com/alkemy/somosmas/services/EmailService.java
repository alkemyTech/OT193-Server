package com.alkemy.somosmas.services;

public interface EmailService {


    void sendWelcomeEmailTo (String to, String message, String subjectEmail);
}
