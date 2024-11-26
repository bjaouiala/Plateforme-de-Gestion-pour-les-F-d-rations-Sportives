package com.example.ala.email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.springframework.mail.javamail.MimeMessageHelper.MULTIPART_MODE_MIXED;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;
    @Async
    public void sendMail(String to,
                             String from,
                         String username,
                         String confirmUrl,
                         String subject) throws MessagingException {

        String emailTemplateName;

            emailTemplateName = EmailTemplateName.ATHLETE_ACTIVATION_ACCOUNT.getName();

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(message, MULTIPART_MODE_MIXED, UTF_8.name());
        Map<String,Object> map = new HashMap<>();
        map.put("username",username);
        map.put("templateUrl",confirmUrl);

        Context context = new Context();
        context.setVariables(map);
        messageHelper.setFrom(from);
        messageHelper.setTo(to);
        messageHelper.setSubject(subject);
        String template = templateEngine.process(emailTemplateName,context);
        messageHelper.setText(template,true);
        mailSender.send(message);
    }
    @Async
    public void senConfirmationAccount(String to,
                                       String from,
                                       String username,
                                       EmailTemplateName emailTemplateName,
                                       String subject,
                                       String confirmUrl,
                                       String password) throws MessagingException {
        String templateName = emailTemplateName.getName();
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, MULTIPART_MODE_MIXED, UTF_8.name());
        Map<String,Object> map = new HashMap<>();
        map.put("username",username);
        map.put("templateUrl",confirmUrl);
        map.put("password",password);
        map.put("email",to);
        Context context =new Context();
        context.setVariables(map);
        helper.setTo(to);
        helper.setFrom(from);
        helper.setSubject(subject);
       String template =  templateEngine.process(templateName,context);
       helper.setText(template,true);
       mailSender.send(message);

    }

    @Async
    public void senUserEmailVerification(String to,
                                         String from,
                                         String username,
                                         EmailTemplateName emailTemplateName,
                                         String subject,
                                         String confirmUrl,
                                         String confirmationCode) throws MessagingException {
        String templateName = emailTemplateName.getName();
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, MULTIPART_MODE_MIXED, UTF_8.name());
        Map<String,Object> map = new HashMap<>();
        map.put("username",username);
        map.put("templateUrl",confirmUrl);
        map.put("confirmationCode",confirmationCode);
        map.put("email",to);
        Context context =new Context();
        context.setVariables(map);
        helper.setTo(to);
        helper.setFrom(from);
        helper.setSubject(subject);
        String template =  templateEngine.process(templateName,context);
        helper.setText(template,true);
        mailSender.send(message);


    }


}
