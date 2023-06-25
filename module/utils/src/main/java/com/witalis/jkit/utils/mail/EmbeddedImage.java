package com.witalis.jkit.utils.mail;

import lombok.extern.slf4j.Slf4j;

import jakarta.activation.DataHandler;
import jakarta.activation.DataSource;
import jakarta.activation.FileDataSource;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import java.io.File;
import java.util.Properties;

@Slf4j
public class EmbeddedImage {
    public static void main(String[] args) {
        String to = "wellaxis@sysdate.com";
        String from = "ngs@ua.sysdate.com";
        final String username = "vitaliy";
        final String password = "vitalik";

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "false");
        properties.put("mail.smtp.host", "10.10.1.18");
        properties.put("mail.smtp.port", "25");

        Session session = Session.getInstance(
            properties,
            new jakarta.mail.Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            }
        );

        try {
            // Create a default MimeMessage object.
            Message message = new MimeMessage(session);
            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));
            // Set To: header field of the header.
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            // Set Subject: header field
            message.setSubject("Testing Subject");
            // This mail has 2 part, the BODY and the embedded image
            MimeMultipart multipart = new MimeMultipart("related");
            // first part (the html)
            BodyPart messageBodyPart = new MimeBodyPart();
            String htmlText = "<H1>Hello</H1><img src=\"cid:image\">";
            messageBodyPart.setContent(htmlText, "text/html");
            // add it
            multipart.addBodyPart(messageBodyPart);
            // second part (the image)
            messageBodyPart = new MimeBodyPart();
            File image = new File("D:\\dev\\ngs\\med\\csr\\reminder\\report\\17\\Emails\\1000000@la.net\\Aanmaning_PE_17_1000000_20151019.png");
            DataSource fds = new FileDataSource(image);
            messageBodyPart.setDataHandler(new DataHandler(fds));
            messageBodyPart.setHeader("Content-ID", "<image>");
            // add image to the multipart
            multipart.addBodyPart(messageBodyPart);
            // put everything together
            message.setContent(multipart);
            // Send message
            Transport.send(message);
            log.info("Sent message successfully....");
        } catch (MessagingException e) {
            throw new RuntimeException("Mail sending errors", e);
        }
    }
}
