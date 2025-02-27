package com.andreluiscardoso.digitalwallet.domain.service;

import com.andreluiscardoso.digitalwallet.util.Mail;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;

import java.io.UnsupportedEncodingException;

@Service
@AllArgsConstructor
public class MailSendService {

    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;

    public void sendMail(String sender, String recipient, String subject, String message) throws MailException {
        final SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(sender);
        simpleMailMessage.setTo(recipient);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(message);
        this.javaMailSender.send(simpleMailMessage);
    }

    public void sendMail(Mail mail, boolean html) throws MessagingException, UnsupportedEncodingException {
        final String content = this.templateEngine.process(mail.template().getPath(), mail.context());
        final MimeMessage mimeMessage = this.javaMailSender.createMimeMessage();
        final MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, "UTF-8");
        mimeMessageHelper.setFrom(mail.sender(), String.format("Digital Wallet <%s>", mail.sender()));
        mimeMessageHelper.setTo(mail.recipient());
        mimeMessageHelper.setSubject(mail.subject());
        mimeMessageHelper.setText(content, html);
        this.javaMailSender.send(mimeMessage);
    }

}
