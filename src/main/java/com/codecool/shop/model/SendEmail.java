package com.codecool.shop.model;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;


public class SendEmail {

    private String from;
    private String password;

    public void send(User user, Basket basket){
        //Get properties object
        Properties props = new Properties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "587");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "587");

        //get Session
        this.from = "codecool.shop@gmail.com";
        this.password = "codecool2016";

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(from,password);
                    }
                });

        //compose message
        try {
            MimeMessage message = new MimeMessage(session);
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(user.getEmail()));
            message.setSubject(createSub(user));
            message.setContent(createMessage(user, basket), "text/html; charset=utf-8");
            //send message
            Transport.send(message);
            System.out.println("message sent successfully");
        } catch (MessagingException e) {throw new RuntimeException(e);}

    }

    private String createMessage(User user, Basket basket) {
        String message = "<h1>Hello " + user.getFirstName() + "</h1>\n";
        message += "<h3>" + basket.getItems() + "</h3>";
        return message;
    }

    private String createSub(User user) {
        return user.getFirstName() + ", thanks for shopping!";
    }


}