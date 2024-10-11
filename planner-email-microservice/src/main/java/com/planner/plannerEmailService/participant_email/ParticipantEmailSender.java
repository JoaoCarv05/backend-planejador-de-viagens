package com.planner.plannerEmailService.participant_email;

import com.planner.plannerEmailService.DTO.ParticipantEmailDTO;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class ParticipantEmailSender {

    @Autowired
    private ParticipantEmailService emailService;
    @Autowired
    private ParticipantEmailBuilderContent emailBuilderContent;
    private final JavaMailSender mailSender;

    public ParticipantEmailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    private MimeMessage createMimeMessage(ParticipantEmailModel emailModel) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom(emailModel.getEmailFrom());
        helper.setSubject(emailModel.getSubject());
        helper.setTo(emailModel.getEmailTo());
        helper.setText(emailModel.getText(), true);
        return mimeMessage;
    }

    public void sendEmail(ParticipantEmailDTO emailDTO) {
        emailDTO.participants().forEach(participant -> {
            ParticipantEmailModel email = emailBuilderContent.emailBuilder(emailDTO, participant);
            MimeMessage message = null;
            try {
                emailService.saveEmail(email);
                message = createMimeMessage(email);
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
            mailSender.send(message);
        });
    }

}
