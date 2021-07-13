package com.germany.paradigmaindie.ParadigmaIndieServer.services;

import com.germany.paradigmaindie.ParadigmaIndieServer.utils.EmailSender;
import lombok.AllArgsConstructor;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;



import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@AllArgsConstructor
public class EmailService implements EmailSender {


    private final JavaMailSender mailSender;

    @Override
    @Async
    public void send(String to, String email) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper =
                    new MimeMessageHelper(mimeMessage, "utf-8");
            helper.setText(email, true);
            helper.setTo(to);
            helper.setSubject("Confirm your email");
            helper.setFrom("hello@amigoscode.com");
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {

            throw new IllegalStateException("failed to send email");
        }
    }
}
