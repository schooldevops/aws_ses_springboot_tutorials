package com.schooldevops.aws.mail.smtp.services;


import com.schooldevops.aws.mail.smtp.dtos.MailDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class SmtpService {

    @Value("${aws.ses.from}")
    private String from;

    private final JavaMailSender javaMailSender;

    public SmtpService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendMail(MailDTO mail) {
        if (mail != null && mail.getAttachFile() != null) {
            sendMailWithAttachments(mail);
        } else if (mail.isHTML()) {
            sendMailWithHTML(mail);
        } else {
            sendMailJustText(mail);
        }
    }

    /**
     * 단순 텍스트 전송
     * @param mail 전달할 메일 객체
     */
    public void sendMailJustText(MailDTO mail) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(mail.getTo(), mail.getTo());
        msg.setSubject(mail.getSubject());
        msg.setText(mail.getMessage());
        msg.setFrom(from);

        javaMailSender.send(msg);
    }

    /**
     * HTML 형태로 메일 전송
     * @param mail 전달할 메일 객체
     */
    public void sendMailWithHTML(MailDTO mail){
        try {
            MimeMessage msg = javaMailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(msg, true);
            helper.setTo(mail.getTo());
            helper.setSubject(mail.getSubject());
            helper.setText(mail.getMessage(), mail.isHTML());
            helper.setFrom(from);

            javaMailSender.send(msg);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    /**
     * 첨부 파일이 존재하는 메일 전송
     * @param mail 전달할 메일 객체
     */
    public void sendMailWithAttachments(MailDTO mail){
        try {
            MimeMessage msg = javaMailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(msg, true);
            helper.setTo(mail.getTo());
            helper.setSubject(mail.getSubject());
            helper.setText(mail.getMessage(), mail.isHTML());
            helper.setFrom(from);
            helper.addAttachment(mail.getAttachFile(), new ClassPathResource(mail.getAttachFilePath()));

            javaMailSender.send(msg);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
