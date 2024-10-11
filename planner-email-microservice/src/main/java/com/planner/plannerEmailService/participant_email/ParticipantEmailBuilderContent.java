package com.planner.plannerEmailService.participant_email;

import com.planner.plannerEmailService.DTO.ParticipantEmailDTO;
import com.planner.plannerEmailService.DTO.Participant;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Service
public class ParticipantEmailBuilderContent {

    @Value("${email.from.default}")
    private String defaultFromEmail;
    @Value("${server.ip.address}")
    private String serverAdress;

    public String buildEmailContent(ParticipantEmailDTO emailDTO) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d 'de' MMMM 'de' yyyy", new Locale("pt", "BR"));

        String confirmLink = serverAdress + "/trip/" + emailDTO.trip_id() + "?participantId=" + emailDTO.participants().get(0).id() + "&participantEmail=" + emailDTO.participants().get(0).email() + "&confirm=true";
        String tripStartsAt = ZonedDateTime.parse(emailDTO.trip_starts_at()).format(formatter);
        String tripEndsAt = ZonedDateTime.parse(emailDTO.trip_ends_at()).format(formatter);

        return "<html>" +
                "<body>" +

                "<h3>Você foi convidado(a) para uma viagem!</h3>" +

                "<p>Destino: " + emailDTO.trip_destination() + "</p>" +

                "<p>Datas: " + tripStartsAt + " a " + tripEndsAt + "</p>" +

                "<p>Para confirmar sua presença na viagem, clique no link abaixo:</p>" +

                "<a href=\"" + confirmLink + "\">Confirmar presença</a>" +

                "<p>Caso você não saiba do que se trata esse e-mail ou não poderá estar presente, apenas ignore este e-mail.</p>" +

                "</body>" +
                "</html>";
    }

    public ParticipantEmailModel emailBuilder(ParticipantEmailDTO emailDTO, Participant participant) {
        ParticipantEmailModel emailModel = new ParticipantEmailModel();

        emailModel.setEmailFrom(defaultFromEmail);
        emailModel.setDateEmail(ZonedDateTime.now());
        emailModel.setEmailTo(participant.email());
        emailModel.setSubject("Você tem uma nova viagem agendada para: " + emailDTO.trip_destination());
        emailModel.setText(buildEmailContent(emailDTO));
        return emailModel;
    }

}
