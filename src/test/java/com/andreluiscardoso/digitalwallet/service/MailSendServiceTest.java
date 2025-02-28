package com.andreluiscardoso.digitalwallet.service;

import com.andreluiscardoso.digitalwallet.util.Mail;
import com.andreluiscardoso.digitalwallet.domain.service.MailSendService;
import com.andreluiscardoso.digitalwallet.domain.enums.MailTemplate;
import jakarta.mail.Session;
import jakarta.mail.internet.MimeMessage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.javamail.JavaMailSender;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Locale;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MailSendServiceTest {

    @InjectMocks
    private MailSendService mailSendService;

    @Mock
    private JavaMailSender javaMailSender;

    @Mock
    private TemplateEngine templateEngine;

    @Test
    void testSendMail() throws Exception {
        Context context = new Context(Locale.ENGLISH);
        context.setVariable("name", "Java");

        MailTemplate mailTemplate = MailTemplate.TEST_HTML;

        Mail mail = new Mail(context,
                mailTemplate,
                "java.sender@email.com",
                "java.email@example.com",
                "Test!",
                "Welcome to our platform!"
        );

        String expectedHtml = "<h2>Hello, Java!</h2>";
        when(templateEngine.process(eq(mailTemplate.getPath()), any(Context.class))).thenReturn(expectedHtml);

        MimeMessage mimeMessage = new MimeMessage((Session) null);
        when(javaMailSender.createMimeMessage()).thenReturn(mimeMessage);

        mailSendService.sendMail(mail, true);

        verify(templateEngine, times(1)).process(eq(mailTemplate.getPath()), any(Context.class));

        verify(javaMailSender, times(1)).send(any(MimeMessage.class));
    }

}