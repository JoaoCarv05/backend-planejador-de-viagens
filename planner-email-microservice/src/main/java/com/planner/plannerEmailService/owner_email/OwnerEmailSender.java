package com.planner.plannerEmailService.owner_email;

import com.planner.plannerEmailService.DTO.OwnerEmailDTO;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class OwnerEmailSender {

    @Autowired
    private OwnerEmailService ownerEmailService;
    @Autowired
    private OwnerEmailBuilderContent emailBuilderContent;
    private final JavaMailSender mailSender;

    public OwnerEmailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    private MimeMessage createMimeMessage(OwnerEmailModel emailModel) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom(emailModel.getEmailFrom());
        helper.setSubject(emailModel.getSubject());
        helper.setTo(emailModel.getEmailTo());
        helper.setText(emailModel.getText(), true);
        return mimeMessage;
    }

    public void sendEmail(OwnerEmailDTO emailDTO) {
        OwnerEmailModel ownerEmailModel = emailBuilderContent.emailBuilder(emailDTO);
        MimeMessage message = null;
        try {
            ownerEmailService.saveEmail(ownerEmailModel);
            message = createMimeMessage(ownerEmailModel);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        } finally {
            mailSender.send(message);
        }
    }
}
