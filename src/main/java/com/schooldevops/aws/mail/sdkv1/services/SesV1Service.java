package com.schooldevops.aws.mail.sdkv1.services;

import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.schooldevops.aws.mail.sdkv1.dtos.EmailDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SesV1Service {

    @Value("${aws.ses.from}")
    private String from;

    private final AmazonSimpleEmailService amazonSimpleEmailService;

    public SesV1Service(AmazonSimpleEmailService amazonSimpleEmailService) {
        this.amazonSimpleEmailService = amazonSimpleEmailService;
    }

    public void send(final String subject, final String content, final List<String> receiver, boolean isHTML) {
        final EmailDto mailDto = new EmailDto(from, receiver, subject, content, isHTML);
        amazonSimpleEmailService.sendEmail(mailDto.toSendRequest());
    }

}
