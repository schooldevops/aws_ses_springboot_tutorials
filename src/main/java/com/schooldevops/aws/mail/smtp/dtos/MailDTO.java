package com.schooldevops.aws.mail.smtp.dtos;

public class MailDTO {

    private String to;
    private String subject;
    private String message;
    private boolean isHTML;
    private String attachFile;
    private String attachFilePath;

    public MailDTO() {
    }

    public MailDTO(String to, String subject, String message, boolean isHTML, String attachFile, String attachFilePath) {
        this.to = to;
        this.subject = subject;
        this.message = message;
        this.isHTML = isHTML;
        this.attachFile = attachFile;
        this.attachFilePath = attachFilePath;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isHTML() {
        return isHTML;
    }

    public void setHTML(boolean HTML) {
        isHTML = HTML;
    }

    public String getAttachFile() {
        return attachFile;
    }

    public void setAttachFile(String attachFile) {
        this.attachFile = attachFile;
    }

    public String getAttachFilePath() {
        return attachFilePath;
    }

    public void setAttachFilePath(String attachFilePath) {
        this.attachFilePath = attachFilePath;
    }
}
