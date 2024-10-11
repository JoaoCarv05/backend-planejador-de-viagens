package com.planner.plannerEmailService.owner_email;
import com.planner.plannerEmailService.DTO.OwnerEmailDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Service
public class OwnerEmailBuilderContent {

    @Value("${email.from.default}")
    private String defaultFromEmail;
    @Value("${server.ip.address}")
    private String serverAdress;

    public String buildEmailContent(OwnerEmailDTO emailDTO) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d 'de' MMMM 'de' yyyy", new Locale("pt", "BR"));

        String confirmLink = serverAdress + "/trip/" + emailDTO.trip_id() + "?confirmtrip=true";
        String tripStartsAt = ZonedDateTime.parse(emailDTO.trip_starts_at()).format(formatter);
        String tripEndsAt = ZonedDateTime.parse(emailDTO.trip_ends_at()).format(formatter);

        return "<html>" +
                "<body>" +

                "<h3> Olá "  + emailDTO.owner_name() + ", sua viagem para" + emailDTO.trip_destination() + "foi cadastrada com sucesso!</h3>" +

                "<p>Destino: " + emailDTO.trip_destination() + "</p>" +

                "<p>Datas: " + tripStartsAt + " a " + tripEndsAt + "</p>" +

                "<p>Para confirmar a viagem clique no link a baixo:</p>" +

                "<a href=\"" + confirmLink + "\">Confirmar viagem</a>" +

                "<p>Atenção, após confirmar a viagem todos os convidados receberão um email para participarem da viagem!</p>" +

                "<p>Caso você não saiba do que se trata esse e-mail ou não poderá estar presente, apenas ignore este e-mail.</p>" +

                "</body>" +
                "</html>";
    }

    public OwnerEmailModel emailBuilder(OwnerEmailDTO emailDTO) {
        OwnerEmailModel emailModel = new OwnerEmailModel();

        emailModel.setEmailFrom(defaultFromEmail);
        emailModel.setDateEmail(ZonedDateTime.now());
        emailModel.setSubject("Você acaba de criar uma nova viagem para: " + emailDTO.trip_destination());
        emailModel.setEmailTo(emailDTO.owner_email());
        emailModel.setText(buildEmailContent(emailDTO));
        return emailModel;
    }

}
