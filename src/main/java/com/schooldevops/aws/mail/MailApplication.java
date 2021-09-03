package com.schooldevops.aws.mail;

import com.schooldevops.aws.mail.sdkv1.services.SesV1Service;
import com.schooldevops.aws.mail.smtp.dtos.MailDTO;
import com.schooldevops.aws.mail.smtp.services.SmtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class MailApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(MailApplication.class, args);
	}

//	@Autowired
//	SesV1Service sendEmailService;

//	@Autowired
//	SesV2Service sendEmailService;

	@Autowired
	SmtpService smtpService;

	@Override
	public void run(String... args) throws Exception {
//		sendEmailService.send("Hello kido..", "My name is...", List.of("baekido@gmail.com"), false);
		MailDTO mail = new MailDTO();
		mail.setTo("baekido@gmail.com");
		mail.setSubject("Hello kido");
		mail.setMessage("This is message");
		mail.setHTML(true);
		smtpService.sendMail(mail);
	}
}
