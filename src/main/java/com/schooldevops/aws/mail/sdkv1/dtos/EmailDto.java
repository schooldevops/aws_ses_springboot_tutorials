package com.schooldevops.aws.mail.sdkv1.dtos;

import com.amazonaws.services.simpleemail.model.*;

import java.util.List;

public class EmailDto {

    private final String from;
    private final List<String> to;
    private final String subject;
    private final String content;
    private final boolean isHtml;

    public EmailDto(String from, List<String> to, String subject, String content, boolean isHtml) {
        this.from = from;
        this.to = to;
        this.subject = subject;
        this.content = content;
        this.isHtml = isHtml;
    }

    public SendEmailRequest toSendRequest() {
        final Destination destination = new Destination().withToAddresses(this.to);

        final Message message = new Message()
                .withSubject(new Content().withCharset("UTF-8").withData(this.subject))
                .withBody(makeBody(this.isHtml));

        return new SendEmailRequest()
                .withSource(this.from)
                .withDestination(destination)
                .withMessage(message);
    }

    private Body makeBody(boolean isHtml) {
        if (isHtml) {
            return new Body().withHtml(new Content().withCharset("UTF-8").withData(this.content));
        } else {
            return new Body().withText(new Content().withCharset("UTF-8").withData(this.content));
        }
    }
}